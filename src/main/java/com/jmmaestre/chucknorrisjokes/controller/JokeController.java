package com.jmmaestre.chucknorrisjokes.controller;

import com.jmmaestre.chucknorrisjokes.dto.JokeApiResponse;
import com.jmmaestre.chucknorrisjokes.feign.clients.ChuckNorrisClient;
import com.jmmaestre.chucknorrisjokes.mapper.JokeMapper;
import com.jmmaestre.chucknorrisjokes.model.Joke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JokeController {

    @Autowired
    private ChuckNorrisClient chuckNorrisClient;

    @Autowired
    private JokeMapper jokeMapper;

    @PostMapping("/joke-request")
    public ResponseEntity<Joke> requestJoke() {
        JokeApiResponse jokeApiResponse = chuckNorrisClient.getRandomJoke();
        Joke joke = this.jokeMapper.toJoke(jokeApiResponse);
        return ResponseEntity.ok(joke);
    }
}
