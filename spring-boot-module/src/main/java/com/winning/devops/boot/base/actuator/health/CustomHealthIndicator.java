package com.winning.devops.boot.base.actuator.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * @author chensj
 * @title 自定义的健康指标
 * @project winning-spring-cloud
 * @package com.winning.devops.boot.base.actuator.health
 * @date: 2019-04-09 23:24
 */
@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.up().withDetail("app","spring boot demo").withDetail("author","chensj");
    }
}
