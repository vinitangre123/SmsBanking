package com.infra.smsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmsappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmsappApplication.class, args);
		System.out.println("Hello");
	}

}
