package com.winning.devops.oauth2.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.oauth2.client
 * @date 2019-05-23 14:32
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class Oauth2ClientApplication {
    public static void main(String[] args){
        SpringApplication.run(Oauth2ClientApplication.class,args);
    }
}
