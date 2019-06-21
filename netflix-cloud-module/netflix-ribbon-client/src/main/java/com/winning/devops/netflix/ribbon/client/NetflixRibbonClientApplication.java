package com.winning.devops.netflix.ribbon.client;

import com.winning.devops.netflix.ribbon.client.service.RibbonClientService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @date 2019-06-21 13:42
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrix
@EnableHystrixDashboard
@RestController
public class NetflixRibbonClientApplication {
    public static void main(String[] args){
       SpringApplication.run(NetflixRibbonClientApplication.class,args);
    }

    @Autowired
    private RibbonClientService ribbonClientService;
    
    @GetMapping(value = "/hi/{name}")
    public String hi(@PathVariable String name){
        return ribbonClientService.hi(name);
    }
}
