package com.winning.devops.consul.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiApi {
    @Autowired
    HiService hiService;

    @GetMapping("/hi")
    public String sayHi(@RequestParam( defaultValue = "forezp",required = false)String name){
        return hiService.sayHi(name);
    }
}
