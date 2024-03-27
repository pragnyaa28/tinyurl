package com.intuit.demo.helper;

import com.intuit.demo.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TinyUrlHelper {

    @Autowired
    UrlRepository urlRepository;

    public boolean existingShortUrlFound(String shortUrl) {
        System.out.println("Hi");

        return urlRepository.findByShortUrl(shortUrl) != null;
    }
}
