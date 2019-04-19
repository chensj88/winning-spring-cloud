# Eureka 注册中心

## 单例注册中心
```yaml
server:
  # 端口
  port: 8000
eureka:
  instance:
    hostname: localhost
  client:
    # 本身为注册中心，不需要去检索服务信息
    register-with-eureka: false
    # 本身为注册中心，是否需要在注册中心注册，默认true  集群设置为true
    fetch-registry: false
    # 注册地址
    service-url:
      # 默认访问地址
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka
spring:
  application:
    name: eureka-server
  devtools:
    restart:
      enabled: true
      additional-paths: src/main/java

```

## 集群

### peer1

```yaml
# peer1 
server:
  # 端口
  port: 8000
eureka:
  instance:
    hostname: peer1
  client:
    # 本身为注册中心，不需要去检索服务信息
    register-with-eureka: false
    # 本身为注册中心，是否需要在注册中心注册，默认true  集群设置为true
    fetch-registry: true
    # 注册地址
    service-url:
      # 默认访问地址
      defaultZone: http://peer2:8001/eureka
spring:
  application:
    name: eureka-server
  devtools:
    restart:
      enabled: true
      additional-paths: src/main/java

```

### peer2

```yaml
# peer2 
server:
  # 端口
  port: 8000
eureka:
  instance:
    hostname: peer1
  client:
    # 本身为注册中心，不需要去检索服务信息
    register-with-eureka: false
    # 本身为注册中心，是否需要在注册中心注册，默认true  集群设置为true
    fetch-registry: true
    # 注册地址
    service-url:
      # 默认访问地址
      defaultZone: http://peer1:8000/eureka
spring:
  application:
    name: eureka-server
  devtools:
    restart:
      enabled: true
      additional-paths: src/main/java

```

## 高可用注册中心

### *application.yml*
```yaml
eureka:
  client:
    service-url:
      # 默认访问地址
      defaultZone: http://peer1:8000/eureka,http://peer2:8001/eureka,http://peer3:8002/eureka
```

### *application-peer1.yml*

```yaml
server:
  # 端口
  port: 8000
eureka:
  instance:
    hostname: peer1
  client:
    # 本身为注册中心，不需要去检索服务信息
    register-with-eureka: false
    # 本身为注册中心，是否需要在注册中心注册，默认true  集群设置为true
    fetch-registry: true
  dashboard:
    path: /eureka/server
spring:
  application:
    name: eureka-server-peer1
  devtools:
    restart:
      enabled: true
      additional-paths: src/main/java

```

> `peer2`与`peer3` 只需要将上面的*`peer1`* 替换即可