package com.winning.devops.oauth2.server.service;

import com.winning.devops.oauth2.server.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.boot.security.service
 * @date: 2019-05-17 13:36
 */
public interface IUserService extends UserDetailsService {
    User create(User user);
}
