package com.intuit.demo.businesslogic;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MDStrategyTest {

    @InjectMocks
    MDStrategy mdStrategy;

    @Mock
    TinyUrlChecker tinyUrlChecker;

    @Test
    public void generateShortUrl() {
        Mockito.when(tinyUrlChecker.existingShortUrlFound(Mockito.anyString()))
                .thenReturn(false);
        Assertions.assertEquals(7,
                mdStrategy.generateShortUrl("https://goofrde.com").length());
    }
}