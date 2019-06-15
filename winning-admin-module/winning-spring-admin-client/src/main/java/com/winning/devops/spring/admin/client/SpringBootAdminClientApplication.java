package com.winning.devops.spring.admin.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author chensj
 * @date 2019-06-15 12:14
 */
@SpringBootApplication
@EnableEurekaClient
public class SpringBootAdminClientApplication {
    public static void main(String[] args){
       SpringApplication.run(SpringBootAdminClientApplication.class,args);
    }
}
