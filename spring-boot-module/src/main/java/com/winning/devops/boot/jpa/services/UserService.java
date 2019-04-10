package com.winning.devops.boot.jpa.services;

import com.winning.devops.boot.jpa.dao.UserDao;
import com.winning.devops.boot.jpa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.boot.jpa.services
 * @date: 2019-04-10 13:28
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getUser(String username){
        return  userDao.findByUsername(username);
    }
}
