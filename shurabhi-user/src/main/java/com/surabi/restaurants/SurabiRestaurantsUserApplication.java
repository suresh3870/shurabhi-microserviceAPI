package com.surabi.restaurants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
@EnableEurekaClient
public class SurabiRestaurantsUserApplication {

	public static void main(String[] args) {

		SpringApplication.run(SurabiRestaurantsUserApplication.class, args);
	}


}
