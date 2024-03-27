package com.intuit.demo.exceptionhandler;

public class CustomShortUrlTakenException extends Exception{
    public CustomShortUrlTakenException(String shortUrl) {
        super("Custom Short URL Already Taken : " + shortUrl);
    }
}
