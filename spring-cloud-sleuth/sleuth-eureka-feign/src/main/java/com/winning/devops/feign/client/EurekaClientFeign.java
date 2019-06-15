package com.winning.devops.feign.client;

import com.winning.devops.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chensj
 * @date 2019-06-15 14:04
 */
@FeignClient(value = "sleuth-eureka-client",configuration = FeignConfig.class)
public interface EurekaClientFeign {

    @GetMapping(value = "/hi")
    String sayHiFromClientEureka(@RequestParam(value = "name") String name);
}
