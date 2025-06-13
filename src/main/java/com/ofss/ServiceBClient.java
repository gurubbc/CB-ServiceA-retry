package com.ofss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class ServiceBClient {

    @Autowired
    private RestTemplate restTemplate;

    String BASE_URL = "http://localhost:8081";

    @Retry(name = "serviceA", fallbackMethod = "retryFallback")
    public String callServiceB() {
        System.out.println("Calling Service B...");
        return restTemplate.getForObject(BASE_URL + "/serviceB", String.class);
    }

    public String retryFallback(Exception e) {
        System.out.println("Retry fallback triggered: " + e.getMessage());
        return "Retry fallback: Service B is currently unavailable. Please try again later.";
//        throw new RuntimeException("Retry fallback failed", e); // allow circuit breaker to see it
    }

   
}
