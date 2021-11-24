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
public class SurabiRestaurantsAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurabiRestaurantsAdminApplication.class, args);
	}

}
