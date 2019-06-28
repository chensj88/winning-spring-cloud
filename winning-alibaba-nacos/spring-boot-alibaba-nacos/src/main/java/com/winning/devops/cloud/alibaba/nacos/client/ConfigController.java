package com.winning.devops.cloud.alibaba.nacos.client;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.cloud.alibaba.nacos.client
 * @date 2019-06-28 22:26
 * 通过调用 Nacos Open API 向 Nacos server 发布配置：dataId 为example，内容为useLocalCache=true
 * curl -X POST "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=example&group=DEFAULT_GROUP&content=useLocalCache=true"
 */
@Controller
@RequestMapping("config")
public class ConfigController {
    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;
    @NacosValue(value = "${spring.nacos.test:'这是一个测试'}", autoRefreshed = true)
    private String test;
    @NacosValue(value = "${spring.datasource.type:'oracle'}", autoRefreshed = true)
    private String type;
    @NacosValue(value = "${spring.datasource.drive-class-name:'这是一个测试'}", autoRefreshed = true)
    private String driverClassName;
    @NacosValue(value = "${spring.datasource.url:'这是一个测试'}", autoRefreshed = true)
    private String url;
    @NacosValue(value = "${spring.datasource.username:'这是一个测试'}", autoRefreshed = true)
    private String username;
    @NacosValue(value = "${spring.datasource.password:'这是一个测试'}", autoRefreshed = true)
    private String password;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Map get() {
        Map<String,Object> param = new HashMap<>();
        param.put("useLocalCache",useLocalCache);
        param.put("test",test);
        param.put("type",type);
        param.put("driverClassName",driverClassName);
        param.put("url",url);
        param.put("username",username);
        param.put("password",password);
        return param;
    }

}
