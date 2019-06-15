package com.winning.devops.rabbitmq.feign;

import com.winning.devops.rabbitmq.feign.client.EurekaClientFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chensj
 * @date 2019-06-15 14:01
 */
@Service
public class HiService {

    @Autowired
    EurekaClientFeign eurekaClientFeign;


    public String sayHi(String name){
        return  eurekaClientFeign.sayHiFromClientEureka(name);
    }
}
