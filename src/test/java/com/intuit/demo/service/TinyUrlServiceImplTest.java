package com.intuit.demo.service;

import com.intuit.demo.businesslogic.Base62Strategy;
import com.intuit.demo.businesslogic.GenerationStrategyFactory;
import com.intuit.demo.businesslogic.TinyUrlChecker;
import com.intuit.demo.entity.UrlEntity;
import com.intuit.demo.exception.CustomShortUrlTakenException;
import com.intuit.demo.exception.InvalidTinyUrlException;
import com.intuit.demo.model.TinyUrlRequest;
import com.intuit.demo.repository.UrlRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.MalformedURLException;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class TinyUrlServiceImplTest {

    @InjectMocks
    TinyUrlServiceImpl tinyUrlService;
    @Mock
    TinyUrlChecker tinyUrlChecker;
    @Mock
    private UrlRepository urlRepo;
    @Mock
    GenerationStrategyFactory generationStrategyFactory;

    @Mock
    Base62Strategy base62Strategy;



    @Test(expected = MalformedURLException.class)
    @DisplayName("Malformed URL")
    public void testCreateMalformedUrl() throws MalformedURLException, CustomShortUrlTakenException {
        UrlEntity urlEntity = tinyUrlService.createTinyUrl(mockTinyUrlRequest1());
    }

    @Test(expected = CustomShortUrlTakenException.class)
    @DisplayName("Already Used Custom Short URL")
    public void testCreateInvalidCustomShortUrl() throws MalformedURLException, CustomShortUrlTakenException {
        Mockito.when(tinyUrlChecker.existingShortUrlFound(Mockito.anyString()))
                .thenReturn(true);
        UrlEntity urlEntity = tinyUrlService.createTinyUrl(mockTinyUrlRequest2());
    }

    @Test
    @DisplayName("Valid Custom Short URL")
    public void testCreateValidCustomShortUrl() throws MalformedURLException, CustomShortUrlTakenException {
        Mockito.when(tinyUrlChecker.existingShortUrlFound(Mockito.anyString()))
                .thenReturn(false);
        Mockito.when(urlRepo.save(Mockito.any(UrlEntity.class)))
                .thenReturn(new UrlEntity());
        UrlEntity urlEntity = tinyUrlService.createTinyUrl(mockTinyUrlRequest2());
        Assertions.assertNotNull(urlEntity);
    }

    @Disabled("This test is disabled WIP")
    @DisplayName("No Custom Short URL")
    public void testCreateNewShortUrl() throws MalformedURLException, CustomShortUrlTakenException {

        Mockito.when(generationStrategyFactory.getGenerationStrategy(Mockito.anyString()))
                .thenReturn(new Base62Strategy());
        Base62Strategy base62Strategy1 = mock(Base62Strategy.class);
        Mockito.when(base62Strategy1.generateShortUrl(Mockito.anyString()))
                .thenReturn("ABC1234");
        Mockito.when(urlRepo.save(Mockito.any(UrlEntity.class)))
                .thenReturn(new UrlEntity());
        UrlEntity urlEntity = tinyUrlService.createTinyUrl(mockTinyUrlRequest3());
        Assertions.assertNotNull(urlEntity);
    }

    @Test(expected = InvalidTinyUrlException.class)
    @DisplayName("Get Long URL Invalid")
    public void testGetLongUrlInvalid() throws InvalidTinyUrlException {
        String longUrl = tinyUrlService.getLongUrl("ABC");
    }

    @Test
    @DisplayName("Get Valid Long URL")
    public void testGetLongUrlValid() throws InvalidTinyUrlException {
        Mockito.when(urlRepo.findByShortUrl(Mockito.anyString()))
                .thenReturn(mockUrlEntity());
        String longUrl = tinyUrlService.getLongUrl("ABC1234");

    }

    private TinyUrlRequest mockTinyUrlRequest1(){
        TinyUrlRequest tinyUrlRequest = new TinyUrlRequest();
        tinyUrlRequest.setLongUrl("hsdsdfds");
        tinyUrlRequest.setCustomShortUrl("mongo");
        return tinyUrlRequest;
    }

    private TinyUrlRequest mockTinyUrlRequest2(){
        TinyUrlRequest tinyUrlRequest = new TinyUrlRequest();
        tinyUrlRequest.setLongUrl("https://google.com");
        tinyUrlRequest.setCustomShortUrl("mongo");
        return tinyUrlRequest;
    }

    private TinyUrlRequest mockTinyUrlRequest3(){
        TinyUrlRequest tinyUrlRequest = new TinyUrlRequest();
        tinyUrlRequest.setLongUrl("https://google.com");
        return tinyUrlRequest;
    }

    private UrlEntity mockUrlEntity(){
        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setShortUrl("ABCD");
        urlEntity.setLongUrl("https://google.com");
        return urlEntity;
    }
}