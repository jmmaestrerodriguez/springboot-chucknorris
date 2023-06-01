package com.jmmaestre.chucknorrisjokes.controller;

import com.jmmaestre.chucknorrisjokes.model.Joke;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


public interface IJokeController {

    @ApiOperation(value = "Retrieves a random joke from 3rd-party API.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request made successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping("/joke-request")
    ResponseEntity<Joke> getRandomJoke();

    @ApiOperation(value = "Search for a joke using it's id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request made successfully"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping("/joke/{id}")
    ResponseEntity<Joke> getJokeById(@Valid @PathVariable("id") String id);
}
