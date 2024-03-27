package com.intuit.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateTinyUrlRequest {

    @NotBlank(message = "Long URL should not be null")
    @Pattern(regexp = "[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)\n" , message = "Not a Valid URL")
    @JsonProperty
    private String longUrl;


    @Size(min = 2, message = "Custom URL too short")
    @JsonProperty
    private String customShortUrl;
}
