package com.surabi.restaurants;


import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;


@SpringBootApplication
@EnableEurekaClient
public class SurabiRestaurantsOfflineApplication {

    public static void main(String[] args) {
        SpringApplication.run(SurabiRestaurantsOfflineApplication.class, args);
    }
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    }

}
