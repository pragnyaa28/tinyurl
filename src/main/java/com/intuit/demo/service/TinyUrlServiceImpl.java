package com.intuit.demo.service;

import com.intuit.demo.businesslogic.Base62Strategy;
import com.intuit.demo.businesslogic.GenerationStrategy;
import com.intuit.demo.businesslogic.GenerationStrategyFactory;
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
    @Autowired
    GenerationStrategyFactory generationStrategyFactory;

    private static final String TINY_URL_PREFIX = "https://tiny.url/";
    @Override
    public UrlEntity createTinyUrl(CreateTinyUrlRequest createTinyUrlRequest) {

        UrlEntity existingMapping = urlRepo.findByLongUrl(createTinyUrlRequest.getLongUrl());

        if(existingMapping!= null){
            return existingMapping;
        }

        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setLongUrl(createTinyUrlRequest.getLongUrl());
        if(StringUtils.isNotBlank(createTinyUrlRequest.getCustomShortUrl()))
        {
            if(tinyUrlHelper.existingShortUrlFound(createTinyUrlRequest.getCustomShortUrl())) {
                //check if already present then error handling
            }
            urlEntity.setShortUrl(createTinyUrlRequest.getCustomShortUrl());
        }
        else {
            GenerationStrategy generationStrategy = generationStrategyFactory.getGenerationStrategy("BASE62");
            urlEntity.setShortUrl(TINY_URL_PREFIX + generationStrategy.generateShortUrl(createTinyUrlRequest.getLongUrl()));
        }

        return urlRepo.save(urlEntity);
    }

    @Override
    public UrlEntity getLongUrl(String shortUrl) {
        shortUrl = TINY_URL_PREFIX + shortUrl;
        UrlEntity urlEntity = urlRepo.findByShortUrl(shortUrl);
        if( StringUtils.isNotBlank(urlEntity.getLongUrl()))
            return  urlEntity;

            //exception
        return null;


    }
}
