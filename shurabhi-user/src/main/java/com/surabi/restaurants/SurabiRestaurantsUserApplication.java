package com.surabi.restaurants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SurabiRestaurantsUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurabiRestaurantsUserApplication.class, args);
	}

}
