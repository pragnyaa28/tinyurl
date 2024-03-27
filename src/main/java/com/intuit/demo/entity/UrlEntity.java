package com.intuit.demo.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Document(collection = "intuit")
@AllArgsConstructor
@NoArgsConstructor
public class UrlEntity {

    @Id
    private String shortUrl;

    private String longUrl;

    @Indexed(name = "deleteAt", expireAfterSeconds = 10)
    private LocalDateTime createdTimestamp;
}
