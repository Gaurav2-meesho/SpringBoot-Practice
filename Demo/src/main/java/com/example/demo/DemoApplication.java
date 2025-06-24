package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
@ComponentScan("com.example.*")	// This is used to scan the com.example package for components
@EntityScan("com.example.entity")	// This is used to scan the com.example.entity package for entities
@EnableJpaRepositories("com.example.repository")	// This is used to scan the com.example.repository package for repositories
@EnableScheduling
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

} 