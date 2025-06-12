package com.db.lab3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Lab3Application implements CommandLineRunner {

	@Autowired
	Domain domain = new Domain();

	public static void main(String[] args) {
		SpringApplication.run(Lab3Application.class, args);
	}

	@Override
	public void run(String... args) {
		domain.importWeatherDB();
	}
}
