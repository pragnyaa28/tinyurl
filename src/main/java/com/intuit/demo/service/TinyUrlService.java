package com.intuit.demo.service;

import com.intuit.demo.entity.UrlEntity;
import com.intuit.demo.exceptionhandler.CustomShortUrlTakenException;
import com.intuit.demo.exceptionhandler.InvalidTinyUrlException;
import com.intuit.demo.model.CreateTinyUrlRequest;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.util.List;

@Component
public interface TinyUrlService {

    UrlEntity createTinyUrl(CreateTinyUrlRequest request) throws CustomShortUrlTakenException, MalformedURLException;
    UrlEntity getLongUrl(String shortUrl) throws InvalidTinyUrlException;

}
