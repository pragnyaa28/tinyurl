package com.intuit.demo.controller;

import com.intuit.demo.entity.UrlEntity;
import com.intuit.demo.exception.CustomShortUrlTakenException;
import com.intuit.demo.exception.InvalidTinyUrlException;
import com.intuit.demo.model.TinyUrlRequest;
import com.intuit.demo.service.TinyUrlService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.MalformedURLException;


@RestController
@RequestMapping("v1/tiny")
public class TinyUrlController {

        @Autowired
        private TinyUrlService tinyUrlService;

        @PostMapping(value="/create")
        public ResponseEntity<UrlEntity> createTinyUrl(@Valid @RequestBody
                                                               TinyUrlRequest tinyUrlRequest)
                throws MalformedURLException, CustomShortUrlTakenException {

            return new ResponseEntity<>(tinyUrlService.createTinyUrl(tinyUrlRequest),
                    HttpStatus.CREATED);

        }

        @GetMapping("/{shortUrl}")
        @Cacheable("tinyurlcache")
        public RedirectView getLongUrl(@PathVariable String shortUrl) throws InvalidTinyUrlException {

            RedirectView redirectView = new RedirectView();
            redirectView.setUrl(tinyUrlService.getLongUrl(shortUrl));
            return redirectView;

        }
}
