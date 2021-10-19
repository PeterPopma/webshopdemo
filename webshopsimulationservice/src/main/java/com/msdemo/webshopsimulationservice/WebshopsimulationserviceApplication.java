package com.msdemo.webshopsimulationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebshopsimulationserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebshopsimulationserviceApplication.class, args);
	}

}
