package com.intuit.demo.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "UrlEntity")
public class UrlEntity {

    @Id
    @Indexed
    private String id;
    private String shortUrl;
    private String longUrl;
}
