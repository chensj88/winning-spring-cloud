package com.winning.devops.netflix.openfeign.client;

import com.winning.devops.netflix.openfeign.client.service.EurekaClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chensj
 * @date 2019-06-21 14:01
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrix
@EnableHystrixDashboard
@RestController
public class NetflixOpenfeignApplication {

    public static void main(String[] args){
       SpringApplication.run(NetflixOpenfeignApplication.class,args);
    }

    @Autowired
    private EurekaClientService eurekaClientService;

    @GetMapping(value = "/hi/{name}")
    public String hi(@PathVariable String name){
        return eurekaClientService.hi(name);
    }
}
