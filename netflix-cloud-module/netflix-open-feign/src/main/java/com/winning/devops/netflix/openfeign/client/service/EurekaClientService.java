package com.winning.devops.netflix.openfeign.client.service;

import com.winning.devops.netflix.openfeign.client.base.OpenFeignForEurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chensj
 * @date 2019-06-21 14:06
 */
@Service
public class EurekaClientService {
    @Autowired
    private OpenFeignForEurekaClient openFeignForEurekaClient;

    public String hi(String name){
        return  openFeignForEurekaClient.hi(name);
    }
}
