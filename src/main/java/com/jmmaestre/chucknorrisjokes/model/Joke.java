package com.jmmaestre.chucknorrisjokes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Joke {

    private String id;
    private String value;
}
