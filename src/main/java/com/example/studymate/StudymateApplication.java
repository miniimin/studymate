package com.example.studymate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StudymateApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudymateApplication.class, args);
	}

}
