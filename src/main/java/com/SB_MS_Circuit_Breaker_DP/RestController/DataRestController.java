package com.SB_MS_Circuit_Breaker_DP.RestController;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class DataRestController {

	@GetMapping("/data")
	@CircuitBreaker(name = "myCircuitBreaker",fallbackMethod="getDBdata")
	public String getRedisData() {
		
		System.out.println("Get data from redis method===main method()");
		if(new Random().nextInt() >= 10) {
			throw new RuntimeException("redis server is down");
		}
		
		return "data accessed from redis server";
	}
	public String getDBdata() {
		System.out.println("Db() method got called");
		return "Db method executed====fall back method() executed";
	}
}
