package com.arisan.online.groupservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GroupserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroupserviceApplication.class, args);
	}
}
