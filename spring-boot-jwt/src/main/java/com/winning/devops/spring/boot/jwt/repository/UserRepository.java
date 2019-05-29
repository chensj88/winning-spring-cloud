package com.winning.devops.spring.boot.jwt.repository;

import com.winning.devops.spring.boot.jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chensj
 * @date 2019-05-29 16:26
 */
public interface UserRepository extends JpaRepository<User, Long>{
    /**
     * 根据ID获取用户
     * @param id
     * @return
     */
    User findUserById(Long id);

    /**
     *  根据username获取用户
     * @param username
     * @return
     */
    User findUserByUsername(String username);
}
