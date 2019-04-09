package com.winning.devops.boot.base.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author chensj
 * @title 加载自定义properties文件
 * @project winning-spring-cloud
 * @package com.winning.devops.boot.base.config
 * @date: 2019-04-09 23:02
 */
@Configuration
@PropertySource(value = "classpath:ftp.properties")
@ConfigurationProperties(prefix = "ftp")
@Component
public class FtpProperties {
    private String server;
    private Integer port;
    private String username;
    private String password;
    private String share;

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    @Override
    public String toString() {
        return "FtpProperties{" +
                "server='" + server + '\'' +
                ", port=" + port +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", share='" + share + '\'' +
                '}';
    }
}
