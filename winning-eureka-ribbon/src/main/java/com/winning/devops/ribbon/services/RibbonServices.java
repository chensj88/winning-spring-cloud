package com.winning.devops.ribbon.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.winning.devops.ribbon.base.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.ribbon.services
 * @date: 2019-04-26 16:19
 */
@Service
public class RibbonServices {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String hi(String username){
        return restTemplate.getForObject("http://"+ Constants.DEFAULT_EUREKA_CLIENT_NAME +"/hi/"+username,String.class);
    }

    /**
     * Ribbon Client 调用远程客户端失败后Hystrix回调返回的值
     * @param username 用户名
     * @return
     */
    public String hiError(String username){
        return  "hi,"+username+",sorry,error!";
    }

}
