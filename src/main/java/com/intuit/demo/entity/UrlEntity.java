package com.intuit.demo.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

import static com.intuit.demo.constants.TinyUrlConstants.EXPIRY_IN_SECONDS;

@Data
@Document(collection = "intuit")
@AllArgsConstructor
@NoArgsConstructor
public class UrlEntity {

    @Id
    private String shortUrl;

    private String longUrl;

    @Indexed(name = "deleteAt", expireAfterSeconds = EXPIRY_IN_SECONDS)
    private LocalDateTime createdTimestamp;
}
