package com.winning.devops.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chensj
 * @date 2019-06-15 13:53
 */
@RestController
public class ApiTestController {

    @Value("${server.port}")
    private String port;

    @GetMapping("hi")
    public String home(@RequestParam String name) {
        return "hi "+name+",i am from port:" +port;
    }
}
