package com.tictoccroc.island;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class IslandApplication {

	public static void main(String[] args) {
		SpringApplication.run(IslandApplication.class, args);
	}

}
