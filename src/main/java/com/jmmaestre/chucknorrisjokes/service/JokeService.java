package com.jmmaestre.chucknorrisjokes.service;

import com.jmmaestre.chucknorrisjokes.exception.NotFoundException;
import com.jmmaestre.chucknorrisjokes.model.Joke;
import com.jmmaestre.chucknorrisjokes.repository.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JokeService {

    @Autowired
    private JokeRepository jokeRepository;

    public Joke save(Joke joke) {
        return jokeRepository.save(joke);
    }

    public Joke getJoke(String id) {
        return jokeRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Joke not found on cache"));
    }
}