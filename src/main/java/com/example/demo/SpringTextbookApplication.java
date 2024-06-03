package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.example.demo.entity")
@SpringBootApplication
public class SpringTextbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTextbookApplication.class, args);
	}

}
