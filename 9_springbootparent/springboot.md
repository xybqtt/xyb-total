@[TOC](第4章 jvm)

# 0 格式
## 0.0 格式要求
html代码后面一定加一行空行；
标题后面需要添加空行；
https://www.cnblogs.com/cndarren/p/14415213.html
视频地址：https://www.bilibili.com/video/BV19K4y1L7MT?p=5&spm_id_from=pageDriver
每个大标题之间空2行，每个小标题之间空1行
<span style="color: red;"></span>
<hr style="height: 10px; background: green;"/>

# 1 为什么用SpringBoot

能快速创建出生产级别的Spring应用

## 1.2 优点

创建独立Spring应用。
内嵌web服务器：tomcat、jetty，使我们开发完成后不用打包放到容器去执行。
自动starter依赖，简化构建配置：一个依赖管理了所有相关的依赖，让我们不用再去导各种包及考虑包之间的版本问题。
自动配置Spring以及第三方功能。
提供生产级别的监控、健康检查及外部化配置。
无代码生成、无需编写XML。

## 1.3 缺点

人称版本帝，迭代快，需要时刻关注变化
封装太深，内部原理复杂，不容易精通

## 1.4 时代背景
### 1.4.1 微服务

微服务是一种架构风格
一个应用拆分为一组小型服务
每个服务运行在自己的进程内，也就是可独立部署和升级
服务之间使用轻量级HTTP交互
服务围绕业务功能拆分
可以由全自动部署机制独立部署
去中心化，服务自治。服务可以使用不同的语言、不同的存储技术

### 1.4.2 分布式

困难：
~~~
远程调用
服务发现
负载均衡
服务容错
配置管理
服务监控
链路追踪
日志管理
任务调度
~~~

解决方案：SpringBoot + SpringCloud。

### 1.4.3 云原生

原生应用如何上云。 Cloud Native
服务自愈
弹性伸缩
服务隔离
自动化部署
灰度发布
流量治理
......

## 1.5 如何学习SpringBoot
### 1.5.1 官网文档架构

https://docs.spring.io/spring-boot/docs/current/reference/html/
~~~
getting started 入门
user spring boot 进阶
spring boot features 高级特性
spring boot actuator 监控
deploying spring boot applications 部署
how to guides 小技巧

application properties 所有配置概览
auto-configuration classes 所有自动配置
test auto-configuration annotations 常见测试注解
executable jars 可执行jar
dependency version 所有场景依赖版本
~~~

查看版本新特性；https://github.com/spring-projects/spring-boot/wiki#release-notes



# 2 SpringBoot2入门
## 2.1 系统要求

~~~
Java 8 & 兼容java14 .
Maven 3.3+
idea 2019.1.2
~~~

## 2.2 maven设置

~~~
<mirrors>
      <mirror>
        <id>nexus-aliyun</id>
        <mirrorOf>central</mirrorOf>
        <name>Nexus aliyun</name>
        <url>http://maven.aliyun.com/nexus/content/groups/public</url>
      </mirror>
  </mirrors>
 
  <profiles>
         <profile>
              <id>jdk-1.8</id>
              <activation>
                <activeByDefault>true</activeByDefault>
                <jdk>1.8</jdk>
              </activation>
              <properties>
                <maven.compiler.source>1.8</maven.compiler.source>
                <maven.compiler.target>1.8</maven.compiler.target>
                <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
              </properties>
         </profile>
  </profiles>
~~~

## 2.3 工程创建

1、创建maven工程
2、引入依赖
~~~
<!-- 设置boot项目的parent -->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.3.4.RELEASE</version>
</parent>

<!-- 
    如果不想设置boot项目的parent为 org.springframework.boot.spring-boot-starter-parent，则需要在父工程中添加如下配置，然后本pom的parent设置为父pom
-->
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>2.0.3.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>


<!-- 引入web工程需要用到的依赖 -->
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
~~~

3、创建主程序
~~~
/**
 * 主程序类
 */
@SpringBootApplication // 让springboot知道这是一个sb应用
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }
}
~~~

4、编写业务
~~~
@RestController // 相当于@Controller + @ResponseBody
public class HelloController {

    @RequestMapping("/hello")
    public String handle01(){
        return "Hello, Spring Boot 2!";
    }
    
}
~~~

5、测试：直接运行main方法。
6、简化配置
~~~
创建application.properties
设置参数：
server.port=8888
~~~

7、简化部署
把项目打成jar包，直接在目标服务器执行即可。
~~~
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <!-- 不加这个打的jar包，不能进行java -jar 操作 -->
            <executions>
                <execution>
                    <goals>
                        <goal>repackage</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
~~~

8、打包后的目录介绍










# 3 自动配置原理
## 3.1 SpringBoot特点
### 3.1.1 依赖管理

父项目做依赖管理：
~~~
依赖管理    
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.3.4.RELEASE</version>
</parent>

他的父项目
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>2.3.4.RELEASE</version>
</parent>

几乎声明了所有开发中常用的依赖的版本号,自动版本仲裁机制
~~~

开发导入starter场景启动器：
~~~
见到很多 spring-boot-starter-* ： *就某种场景
只要引入starter，这个场景的所有常规需要的依赖我们都自动引入
SpringBoot所有支持的场景
  https://docs.spring.io/spring-boot/docs/current/reference/html/using-spring-boot.html#using-boot-starter
