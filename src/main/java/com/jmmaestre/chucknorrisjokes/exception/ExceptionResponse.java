package com.jmmaestre.chucknorrisjokes.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.List;


@Getter
public class ExceptionResponse {

    private HttpStatus status;
    private String description;
    private List<String> errors;

    public ExceptionResponse(HttpStatus status, String description, String error) {
        super();
        this.status = status;
        this.description = description;
        this.errors = Arrays.asList(error);
    }

    public ExceptionResponse(HttpStatus status, String description, List<String> errors) {
        super();
        this.status = status;
        this.description = description;
        this.errors = errors;
    }
}