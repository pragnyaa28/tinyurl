package com.intuit.demo.service;

import com.intuit.demo.entity.UrlEntity;
import com.intuit.demo.helper.TinyUrlHelper;
import com.intuit.demo.model.CreateTinyUrlRequest;
import com.intuit.demo.repository.UrlRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TinyUrlServiceImpl implements TinyUrlService{

    @Autowired
    TinyUrlHelper tinyUrlHelper;
    @Autowired
    private UrlRepository urlRepo;
    @Override
    public UrlEntity createTinyUrl(CreateTinyUrlRequest createTinyUrlRequest) {
        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setLongUrl(createTinyUrlRequest.getLongUrl());
        if(StringUtils.isNotBlank(createTinyUrlRequest.getCustomShortUrl()))
        {
            if(tinyUrlHelper.findExistingPool(createTinyUrlRequest.getCustomShortUrl())) {
                //
            }
            urlEntity.setShortUrl(createTinyUrlRequest.getCustomShortUrl());
        }
        else {
            urlEntity.setShortUrl(tinyUrlHelper.generateShortUrl(createTinyUrlRequest.getLongUrl()));
        }

        return urlRepo.save(urlEntity);
    }
    @Override
    public String getAllTinyUrlsByUserId(String userId) {
        return null;
    }

    @Override
    public List<UrlEntity> findAll() {
        return urlRepo.findAll();
    }
}
