package com.intuit.demo.controller;

import com.intuit.demo.entity.UrlEntity;
import com.intuit.demo.model.CreateTinyUrlRequest;
import com.intuit.demo.service.TinyUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("api/v1/tinyurl")
public class TinyUrlController {

        @Autowired
        private TinyUrlService tinyUrlService;

        @PostMapping(value="/create")
        public UrlEntity createTinyUrl(@RequestBody CreateTinyUrlRequest createTinyUrlRequest) {

            return tinyUrlService.createTinyUrl(createTinyUrlRequest);


//            User user = userRepository.findByUserId(createTinyUrlRequest.getUserId());
//            if (user == null) {
//                return "User doesn't exist";
//            }
//            try {
//                return tinyUrlService.generateTinyUrl(createTinyUrlRequest);
//                String hashKey = findShortenedUrl(origUrl, request.getUserId());
//
//                request.setHashKey(hashKey);
//                request.setCreationDate(LocalDateTime.now());
//                urlRepository.save(request);
//                return "Shortedn URL created=>"+hashKey;
//
//            }catch(Exception e) {
//                e.printStackTrace();
////                return "Failed to generate shortened URL";
//            }
//
//            return null;
        }
//
//        private String findShortenedUrl(String origUrl, int userId) {
//            String hashKey = null;
//            while(hashKey == null) {
//                byte[] encodedhash = digest.digest(
//                        origUrl.getBytes(StandardCharsets.UTF_8));
//                String encodedHash = Base64.getUrlEncoder().encodeToString(encodedhash);
//                String shaString = new String(encodedHash.substring(0, 10));
//                URL url = urlRepository.findByHashKey(shaString);
//                if (url == null || (userId == url.getUserId() && origUrl.equals(url.getOriginalUrl()))) {
//                    hashKey = shaString;
//                }else {
//                    origUrl = origUrl+""+Math.random();
//                }
//            }
//            return hashKey;
//        }
//
//        @PostMapping(value="/api/deleteUrl")
//        public String deleteUrl(@RequestBody URL request) {
//            if (StringUtils.isEmpty(request.hashKey)) {
//                return "Please specify valid hashKey";
//            }
//            User user = userRepository.findByUserId(request.getUserId());
//            if (user == null) {
//                return "User doesn't exist";
//            }
//            int count = urlRepository.deleteByHashKey(request.getHashKey());
//            if (count > 0)
//                return "Shortened URL deleted";
//            else
//                return "Shortened URL not found";
//
//        }
//
//
//
//
    @GetMapping("/{shortUrl}")
    public RedirectView redirect(@PathVariable String shortUrl) {
        RedirectView redirectView = new RedirectView();

        List<UrlEntity> urlEntities = tinyUrlService.findAll();
        for (UrlEntity url : urlEntities) {
            if (shortUrl.equals(url.getShortUrl())) {
                String sUrl = url.getLongUrl();
                redirectView.setUrl(sUrl);
            }
        }
        return redirectView;
    }
}
