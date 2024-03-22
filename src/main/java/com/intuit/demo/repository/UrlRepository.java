package com.intuit.demo.repository;

import com.intuit.demo.entity.UrlEntity;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UrlRepository extends CassandraRepository<UrlEntity, Long> {

    @AllowFiltering
    UrlEntity findUrlEntityByShortUrl(String shortUrl);

    @AllowFiltering
    UrlEntity findUrlEntityByLongUrl(String longUrl);
}
