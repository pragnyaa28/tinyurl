package com.intuit.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableMongoRepositories("com.intuit.demo")
public class TinyurlApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinyurlApplication.class, args);
	}
}
