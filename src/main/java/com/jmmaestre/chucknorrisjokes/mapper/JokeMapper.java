package com.jmmaestre.chucknorrisjokes.mapper;

import com.jmmaestre.chucknorrisjokes.dto.JokeApiResponse;
import com.jmmaestre.chucknorrisjokes.model.Joke;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JokeMapper {

    @Mapping(target = "text", source = "jokeApiResponse.value")
    Joke toJoke(JokeApiResponse jokeApiResponse);
}
