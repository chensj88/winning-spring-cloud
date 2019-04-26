package com.winning.devops.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.zuul
 * @date: 2019-04-26 21:14
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableHystrix
@EnableCircuitBreaker
public class WinningZuulApplication {

    public static void main(String[] args){
        SpringApplication.run(WinningZuulApplication.class,args);
    }
}
