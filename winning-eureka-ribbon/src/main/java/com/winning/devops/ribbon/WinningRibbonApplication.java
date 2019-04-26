package com.winning.devops.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.ribbon
 * @date: 2019-04-26 16:14
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableCircuitBreaker
@EnableHystrixDashboard
public class WinningRibbonApplication {

    public static void main(String[] args){
        SpringApplication.run(WinningRibbonApplication.class,args);
    }

}
