package com.winning.devops.oauth2.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.oauth2.server
 * @date 2019-05-23 14:31
 */
@SpringBootApplication
@EnableEurekaClient
public class Oauth2ServerApplication {
    public static void main(String[] args){
        SpringApplication.run(Oauth2ServerApplication.class,args);
    }
}
