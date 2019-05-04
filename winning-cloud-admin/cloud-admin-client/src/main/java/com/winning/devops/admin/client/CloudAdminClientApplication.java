package com.winning.devops.admin.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package PACKAGE_NAME
 * @date: 2019-05-04 22:29
 */
@SpringBootApplication
@EnableEurekaClient
public class CloudAdminClientApplication {
    public static void main(String[] args){
        SpringApplication.run(CloudAdminClientApplication.class,args);
    }
}
