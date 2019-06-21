package com.winning.devops.netflix.ribbon.client.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author chensj
 * @date 2019-06-21 13:45
 */
@Service
public class RibbonClientService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String hi(String name){
        return restTemplate.getForObject("http://netflix-eureka-client/hi/"+name,String.class);
    }
    /**
     * Ribbon Client 调用远程客户端失败后Hystrix回调返回的值
     * @param name 用户名
     * @return
     */
    public String hiError(String name){
        return  "hi,"+name+",sorry,error!";
    }
}
