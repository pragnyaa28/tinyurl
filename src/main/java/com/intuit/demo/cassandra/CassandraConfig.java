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

//import com.datastax.oss.driver.api.core.CqlSessionBuilder;
//import com.datastax.oss.driver.api.core.config.DefaultDriverOption;
//import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
//import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
//import org.springframework.data.cassandra.config.SessionBuilderConfigurer;
//import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
//
//import java.time.Duration;
//
//@Configuration
//@PropertySource(value = { "classpath:application.properties" })
//@EnableCassandraRepositories(basePackages = "com.intuit.demo")
//public class CassandraConfig extends AbstractCassandraConfiguration {
//
//    @Value("${astra.keyspace}")
//    private String keySpace;
//
//    @Value("${spring.data.cassandra.contact-points}")
//    private String contactPoints;
//
//    @Value("${astra.cloud-region}")
//    private String datacenter;
//    @Value("${spring.data.cassandra.port}")
//    private int port;
//    @Value("${spring.data.cassandra.username}")
//    private String username;
//
//    @Value("${spring.data.cassandra.password}")
//    private String password;
//    @Value("${astra.keyspace}")
//    private String keyspaceName;
//
//    @Override
//    protected String getContactPoints() {
//        return contactPoints;
//    }
//
//    @Override
//    protected String getKeyspaceName() {
//        return keySpace;
//    }
//
//
//    @Override
//    protected int getPort() {
//        return port;
//    }
//
//    @Override
//    protected String getLocalDataCenter() {
//        return datacenter;
//    }
//
//    @Bean
//    @Override
//    public CqlSessionFactoryBean cassandraSession() {
//        CqlSessionFactoryBean cassandraSession = super.cassandraSession();//super session should be called only once
//        cassandraSession.setUsername(username);
//        cassandraSession.setPassword(password);
//        cassandraSession.setContactPoints(contactPoints);
//        cassandraSession.setPort(port);
//        cassandraSession.setLocalDatacenter(datacenter);
//        cassandraSession.setKeyspaceName(keySpace);
//        return cassandraSession;
//    }
//
//    @Override
//    protected SessionBuilderConfigurer getSessionBuilderConfigurer() {
//        return new SessionBuilderConfigurer() {
//
//            @Override
//            public CqlSessionBuilder configure(CqlSessionBuilder sessionBuilder) {
//                return sessionBuilder
//                        .withConfigLoader(DriverConfigLoader.programmaticBuilder()
//                                // Resolves the timeout query 'SELECT * FROM system_schema.tables' timed out after PT2S
//                                .withDuration(DefaultDriverOption.METADATA_SCHEMA_REQUEST_TIMEOUT, Duration.ofMillis(60000))
//                                .withDuration(DefaultDriverOption.CONNECTION_INIT_QUERY_TIMEOUT, Duration.ofMillis(60000))
//                                .withDuration(DefaultDriverOption.REQUEST_TIMEOUT, Duration.ofMillis(15000))
//                                .build());
//            }
//        };
//    }
//}