package com.example.redisdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@EnableCaching
@SpringBootApplication
public class RedisDemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(RedisDemoApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(final CompanyRepository repository){
		return args -> {
			repository.save(new Company("A"));
			repository.save(new Company("B"));
			repository.save(new Company("C"));
		};
	}
}
