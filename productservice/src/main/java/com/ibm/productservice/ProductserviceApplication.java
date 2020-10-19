package com.ibm.productservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
public class ProductserviceApplication {
	static Logger logger = LoggerFactory.getLogger(ProductserviceApplication.class);
	public static void main(String[] args) {
		logger.info("****************Product Service Application*****************");
		SpringApplication.run(ProductserviceApplication.class, args);
	}

}
