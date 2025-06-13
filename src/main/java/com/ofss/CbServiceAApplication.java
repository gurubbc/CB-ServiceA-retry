package com.ofss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CbServiceAApplication {

	public static void main(String[] args) {
		SpringApplication.run(CbServiceAApplication.class, args);
		System.out.println("Service A is up and running");
	}
	
	@Bean
	public RestTemplate m1() {
		System.out.println("RestTemplate bean is created");
		return new RestTemplate();
	}

}
