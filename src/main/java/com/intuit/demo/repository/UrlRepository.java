package com.intuit.demo.repository;

import com.intuit.demo.entity.UrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends MongoRepository<UrlEntity, Long> {

    UrlEntity findByShortUrl(String shortUrl);

   UrlEntity findByLongUrl(String longUrl);
}
