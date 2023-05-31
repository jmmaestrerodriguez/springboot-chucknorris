package com.jmmaestre.chucknorrisjokes.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JokeApiResponse {

    private String id;
    private String value;
}

