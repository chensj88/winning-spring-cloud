package com.winning.devops.boot.jpa.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author chensj
 * @title 用户
 * @project winning-spring-cloud
 * @package com.winning.devops.boot.base.model
 * @date: 2019-04-10 11:09
 */
@Entity
@Table(name = "fd_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * nullable 不允许为空
     * unique 唯一性
     * length 字段长度
     */
    @Column(nullable = false,unique = true,length = 64)
    private String username;
    @Column(length = 128)
    private String password;
}
