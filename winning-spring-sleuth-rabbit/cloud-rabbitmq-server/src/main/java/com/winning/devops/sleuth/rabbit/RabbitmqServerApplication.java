package com.winning.devops.sleuth.rabbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.sleuth.rabbit
 * @date: 2019-04-30 21:54
 */
@SpringBootApplication
@EnableEurekaClient
public class RabbitmqServerApplication {
    public static void main(String[] args){
        SpringApplication.run(RabbitmqServerApplication.class,args);
    }
}
