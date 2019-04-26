package com.winning.devops.client.web.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Value("${server.port}")
    private int port;
    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private EurekaClient eurekaClient;

    /**
     * 测试hi接口
     * @param username
     * @return
     */
    @GetMapping(value = "/hi/{username}")
    public String h1(@PathVariable String username){
        // 获取Eureka Client 信息
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka(applicationName.toUpperCase(), false);
        logger.info(instanceInfo.getHomePageUrl());
        return  "h1," + username + ", i'm from port @" + port;
    }
}
