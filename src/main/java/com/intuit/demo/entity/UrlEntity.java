package com.intuit.demo.entity;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

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
