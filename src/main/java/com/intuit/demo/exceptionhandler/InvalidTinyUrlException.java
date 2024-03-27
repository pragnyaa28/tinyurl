package com.intuit.demo.exceptionhandler;

public class InvalidTinyUrlException extends Exception{


    public InvalidTinyUrlException(String shortUrl) {
        super("Invalid Tiny URL provided : " + shortUrl);
    }
}
