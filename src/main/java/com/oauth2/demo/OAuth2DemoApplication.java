package com.oauth2.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class OAuth2DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OAuth2DemoApplication.class, args);
	}
	
	@Bean
	public ObjectMapper objectNode() {
		return new ObjectMapper();
	}
}
