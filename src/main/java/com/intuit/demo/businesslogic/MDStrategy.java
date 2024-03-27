package com.intuit.demo.businesslogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.intuit.demo.constants.TinyUrlConstants.SHORT_URL_CHAR_SIZE;

@Component
public class MDStrategy implements  GenerationStrategy{

    /**
     * Code Sourced from https://www.baeldung.com/java-md5
     */

    @Autowired
    TinyUrlChecker tinyUrlChecker;

    @Override
    public String generateShortUrl(String longURL) {
        String hash = getHash(longURL);
        int numberOfCharsInHash = hash.length();
        int counter = 0;
        String shortUrl="";
        while(counter < numberOfCharsInHash-SHORT_URL_CHAR_SIZE){
            String hashSubstring = hash.substring(counter, counter+SHORT_URL_CHAR_SIZE);
            if(!tinyUrlChecker.existingShortUrlFound(hashSubstring)){
                shortUrl = hashSubstring;
                break;
            }
            counter++;
        }
        return shortUrl;
    }

    private String getHash(String longURL){

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        digest.update(longURL.getBytes());
        byte[] messageDigest = digest.digest();
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(Integer.toHexString(0xFF & b));
        }
        return hexString.toString();

    }
}
