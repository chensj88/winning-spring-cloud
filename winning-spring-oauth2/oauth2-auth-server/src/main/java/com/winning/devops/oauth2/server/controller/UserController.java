package com.winning.devops.oauth2.server.controller;

import com.winning.devops.oauth2.server.entity.User;
import com.winning.devops.oauth2.server.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.oauth2.server.controller
 * @date: 2019-05-23 22:11
 */
@RestController
@RequestMapping("/users")
public class UserController {
    /**logger*/
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserService userService;
    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        logger.info(principal.toString());
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        return principal;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public User createUser(@RequestBody User user) {
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        logger.info(user.toString());
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        userService.create(user);

        return user;
    }

}
