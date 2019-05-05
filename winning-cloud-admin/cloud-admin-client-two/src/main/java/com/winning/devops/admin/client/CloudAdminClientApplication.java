package com.winning.devops.admin.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package PACKAGE_NAME
 * @date: 2019-05-04 22:29
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableCircuitBreaker
@EnableHystrixDashboard
@RestController
public class CloudAdminClientApplication {
    public static void main(String[] args){
        SpringApplication.run(CloudAdminClientApplication.class,args);
    }

    @Value("${server.port}")
    private int port;

    @GetMapping(value = "/hi/{username}")
    @HystrixCommand(fallbackMethod = "hiError")
    public String home(@PathVariable String username){
        return  "Hi,"+username +". I'm demo from port @"+port;
    }

    public String hiError(String username){
        return  "Hi,"+username +". Error";
    }
}
