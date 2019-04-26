package com.winning.devops.feign.client;

import com.winning.devops.feign.base.Constants;
import com.winning.devops.feign.base.config.FeignConfig;
import com.winning.devops.feign.client.hystrix.EurekaFeignClientHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.feign.client
 * @date: 2019-04-26 19:35
 */

@FeignClient(
        value = Constants.DEFAULT_EUREKA_CLIENT_NAME,
        configuration = FeignConfig.class,
        fallback = EurekaFeignClientHystrix.class
)
public interface EurekaFeignClient {
    /**
     * Feign 客户端hi请求
     * @param username
     * @return
     */
    @GetMapping(value = "/hi/{username}")
    String hello(@PathVariable("username") String username);
}
