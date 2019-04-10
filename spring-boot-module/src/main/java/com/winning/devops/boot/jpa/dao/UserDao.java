package com.winning.devops.boot.jpa.dao;

import com.winning.devops.boot.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author chensj
 * @title 用户DAO
 * @project winning-spring-cloud
 * @package com.winning.devops.boot.base.dao
 * @date: 2019-04-10 11:14
 */
@Repository
public interface UserDao extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
