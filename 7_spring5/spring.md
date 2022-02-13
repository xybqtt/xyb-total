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



## 2.6 IOC操作Bean管理(基于注解)

使用注解的目的是简化xml配置。
基于注解方式实现对象创建步骤：
~~~
引入依赖 spring-aop-5.2.6.RELEASE.jar
引入命名空间：
    xml文件添加 xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context-4.0.xsd"
开启组件扫描：
    <context:component-scan base-package="com.atguigu.spring5.a2anno"></context:component-scan>，多个包用逗号隔开。
在类上添加@Service注解，相当于<bean id="ddd" class="sdaf.ad.ad"/>
~~~


基于注解方式实现属性注入：
~~~
@Autowired：根据属性类型进行自动装配
    第一步 把 service 和 dao 对象创建，在 service 和 dao 类添加创建对象注解
    第二步 在 service 注入 dao 对象，在 service 类添加 dao 类型属性，在属性上面使用注解

@Qualifier：根据名称进行注入
    这个@Qualifier 注解的使用，和上面@Autowired 一起使用，@Qualifier(value = "userDaoImpl1")

@Resource：
    @Resource：可以根据类型注入，
    @Resource(name = "abc")：可以根据名称注入

@Value：注入普通类型属性
    @Value(value = "张三")

~~~

**完全注解开发**，即完全不需要xml，使用注解代替开启组件扫描
~~~
创建配置类，替代 xml 配置文件，类名自定
    @Configuration //作为配置类，替代 xml 配置文件，让spring知道这个类是配置类
    @ComponentScan(basePackages = {"com.atguigu"}) // 开启组件扫描
    public class SpringConfig {
    }
因为没有文件了，使用下面这种方式进行加载：
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

~~~



# 3 AOP
## 3.1 什么是Aop

面向切面编程（方面），利用 AOP 可以对业务逻辑的各个部分进行隔离，从而使得 业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。
通俗描述：不通过修改源代码方式，在主干功能里面添加新功能。

## 3.2 AOP（底层原理）

JDK动态代理：需要有接口，创建接口实现类代理对象，增强类的方法。
CGLIB代理：没有接口，创建子类代理对象，增强类的方法。

## 3.3 Aop术语

~~~
连接点(JointPoint)：连接点是程序执行过程中明确的点，一般是类中方法的调用。连接点是程序在运行过程中能够插入切面的地点，比如方法调用、异常抛出、字段修改等；
切入点(PointCout)：切入点是可以插入增强处理的连接点，当某个连接点满足执行要求时，该连接点将被连接增强处理，该连接点也就变成了切入点。切入点是拦截的方法，连接点JointPoint拦截后将变成切入点
通知(增强)Advice：
    实际增强的逻辑部分称为通知(增强)；
    通知的多种类型：前置、后置、环绕、异常、最终通知；
切面：切面通常是一个类，可以定义切入点和通知。类是对物体特征的抽象，切面是对横切关注点的抽象。
~~~

## 3.4 Aop操作(准备工作)

添加AspectJ：AspectJ 不是 Spring 组成部分，独立 AOP 框架，一般把 AspectJ 和 Spirng 框架一起使 用，进行 AOP 操作。

## 3.5 切入点表达式

切入点表达式作用：知道对哪个类里面的哪个方法进行增强
语法结构： execution([权限修饰符] [返回类型] [类全路径] [方法名称]([参数列表]) )

举例 1：对 com.atguigu.dao.BookDao 类里面的 add 进行增强
execution(* com.atguigu.dao.BookDao.add(..))
举例 2：对 com.atguigu.dao.BookDao 类里面的所有的方法进行增强
execution(* com.atguigu.dao.BookDao.* (..))
举例 3：对 com.atguigu.dao 包里面所有类，类里面所有方法进行增强
execution(* com.atguigu.dao.*.* (..))















