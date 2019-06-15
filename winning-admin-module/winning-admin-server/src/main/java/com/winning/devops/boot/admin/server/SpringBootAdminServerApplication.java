package com.winning.devops.boot.admin.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author chensj
 * @date 2019-06-15 11:50
 */
@SpringBootApplication
@EnableAdminServer
@EnableEurekaClient
public class SpringBootAdminServerApplication {
    public static void main(String[] args){
       SpringApplication.run(SpringBootAdminServerApplication.class,args);
    }
}
