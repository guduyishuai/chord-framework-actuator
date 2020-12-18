# 场景

spring boot从2.2.x开始提供了存活和就绪状态的探针，方便kubernetes使用。

本项目为旧版本的spring boot提供存活和就绪状态的探针，本项目适合如下版本

- 2.0.6.RELEASE

- 2.0.7.RELEASE

- 2.0.8.RELEASE

- 2.0.9.RELEASE

- 2.1.0.RELEASE

- 2.1.1.RELEASE

- 2.1.2.RELEASE

- 2.1.3.RELEASE

- 2.1.4.RELEASE

- 2.1.5.RELEASE

- 2.1.6.RELEASE

- 2.1.7.RELEASE

- 2.1.8.RELEASE

- 2.1.9.RELEASE

  

# 依赖

引入框架依赖

```xml
<dependency>
  <groupId>com.chord.framework</groupId>
  <artifactId>chord-framework-autuator-starter</artifactId>
</dependency>	
```

引入spring-boot依赖

```xml
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
```

引入prometheus依赖

```xml
<dependency>
            <groupId>io.micrometer</groupId>
            <artifactId>micrometer-registry-prometheus</artifactId>
</dependency>
```



# 配置

开启prometheus的exporter

开启health，liveness和readiness端点

```yaml
management:
  endpoints:
    web:
      exposure:
        include: prometheus, health, liveness, readiness
  endpoint:
    prometheus:
      enabled: true
    health:
      enabled: true
      show-details: always
  health:
    livenessstate:
      enabled: true
    readinessstate:
      enabled: true
```

如果是2.1.x版本的spring-boot，还需要开启允许修改spring的内置bean，因为框架提供了PathMapper的另一个实现，从而重新修改请求地址。不能通过Endpoint的id设置地址，因为默认的EndpointId会禁止在路径中使用"/"，这是因为考虑到jmx的原因。

在2.2.x版本中是通过HealthEndpointGroup这个组来解决的。

```
spring: 
  main: 
    allow-bean-definition-overriding: true
```



# 使用

本框架提供以下两个探针

## liveness

请求地址/actuator/health/liveness

如果要手动改变状态为存活

```java
AvailabilityChangeEvent.publish(applicationContext, LivenessState.CORRECT)
```

如果要手动改变状态为宕机

```java
AvailabilityChangeEvent.publish(applicationContext, LivenessState.BROKEN)
```



## readiness

请求地址/actuator/health/readiness

如果要手动改变状态为就绪

```java
AvailabilityChangeEvent.publish(applicationContext, ReadinessState.ACCEPTING_TRAFFIC);
```

如果要手动改变状态为下线

```java
 AvailabilityChangeEvent.publish(applicationContext, ReadinessState.REFUSING_TRAFFIC);
```

