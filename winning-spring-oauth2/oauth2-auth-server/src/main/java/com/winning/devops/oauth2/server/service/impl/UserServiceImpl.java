package com.winning.devops.oauth2.server.service.impl;

import com.winning.devops.oauth2.server.entity.User;
import com.winning.devops.oauth2.server.repository.UserRepository;
import com.winning.devops.oauth2.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.boot.security.service.impl
 * @date: 2019-05-17 13:37
 */
@Service
public class UserServiceImpl implements IUserService {

    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Override
    public User create(User user) {
        String hash = ENCODER.encode(user.getPassword());
        user.setPassword(hash);
        return userRepository.save(user);
    }
}
