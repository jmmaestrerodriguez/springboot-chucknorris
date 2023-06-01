package com.jmmaestre.chucknorrisjokes.repository;

import com.jmmaestre.chucknorrisjokes.model.Joke;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JokeRepository extends CrudRepository<Joke, String> {}