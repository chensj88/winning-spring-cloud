package com.winning.devops.boot.admin.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author chensj
 * @date 2019-06-15 11:38
 */
@SpringBootApplication
@EnableEurekaServer
public class SpringBootAdminEurekaServerApplication {
    public static void main(String[] args){
        SpringApplication.run(SpringBootAdminEurekaServerApplication.class,args);
    }
}
