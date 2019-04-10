package com.winning.devops.boot.jenkins;

import com.offbytwo.jenkins.JenkinsServer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.boot.jenkins
 * @date: 2019-04-10 16:50
 */
@Configuration
@PropertySource("classpath:jenkins.properties")
@ConfigurationProperties(prefix = "jenkins")
public class JenkinsConfig {

    private String url;
    private String username;
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
    @Bean
    public JenkinsServer initJenkinsServer() throws URISyntaxException {
        JenkinsServer jenkins = new JenkinsServer(new URI(this.url), this.username, this.password);
        return jenkins;
    }
}
