package com.winning.devops.rabbitmq.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author chensj
 * @date 2019-06-15 13:57
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class SleuthRabbitmqFeignApplication {
    public static void main(String[] args){
       SpringApplication.run(SleuthRabbitmqFeignApplication.class,args);
    }
}
