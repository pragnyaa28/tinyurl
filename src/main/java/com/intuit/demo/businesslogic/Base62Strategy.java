package com.intuit.demo.businesslogic;

import com.intuit.demo.helper.TinyUrlHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class Base62Strategy implements GenerationStrategy{

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = ALPHABET.length();

    private static int SHORT_URL_CHAR_SIZE=7;

    @Autowired
    TinyUrlHelper tinyUrlHelper;
    @Override
    public String generateShortUrl(String longUrl) {

        StringBuilder encodedString = new StringBuilder();

        while(StringUtils.isNotBlank(encodedString.toString())
                && tinyUrlHelper.findExistingShortUrl(encodedString.toString())){
            for(int i= 1; i<= SHORT_URL_CHAR_SIZE ;i++)
            {
                encodedString.append(ALPHABET.charAt(ThreadLocalRandom.current().nextInt(0, BASE)));
            }
        }
        return encodedString.toString();
    }
}
