package com.winning.devops.oauth2.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package PACKAGE_NAME
 * @date 2019-05-23 14:39
 */
@SpringBootApplication
@EnableEurekaServer
public class Oauth2EurekaServerApplication {
    public static void main(String[] args){
        SpringApplication.run(Oauth2EurekaServerApplication.class,args);
    }
}
