package com.winning.devops.client.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chensj
 * @title hello Controller
 * @project winning-spring-cloud
 * @package com.winning.devops.client.web
 * @date: 2019-04-10 22:32
 */
@RestController
public class HelloController {

    @Value("${server.port}")
    private int port;

    /**
     * 测试hi接口
     * @param name
     * @return
     */
    @GetMapping(value = "hi")
    public String h1(@RequestParam String name){
        return  "h1," + name + ", i'm from port" + port;
    }
}
