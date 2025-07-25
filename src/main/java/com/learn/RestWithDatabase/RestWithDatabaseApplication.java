package com.learn.RestWithDatabase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestWithDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestWithDatabaseApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		CommandLineRunner runner = new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				System.out.println("API is running!");
			}
		};
		return runner;
		// return runner -> {
		// 	System.out.println("API is running!");
		// };
	}
}
