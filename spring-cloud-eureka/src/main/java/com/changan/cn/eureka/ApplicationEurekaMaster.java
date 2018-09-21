	package com.changan.cn.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaServer
public class ApplicationEurekaMaster {
    
	public static void main(String[] args) {
		SpringApplication.run(ApplicationEurekaMaster.class, args);
	}
}
 	