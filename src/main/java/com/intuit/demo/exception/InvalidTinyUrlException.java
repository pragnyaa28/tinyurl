package com.intuit.demo.exception;

public class InvalidTinyUrlException extends Exception{


    public InvalidTinyUrlException(String shortUrl) {
        super("Invalid Tiny URL provided : " + shortUrl);
    }
}
