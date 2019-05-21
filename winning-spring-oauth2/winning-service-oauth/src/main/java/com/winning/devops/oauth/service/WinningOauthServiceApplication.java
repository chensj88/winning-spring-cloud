package com.winning.devops.oauth.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.oauth.service
 * @date: 2019-05-21 17:08
 */
@SpringBootApplication
@EnableResourceServer
@EnableEurekaClient
public class WinningOauthServiceApplication {

    public static void main(String[] args){
        SpringApplication.run(WinningOauthServiceApplication.class,args);
    }
}
