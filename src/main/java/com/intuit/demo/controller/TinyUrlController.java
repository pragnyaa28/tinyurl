package com.intuit.demo.controller;

import com.intuit.demo.entity.UrlEntity;
import com.intuit.demo.exceptionhandler.CustomShortUrlTakenException;
import com.intuit.demo.exceptionhandler.InvalidTinyUrlException;
import com.intuit.demo.model.CreateTinyUrlRequest;
import com.intuit.demo.service.TinyUrlService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.MalformedURLException;
import java.util.concurrent.*;


@RestController
@RequestMapping("v1/tiny")
public class TinyUrlController {

        @Autowired
        private TinyUrlService tinyUrlService;

        @PostMapping(value="/create")
        public ResponseEntity<UrlEntity> createTinyUrl(@Valid @RequestBody CreateTinyUrlRequest createTinyUrlRequest)
                throws ExecutionException, InterruptedException {
            ExecutorService executor = Executors.newFixedThreadPool(10);
            Callable<ResponseEntity<UrlEntity>> callableTask = () -> new ResponseEntity<>(tinyUrlService.createTinyUrl(createTinyUrlRequest),
                    HttpStatus.CREATED);

            return executor.submit(callableTask).get();
        }

        @GetMapping("/{shortUrl}")
        public RedirectView getLongUrl(@PathVariable String shortUrl) throws ExecutionException, InterruptedException {
            ExecutorService executor = Executors.newFixedThreadPool(10);
            Callable<RedirectView> callableTask = () -> {
                RedirectView redirectView = new RedirectView();
                redirectView.setUrl(tinyUrlService.getLongUrl(shortUrl).getLongUrl());
                return redirectView;
            };
            return executor.submit(callableTask).get();
        }
}
