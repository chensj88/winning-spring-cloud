package com.winning.devops.oauth2.client.config;

import feign.RequestInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

/**
 * @author chensj
 * @title 注解EnableOAuth2Client开启了OAuth2 Client功能，注入一个OAuth2RestTemplate 类型的Bean用于向service-auth服务请求。
 * @project winning-spring-cloud
 * @package com.winning.devops.oauth2.client.config
 * @date: 2019-05-23 23:36
 */
@EnableOAuth2Client
@EnableConfigurationProperties
@Configuration
public class Oauth2ClientConfig {

    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
    public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
        return new ClientCredentialsResourceDetails();
    }

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), clientCredentialsResourceDetails());
    }

    /**
     * 注入一个OAuth2RestTemplate 类型的Bean用于向service-auth服务请求。
     * @return
     */
    @Bean
    public OAuth2RestTemplate clientCredentialsRestTemplate() {
        return new OAuth2RestTemplate(clientCredentialsResourceDetails());
    }
}
