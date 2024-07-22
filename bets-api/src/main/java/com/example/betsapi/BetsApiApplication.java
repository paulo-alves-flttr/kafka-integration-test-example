package com.example.betsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BetsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BetsApiApplication.class, args);
	}

}
