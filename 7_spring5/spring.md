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
    核心部分导入相关jar包：spring-beans、spring-context、spring-core、spring-expression，另外需要一个commons-logging-1.1.1.jar的包。
创建配置文件：
    
~~~

# 2 IOC容器
## 2.1 IOC底层原理

什么是 IOC
~~~
控制反转，把对象创建和对象之间的调用过程，交给 Spring 进行管理
使用 IOC 目的：为了耦合度降低
做入门案例就是 IOC 实现
~~~

IOC底层原理：xml解析、工厂模式、反射。

## 2.2 IOC接口(BeanFactory)

IOC 思想基于 IOC 容器完成，IOC 容器底层就是对象工厂。

Spring 提供 IOC 容器实现两种方式：（两个接口）
~~~
BeanFactory：IOC 容器基本实现，是 Spring 内部的使用接口，不提供开发人员进行使用
    加载配置文件时候不会创建对象，在获取对象（使用）才去创建对象
ApplicationContext：BeanFactory 接口的子接口，提供更多更强大的功能，一般由开发人员进行使用
    加载配置文件时候就会把在配置文件对象进行创建
~~~

ApplicationContext 接口有实现类

## 2.3 IOC操作Bean管理

什么是 Bean 管理
~~~
Bean 管理指的是两个操作
Spring 创建对象
Spirng 注入属性
~~~


Bean管理操作有两种方式
~~~
基于 xml 配置文件方式实现
基于注解方式实现
~~~


## 2.4 IOC操作Bean管理(基于xml)

基于xml方式创建对象
~~~
在 spring 配置文件中，使用 bean 标签，标签里面添加对应属性，就可以实现对象创建
在 bean 标签有很多属性，介绍常用的属性
    id 属性：唯一标识
    class 属性：类全路径（包类路径）
创建对象时候，默认也是执行无参数构造方法完成对象创建
~~~

基于 xml 方式注入属性
DI：依赖注入，就是注入属性

spring常用的注入方式：
~~~
set注入；
使用有参数构造进行注入。
~~~

## 2.5 IOC 操作 Bean 管理（bean 生命周期）

生命周期：从对象创建到对象销毁的过程

bean 生命周期(7步)，第3、5步需要配置BeanPostProcessor
~~~
通过构造器创建 bean 实例（无参数构造）
为 bean 的属性设置值和对其他 bean 引用（调用 set 方法）
把 bean 实例传递 bean 后置处理器的方法 postProcessBeforeInitialization 
调用 bean 的初始化的方法（需要进行配置初始化的方法）
把 bean 实例传递 bean 后置处理器的方法 postProcessAfterInitialization
bean 可以使用了（对象获取到了）
当容器关闭时候，调用 bean 的销毁的方法（需要进行配置销毁的方法）
~~~

### 2.5.1 BeanPostProcessor bean后置处理器

在Bean对象在实例化和依赖注入完毕后，在显示调用初始化方法的前后添加我们自己的逻辑。注意是Bean实例化完毕后及依赖注入完成后触发的。自定义的后置处理器需要实现其中的方法。

我们可以在Spring配置文件中添加多个BeanPostProcessor(后置处理器)接口实现类，在默认情况下Spring容器会根据后置处理器的定义顺序来依次调用。

显式指定顺序
在Spring机制中可以指定后置处理器调用顺序，通过让BeanPostProcessor接口实现类实现Ordered接口getOrder方法，该方法返回一整数，默认值为 0，优先级最高，值越大优先级越低.





