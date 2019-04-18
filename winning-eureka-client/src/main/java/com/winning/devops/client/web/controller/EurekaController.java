package com.winning.devops.client.web.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chensj
 * @title 使用EurekaClient
 * @project winning-eureka-client
 * @package com.winning.devops.client.web.controller
 * @date: 2019-04-18 21:44
 */
@RestController
public class EurekaController {

    private static final Logger logger = LoggerFactory.getLogger(EurekaController.class);

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private EurekaClient eurekaClient;
    @Autowired
    private EurekaDiscoveryClient eurekaDiscoveryClient;

    @PostMapping(value = "service")
    public String serviceUrl() {
        Set<String> instanceIds = new HashSet<>(10);
        List<String> services = discoveryClient.getServices();
        logger.info("Services======================================================>");
        for (String serviceId : services) {
            logger.info("Services:{}", serviceId);
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceId);
            for (ServiceInstance service : serviceInstances) {
                instanceIds.add(service.getInstanceId());
                logger.info("InstanceId:{},Host:{},Port:{},Scheme:{},Uri:{}", service.getInstanceId(), service.getHost(), service.getPort(), service.getScheme(), service.getUri());
                for (String s : service.getMetadata().keySet()) {
                    logger.info("InstanceInfo Metadata:{}:{}", s, service.getMetadata().get(s));
                }
                System.out.println();
            }
        }
        logger.info("Services<======================================================");
        logger.info("InstanceInfo======================================================>");
        for (String instanceId : instanceIds) {
            List<InstanceInfo> instances= eurekaClient.getInstancesById(instanceId);
            for (InstanceInfo instance : instances) {
                logger.info("AppName:{},HostName:{},Port:{},LastUpdatedTimestamp:{},LeaseInfo:{}",instance.getAppName(),instance.getHostName(),instance.getPort(),instance.getLastUpdatedTimestamp(),instance.getLeaseInfo());
            for (String s : instance.getMetadata().keySet()) {
                logger.info("InstanceInfo Metadata:{}:{}",s,instance.getMetadata().get(s));
            }
            }
        }
        logger.info("InstanceInfo<======================================================");

        return services.get(0);
    }

    @PostMapping(value = "eureka")
    public void eureka() {
        List<String> services = eurekaDiscoveryClient.getServices();
        logger.info("Services======================================================>");
        for (String serviceId : services) {
            List<ServiceInstance> serviceInstances = eurekaDiscoveryClient.getInstances(serviceId);
            for (ServiceInstance service : serviceInstances) {
                logger.info("InstanceId:{},Host:{},Port:{},Scheme:{},Uri:{}", service.getInstanceId(), service.getHost(), service.getPort(), service.getScheme(), service.getUri());
                for (String s : service.getMetadata().keySet()) {
                    logger.info("InstanceInfo Metadata:{}:{}", s, service.getMetadata().get(s));
                }
                System.out.println();
            }
        }
    }

}
