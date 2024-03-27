package com.intuit.demo.businesslogic;

import com.intuit.demo.helper.TinyUrlHelper;
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
    TinyUrlHelper tinyUrlHelper;

    @Override
    public String generateShortUrl(String longURL) {
        String hash = getHash(longURL);
        int numberOfCharsInHash = hash.length();
        int counter = 0;
        while(counter < numberOfCharsInHash-SHORT_URL_CHAR_SIZE){
            String hashSubstring = hash.substring(counter, counter+SHORT_URL_CHAR_SIZE);
            if(!tinyUrlHelper.existingShortUrlFound(hashSubstring)){
                return hashSubstring;
            }
            counter++;
        }
        return null;
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
