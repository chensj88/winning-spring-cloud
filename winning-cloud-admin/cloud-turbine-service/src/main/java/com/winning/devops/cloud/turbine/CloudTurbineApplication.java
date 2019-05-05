package com.winning.devops.cloud.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.cloud.turbine
 * @date: 2019-05-05 20:39
 */
@SpringBootApplication
@EnableTurbine
public class CloudTurbineApplication {
    public static void main(String[] args){
        SpringApplication.run(CloudTurbineApplication.class,args);
    }
}
