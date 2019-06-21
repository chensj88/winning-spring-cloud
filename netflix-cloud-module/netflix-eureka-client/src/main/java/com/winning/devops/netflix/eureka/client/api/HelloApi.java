package com.winning.devops.netflix.eureka.client.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chensj
 * @date 2019-06-21 13:34
 */
@RestController
public class HelloApi {
    @Value("${server.port}")
    private String port;
    @Value("${spring.application.name}")
    private String application;

    @GetMapping(value = "/hi/{name}")
    public String hi(@PathVariable("name")String name){
        return "Hi," +name +", response from "+application + "@" + port;
    }
}
