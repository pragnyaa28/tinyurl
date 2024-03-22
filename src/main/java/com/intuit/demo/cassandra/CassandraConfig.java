package com.intuit.demo.cassandra;

import com.datastax.astra.boot.autoconfigure.AstraClientProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackages = {"com.intuit.demo"})
public class CassandraConfig extends AstraClientProperties {

    public String getClientId() {
        return "AstraCS";
    }

    public String getApplicationToken() {
        return "08907c2f0da84c8c35e6d5d195593893c9e34f8483980ee69ccc13e824f0d126";
    }

    public String getClientSecret() {
        return "CicSbRKJOIXrjWWGGeWxwruq";
    }

    public String getCloudRegion() {
        return "us-east-1";
    }

    public String getDatabaseId() {
        return "30d587fd-46aa-42e8-bbb7-ec57511df3e5";
    }

    public String getKeyspace() {
        return "spring_cassandra";
    }
}