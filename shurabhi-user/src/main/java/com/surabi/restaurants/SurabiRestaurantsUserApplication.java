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
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public static void main(String[] args) {

		SpringApplication.run(SurabiRestaurantsUserApplication.class, args);
	}
	@PostConstruct
	private void initDb() {

		String sqlStatements[] = {
				"INSERT INTO MENU  VALUES (1, 'Dosa', 35)",
				"INSERT INTO MENU VALUES (2, 'Rice', 45)",
				"INSERT INTO MENU VALUES (3, 'Roti', 30)",
				"INSERT INTO MENU VALUES (4, 'Jira Rice', 60)",
				"INSERT INTO MENU VALUES (5, 'Dal', 90)",
				"INSERT INTO USERS VALUES ('ram', 'USER', TRUE, '$2a$10$CEowIBoP73DKZf./TVVLqO.Z2034Rk717xxfecnvxyt8HHVFLLe0a')",
				"INSERT INTO USERS VALUES ('suresh', 'ADMIN', TRUE, '$2a$10$KzQAQ4kAjSBsd.0MiHgyAecxwhj8MZrqju3FcjGrVn0TxWz563Qta')",
				"INSERT INTO USERS VALUES ('mohan', 'ADMIN', TRUE, '$2a$10$2mT8yco7eGGm/dtUsQffLOXME5aq5sYR9JJpG6xTOtuXGePIBPhUS')"
		};

		Arrays.asList(sqlStatements).forEach(sql -> {
			jdbcTemplate.execute(sql);
		});

	}

}
