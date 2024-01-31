package com.kumaiscoding.notesappspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.kumaiscoding.notesappspring")
public class NotesAppSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotesAppSpringApplication.class, args);
	}

}
