package com.winning.devops.spring.boot.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author chensj
 * @date 2019-05-29 15:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jwt_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name",nullable = false,unique = true)
    private String username;
    @Column(name = "password",nullable = false)
    private String password;
}
