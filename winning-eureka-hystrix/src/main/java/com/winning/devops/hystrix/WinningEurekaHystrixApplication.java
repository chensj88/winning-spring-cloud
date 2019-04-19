package com.winning.devops.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

/**
 * Hystrix
 * @author chensj
 * @date 2019年4月19日10:49:26
 */
@SpringBootApplication
@EnableCircuitBreaker
public class WinningEurekaHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(WinningEurekaHystrixApplication.class, args);
    }

}
