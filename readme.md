# Spring Cloud

## 一、常用注解

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

* `@Import(EurekaServerInitializerConfiguration.class)`

  * 将标记的class注入到spring IOC容器中

  * 只能注解在类上，以及唯一的参数value上可以配置3种类型的值Configuration，ImportSelector，ImportBeanDefinitionRegistrar

    * **基于Configuration也就是直接填对应的class数组**

      ```java 
      @Import({Square.class,Circular.class})
      ```

    * **基于自定义ImportSelector的使用**

      ```java
      /**
       * 定义一个我自己的ImportSelector
       *
       * @author zhangqh
       * @date 2018年5月1日
       */
      public class MyImportSelector implements  ImportSelector{
          public String[] selectImports(AnnotationMetadata importingClassMetadata) {
              return new String[]{"com.zhang.bean.Triangle"};
          }
      }
      
      // 使用@Import
      @Import({Square.class,Circular.class,MyImportSelector.class})
      ```

    * **基于ImportBeanDefinitionRegistrar的使用**

    ```java
    /**
     * 定义一个自定的ImportBeanDefinitionRegistrar
     *
     * @author zhangqh
     * @date 2018年5月1日
     */
    public class MyImportBeanDefinitionRegistrar  implements ImportBeanDefinitionRegistrar{
        public void registerBeanDefinitions(
                AnnotationMetadata importingClassMetadata,
                BeanDefinitionRegistry registry) {
            // new一个RootBeanDefinition
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Rectangle.class);
            // 注册一个名字叫rectangle的bean
            registry.registerBeanDefinition("rectangle", rootBeanDefinition);
        }
    }
    
    // 使用@Import
    @Import({Square.class,Circular.class,MyImportSelector.class,MyImportBeanDefinitionRegistrar.class})
    ```

    
## 二、坑点

### 1、`eureka client` 使用如下配置时，启动后会自动关闭

在spring cloud `Finchley.RELEASE`、`Greenwich.RELEASE`都有问题

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





## 三、Eureka

### 3.1 Eureka概念

#### （1）Register-服务注册

​	当Eureka Client向Eureka Server注册时，Eureka Client 提供自身的元数据，比如 IP 地址、端口、运行状况H1标的 Uri 主页地址等信息。

#### （2）Renew-服务续约

​	Eureka CLIent 在默认的情况下会每隔 30 秒发送一次心跳来进行服务续约。通过服务续约来告知Eureka Server该Eureka Client 仍然可用，没有出现故障。正常情况下，如果Eureka Server在90 秒内没有收到 ureka Client 的心跳， Eureka Server 会将 Eureka Client 实例从注册列表中删除。注意：官网建议不要更改服务续约的间隔时间。

#### （3）Fetch Registries-获取服务注册列表信息

​	Eureka Client从Eureka Server 获取服务注册表信息，井将其缓存在本地。Eureka Client会
使用服务注册列表信息查找其他服务的信息，从而进行远程调用。该注册列表信息定时（每30 秒） 更新一次，每次返回注册列表信息可能与 Eureka Client 的缓存信息不同，Eureka Client会自己处理这些信息。如果由于某种原因导致注册列表信息不能及时匹配，Eureka Client 会重新获取整个注册表信息。 Eureka Server 缓存了所有的服务注册列表信息，并将整个注册列表以及每个应用程序 信息进行了压缩，压缩内容和没有压缩的内容完全相同。 Eureka Client和Eureka Server 可以使用 JSON和XML 数据格式进行通信。在默认的情况下， Eureka Client使用JSON 格式的方式来获取服务注册列表的信息。

####  （4）Cancel-服务下线

​	Eureka Client 在程序关闭时可以向 Eureka Server 发送下线请求。发送请求后，该客户端的
实例信息将从Eureka Server 的服务注册列表中删除。该下线请求不会自动完成，需要在程序关闭时调用以下代码：

```java
DiscoveryManager.getinstance().shutdownComponent();
```

#### （5）Eviction-服务剔除

在默认情况下，当 Eureka Client 90 秒没有向 Eureka Server 发送服务续约（即心跳〉时，Eureka Server 会将该服务实例从服务注册列表删除，即服务剔除。

