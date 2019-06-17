package com.winning.devops.consul.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HiService {

    @Autowired
    ConsulFeignClient consulFeignClient;


    public String sayHi(String name){
        return  consulFeignClient.sayHiFromClientEureka(name);
    }
}
