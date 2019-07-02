package com.winning.devops.cloud.nacos.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.cloud.nacos.config
 * @date: 2019-07-02 20:53
 */
@SpringBootApplication
public class NacosConfigClientApplication {
    public static void main(String[] args){
        SpringApplication.run(NacosConfigClientApplication.class,args);
    }
}
