package com.winning.devops.spring.boot.jwt.service;

import com.winning.devops.spring.boot.jwt.model.User;
import com.winning.devops.spring.boot.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chensj
 * @date 2019-05-29 16:23
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findUserById(long id){
        return userRepository.findUserById(id);
    }

    public User findByUsername(User user) {
        return userRepository.findUserByUsername(user.getUsername());
    }
}
