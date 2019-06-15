package com.winning.devops.elasticsearch.zipkin.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * @author chensj
 * @date 2019-06-15 15:59
 */
@SpringBootApplication
@EnableZipkinServer
@EnableEurekaClient
public class ElasticsearchZipkinServerApplication {
    public static void main(String[] args){
       SpringApplication.run(ElasticsearchZipkinServerApplication.class,args);
    }
}
