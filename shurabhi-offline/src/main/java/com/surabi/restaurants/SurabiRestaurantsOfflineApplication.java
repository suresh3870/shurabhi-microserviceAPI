package com.surabi.restaurants;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class SurabiRestaurantsOfflineApplication {

    public static void main(String[] args) {
        SpringApplication.run(SurabiRestaurantsOfflineApplication.class, args);
    }

}
