package com.winning.devops.boot.jpa.dao;

import com.winning.devops.boot.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chensj
 * @title 用户DAO
 * @project winning-spring-cloud
 * @package com.winning.devops.boot.base.dao
 * @date: 2019-04-10 11:14
 */
public interface UserDao extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
