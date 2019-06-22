package com.winning.devops.cloud.consul.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chensj
 * @title 返回从consul 配置中心读取foo.bar的值
 * @project winning-spring-cloud
 * @package com.winning.devops.cloud.consul.config
 * @date: 2019-06-22 22:08
 */
@RestController
public class FooBarController {

    @Value("${foo.bar}")
    String fooBar;

    @GetMapping("/foo")
    public String getFooBar() {
        return fooBar;
    }
}
