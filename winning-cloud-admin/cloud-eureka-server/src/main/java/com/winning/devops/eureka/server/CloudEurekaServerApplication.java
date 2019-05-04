package com.winning.devops.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.eureka.server
 * @date: 2019-05-04 22:06
 */
@SpringBootApplication
@EnableEurekaServer
public class CloudEurekaServerApplication {

    public static void main(String[] args){
        SpringApplication.run(CloudEurekaServerApplication.class,args);
    }
}
