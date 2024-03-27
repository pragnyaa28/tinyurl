package com.intuit.demo.advice;

import com.intuit.demo.exceptionhandler.CustomShortUrlTakenException;
import com.intuit.demo.exceptionhandler.InvalidTinyUrlException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        log.error("Invalid Arguments in Request Body");
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put("errorMessage",error.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(InvalidTinyUrlException.class)
    public Map<String, String> handleBusinessException(InvalidTinyUrlException ex) {
        log.warn("Tiny URL Does Not Exist {}", ex.getMessage());
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomShortUrlTakenException.class)
    public Map<String, String> handleBusinessException(CustomShortUrlTakenException ex) {
        log.error("Existing custom short URL {}", ex.getMessage());
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MalformedURLException.class)
    public Map<String, String> handleUrlFormatException(MalformedURLException ex) {
        log.error("URL is not in correct format {}", ex.getMessage());
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", "Malformed Long URL");
        return errorMap;
    }

}
