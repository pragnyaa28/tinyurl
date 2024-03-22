package com.intuit.demo.service;

import com.intuit.demo.entity.UrlEntity;
import com.intuit.demo.model.CreateTinyUrlRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TinyUrlService {

    UrlEntity createTinyUrl(CreateTinyUrlRequest request);
//    UrlEntity getAllTinyUrlsByUserId(String userId);
    List<UrlEntity> findAll();

}
