package com.winning.devops.spring.boot.jwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.winning.devops.spring.boot.jwt.model.User;
import org.springframework.stereotype.Service;

/**
 * @author chensj
 * @date 2019-05-29 16:32
 */
@Service
public class TokenService {
    /**
     * 获取用户Token
     * @param user
     * @return
     */
    public String getToken(User user){
        String token;
        token= JWT.create()
                // 存入需要保存在token的信息
                .withAudience(user.getId()+"")
                // 使用HS256生成token,密钥则是用户的密码，唯一密钥的话可以保存在服务端。
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
