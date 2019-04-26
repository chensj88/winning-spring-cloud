package com.winning.devops.feign.web;

import com.winning.devops.feign.base.Constants;
import com.winning.devops.feign.client.EurekaFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.feign.web
 * @date: 2019-04-26 19:43
 */
@RestController
public class HelloController {

    /**logger*/
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);


    @Autowired
    private EurekaFeignClient eurekaFeignClient;

    /**
     * feign 客户端请求
     * @param username 参数
     * @return
     */
    @GetMapping(value = "/hi/{username}")
    public Map<String,Object> hi(@PathVariable String username){
        logger.info("Feign Service:{}", username);
        Map<String,Object> resultMap = new HashMap<>(5);
        resultMap.put("data",eurekaFeignClient.hello(username));
        resultMap.put("status", Constants.SUCCESS);
        return resultMap;
    }
}
