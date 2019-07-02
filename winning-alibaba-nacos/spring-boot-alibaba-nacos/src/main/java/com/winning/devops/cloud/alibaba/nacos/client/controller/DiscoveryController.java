package com.winning.devops.cloud.alibaba.nacos.client.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author chensj
 * @title 使用 @NacosInjected 注入 Nacos 的 NamingService 实例
 * @project winning-spring-cloud
 * @package com.winning.devops.cloud.alibaba.nacos.client
 * @date 2019-06-28 22:45
 * curl http://localhost:8300/discovery/get?serviceName=example，此时返回为空 JSON 数组[]
 * 通过调用 Nacos Open API 向 Nacos server 注册一个名称为 example 服务
 * curl -X PUT 'http://127.0.0.1:8848/nacos/v1/ns/instance?serviceName=example&ip=192.168.31.59&port=8300'
 * 再次访问 curl http://localhost:8300/discovery/get?serviceName=example
 * [
 *   {
 *     "instanceId": "192.168.31.59-8300-DEFAULT-example",
 *     "ip": "192.168.31.59",
 *     "port": 8300,
 *     "weight": 1.0,
 *     "healthy": true,
 *     "cluster": {
 *       "serviceName": null,
 *       "name": "",
 *       "healthChecker": {
 *         "type": "TCP"
 *       },
 *       "defaultPort": 80,
 *       "defaultCheckPort": 80,
 *       "useIPPort4Check": true,
 *       "metadata": {}
 *     },
 *     "service": null,
 *     "metadata": {}
 *   }
 * ]
 *
 */
@Controller
@RequestMapping("discovery")
public class DiscoveryController {

    @NacosInjected
    private NamingService namingService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public List<Instance> get(@RequestParam String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }
}
