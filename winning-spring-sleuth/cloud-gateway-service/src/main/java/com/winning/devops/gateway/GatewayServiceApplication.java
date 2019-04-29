package com.winning.devops.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author chensj
 * @title Gateway Service Application
 * @project winning-spring-cloud
 * @package com.winning.devops.gateway
 * @date: 2019-04-29 21:41
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class GatewayServiceApplication {

    public static void main(String[] args){
        SpringApplication.run(GatewayServiceApplication.class,args);
    }
}
