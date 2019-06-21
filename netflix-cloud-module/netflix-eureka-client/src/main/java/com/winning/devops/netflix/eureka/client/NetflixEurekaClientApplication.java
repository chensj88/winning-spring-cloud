package com.winning.devops.netflix.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author chensj
 * @date 2019-06-21 13:31
 */
@SpringBootApplication
@EnableEurekaClient
public class NetflixEurekaClientApplication {
    public static void main(String[] args){
       SpringApplication.run(NetflixEurekaClientApplication.class,args);
    }
}
