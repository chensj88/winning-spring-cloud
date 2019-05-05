package com.winning.devops.admin.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.admin.server
 * @date: 2019-05-05 22:15
 */
@SpringBootApplication
@EnableAdminServer
@EnableEurekaClient
public class CloudAdminServerApplication {
    public static void main(String[] args){
        SpringApplication.run(CloudAdminServerApplication.class,args);
    }
}
