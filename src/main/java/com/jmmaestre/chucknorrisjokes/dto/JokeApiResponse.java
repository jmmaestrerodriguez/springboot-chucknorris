package com.jmmaestre.chucknorrisjokes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JokeApiResponse {

    private String id;
    private String value;
}

