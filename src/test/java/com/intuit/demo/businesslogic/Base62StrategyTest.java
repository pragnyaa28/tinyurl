package com.intuit.demo.businesslogic;

import com.intuit.demo.helper.TinyUrlHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Base62StrategyTest {

    @InjectMocks
    Base62Strategy base62Strategy;

    @Mock
    TinyUrlHelper tinyUrlHelper;

    @Before
    public void createMocks() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void generateShortUrl() {
        Mockito.when(tinyUrlHelper.existingShortUrlFound(Mockito.anyString())).thenReturn(false);
        Assertions.assertEquals(7,
                base62Strategy.generateShortUrl("https://googleghfhdhhfrde.com").length());
    }
}