见到的  *-spring-boot-starter： 第三方为我们提供的简化开发的场景启动器。
所有场景启动器最底层的依赖
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <version>2.3.4.RELEASE</version>
    <scope>compile</scope>
</dependency>
~~~

无需关注版本号，自动版本仲裁：
~~~
引入依赖默认都可以不写版本
引入非版本仲裁的jar，要写版本号。
~~~

可以修改默认版本号：
~~~
查看spring-boot-dependencies里面规定当前依赖的版本 用的 key。
在当前项目里面重写配置
<properties>
    <mysql.version>5.1.43</mysql.version>
</properties>
~~~

### 3.1.2 自动配置

自动配好Tomcat：
    引入Tomcat依赖。
    配置Tomcat，在spring-boot-starter-web中。
~~~
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-tomcat</artifactId>
    <version>2.3.4.RELEASE</version>
    <scope>compile</scope>
</dependency>
~~~

自动配好SpringMVC
- 引入SpringMVC全套组件
- 自动配好SpringMVC常用组件（功能）

自动配好Web常见功能，如：字符编码问题
- SpringBoot帮我们配置好了所有web开发的常见场景

默认的包结构
- 主程序所在包及其下面的所有子包里面的组件都会被默认扫描进来
- 无需以前的包扫描配置
- 想要改变扫描路径，@SpringBootApplication(scanBasePackages="com.atguigu")
  - 或者@ComponentScan 指定扫描路径
~~~
@SpringBootApplication
等同于
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan("com.atguigu.boot")
~~~

各种配置拥有默认值：
- 默认配置最终都是映射到某个类上，如：MultipartProperties
- 配置文件的值最终会绑定每个类上，这个类会在容器中创建对象

按需加载所有自动配置项
- 非常多的starter
- 引入了哪些场景这个场景的自动配置才会开启
- SpringBoot所有的自动配置功能都在 spring-boot-autoconfigure 包里面

## 3.2 容器功能
### 3.2.1 组件添加
#### 3.2.1.1 @Configuration

用于添加到配置类上，代替spring配置文件。
proxyBeanMethods属性：
- Full(proxyBeanMethods = true)全模式，该模式下注入容器中的同一个组件无论被取出多少次都是同一个bean实例，即单实例对象，在该模式下SpringBoot每次启动都会判断检查容器中是否存在该组件。
- Lite(proxyBeanMethods = false)轻量级模式。该模式下注入容器中的同一个组件无论被取出多少次都是不同的bean实例，即多实例对象，在该模式下SpringBoot每次启动会跳过检查容器中是否存在该组件。

什么时候用Full全模式，什么时候用Lite轻量级模式？
- 当在你的同一个Configuration配置类中，注入到容器中的bean实例之间有依赖关系时，建议使用Full全模式
- 当在你的同一个Configuration配置类中，注入到容器中的bean实例之间没有依赖关系时，建议使用Lite轻量级模式，以提高springboot的启动速度和性能。

proxyBeanMethods 属性默认值是 true, 也就是说该配置类会被代理（CGLIB），在同一个配置文件中调用其它被 @Bean 注解标注的方法获取对象时会直接从 IOC 容器之中获取。

#### 3.2.1.2 5个声明组件的注解

@Bean、@Component、@Controller、@Service、@Repository

#### 3.2.1.3 扫包、导入其它类注解

@ComponentScan、@Import
@Import 高级用法： https://www.bilibili.com/video/BV1gW411W7wy?p=8

#### 3.2.1.4 @Conditional

条件装配：满足Conditional指定的条件，则进行组件注入

必须是@Conditional指定的条件成立，才给容器中添加组件，配置配里面的所有内容才生效。当一个 Bean 被 Conditional 注解修饰时，Spring容器会对数组中所有 Condition 接口的 matches() 方法进行判断，只有当其中所有 Condition 接口的 matches()方法都为 ture 时，才会创建 Bean 。

|注解及派生注解|含义|
|:--|:--|
|@Conditional|判断是否满足当前指定条件|
|@ConditionalOnJava|系统的java版本是否符合要求|
|@ConditionalOnBean|容器中存在指定Bean；|
|@ConditionalOnMissingBean|容器中不存在指定Bean；|
|@ConditionalOnExpression|满足SpEL表达式指定|
|@ConditionalOnClass|系统中有指定的类|
|@ConditionalOnMissingClass|系统中没有指定的类|
|@ConditionalOnSingleCandidate|容器中只有一个指定的Bean，或者这个Bean是首选Bean|
|@ConditionalOnProperty|系统中指定的属性是否有指定的值|
|@ConditionalOnResource|类路径下是否存在指定资源文件|
|@ConditionalOnWebApplication|当前是web环境|
|@ConditionalOnNotWebApplication|当前不是web环境|
|@ConditionalOnJndi|JNDI存在指定项|

### 3.2.2 原生配置文件引入

@ImportResource：导入Spring的配置文件，让配置文件里面的内容生效；
例：@ImportResource("classpath:beans.xml")

### 3.2.3 配置绑定

@ConfigurationProperties：把properties配置文件的信息，读取并根据名称注入到对应的属性中。因为只有被ioc容器管理才能进行这样的操作，所以需要和"@Component、@Bean、@EnableConfigurationProperties"等一起使用。

@EnableConfigurationProperties注解的作用是：使使用 @ConfigurationProperties 注解(因为不是自定义的类，没办法添加@Component注解)的类生效，即使其被ioc容器管理。
























