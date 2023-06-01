package com.jmmaestre.chucknorrisjokes.feign;

import com.jmmaestre.chucknorrisjokes.dto.JokeApiResponse;
import com.jmmaestre.chucknorrisjokes.feign.clients.ChuckNorrisClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("Chuck Norris Feign Client Test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ChuckNorrisClientTest {

    @Autowired
    private ChuckNorrisClient chuckNorrisClient;

    @Test
    public void testGetRandomJoke() {
        // When
        JokeApiResponse jokeApiResponse = chuckNorrisClient.getRandomJoke();

        // Then
        Assertions.assertNotNull(jokeApiResponse);
        Assertions.assertNotNull(jokeApiResponse.getId());
        Assertions.assertNotNull(jokeApiResponse.getValue());
    }
}
