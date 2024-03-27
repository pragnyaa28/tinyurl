package com.intuit.demo.service;

import com.intuit.demo.businesslogic.GenerationStrategy;
import com.intuit.demo.businesslogic.GenerationStrategyFactory;
import com.intuit.demo.entity.UrlEntity;
import com.intuit.demo.exceptionhandler.CustomShortUrlTakenException;
import com.intuit.demo.exceptionhandler.InvalidTinyUrlException;
import com.intuit.demo.helper.TinyUrlHelper;
import com.intuit.demo.model.CreateTinyUrlRequest;
import com.intuit.demo.repository.UrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

@Service("TinyUrlServiceImpl")
@Slf4j
public class TinyUrlServiceImpl implements TinyUrlService{

    @Autowired
    TinyUrlHelper tinyUrlHelper;
    @Autowired
    private UrlRepository urlRepo;
    @Autowired
    GenerationStrategyFactory generationStrategyFactory;

    private static final String TINY_URL_PREFIX = "http://localhost:8080/v1/tiny/";
    @Override
    public UrlEntity createTinyUrl(CreateTinyUrlRequest createTinyUrlRequest)
            throws CustomShortUrlTakenException, MalformedURLException {

        validateLongUrl(createTinyUrlRequest.getLongUrl());
        log.info("Validation Successful for Long URL Format");

        UrlEntity existingMapping = urlRepo.findByLongUrl(createTinyUrlRequest.getLongUrl());

        if(existingMapping!= null){
            log.debug("Existing Tiny URL found for the Long URL  {}",
                    existingMapping.getShortUrl());
            return existingMapping;
        }

        UrlEntity urlEntity = new UrlEntity();

        urlEntity.setLongUrl(createTinyUrlRequest.getLongUrl());

        if(StringUtils.isNotBlank(createTinyUrlRequest.getCustomShortUrl())) {

            if(tinyUrlHelper.existingShortUrlFound(createTinyUrlRequest.getCustomShortUrl())) {
                log.error("Custom Short URL Already Taken {}",
                        createTinyUrlRequest.getCustomShortUrl());
                throw new CustomShortUrlTakenException(createTinyUrlRequest.getCustomShortUrl());
            }
            urlEntity.setShortUrl(TINY_URL_PREFIX + createTinyUrlRequest.getCustomShortUrl());
            log.debug("New Mapping formed {} to {}", createTinyUrlRequest.getLongUrl(),
                    createTinyUrlRequest.getCustomShortUrl());
        }
        else {

            GenerationStrategy generationStrategy = generationStrategyFactory.getGenerationStrategy("BASE62");
            urlEntity.setShortUrl(TINY_URL_PREFIX + generationStrategy.generateShortUrl(createTinyUrlRequest.getLongUrl()));
        }

        urlEntity.setCreatedTimestamp(LocalDateTime.now());

        return urlRepo.save(urlEntity);
    }

    private void validateLongUrl(String longUrl) throws MalformedURLException {
        URL url = new URL(longUrl);
    }

    @Override
    public UrlEntity getLongUrl(String shortUrl) throws InvalidTinyUrlException {
        /*
          Minimum size of custom short URLs is 5 & generated short URLs is 7,
          no need to check DB
         */
        if(shortUrl.length()<5)
            throw new InvalidTinyUrlException(shortUrl);

        log.info("Finding Long URL for {}", shortUrl);
        shortUrl = TINY_URL_PREFIX + shortUrl;
        UrlEntity urlEntity = urlRepo.findByShortUrl(shortUrl);

        if( urlEntity != null && StringUtils.isNotBlank(urlEntity.getLongUrl()))
            return  urlEntity;
        else
            throw new InvalidTinyUrlException(shortUrl);

    }
}
