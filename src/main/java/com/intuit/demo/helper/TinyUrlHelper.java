package com.intuit.demo.helper;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class TinyUrlHelper {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = ALPHABET.length();
    private static int SHORT_URL_CHAR_SIZE=7;
    public boolean findExistingPool(String customShortUrl) {

        return false;
    }

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
    public String generateShortUrl(String longURL) {
        String hash= convert(longURL);
        int numberOfCharsInHash=hash.length();
        int counter=0;
        while(counter < numberOfCharsInHash-SHORT_URL_CHAR_SIZE){
            if(!findExistingPool(hash.substring(counter, counter+SHORT_URL_CHAR_SIZE))){
                return hash.substring(counter, counter+SHORT_URL_CHAR_SIZE);
            }
            counter++;
        }
        return null;
    }
}
