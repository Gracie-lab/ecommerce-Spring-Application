package com.ecommerce.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@EnableMongoRepositories(basePackages = {"com.ecommerce.ecommerce.*"})
@EntityScan(basePackages = {"com.ecommerce.ecommerce.*"})
@SpringBootApplication
public class EcommerceWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceWebsiteApplication.class, args);
	}

}
