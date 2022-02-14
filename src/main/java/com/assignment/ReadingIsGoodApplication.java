package com.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class ReadingIsGoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadingIsGoodApplication.class, args);
	}

}
