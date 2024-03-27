package com.intuit.demo.businesslogic;

import com.intuit.demo.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.intuit.demo.constants.TinyUrlConstants.TINY_URL_PREFIX;

@Component
public class TinyUrlChecker {

    @Autowired
    UrlRepository urlRepository;

    public boolean existingShortUrlFound(String shortUrl) {
        return urlRepository.findByShortUrl(TINY_URL_PREFIX + shortUrl) != null;
    }
}
