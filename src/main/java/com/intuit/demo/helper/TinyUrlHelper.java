package com.intuit.demo.helper;

import com.intuit.demo.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TinyUrlHelper {

    @Autowired
    UrlRepository urlRepository;

    private static final String TINY_URL_PREFIX = "https://tiny.url/";

    public boolean existingShortUrlFound(String shortUrl) {
        return urlRepository.findByShortUrl(TINY_URL_PREFIX + shortUrl) != null;
    }
}
