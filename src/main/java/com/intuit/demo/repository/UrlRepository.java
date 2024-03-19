package com.intuit.demo.repository;

import com.intuit.demo.entity.UrlEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<UrlEntity, String> {}
