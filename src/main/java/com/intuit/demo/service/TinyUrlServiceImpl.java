package com.intuit.demo.service;

import com.intuit.demo.businesslogic.Base62Strategy;
import com.intuit.demo.businesslogic.GenerationStrategy;
import com.intuit.demo.entity.UrlEntity;
import com.intuit.demo.helper.TinyUrlHelper;
import com.intuit.demo.model.CreateTinyUrlRequest;
import com.intuit.demo.repository.UrlRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("TinyUrlServiceImpl")
public class TinyUrlServiceImpl implements TinyUrlService{

    @Autowired
    TinyUrlHelper tinyUrlHelper;
    @Autowired
    private UrlRepository urlRepo;
    @Override
    public UrlEntity createTinyUrl(CreateTinyUrlRequest createTinyUrlRequest) {

        if(urlRepo.findByLongUrl(createTinyUrlRequest.getLongUrl())!= null){
            return urlRepo.findByLongUrl(createTinyUrlRequest.getLongUrl());
        }

        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setLongUrl(createTinyUrlRequest.getLongUrl());
        if(StringUtils.isNotBlank(createTinyUrlRequest.getCustomShortUrl()))
        {
            if(tinyUrlHelper.findExistingShortUrl(createTinyUrlRequest.getCustomShortUrl())) {
                //check if already present then error handling
            }
            urlEntity.setShortUrl(createTinyUrlRequest.getCustomShortUrl());
        }
        else {
            GenerationStrategy generationStrategy = new Base62Strategy();
            urlEntity.setShortUrl(generationStrategy.generateShortUrl(createTinyUrlRequest.getLongUrl()));
        }

        return urlRepo.save(urlEntity);
    }

    @Override
    public String getLongUrl(String shortUrl) {
        return urlRepo.findByShortUrl(shortUrl).getLongUrl();
    }
}
