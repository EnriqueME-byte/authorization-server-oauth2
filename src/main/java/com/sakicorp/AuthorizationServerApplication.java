package com.sakicorp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthorizationServerApplication {

	public static void main(String[] args) {
		System.out.println("AUTHORIZATION SERVER");
		SpringApplication.run(AuthorizationServerApplication.class, args);
	}

}
