package com.hsuhau.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

//@SpringBootApplication(scanBasePackages = "com.hsuhau.config")
@EnableAutoConfiguration
public class FirstAppByGuiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstAppByGuiApplication.class, args);
	}
}
