package com.intuit.demo.repository;

import com.intuit.demo.entity.UrlEntity;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface UrlRepository extends CassandraRepository<UrlEntity, Long> {

    @AllowFiltering
    UrlEntity findUrlEntityByShortUrl(String shortUrl);
}
