package com.winning.devops.admin.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.admin.server
 * @date: 2019-06-18 19:20
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class ConsulAdminServerApplication {
    public static void main(String[] args){
        SpringApplication.run(ConsulAdminServerApplication.class,args);
    }
}
