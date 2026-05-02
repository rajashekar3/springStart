package com.example.springStart;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringStartApplication.class, args);
	}

	@Bean
	CommandLineRunner run(StudentRepository repo) {
		return args -> {
			repo.save(new Student("Raj"));
			repo.save(new Student("Sai"));
			repo.save(new Student("Raj12"));

			repo.findAll().forEach(student ->
					System.out.println(student.getId() + " " + student.getName())
			);
		};
	}
}