package com.winning.devops.boot.jpa.web;

import com.winning.devops.boot.base.Constants;
import com.winning.devops.boot.jpa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.boot.jpa.web
 * @date: 2019-04-10 13:29
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *
     * @param username
     * @return
     */
    @PostMapping(value = "/user/{username}")
    public Map<String, Object> getUser(@PathVariable("username") String username){
       Map<String, Object> resultMap = new HashMap<>(6);
       resultMap.put("status", Constants.SUCCESS);
       resultMap.put("data",userService.getUser(username));
       return resultMap;
    }
}
