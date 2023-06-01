package com.jmmaestre.chucknorrisjokes.controller.impl;

import com.jmmaestre.chucknorrisjokes.controller.IJokeController;
import com.jmmaestre.chucknorrisjokes.dto.JokeApiResponse;
import com.jmmaestre.chucknorrisjokes.feign.clients.ChuckNorrisClient;
import com.jmmaestre.chucknorrisjokes.mapper.JokeMapper;
import com.jmmaestre.chucknorrisjokes.model.Joke;
import com.jmmaestre.chucknorrisjokes.service.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JokeController implements IJokeController {

    @Autowired
    private ChuckNorrisClient chuckNorrisClient;

    @Autowired
    private JokeMapper jokeMapper;

    @Autowired
    private JokeService jokeService;

    @Override
    public ResponseEntity<Joke> getRandomJoke() {
        JokeApiResponse jokeApiResponse = this.chuckNorrisClient.getRandomJoke();
        Joke joke = this.jokeMapper.toJoke(jokeApiResponse);
        jokeService.save(joke);
        return ResponseEntity.ok(joke);
    }

    @Override
    public ResponseEntity<Joke> getJokeById(String id) {
        Joke joke = this.jokeService.getJoke(id);
        return ResponseEntity.ok(joke);
    }
}
