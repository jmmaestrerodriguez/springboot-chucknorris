package com.jmmaestre.chucknorrisjokes.exception;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@RestControllerAdvice
@EnableWebMvc
public class GlobalExceptionHandler
        extends ResponseEntityExceptionHandler {

    private static final Pattern ENUM_VALUES = Pattern.compile("the values accepted for Enum class:\\s*\\[\\s*((\\S+\\s*,\\s*)*\\S+)\\s*\\]");

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> fieldErrors = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        List<String> globalErrors = exception.getBindingResult().getGlobalErrors().stream()
                .map(error -> error.getObjectName() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        List<String> errors = new ArrayList<>();
        errors.addAll(fieldErrors);
        errors.addAll(globalErrors);

        ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST,
                exception.getLocalizedMessage(), errors);

        return handleExceptionInternal(exception, response, headers, response.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatus status, WebRequest request){
        if (exception.getCause() != null && exception.getCause() instanceof InvalidFormatException) {
            Matcher match = ENUM_VALUES.matcher(exception.getCause().getMessage());

            if (match.find()) {
                ExceptionResponse response = new ExceptionResponse(
                        HttpStatus.BAD_REQUEST,
                        exception.getLocalizedMessage(),
                        "value should be: " + match.group(1));
                return handleExceptionInternal(exception, response, new HttpHeaders(), response.getStatus(), request);
            }
        }
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.BAD_REQUEST,
                exception.getLocalizedMessage(),
                "Bad Request");

        return handleExceptionInternal(exception, response, new HttpHeaders(), response.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        String supportedMediaTypes = exception.getSupportedMediaTypes().stream()
                .map(MediaType::toString)
                .collect(Collectors.joining(", "));

        String message = String.format("%s media type is not supported. Supported media types are %s",
                exception.getContentType(),
                supportedMediaTypes);

        ExceptionResponse response = new ExceptionResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                exception.getLocalizedMessage(),
                message);

        return handleExceptionInternal(exception, response, new HttpHeaders(),
                response.getStatus(), request);
    }


    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        String supportedMethods = exception.getSupportedHttpMethods().stream()
                .map(HttpMethod::toString)
                .collect(Collectors.joining(", "));

        String message = String.format("%s method is not supported for this request. Supported methods are %s",
                exception.getMethod(),
                supportedMethods);

        ExceptionResponse response = new ExceptionResponse(HttpStatus.METHOD_NOT_ALLOWED,
                exception.getLocalizedMessage(),
                message);

        return handleExceptionInternal(exception, response, new HttpHeaders(),
                response.getStatus(), request);
    }

    @Override
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String message = "Path '" + request.getDescription(false).substring(4) + "' does not exists.";

        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.NOT_FOUND,
                exception.getLocalizedMessage(),
                message);

        return handleExceptionInternal(exception, response, new HttpHeaders(), response.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        String parameterName = exception.getParameterName();
        String message = String.format("The '%s' parameter is missing", parameterName);
        ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST,
                exception.getLocalizedMessage(),
                message);

        return handleExceptionInternal(exception, response, new HttpHeaders(),
                response.getStatus(), request);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException exception,
            WebRequest request) {

        List<String> errors = exception.getConstraintViolations().stream()
                .map(violation -> String.format("%s %s: %s",
                        violation.getRootBeanClass().getName(),
                        violation.getPropertyPath(),
                        violation.getMessage()))
                .collect(Collectors.toList());

        ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST,
                exception.getLocalizedMessage(),
                errors);

        return handleExceptionInternal(exception, response, new HttpHeaders(),
                response.getStatus(), request);
    }


    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException exception,
            WebRequest request) {

        String message = String.format("%s should be of type %s",
                exception.getName(),
                exception.getRequiredType().getName());

        ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST,
                exception.getLocalizedMessage(),
                message);

        return handleExceptionInternal(exception, response, new HttpHeaders(),
                response.getStatus(), request);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception exception, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getLocalizedMessage(),
                "An unknown error has occurred");
        return handleExceptionInternal(exception, response, new HttpHeaders(), response.getStatus(), request);
    }
}
