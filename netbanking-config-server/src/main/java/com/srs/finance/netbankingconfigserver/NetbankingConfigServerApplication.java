package com.srs.finance.netbankingconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class NetbankingConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetbankingConfigServerApplication.class, args);
	}

}
