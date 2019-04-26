package com.winning.devops.feign.client.hystrix;

import com.winning.devops.feign.client.EurekaFeignClient;
import org.springframework.stereotype.Component;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.feign.client.hystrix
 * @date: 2019-04-26 19:40
 */
@Component
public class EurekaFeignClientHystrix implements EurekaFeignClient {
    @Override
    public String hello(String username) {
        return "Error,Sorry "+username;
    }
}
