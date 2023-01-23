package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EurekaApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaApiGatewayApplication.class, args);
	}

}
