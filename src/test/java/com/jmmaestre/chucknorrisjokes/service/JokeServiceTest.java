package com.jmmaestre.chucknorrisjokes.service;

import com.jmmaestre.chucknorrisjokes.exception.NotFoundException;
import com.jmmaestre.chucknorrisjokes.model.Joke;
import com.jmmaestre.chucknorrisjokes.repository.JokeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import java.util.Optional;

@DisplayName("Joke Service Tests")
public class JokeServiceTest {

    @Mock
    private JokeRepository jokeRepository;

    @InjectMocks
    private JokeService jokeService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Save Joke")
    public void testSaveJoke() {
        // Given
        Joke joke = new Joke("123", "Chuck Norris can divide by zero.");

        // When
        when(jokeRepository.save(joke)).thenReturn(joke);
        Joke savedJoke = jokeService.save(joke);

        // Then
        Assertions.assertEquals(joke, savedJoke);
        verify(jokeRepository, times(1)).save(joke);
    }

    @Test
    @DisplayName("Get Joke")
    public void testGetJoke() {
        // Given
        Joke joke = new Joke("123", "Chuck Norris can divide by zero.");

        // When
        when(jokeRepository.findById("123")).thenReturn(Optional.of(joke));
        Joke retrievedJoke = jokeService.getJoke("123");

        // Then
        Assertions.assertEquals(joke, retrievedJoke);
        verify(jokeRepository, times(1)).findById("123");
    }

    @Test
    @DisplayName("Get Joke - Joke not found")
    public void testGetJokeNotFound() {
        // Given
        String jokeId = "123";

        // When
        when(jokeRepository.findById(jokeId)).thenReturn(Optional.empty());

        // Then
        Assertions.assertThrows(NotFoundException.class, () -> jokeService.getJoke(jokeId));
        verify(jokeRepository, times(1)).findById(jokeId);
    }
}

