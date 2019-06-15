package com.winning.devops.sleuth.rabbitmq.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author chensj
 * @date 2019-06-15 13:49
 */
@SpringBootApplication
@EnableEurekaClient
public class SleuthRabbitmqProducerApplication {
    public static void main(String[] args){
       SpringApplication.run(SleuthRabbitmqProducerApplication.class,args);
    }
}
