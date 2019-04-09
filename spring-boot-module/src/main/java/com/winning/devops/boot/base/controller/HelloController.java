package com.winning.devops.boot.base.controller;

import com.winning.devops.boot.base.Constants;
import com.winning.devops.boot.base.config.ConfigProperties;
import com.winning.devops.boot.base.config.FtpProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chensj
 * @title Hello 控制器
 * @project winning-spring-cloud
 * @package com.winning.devops.boot.base.controller
 * @date: 2019-04-09 22:30
 * @EnableConfigurationProperties 指定配置的实体类
 */
@RestController
@EnableConfigurationProperties({ConfigProperties.class, FtpProperties.class})
public class HelloController {

    @Autowired
    private ConfigProperties configProperties;
    @Autowired
    private FtpProperties ftpProperties;

    @GetMapping(value = "hi")
    public Map<String, Object> hi(){
        System.out.println(configProperties.toString());
        System.out.println(ftpProperties.toString());
        Map<String,Object> resultMap = new HashMap<>(5);
        resultMap.put("status", Constants.SUCCESS);
        return resultMap;
    }
}
