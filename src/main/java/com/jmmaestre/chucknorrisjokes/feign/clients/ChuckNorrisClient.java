package com.jmmaestre.chucknorrisjokes.feign.clients;

import com.jmmaestre.chucknorrisjokes.dto.JokeApiResponse;
import com.jmmaestre.chucknorrisjokes.feign.config.ChuckNorrisClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "chucknorris", url = "https://api.chucknorris.io/",
        configuration = ChuckNorrisClientConfiguration.class)

public interface ChuckNorrisClient {

    @GetMapping("/jokes/random")
    JokeApiResponse getRandomJoke();
}
