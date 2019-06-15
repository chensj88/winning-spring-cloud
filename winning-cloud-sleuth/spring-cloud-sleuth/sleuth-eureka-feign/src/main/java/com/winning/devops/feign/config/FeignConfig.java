package com.winning.devops.feign.config;

import feign.Feign;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author chensj
 * @date 2019-06-15 14:00
 */
@Configuration
public class FeignConfig {
    /**
     * Feign 在远程调用失败后会进行重试。
     * @return Retryer
     */
    @Bean
    public Retryer feignRetryer(){
        return new Retryer.Default(100,SECONDS.toMillis(1),5);
    }

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder(){
        return Feign.builder();
    }
}
