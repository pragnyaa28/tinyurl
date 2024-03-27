package com.intuit.demo.businesslogic;

import com.intuit.demo.helper.TinyUrlHelper;
import com.intuit.demo.repository.UrlRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

class Base62StrategyTest {

//    @Mock
//    Base62Strategy base62Strategy;
//
//    @InjectMocks
//    TinyUrlHelper tinyUrlHelper;
//
//    @BeforeEach
//    void setUp(){
//        base62Strategy = new Base62Strategy();
//        //MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void generateShortUrl() {
//        Mockito.when(tinyUrlHelper.existingShortUrlFound(Mockito.anyString())).thenReturn(false);
//        Assertions.assertEquals(7,
//                base62Strategy.generateShortUrl("https://google.com").length());
//    }
}