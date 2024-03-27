package com.intuit.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateTinyUrlRequest {

    @JsonProperty
    @NotBlank(message = "Long URL should not be empty.")
    private String longUrl;


    @JsonProperty
    @Size(min = 5, max = 7, message = "Custom Short URL should be 5 to 7 characters.")
    private String customShortUrl;
}
