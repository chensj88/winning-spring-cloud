package com.winning.devops.netflix.zuul.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author chensj
 * @date 2019-06-21 14:47
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableHystrix
@EnableCircuitBreaker
public class NetflixZuulGatewayApplication {
    public static void main(String[] args){
       SpringApplication.run(NetflixZuulGatewayApplication.class,args);
    }
}
