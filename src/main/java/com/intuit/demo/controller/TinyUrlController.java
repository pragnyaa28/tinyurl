package com.intuit.demo.controller;

import com.intuit.demo.entity.UrlEntity;
import com.intuit.demo.model.CreateTinyUrlRequest;
import com.intuit.demo.service.TinyUrlService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("v1/tinyurl")
public class TinyUrlController {

        @Autowired
        private TinyUrlService tinyUrlService;

        @PostMapping(value="/create")
        public ResponseEntity<UrlEntity> createTinyUrl(@Valid @RequestBody CreateTinyUrlRequest createTinyUrlRequest) {
            return new ResponseEntity<>(tinyUrlService.createTinyUrl(createTinyUrlRequest),
                    HttpStatus.CREATED);
        }

        @GetMapping("/{shortUrl}")
        public ResponseEntity<UrlEntity> getLongUrl(@PathVariable String shortUrl) {
            return new ResponseEntity<>(tinyUrlService.getLongUrl(shortUrl),
                    HttpStatus.OK);

        }
}
