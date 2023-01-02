package com.infra.smsapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;





@SpringBootApplication
public class SmsappApplication extends SpringBootServletInitializer {
	
	private static Logger logger = LogManager.getLogger(SmsappApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SmsappApplication.class, args);
		logger.info("Webserives Started for db");

	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SmsappApplication.class);
	}

}
