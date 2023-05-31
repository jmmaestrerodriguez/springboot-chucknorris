package com.jmmaestre.chucknorrisjokes.controller;

import com.jmmaestre.chucknorrisjokes.feign.clients.ChuckNorrisClient;
import com.jmmaestre.chucknorrisjokes.model.Joke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JokeController {

    @Autowired
    private ChuckNorrisClient chuckNorrisClient;

    @PostMapping("/joke-request")
    public ResponseEntity<Joke> requestJoke() {
        Joke joke = chuckNorrisClient.getRandomJoke();
        return ResponseEntity.ok(joke);
    }
}
