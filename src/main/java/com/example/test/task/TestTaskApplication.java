package com.example.test.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TestTaskApplication {



	public static void main(String[] args) {
		SpringApplication.run(TestTaskApplication.class, args);

	}

	@Bean
	@Scope("singleton")
	public RestTemplate restTemplate () {
		return new RestTemplate();
	}



}
