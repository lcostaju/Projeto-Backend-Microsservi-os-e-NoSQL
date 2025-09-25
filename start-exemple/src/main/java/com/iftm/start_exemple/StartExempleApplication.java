package com.iftm.start_exemple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class StartExempleApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartExempleApplication.class, args);
	}

}
