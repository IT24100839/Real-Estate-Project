package com.yourdomain.realestate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan; // Import ComponentScan

@SpringBootApplication
// Explicitly tell Spring where to scan for components (controllers, services, etc.)
// This should be your base package where all your sub-packages (controller, service, model, util) are located
@ComponentScan(basePackages = "com.yourdomain.realestate") // <-- Ensure this is present and NOT commented out
public class RealestateApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealestateApplication.class, args);
	}

}
