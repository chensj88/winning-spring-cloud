# Spring Cloud Zuul

Zuul作为路由网关组件，在微服务架构中有着非常重要的作用，主要体现在以下6个方面。 

- Zuul Ribbon以及Eureka相结合，可以实现智能路由和负载均衡的功能， Zuul能够 将请求流量按某种策略分发到集群状态的多个服务实例。 
- 网关将所有服务的API接口统一聚合，并统一对外暴露。外界系统调用API接口时， 都是由网关对外暴露的API 接口，外界系统不需要知道微服务系统中各服务相互调用的复杂性。微服务系统也保护了其内部微服务单元的 API 接口 防止其被外界直接调用，导致服务的敏感信息对外暴露。 
- 网关服务可以做用户身份认证和权限认证，防止非法请求操作API接口，对服务器起到保护作用。
- 网关可以实现监控功能，实时日志输出，对请求进行记录。
- 网关可以用来实现流量监控 在高流量的情况下，对服务进行降级。
- API接口从内部服务分离出来，方便做测试。 

## Zuul架构图

![](https://images2015.cnblogs.com/blog/486074/201702/486074-20170220185335288-1703224333.png)

Zuul是通过Servlet来实现的，Zuul通过自定义的Zuu!Servlet(类似于 Spring MVC DispatcServlet)来对请求进行控制。 Zuul的核心是一些系列过滤器，可以在Http 请求的发起和响应返回期间执行一系列的过滤器。 Zuul种包括以下4种过滤器 ：

- `PRE`过滤器：它是在请求路由到具体的服务之前执行的，这种类型的过滤器可以做安全验证，例如身份验证、 参数验证等。 

- `ROUTING`过滤器 它用于将请求路由到具体的微服务 。在默认情况下，它使用`Http Client`进行网络请求。 

- `POST`过滤器：它是在请求己被路由到微服务后执行的一般情况下，用作收集统计信息、指标，以及将响应传输到客户端 

- `ERROR`过滤器：它是在其他过滤器发生错误时执行的

  

## Zuul请求的生命周期

如图，该图详细描述了各种类型的过滤器的执行顺序。

![](https://images2015.cnblogs.com/blog/1099841/201706/1099841-20170630111344414-1260445909.png)

​		Zuul采取了动态读取、编译和运行这些过滤器。过滤器之间不能直接通信，而是通过RequestContext对象来共享数据，每个请求都会创建一个RequestContext对象。Zuul过滤器具有以下关键特性。 

- Type (类型):  Zuul 过滤器的类型，这个类型决定了过滤器在请求的哪个阶段起作用， 例如Pre、Post 阶段等。 
- Execution Order(执行顺序)：规定了过滤器的执行顺序， Order 的值越小，越先执行 
- Criteria(标准):  Filter执行所需的条件
- Action(行动):  如果符合执行条件，则执行action()即逻辑代码)

Zuul的核心是`ZuulServlet`,下面是service方法的代码：

```java
public class ZuulServlet extends HttpServlet {
    // service方法
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        try {
            this.init((HttpServletRequest)servletRequest, (HttpServletResponse)servletResponse);
            RequestContext context = RequestContext.getCurrentContext();
            context.setZuulEngineRan();
    
            try {
                // PRE过滤器
                this.preRoute();
            } catch (ZuulException var13) {
                // ERROR过滤器
                this.error(var13);
                // POST过滤器
                this.postRoute();
                return;
            }
    
            try {
                // ROUTING过滤器
                this.route();
            } catch (ZuulException var12) {
                this.error(var12);
                this.postRoute();
                return;
            }
    
            try {
                //POST过滤器
                this.postRoute();
            } catch (ZuulException var11) {
                this.error(var11);
            }
        } catch (Throwable var14) {
            this.error(new ZuulException(var14, 500, "UNHANDLED_EXCEPTION_" + var14.getClass().getName()));
        } finally {
            RequestContext.getCurrentContext().unset();
        }
    }
}

```

## 路由配置

```yaml
zuul:
  routes:
    # http://host:port/hiappi/
    hiapi:
      path: /hiapi/**
      serviceId: eureka-client
    # http://host:port/ribbonapi/
    ribbonapi:
      path: /ribbonapi/**
      serviceId: eureka-ribbon
    # http://host:port/feignapi/
    feignapi:
      path: /feignapi/**
      serviceId: eureka-feign
```

  在Zuul中在路由转发做了负载均衡，如果不需要做负载均衡，可以指定服务实例的URL，上面的路由配置修改为如下
```yaml
zuul:
  routes:
    # http://host:port/hiappi/
    hiapi:
      path: /hiapi/**
      url: http://localhost:8762
```

  一旦指定了URL，Zuul就不能做负载均衡，而是直接访问指定的URL，但是这种方法不是很可取，正常的修改方法是如下的

```yaml
zuul:
  routes:
    # http://host:port/hiappi/
    hiapi:
      path: /hiapi/**
      serviceId: hiapi-v1
# Ribbon的负载均衡客户端不向Eureka Client获取服务注册信息
# TODO 但是需要自己维护一份注册列表，这个注册列表对应的服务名是`hiapi-v1`(名称可以自定义)
ribbon:
  eureka:
    enabled: false

# 自定义服务注册列表
hiapi-v1:
  ribbon:
    listOfServers: http://localhost:8762,http://localhost:8763

```

这样既可以实现自定义的服务端的负载均衡

## 版本号


  如果想给每一个服务API接口家前缀，例如http://localhost:8500/v1/hiapi/hi?username=admin,即在所有的API接口上加上一个vl作为版本号。 
这时需要用到`zuul.prefix`的配置

```yaml
zuul:
  routes:
    hiapi:
      path: /hiapi/**
      serviceId: eureka-client
    ribbonapi:
      path: /ribbonapi/**
      serviceId: eureka-ribbon
    feignapi:
      path: /feignapi/**
      serviceId: eureka-feign
  # 版本号 访问的地址就是 http://localhost:8500/v1/hiapi/hi/admin
  prefix: /v1
```

## 熔断器

  Zuul作为Netflix组件，可以与Ribbon、Eureka、Hystrix等组件相结合，实现负载均衡、熔断器的功能。在默认情况下，Zuul和Ribbon结合实现了负载均衡的功能。
使用熔断器的话需要自行配置。

  在Zuul实现熔断功能需要实现`ZuulFallbackProvider`(1.x)或者`FallbackProvider`(2.x)接口。实现该接口有两个方法，`getRoute()`方法，用于指定熔断功能应用于哪些路由的服务;
另外一个个方法`fallbackResponse()`为进入熔断功能时执行的逻辑。

```java
public interface FallbackProvider {
    String getRoute();
    ClientHttpResponse fallbackResponse(String route, Throwable cause);
}
```

### 单服务熔断器

针对eureka-feign服务的熔断器，在eureka-feign请求服务出现故障的时候，进入熔断器，输出错误提示

```java
@Component
public class FeignApiHystrixFallbackProvider implements FallbackProvider {
    @Override
    public String getRoute() {
        return "feignapi";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if (cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return response(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ClientHttpResponse response(final HttpStatus status) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return status;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return status.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return status.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("Feign Api fallback".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
```

修改`application.yml`

```yaml
#熔断机制超时
hystrix:
  command:
    # 与routes中对应，服务名称
    feignapi:
      excution:
        timeout:
          enabled: true
        isolation:
          thread:
            timeout-in-milliseconds: 60000
# 配置失效时间
ribbon:
  ReadTimeout: 60000
  ConnectTimeout: 60000
```

### 默认熔断器

```java
@Component
public class DefaultHystrixFallbackProvider implements FallbackProvider {
    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        if (cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return response(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ClientHttpResponse response(final HttpStatus status) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return status;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return status.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return status.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException {
                Map<String,Object> resultMap = new HashMap<>(5);
                resultMap.put("msg","Sorry, It's Error!");
                resultMap.put("status", Constants.FAIL);
                return new ByteArrayInputStream(JSON.toJSONString(resultMap).getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
```

修改`application.yml`

```yaml
#熔断机制超时
hystrix:
  command:
    default:
      excution:
        timeout:
          enabled: true
        isolation:
          thread:
            timeout-in-milliseconds: 60000
# 配置失效时间
ribbon:
  ReadTimeout: 60000
  ConnectTimeout: 60000
```

### 坑点

在访问服务的时候，如果服务不可访问，Zuul会报错，这个需要通过设置如下参数来修改

```yaml
zuul:
  routes:
    hiapi:
      path: /hiapi/**
      serviceId: eureka-client
    ribbonapi:
      path: /ribbonapi/**
      serviceId: eureka-ribbon
    feignapi:
      path: /feignapi/**
      serviceId: eureka-feign
  # 版本号 访问的地址就是 http://localhost:8500/v1/hiapi/hi/admin
  prefix: /v1
  # TODO 报错解决 设置超时时间
  host:
    connect-timeout-millis: 60000
    socket-timeout-millis: 60000
    max-total-connections: 500
#熔断机制超时
hystrix:
  command:
    default:
      excution:
        timeout:
          enabled: true
        isolation:
          thread:
            timeout-in-milliseconds: 60000
#  TODO 报错解决 配置失效时间
ribbon:
  ReadTimeout: 60000
  ConnectTimeout: 60000
```

## 过滤器

  自定义过滤器，用于一些验证，比如token等，实现方法比较简单，只需要继承`ZuulFilter`即可，方法说明：

  * `filterType`  过滤器的类型 PRE/POST/ROUTING/ERROR
  * `filterOrder` 过滤顺序 值越小，越早执行该过滤器
  * `shouldFilter`  过滤器是否执行过滤逻辑

      * 如果为true,则执行run()方法
      * 如果为 false ，则不执行run()方法
* `run()`  具体的过滤的逻辑

下面是一个TokenFilter，用于过滤Token

```java
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
```

## Zuul 的常见使用方式

​        Zuul 是采用了类似于Spring MVC的DispatchServlet 来实现的，采用的是异步阻塞模型，所以性能比Ngnix差。由于Zuul和其他Netflix组件可以相互配合、无缝集成，Zuul 很容易就能够实现负载均衡、智能路由和熔断器等功能。在大多数情况下Zuul都是以集群的形式存在的。由于Zuul的横向扩展能力非常好，所以当负载过高时，可以通过添加实例来解决性瓶颈。

​        一种常见的使用方式是对不同的渠道使用不同的Zuul来进行路由，例如移动端共用一个Zuul网关实例，Web 端用另一个Zuul网关实例，其他的客户端用另外一个Zuul实例进行路由。这种不同的渠边用不同Zuul 实例的架构如图示。

![](https://img2018.cnblogs.com/blog/285763/201809/285763-20180927204405067-1796559497.png)

​		另外一种种常见的集群是通过 Ngnix和Zuul相互结合来做负载均衡。暴露在最外面的是Ngnix从双热备进行 Keepalive, Ngnix 经过某种路由策略，将请求路由转发到 Zuul 集群上，最终将请求分发到具体的服务上。

![](https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556308257513&di=51637887563418ee53f3e9bc472e5ec8&imgtype=0&src=http%3A%2F%2Fyun.itheima.com%2FUpload%2FImages%2F20171228%2F1514442541668414.jpg)

