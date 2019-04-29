package com.winning.devops.zipkin.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * @author chensj
 * @title Zipkin Server Application
 * @project winning-spring-cloud
 * @package com.winning.devops.zipkin.server
 * @date: 2019-04-29 21:25
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer
public class ZipkinServerApplication {

    public static void main(String[] args){
        SpringApplication.run(ZipkinServerApplication.class,args);
    }
}
