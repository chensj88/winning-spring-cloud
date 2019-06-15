package com.winning.devops.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author chensj
 * @date 2019-06-15 13:49
 */
@SpringBootApplication
@EnableEurekaClient
public class SleuthProducerApplication {
    public static void main(String[] args){
       SpringApplication.run(SleuthProducerApplication.class,args);
    }
}
