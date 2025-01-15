package com.Tiago.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.Tiago.rest", "com.Tiago.calculator"})
/**
 * Main application for the Rest and the Spring Boot application
 */
public class RestModuleApplication {

	/**
	 *  Method to execute the Spring Boot application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(RestModuleApplication.class, args);
	}

}
