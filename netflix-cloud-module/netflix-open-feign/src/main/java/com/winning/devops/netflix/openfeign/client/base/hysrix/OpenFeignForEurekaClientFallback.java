package com.winning.devops.netflix.openfeign.client.base.hysrix;

import com.winning.devops.netflix.openfeign.client.base.OpenFeignForEurekaClient;
import org.springframework.stereotype.Component;

/**
 * @author chensj
 * @date 2019-06-21 14:04
 */
@Component
public class OpenFeignForEurekaClientFallback implements OpenFeignForEurekaClient {
    @Override
    public String hi(String name) {
        return "Sorry "+name+",remote error!!!";
    }
}
