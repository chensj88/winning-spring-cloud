package com.winning.devops.oauth2.server.repository;

import com.winning.devops.oauth2.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

/**
 * @author chensj
 * @title
 * @project winning-cloud-dalston
 * @package com.winning.devops.boot.security.repository
 * @date: 2019-05-17 13:32
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    UserDetails findByUsername(String username);
}
