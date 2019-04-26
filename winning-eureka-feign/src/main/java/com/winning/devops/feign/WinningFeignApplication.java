package com.winning.devops.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.feign
 * @date: 2019-04-26 16:42
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
public class WinningFeignApplication {

    public static void main(String[] args){

        SpringApplication.run(WinningFeignApplication.class,args);

    }

}
