package com.Tiago.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.Tiago.rest", "com.Tiago.calculator"})
public class RestModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestModuleApplication.class, args);
	}

}
