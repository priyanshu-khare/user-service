package com.app.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		System.out.println("This microservice contains Authentication and Authorization Logic");
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
