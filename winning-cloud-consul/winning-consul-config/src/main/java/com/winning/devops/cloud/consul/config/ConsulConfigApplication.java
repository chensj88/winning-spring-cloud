package com.winning.devops.cloud.consul.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.cloud.consul.config
 * @date: 2019-06-22 22:02
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulConfigApplication {
    public static void main(String[] args){
       SpringApplication.run(ConsulConfigApplication.class,args);
    }
}
