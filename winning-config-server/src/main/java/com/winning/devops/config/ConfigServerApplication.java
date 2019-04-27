package com.winning.devops.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.config
 * @date: 2019-04-27 16:17
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {

    public static void main(String[] args){
        SpringApplication.run(ConfigServerApplication.class,args);
    }
}
