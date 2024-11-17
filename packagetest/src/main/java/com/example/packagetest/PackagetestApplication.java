package com.example.packagetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PackagetestApplication {

	@GetMapping("/")
	public String index() {

		return "Greetings from Spring Boot!";
	}
	@GetMapping("/search")
	public String search() {

		return "Greetings from Search Page!";
	}
	@GetMapping("/create")
	public String create() {

		return "CREATE PETITION Page!";
	}
	//test again 2
	@GetMapping("/viewall")
	public String viewall() {

		return "View All Petitions Page!";
	}
	//for testing 2
	@GetMapping("/viewpetition")
	public String viewpetionl() {

		return "View  Petitions Page!";
	}

	public static void main(String[] args) {
		SpringApplication.run(PackagetestApplication.class, args);
	}

}
