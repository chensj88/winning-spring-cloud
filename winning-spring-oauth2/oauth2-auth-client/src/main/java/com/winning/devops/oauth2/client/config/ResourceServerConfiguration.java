package com.winning.devops.oauth2.client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author chensj
 * @title 添加EnableResourceServer注解开启资源服务的功能，
 * 加注解EnableGlobalMethodSecurity开户方法级别的保护，
 * ResourceServerConfigurerAdapter是配置类，configure(HttpSecurity http)中只配置了"/order/**"需要验证。
 *
 * @project winning-spring-cloud
 * @package com.winning.devops.oauth2.client.config
 * @date: 2019-05-23 23:35
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    /**
     * 配置http验证规则
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 认证表达式
        http.authorizeRequests()
                // ant表达式 配置
                .antMatchers("/users/register")
                // 无需验证
                .permitAll()
                .anyRequest()
                .authenticated();
    }
}
