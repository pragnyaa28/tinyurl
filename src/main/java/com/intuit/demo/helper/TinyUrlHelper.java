package com.intuit.demo.helper;

import com.intuit.demo.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TinyUrlHelper {

    @Autowired
    UrlRepository urlRepository;

    public boolean findExistingShortUrl(String shortUrl) {

        return urlRepository.findByShortUrl(shortUrl) == null;
    }
}
