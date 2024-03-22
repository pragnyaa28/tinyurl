package com.intuit.demo.businesslogic;

import com.intuit.demo.helper.TinyUrlHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class MDStrategy implements  GenerationStrategy{

    private static int SHORT_URL_CHAR_SIZE=7;

    @Autowired
    TinyUrlHelper tinyUrlHelper;
    public String convert(String longURL){

        MessageDigest digest = null;
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
    @Override
    public String generateShortUrl(String longURL) {
        String hash= convert(longURL);
        int numberOfCharsInHash=hash.length();
        int counter=0;
        while(counter < numberOfCharsInHash-SHORT_URL_CHAR_SIZE){
            if(!tinyUrlHelper.findExistingShortUrl(hash.substring(counter, counter+SHORT_URL_CHAR_SIZE))){
                return hash.substring(counter, counter+SHORT_URL_CHAR_SIZE);
            }
            counter++;
        }
        return null;
    }
}
