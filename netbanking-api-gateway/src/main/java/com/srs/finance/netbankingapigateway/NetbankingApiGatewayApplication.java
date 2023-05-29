package com.srs.finance.netbankingapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class NetbankingApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetbankingApiGatewayApplication.class, args);
	}

}
