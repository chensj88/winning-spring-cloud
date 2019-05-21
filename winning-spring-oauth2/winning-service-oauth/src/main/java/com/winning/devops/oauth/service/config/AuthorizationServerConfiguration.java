package com.winning.devops.oauth.service.config;

import com.winning.devops.oauth.service.customImpl.MyRedisTokenStore;
import com.winning.devops.oauth.service.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.oauth.service.config
 * @date: 2019-05-21 17:09
 */
@Configurable
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    /**
     * 用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息；
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");

        // 配置两个客户端，一个用于password认证一个用于client认证
        clients.inMemory().withClient("client_1")
                .resourceIds(Utils.RESOURCEIDS.ORDER)
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("select")
                .authorities("oauth2")
                .secret(finalSecret)
                .and().withClient("client_2")
                .resourceIds(Utils.RESOURCEIDS.ORDER)
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("server")
                .authorities("oauth2")
                .secret(finalSecret);
    }

    /**
     * 用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)，
     * 还有token的存储方式(tokenStore)；
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(new MyRedisTokenStore(redisConnectionFactory))
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    /**
     * 用来配置令牌端点(Token Endpoint)的安全约束；
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许表单认证
        security.allowFormAuthenticationForClients();
    }
}
