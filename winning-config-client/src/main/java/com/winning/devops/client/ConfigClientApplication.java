package com.winning.devops.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.client
 * @date: 2019-04-27 16:20
 */
@SpringBootApplication
@RestController
public class ConfigClientApplication {

    public static void main(String[] args){
        SpringApplication.run(ConfigClientApplication.class,args);
    }

    @Value("${foo}")
    private String foo;

    @GetMapping(value = "hi")
    public String foo(){
        return foo;
    }
}
