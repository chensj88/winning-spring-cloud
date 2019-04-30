package com.winning.devops.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chensj
 * @title  user API 接口
 * @project winning-spring-cloud
 * @package com.winning.devops.user.controller
 * @date: 2019-04-29 21:36
 */
@RestController
public class UserController {

    @GetMapping(value = "/hi")
    public String hi(){
        return "I'm User Service";
    }
}
