package com.db.lab3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;


@SpringBootApplication
public class Lab3Application implements CommandLineRunner {

	@Autowired
	Domain domain;

	@Autowired
	Presentation presentation;

	public static void main(String[] args) {
		SpringApplication.run(Lab3Application.class, args);
	}

	@Override
	public void run(String... args) {
		if (presentation.testUpdate()) {
			domain.importWeatherDB();
		}
		boolean loop = true;
		while (loop) {
			presentation.printWeatherForecast(
				domain.getWeatherForecast(
					presentation.inputWeatherAddress()
				)
			);
			loop = !presentation.testExit();
		}
	}
}
