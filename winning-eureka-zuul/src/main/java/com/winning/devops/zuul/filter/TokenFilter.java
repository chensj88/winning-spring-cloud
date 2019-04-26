package com.winning.devops.zuul.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.winning.devops.zuul.base.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author chensj
 * @title Zuul 自定义拦截器
 * @project winning-spring-cloud
 * @package com.winning.devops.zuul.filter
 * @date: 2019-04-26 23:56
 */
@Component
public class TokenFilter extends ZuulFilter {

    /**logger*/
    private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);

    /**
     * filter Type
     * 过滤器的类型
     * @return PRE_TYPE
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 过滤顺序
     * 值越小，越早执行该过滤器
     * @return
     */
    @Override
        public int filterOrder() {
        return 0;
    }

    /**
     * 过滤器是否执行过滤逻辑
     * 如果为true,则执行run()方法;
     * 如果为 false ，则不执行run()方法。
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 具体的过滤的逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Object accessToken = request.getParameter(Constants.TOKEN_FLAG);
        if(accessToken == null){
            logger.warn("Token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(404);
            try {
                Map<String,Object> resultMap = new HashMap<>(5);
                resultMap.put("msg","Token is empty!");
                resultMap.put("status", Constants.FAIL);
                ctx.getResponse().getWriter().write(JSON.toJSONString(resultMap));
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
            return  null;
        }
        logger.info("Validate Token is OK!");
        return null;
    }
}
