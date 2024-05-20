package com.infovergne.restgw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RestGwApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestGwApplication.class, args);
	}

}
