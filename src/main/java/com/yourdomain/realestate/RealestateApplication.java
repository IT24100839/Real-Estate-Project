package com.yourdomain.realestate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.ComponentScan; // Removed explicit ComponentScan import

@SpringBootApplication // This annotation includes component scanning by default
// The base package for scanning is automatically detected as the package of this class (com.yourdomain.realestate)
// and its sub-packages (controller, service, model, util).
// @ComponentScan(basePackages = "com.yourdomain.realestate") // Removed explicit ComponentScan annotation

public class RealestateApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealestateApplication.class, args);
	}

}
// on
