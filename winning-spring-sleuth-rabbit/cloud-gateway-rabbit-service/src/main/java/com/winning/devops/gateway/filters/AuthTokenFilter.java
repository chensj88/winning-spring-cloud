package com.winning.devops.gateway.filters;

import brave.Span;
import brave.Tracer;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author chensj
 * @title 向链路中添加数据
 * @project winning-spring-cloud
 * @package com.winning.devops.gateway.filters
 * @date: 2019-04-30 9:33
 */
@Component
public class AuthTokenFilter extends ZuulFilter {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
    @Autowired
    private Tracer tracer;
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 900;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        Span currentSpan = this.tracer.currentSpan();
        // 向当前的span中添加tag
        currentSpan.tag("operator","chensj");
        // 输出 TraceId
        logger.info(this.tracer.currentSpan().context().traceIdString());
        return null;
    }
}
