package com.intuit.demo.businesslogic;

import org.springframework.stereotype.Component;

public interface GenerationStrategy {
    String generateShortUrl(String longUrl);
}
