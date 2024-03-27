package com.intuit.demo.service;

import com.intuit.demo.entity.UrlEntity;
import com.intuit.demo.exception.CustomShortUrlTakenException;
import com.intuit.demo.exception.InvalidTinyUrlException;
import com.intuit.demo.model.TinyUrlRequest;

import java.net.MalformedURLException;

public interface TinyUrlService {

    UrlEntity createTinyUrl(TinyUrlRequest request) throws CustomShortUrlTakenException, MalformedURLException;

    String getLongUrl(String shortUrl) throws InvalidTinyUrlException;

}
