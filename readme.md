# Spring Cloud

## 常用注解

* `@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)`
  * 为Web 测试环境的端口为随机端口的配置
  
* `@ConfigurationProperties`
  * 设置属性的信息 如`prefix`就是前缀
  
* `@PathVariable`
  * 可以获取`RESTful`风格的`Uri`路径上的参数。
  
* `@EnableEurekaServer`
  * 开启Eureka Server
  
* `@EnableEurekaClient`
  * 开启Eureka Client
  
* `@EnableDiscoveryClient`
  * 开启客户端注册
  > spring cloud中discovery service有许多种实现（eureka、consul、zookeeper等等），
  > `@EnableDiscoveryClient`基于`spring-cloud-commons`, 
  > `@EnableEurekaClient`基于`spring-cloud-netflix`。
  > 其实用更简单的话来说，就是如果选用的注册中心是`eureka`，那么就推荐`@EnableEurekaClient`，
  > 如果是其他的注册中心，那么推荐使用`@EnableDiscoveryClient`。
   
   
## 坑点

### eureka client 使用如下配置时，启动后会自动关闭，在spring cloud Finchley.RELEASE、Greenwich.RELEASE都有问题
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```  
必须添加如下的引用，才不会启动后关闭
```xml
 <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```