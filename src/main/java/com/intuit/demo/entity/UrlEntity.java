package com.intuit.demo.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.UUID;

@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class UrlEntity {

    @PrimaryKey
    private long id;
    private String shortUrl;
    private String longUrl;
}
