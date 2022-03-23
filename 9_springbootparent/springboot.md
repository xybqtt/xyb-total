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






