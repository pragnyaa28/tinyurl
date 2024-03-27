package com.intuit.demo.service;

import com.intuit.demo.businesslogic.GenerationStrategy;
import com.intuit.demo.businesslogic.GenerationStrategyFactory;
import com.intuit.demo.entity.UrlEntity;
import com.intuit.demo.exception.CustomShortUrlTakenException;
import com.intuit.demo.exception.InvalidTinyUrlException;
import com.intuit.demo.businesslogic.TinyUrlChecker;
import com.intuit.demo.model.TinyUrlRequest;
import com.intuit.demo.repository.UrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

import static com.intuit.demo.constants.TinyUrlConstants.TINY_URL_PREFIX;

@Service("TinyUrlServiceImpl")
@Slf4j
public class TinyUrlServiceImpl implements TinyUrlService{

    @Autowired
    TinyUrlChecker tinyUrlChecker;
    @Autowired
    private UrlRepository urlRepo;
    @Autowired
    GenerationStrategyFactory generationStrategyFactory;

    @Override
    public UrlEntity createTinyUrl(TinyUrlRequest tinyUrlRequest)
            throws CustomShortUrlTakenException, MalformedURLException {

        validateLongUrl(tinyUrlRequest.getLongUrl());
        log.info("Validation Successful for Long URL Format");

        UrlEntity existingMapping = urlRepo.findByLongUrl(tinyUrlRequest.getLongUrl());

        if(existingMapping!= null){
            log.debug("Existing Tiny URL found for the Long URL  {}",
                    existingMapping.getShortUrl());
            return existingMapping;
        }

        UrlEntity urlEntity = new UrlEntity();

        urlEntity.setLongUrl(tinyUrlRequest.getLongUrl());

        if(StringUtils.isNotBlank(tinyUrlRequest.getCustomShortUrl())) {

            if(tinyUrlChecker.existingShortUrlFound(tinyUrlRequest.getCustomShortUrl())) {
                log.error("Custom Short URL Already Taken {}",
                        tinyUrlRequest.getCustomShortUrl());
                throw new CustomShortUrlTakenException(tinyUrlRequest.getCustomShortUrl());
            }
            urlEntity.setShortUrl(TINY_URL_PREFIX + tinyUrlRequest.getCustomShortUrl());
            log.debug("New Mapping formed {} to {}", tinyUrlRequest.getLongUrl(),
                    tinyUrlRequest.getCustomShortUrl());
        }
        else {

            GenerationStrategy generationStrategy = generationStrategyFactory.getGenerationStrategy("BASE62");
            urlEntity.setShortUrl(TINY_URL_PREFIX + generationStrategy.generateShortUrl(tinyUrlRequest.getLongUrl()));
        }

        urlEntity.setCreatedTimestamp(LocalDateTime.now());

        return urlRepo.save(urlEntity);
    }

    private void validateLongUrl(String longUrl) throws MalformedURLException {
        URL url = new URL(longUrl);
    }

    @Override
    public String getLongUrl(String shortUrl) throws InvalidTinyUrlException {
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
            return  urlEntity.getLongUrl();
        else
            throw new InvalidTinyUrlException(shortUrl);

    }
}
