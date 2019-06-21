package com.winning.devops.boot.admin.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author chensj
 * @title spring boot admin server
 * @project winning-spring-cloud
 * @package com.winning.devops.boot.admin.server
 * @date: 2019-06-21 21:48
 */
@SpringBootApplication
@EnableEurekaClient
@EnableAdminServer
public class BootAdminServerApplication {
    public static void main(String[] args){
        SpringApplication.run(BootAdminServerApplication.class,args);
    }
}
