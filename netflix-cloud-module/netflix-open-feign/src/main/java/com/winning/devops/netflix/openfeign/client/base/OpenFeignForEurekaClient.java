package com.winning.devops.netflix.openfeign.client.base;

import com.winning.devops.netflix.openfeign.client.base.hysrix.OpenFeignForEurekaClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author chensj
 * @date 2019-06-21 14:03
 */
@FeignClient(value = "netflix-eureka-client",fallback = OpenFeignForEurekaClientFallback.class)
public interface OpenFeignForEurekaClient {

    @GetMapping(value = "/hi/{name}")
    public String hi(@PathVariable String name);
}
