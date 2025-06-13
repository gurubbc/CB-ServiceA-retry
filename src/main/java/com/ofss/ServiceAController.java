package com.ofss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class ServiceAController {
	
	@Autowired
	ServiceBClient serviceBClient;
	
//	@Autowired
//	RestTemplate restTemplate;
//	String BASE_URL="http://localhost:8081"; // Service B URL

	
	
	@GetMapping("/serviceA")
	@CircuitBreaker(name = "serviceA", fallbackMethod = "serviceAFallBack")
	public String serviceA() {
		try {
			return "Service A - response from Service B " + serviceBClient.callServiceB();
		}
		catch(ResourceAccessException e) {
			System.out.println("Failed to call service b, retry count is ");
			throw e;
		}
		catch(Exception e) {
			System.out.println("Failed to call service b, retry count is ");
			throw e;
		}
	}
	
	public String serviceAFallBack(Exception e) {
		System.out.println("Service A fallback method called "+e.getMessage());
		return "Service B is down, try again later";
	}
}
