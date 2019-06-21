package com.winning.devops.netflix.turbine.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @author chensj
 * @date 2019-06-21 14:24
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableTurbine
public class NetflixTurbineMonitorApplication {
    public static void main(String[] args){
       SpringApplication.run(NetflixTurbineMonitorApplication.class,args);
    }
}
