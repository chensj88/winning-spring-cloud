package com.winning.devops.zipkin.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * @author chensj
 * @date 2019-06-15 13:44
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer
public class SleuthZipkinServerApplication {
    public static void main(String[] args){
       SpringApplication.run(SleuthZipkinServerApplication.class,args);
    }
}
