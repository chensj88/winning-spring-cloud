package com.winning.devops.netflix.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author chensj
 * @date 2019-06-21 13:27
 */
@SpringBootApplication
@EnableEurekaServer
public class NetflixEurekaServerApplication {
    public static void main(String[] args){
       SpringApplication.run(NetflixEurekaServerApplication.class,args);
    }
}
