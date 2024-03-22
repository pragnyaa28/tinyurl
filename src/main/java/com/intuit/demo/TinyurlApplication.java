package com.intuit.demo;

import com.intuit.demo.entity.UrlEntity;
import com.intuit.demo.repository.UrlRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class TinyurlApplication  {

	public static void main(String[] args) {
		SpringApplication.run(TinyurlApplication.class, args);
	}
	@Bean
	public CommandLineRunner clr(UrlRepository urlRepository) {
		return args -> {

			// save books in the database
			urlRepository.saveAll(Arrays.asList(
					new UrlEntity(1L, "ab", "google.com"),
					new UrlEntity(2L, "hp","HarryPotter.com")

			));

			// select only on book
			UrlEntity urlEntity = urlRepository.findUrlEntityByShortUrl("hp");
			System.out.println(urlEntity.getLongUrl());
			//delete the book with id 1
			urlRepository.deleteById(1L);

			//get all the books
			urlRepository.findAll().forEach(System.out::println);
		};
	}
}
