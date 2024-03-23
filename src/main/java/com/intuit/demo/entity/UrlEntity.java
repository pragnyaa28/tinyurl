package com.intuit.demo.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "intuit")
@AllArgsConstructor
@NoArgsConstructor
public class UrlEntity {


    @Id
    private String shortUrl;
    private String longUrl;
}
