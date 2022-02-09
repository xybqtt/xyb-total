@[TOC](第4章 jvm)

# 0 格式
## 0.0 格式要求
html代码后面一定加一行空行；
标题后面需要添加空行；
https://www.cnblogs.com/cndarren/p/14415213.html
视频地址：https://www.bilibili.com/video/BV1Vf4y127N5?p=2&spm_id_from=pageDriver
每个大标题之间空2行，每个小标题之间空1行
<span style="color: red;"></span>
<hr style="height: 10px; background: green;"/>
<div style="border: 5px black solid;"><div>

# 1 Spring框架概述(spring 5.x版本)

~~~
Spring是轻量级开源JavaEE框架；
可以解决企业应用开发的复杂性；
有2个核心部分：IOC和AOP
    IOC：控制反转，把创建对象过程交给Spring管理；
    AOP：面向切面，不修改源代码进行功能增强。
特点：
    方便解耦，简化开发；
    AOP编程的支持；
    声明式事务的支持；
    方便程序的测试；
    方便集成各种优秀框架；
    降低Java EE API的使用难度；
    Java 源码是经典学习范例：Spring的源码设计精妙、结构清晰、匠心独运，处处体现着大师对Java设计模式灵活运用以及对Java技术的高深造诣。Spring框架源码无疑是Java技术的最佳实践范例。如果想在短时间内迅速提高自己的Java技术水平和应用开发水平，学习和研究Spring源码将会使你收到意想不到的效果。
~~~

<hr style="height: 10px; background: green;"/>

spring入门案例创建
~~~
下载：
    spring.io
    Projects
    Spring Framework
    Learn 查看最新GA版本(稳定版本)
    点击旁边的github
    找到Access to Binaries，点击 Spring Framework Artifacts，找到并进入 https://repo.spring.io(这就是下载地址)
    Artifacts > release > org > springframework > spring > 拼接：https://repo.spring.io/和Repository Path部分内容
    即下载地址https://repo.spring.io/release/org/springframework/spring/
    找到合适的GA版本进行下载，下载dist.zip
解压：
    docs：
    libs：spring需要用到的依赖；
    schema：
工程创建：
    创建普通java工程或Maven工程；
    核心部分导入相关jar包：spring-beans、spring-context、spring-core、spring-expression；
~~~

# 2 IOC容器
## 2.1 IOC底层原理

IOC
~~~
概念：控制反转（Inversion of Control，缩写为IoC），把对象创建和对象之间的调用过程，交给Spring管理；
目的：降低耦合度。
~~~

<hr style="height: 10px; background: green;"/>

IOC底层原理：xml解析、工厂模式、反射。






## 2.2 IOC接口(BeanFactory)



## 2.3 IOC操作Bean管理(基于xml)



## 2.4 IOC操作Bean管理(基于注解)