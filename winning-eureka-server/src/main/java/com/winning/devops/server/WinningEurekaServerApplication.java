package com.winning.devops.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author chensj
 * @title Eureka 治理中心
 */
@SpringBootApplication
@EnableEurekaServer
public class WinningEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WinningEurekaServerApplication.class, args);
    }

}
