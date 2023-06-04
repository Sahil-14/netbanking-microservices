package com.srs.finance.netbankingserviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class NetbankingServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetbankingServiceRegistryApplication.class, args);
	}

}
