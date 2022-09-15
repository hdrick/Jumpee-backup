package com.dricks.jumpee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
//@SpringBootApplication
public class DricksApplication {

	public static void main(String[] args) {
		SpringApplication.run(DricksApplication.class, args);
	}

}
