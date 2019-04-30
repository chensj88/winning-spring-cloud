package com.winning.devops.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package PACKAGE_NAME
 * @date: 2019-04-29 21:30
 */
@SpringBootApplication
@EnableEurekaClient
public class UserRabbitmqServiceApplication  {

    public static void main(String[] args){
        SpringApplication.run(UserRabbitmqServiceApplication.class,args);
    }
}
