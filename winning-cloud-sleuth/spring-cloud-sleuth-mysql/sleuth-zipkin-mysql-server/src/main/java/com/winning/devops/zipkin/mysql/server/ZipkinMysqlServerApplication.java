package com.winning.devops.zipkin.mysql.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * @author chensj
 * @date 2019-06-15 15:16
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer
public class ZipkinMysqlServerApplication {
    public static void main(String[] args){
       SpringApplication.run(ZipkinMysqlServerApplication.class,args);
    }
}
