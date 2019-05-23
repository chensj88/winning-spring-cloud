package com.winning.devops.oauth2.client.entity;

import lombok.Data;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.oauth2.client.entity
 * @date: 2019-05-23 23:52
 */
@Data
public class User {
    private Long id;
    private String username;
    private String password;
}
