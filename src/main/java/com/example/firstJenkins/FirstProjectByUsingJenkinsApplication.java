package com.example.firstJenkins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@SpringBootApplication
public class FirstProjectByUsingJenkinsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstProjectByUsingJenkinsApplication.class, args);
		System.out.println("Hello Manisha");
	}
	@GetMapping("/")
	public String home() {
		return "hello manisha";
	}
	
	}

