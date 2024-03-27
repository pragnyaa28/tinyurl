package com.intuit.demo.exception;

public class CustomShortUrlTakenException extends Exception{
    public CustomShortUrlTakenException(String shortUrl) {
        super("Custom Short URL Already Taken : " + shortUrl);
    }
}
