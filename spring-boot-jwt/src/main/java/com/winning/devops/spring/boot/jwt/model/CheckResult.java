package com.winning.devops.spring.boot.jwt.model;

import io.jsonwebtoken.Claims;
import lombok.Data;

/**
 * @author chensj
 * @date 2019-05-29 17:15
 */
@Data
public class CheckResult {

    private Claims claims;
    private boolean success;
    private String errorCode;

}
