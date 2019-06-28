package com.winning.devops.cloud.alibaba.nacos.client;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chensj
 * @title
 * @project winning-spring-cloud
 * @package com.winning.devops.cloud.alibaba.nacos.client
 * @date 2019-06-28 22:12
 * @NacosPropertySource(dataId = "example", autoRefreshed = true)
 * 使用 @NacosPropertySource 加载 dataId 为 example 的配置源，并开启自动更新
 */
@SpringBootApplication
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class SpringBootAlibabaNacosApplication {
    public static void main(String[] args){
       SpringApplication.run(SpringBootAlibabaNacosApplication.class,args);
    }
}
