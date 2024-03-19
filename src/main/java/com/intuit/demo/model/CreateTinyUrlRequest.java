package com.intuit.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateTinyUrlRequest {

    @JsonProperty
    private String longUrl;
    @JsonProperty
    private String userId;
    @JsonProperty
    private String customShortUrl;
}
