package com.winning.devops.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.monitor
 * @date: 2019-04-26 20:06
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableTurbine
public class EurekaTurbineApplication {

    public static void main(String[] args){
        SpringApplication.run(EurekaTurbineApplication.class,args);
    }
}
