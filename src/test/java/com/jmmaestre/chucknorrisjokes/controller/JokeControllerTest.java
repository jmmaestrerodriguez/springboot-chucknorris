package com.jmmaestre.chucknorrisjokes.controller;

import com.jmmaestre.chucknorrisjokes.controller.impl.JokeController;
import com.jmmaestre.chucknorrisjokes.dto.JokeApiResponse;
import com.jmmaestre.chucknorrisjokes.feign.clients.ChuckNorrisClient;
import com.jmmaestre.chucknorrisjokes.mapper.JokeMapper;
import com.jmmaestre.chucknorrisjokes.model.Joke;
import com.jmmaestre.chucknorrisjokes.service.JokeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;


@DisplayName("Joke Controller Tests")
public class JokeControllerTest {

    @Mock
    private ChuckNorrisClient chuckNorrisClient;

    @Mock
    private JokeMapper jokeMapper;

    @Mock
    private JokeService jokeService;

    @InjectMocks
    private JokeController jokeController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Get Random Joke")
    void testGetRandomJoke() {
        // Given
        JokeApiResponse jokeApiResponse = new JokeApiResponse("123", "Chuck Norris can divide by zero.");
        Joke joke = new Joke("123", "Chuck Norris can divide by zero.");
        when(chuckNorrisClient.getRandomJoke()).thenReturn(jokeApiResponse);
        when(jokeMapper.toJoke(jokeApiResponse)).thenReturn(joke);

        // When
        ResponseEntity<Joke> result = jokeController.getRandomJoke();

        // Then
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(joke, result.getBody());
        verify(jokeService, times(1)).save(joke);
    }

    @Test
    @DisplayName("Get Joke By ID")
    void testGetJokeById() {
        // Given
        String jokeId = "123";
        Joke joke = new Joke("123", "Chuck Norris can divide by zero.");
        when(jokeService.getJoke(jokeId)).thenReturn(joke);

        // When
        ResponseEntity<Joke> result = jokeController.getJokeById(jokeId);

        // Then
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(joke, result.getBody());
    }
}
