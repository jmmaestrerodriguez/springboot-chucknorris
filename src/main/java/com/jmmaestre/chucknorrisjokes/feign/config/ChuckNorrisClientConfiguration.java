package com.jmmaestre.chucknorrisjokes.feign.config;

import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;


public class ChuckNorrisClientConfiguration {

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }
}