### 3.2 Eureka 架构

![Eureka高可用架构](https://github.com/Netflix/eureka/raw/master/images/eureka_architecture.png)

​	在这个架构中有两个角色 ，即 Eureka Server和Eureka Client。而Eureka Client 又分为Applicaton Service和Application Client 即服务提供者和服务消费者。每个区域有一个Eureka 集群，并且每个区域至少有一个 Eureka Server 可以处理区域故障，以防服务器瘫痪。

​	Eureka Client向Eureka Server注册， 将自己的客户端信息提交给 Eureka Server。然后，Eureka Client通过向 Eureka Server 发送心跳（每 30 次）来续约服务。如果某个客户端不能持续续约，那Eureka Server 判定该客户端不可用，该不可用的客户端将在大约 90 秒后从Eureka Server服务注册列表中删除。服务注册列表信息和服务续约信息会被复制到集群中的每个Eureka Server节点。来自任何区域的Eureka Client 都可以获取整个系统的服务注册列表信息，根据这些注 列表信息，Application Client 可以远程调用Applicaton Service 来消费服务。

### 3.3 Register 服务注册

​	服务注册，即Eureka Client向Eureka Server 提交自己的服务信息，包括 IP 地址、端口、Serviceld 等信息。如果 Eureka Client 置文件中没有配置 Serviceld ，则默认为配置文件中配置的服务名 ，即`$ {spring application.name ｝`的值。

​	当Eureka Client 启动时，会将自身 的服务信息发送到 Eureka Server 这个过程其实非常简单，现在来从源码的角度分析服务注册的过程。在Maven的依赖包下，找到`eureka-client-1.9.8.jar`包。在`com.netflix.discovery`包下有`DiscoveryClient`类，该类包含了
Eureka Client向Eureka Server注册的相关方法。其中，`DiscoveryClient`实现了`EurekaClient`
并且它是单例模式(`@Singleton`)，而 `EurekaClient`继承了`LookupService`接口之间的关系:

![1554910343477](C:\Users\chensj\AppData\Roaming\Typora\typora-user-images\1554910343477.png)

在`DiscoveryClient`类中有 个服务注册的方法`register()`， 该方法Http请求`Eureka Server`注册，代码如下：

```java
boolean register() throws Throwable {
        logger.info(PREFIX + "{}: registering service...", appPathIdentifier);
        EurekaHttpResponse<Void> httpResponse;
        try {
            httpResponse = eurekaTransport.registrationClient.register(instanceInfo);
        } catch (Exception e) {
            logger.warn(PREFIX + "{} - registration failed {}", appPathIdentifier, e.getMessage(), e);
            throw e;
        }
        if (logger.isInfoEnabled()) {
            logger.info(PREFIX + "{} - registration status: {}", appPathIdentifier, httpResponse.getStatusCode());
        }
        return httpResponse.getStatusCode() == Status.NO_CONTENT.getStatusCode();
    }

```

这个方法的调用则是来至于`InstanceInfoReplicator`,该类实现了`Runnable`接口，在`run()`方法中调用了`DiscoveryClient.register()`

```java
 public void run() {
        try {
            discoveryClient.refreshInstanceInfo();

            Long dirtyTimestamp = instanceInfo.isDirtyWithTime();
            if (dirtyTimestamp != null) {
                discoveryClient.register();
                instanceInfo.unsetIsDirty(dirtyTimestamp);
            }
        } catch (Throwable t) {
            logger.warn("There was a problem with the instance info replicator", t);
        } finally {
            Future next = scheduler.schedule(this, replicationIntervalSeconds, TimeUnit.SECONDS);
            scheduledPeriodicRef.set(next);
        }
    }
```

而`InstancelnfoReplicator`类是在`DiscoveryClient`初始化过程中使用的， 其中有一个`initScheduledTasks()` 方法，该方法主要开启了 取服务注册列表的信息。如果需要向Eureka Server注册，则开启注册，同时开启定时任务Eureka Server服务续约。

```java
private void initScheduledTasks() {
        // 获取服务列表，任务调度获取注册列表代码
        if (clientConfig.shouldFetchRegistry()) {
            // registry cache refresh timer
            int registryFetchIntervalSeconds = clientConfig.getRegistryFetchIntervalSeconds();
            int expBackOffBound = clientConfig.getCacheRefreshExecutorExponentialBackOffBound();
            scheduler.schedule(
                    new TimedSupervisorTask(
                            "cacheRefresh",
                            scheduler,
                            cacheRefreshExecutor,
                            registryFetchIntervalSeconds,
                            TimeUnit.SECONDS,
                            expBackOffBound,
                            new CacheRefreshThread()
                    ),
                    registryFetchIntervalSeconds, TimeUnit.SECONDS);
        }
		// 向Eureka Server注册服务
        if (clientConfig.shouldRegisterWithEureka()) {
            int renewalIntervalInSecs = instanceInfo.getLeaseInfo().getRenewalIntervalInSecs();
            int expBackOffBound = clientConfig.getHeartbeatExecutorExponentialBackOffBound();
            logger.info("Starting heartbeat executor: " + "renew interval is: {}", renewalIntervalInSecs);

            // Heartbeat timer
            scheduler.schedule(
                    new TimedSupervisorTask(
                            "heartbeat",
                            scheduler,
                            heartbeatExecutor,
                            renewalIntervalInSecs,
                            TimeUnit.SECONDS,
                            expBackOffBound,
                            new HeartbeatThread()
                    ),
                    renewalIntervalInSecs, TimeUnit.SECONDS);

            // InstanceInfo replicator
            instanceInfoReplicator = new InstanceInfoReplicator(
                    this,
                    instanceInfo,
                    clientConfig.getInstanceInfoReplicationIntervalSeconds(),
                    2); // burstSize

            statusChangeListener = new ApplicationInfoManager.StatusChangeListener() {
                @Override
                public String getId() {
                    return "statusChangeListener";
                }

                @Override
                public void notify(StatusChangeEvent statusChangeEvent) {
                    if (InstanceStatus.DOWN == statusChangeEvent.getStatus() ||
                            InstanceStatus.DOWN == statusChangeEvent.getPreviousStatus()) {
                        // log at warn level if DOWN was involved
                        logger.warn("Saw local status change event {}", statusChangeEvent);
                    } else {
                        logger.info("Saw local status change event {}", statusChangeEvent);
                    }
                    // 注册服务
                    instanceInfoReplicator.onDemandUpdate();
                }
            };

            if (clientConfig.shouldOnDemandUpdateStatusChange()) {
                applicationInfoManager.registerStatusChangeListener(statusChangeListener);
            }

            instanceInfoReplicator.start(clientConfig.getInitialInstanceInfoReplicationIntervalSeconds());
        } else {
            logger.info("Not registering with Eureka server per configuration");
        }
    }
```

​	再来跟踪 Eureka server端的代码，在Maven的 org.springframework.cloud:spring-cloud-netflix-eureka-server:2.1.0.RELEASE包下，在org.springframework.cloud.netflix.eureka.server会发现有一 个`EurekaServerBootstrap`的类，` EurekaServerBootstrap`类在程序启动时具有最先初始化的权利，代码如下

```java
protected void initEurekaServerContext() throws Exception {
		// For backward compatibility
		JsonXStream.getInstance().registerConverter(new V1AwareInstanceInfoConverter(),
				XStream.PRIORITY_VERY_HIGH);
		XmlXStream.getInstance().registerConverter(new V1AwareInstanceInfoConverter(),
				XStream.PRIORITY_VERY_HIGH);

		if (isAws(this.applicationInfoManager.getInfo())) {
			this.awsBinder = new AwsBinderDelegate(this.eurekaServerConfig,
					this.eurekaClientConfig, this.registry, this.applicationInfoManager);
			this.awsBinder.start();
		}

		EurekaServerContextHolder.initialize(this.serverContext);

		log.info("Initialized server context");

		// Copy registry from neighboring eureka node
		int registryCount = this.registry.syncUp();
    	// com.netflix.eureka.registry.PeerAwareInstanceRegistryImpl#openForTraffic
		this.registry.openForTraffic(this.applicationInfoManager, registryCount);

		// Register all monitoring statistics.
        // 注册需要监听的事件
		EurekaMonitors.registerAllStats();
	}
```

`com.netflix.eureka.registry.PeerAwareInstanceRegistryImpl#openForTraffic`,接下来将进行如下的操作：

1. 将会统计当前会续约(Renews)的客户端数(`this.expectedNumberOfClientsSendingRenews = count;`),当前为1（Eureka Server也是一个Client）

2. 更新续约的定时任务的时间(`updateRenewsPerMinThreshold();`)

3. 判断当前是否是Amazon AWS

4. 设置当前实例状态为`UP`

5. 执行`super.postInit();` `com.netflix.eureka.registry.AbstractInstanceRegistry#postInit`

   > 注意：注册客户端的方法也在这里`com.netflix.eureka.registry.AbstractInstanceRegistry#register`
   >
   > com.netflix.eureka.registry.PeerAwareInstanceRegistryImpl#register

6. 执行如下代码

```java
 protected void postInit() {
        renewsLastMin.start();
        if (evictionTaskRef.get() != null) {
            evictionTaskRef.get().cancel();
        }
        evictionTaskRef.set(new EvictionTask());
        evictionTimer.schedule(evictionTaskRef.get(),
                serverConfig.getEvictionIntervalTimerInMs(),
                serverConfig.getEvictionIntervalTimerInMs());
    }
```

7. 注册需要监听的事件

```java
EurekaMonitors.registerAllStats();
```

在`EurekaServerBootstrap`被调用的地方在`org.springframework.cloud.netflix.eureka.server.EurekaServerInitializerConfiguration#start`被调用

```java
public void start() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					//TODO: is this class even needed now?
					eurekaServerBootstrap.contextInitialized(EurekaServerInitializerConfiguration.this.servletContext);
					log.info("Started Eureka Server");
					// 监听事件
					publish(new EurekaRegistryAvailableEvent(getEurekaServerConfig()));
					EurekaServerInitializerConfiguration.this.running = true;
                    // 监听事件
					publish(new EurekaServerStartedEvent(getEurekaServerConfig()));
				}
				catch (Exception ex) {
					// Help!
					log.error("Could not initialize Eureka servlet context", ex);
				}
			}
		}).start();
	}
```

注册客户端

EurekaServer处理：

PeerReplicationResource.batchReplication()-->PeerReplicationResource.dispatch()-->判断类型

PeerReplicationResource.handleRegister()-->ApplicationResource.addInstance()-->注册PeerAwareInstanceRegistry.register()-->InstanceRegistry.register()-->PeerAwareInstanceRegistryImpl。register()-->AbstractInstanceRegistry.register() 注册成功

### 3.4 与InstanceRegistry相关的接口

**注册表 InstanceRegistry 的类关系**，为后文的**应用实例注册发现**、**Eureka-Server 集群复制**做整体的铺垫

![类图](http://static.iocoder.cn/images/Eureka/2018_05_21/01.png)





- ``com.netflix.eureka.registry.AwsInstanceRegistry``，主要用于亚马逊 AWS，跳过。
- `com.netflix.eureka.registry.RemoteRegionRegistry`，笔者暂时不太理解它的用途。目前猜测 Eureka-Server 集群和集群之间的注册信息的交互方式。查阅官方资料，[《Add ability to retrieve instances from any remote region》](https://github.com/Netflix/eureka/issues/29) 在做了简单介绍。翻看目前网络上的博客、书籍、项目实战，暂时都没提及此块。估摸和亚马逊 AWS 跨区域( `region` ) 机制有一定关系，先暂时跳过。有了解此块的同学，麻烦告知下笔者，万分感谢。TODO[0009]：RemoteRegionRegistry。
- **蓝框**部分，本文主角。

#### 3.4.1 LookupService

`com.netflix.discovery.shared.LookupService`，查找服务**接口**，提供**简单单一**的方式获取应用集合

(`com.netflix.discovery.shared.Applications`) 和 应用实例信息集合( `com.netflix.appinfo.InstanceInfo` )。接口代码如下：



```java
public interface LookupService<T> {

    Application getApplication(String appName);
    
    Applications getApplications();
    
    List<InstanceInfo> getInstancesById(String id);
    
    InstanceInfo getNextServerFromEureka(String virtualHostname, boolean secure);

}
```

![](http://static.iocoder.cn/images/Eureka/2018_04_29/01.png)

- 在 Eureka-Client 里，EurekaClient 继承该接口。
- 在 Eureka-Server 里，`com.netflix.eureka.registry.InstanceRegistry` 继承该接口。

#### 3.4.2 LeaseManager

`com.netflix.eureka.lease.LeaseManager`，租约管理器**接口**，提供租约的注册、续租、取消( 主动下线 )、过期( 过期下线 )。接口代码如下：

```java
public interface LeaseManager<T> {

    void register(T r, int leaseDuration, boolean isReplication);
    
    boolean cancel(String appName, String id, boolean isReplication);
    
    boolean renew(String appName, String id, boolean isReplication);
    
    void evict();
    
}
```



#### 3.4.3 InstanceRegistry

`com.netflix.eureka.registry.InstanceRegistry`，**应用实例**注册表**接口**。它继承了 LookupService 、LeaseManager 接口，提供应用实例的**注册**与**发现**服务。另外，它结合实际业务场景，定义了**更加丰富**的接口方法。接口代码如下：

```java
public interface InstanceRegistry extends LeaseManager<InstanceInfo>, LookupService<String> {

    // ====== 开启与关闭相关 ======

    void openForTraffic(ApplicationInfoManager applicationInfoManager, int count);
    
    void shutdown();
    
    void clearRegistry();

    // ====== 应用实例状态变更相关 ======
    
    void storeOverriddenStatusIfRequired(String appName, String id, InstanceStatus overriddenStatus);

    boolean statusUpdate(String appName, String id, InstanceStatus newStatus,
                         String lastDirtyTimestamp, boolean isReplication);

    boolean deleteStatusOverride(String appName, String id, InstanceStatus newStatus,
                                 String lastDirtyTimestamp, boolean isReplication);

    Map<String, InstanceStatus> overriddenInstanceStatusesSnapshot();

    // ====== 响应缓存相关 ======

    void initializedResponseCache();

    ResponseCache getResponseCache();
    
    // ====== 自我保护模式相关 ======
    
    long getNumOfRenewsInLastMin();

    int getNumOfRenewsPerMinThreshold();

    int isBelowRenewThresold();
    
    boolean isSelfPreservationModeEnabled();
    
    public boolean isLeaseExpirationEnabled();
    
    // ====== 调试/监控相关 ======
    List<Pair<Long, String>> getLastNRegisteredInstances();

    List<Pair<Long, String>> getLastNCanceledInstances();
}
```

#### 3.4.4 AbstractInstanceRegistry

`com.netflix.eureka.registry.AbstractInstanceRegistry`，应用对象注册表**抽象实现**。

这里先不拓展开，[《Eureka 源码解析 —— 应用实例注册发现》系列](http://www.iocoder.cn/categories/Eureka/?self) 逐篇分享。



#### 3.4.5 PeerAwareInstanceRegistry

`com.netflix.eureka.registry.PeerAwareInstanceRegistry`，PeerAware ( 暂时找不到合适的翻译 ) 应用对象注册表**接口**，提供 Eureka-Server 集群内注册信息的**同步**服务。接口代码如下：

```java
public interface PeerAwareInstanceRegistry extends InstanceRegistry {

    void init(PeerEurekaNodes peerEurekaNodes) throws Exception;
    
    int syncUp();
    
    boolean shouldAllowAccess(boolean remoteRegionRequired);

    void register(InstanceInfo info, boolean isReplication);

    void statusUpdate(final String asgName, final ASGResource.ASGStatus newStatus, final boolean isReplication);
}

```

#### 3.4.6 PeerAwareInstanceRegistryImpl

`com.netflix.eureka.registry.PeerAwareInstanceRegistryImpl`，PeerAware ( 暂时找不到合适的翻译 ) 应用对象注册表**实现类**。

这里先不拓展开，[《Eureka 源码解析 —— Eureka-Server 集群》系列](http://www.iocoder.cn/categories/Eureka/?self) 逐篇分享。