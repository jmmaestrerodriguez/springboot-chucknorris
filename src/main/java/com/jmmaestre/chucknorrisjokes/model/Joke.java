package com.jmmaestre.chucknorrisjokes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("Joke")
public class Joke {

    private String id;
    private String text;
}
