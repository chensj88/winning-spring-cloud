package com.winning.devops.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author chensj
 * @title Eureka Client
 */
@SpringBootApplication
@EnableEurekaClient
public class WinningEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(WinningEurekaClientApplication.class, args);
    }

}
