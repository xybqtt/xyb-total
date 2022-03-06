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
引入依赖：
    spring-aop-5.2.6.RELEASE.jar

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

## 3.5 切入点表达式

切入点表达式作用：知道对哪个类里面的哪个方法进行增强
语法结构： execution([权限修饰符] 返回类型 [类全路径] 方法名称(参数列表) 方法申明抛出的异常)
比较特殊的为形参表部分，其支持两种通配符
    "*"：代表一个任意类型的参数；
    “..”：代表零个或多个任意类型的参数。



## 3.6 几种通知类型

前置通知：@Before
~~~
在目标方法执行之前执行执行的通知。
前置通知方法，可以没有参数，也可以额外接收一个JoinPoint，Spring会自动将该对象传入，代表当前的连接点，通过该对象可以获取目标对象 和 目标方法相关的信息。 
注意，如果接收JoinPoint，必须保证其为方法的第一个参数，否则报错。
~~~

环绕通知：@Around
~~~
在目标方法执行之前和之后都可以执行额外代码的通知。
在环绕通知中必须显式的调用目标方法，目标方法才会执行，这个显式调用时通过ProceedingJoinPoint来实现的，可以在环绕通知中接收一个此类型的形参，spring容器会自动将该对象传入，注意这个参数必须处在环绕通知的第一个形参位置。
要注意，只有环绕通知可以接收ProceedingJoinPoint，而其他通知只能接收JoinPoint。
环绕通知需要返回返回值，否则真正调用者将拿不到返回值，只能得到一个null。
环绕通知有控制目标方法是否执行、有控制是否返回值、有改变返回值的能力。
环绕通知虽然有这样的能力，但一定要慎用，不是技术上不可行，而是要小心不要破坏了软件分层的“高内聚 低耦合”的目标。
~~~

后置(返回)通知：@AfterReturning
~~~
在目标方法执行之后执行的通知。
在后置通知中也可以选择性的接收一个JoinPoint来获取连接点的额外信息，但是这个参数必须处在参数列表的第一个。
在后置通知中，还可以通过配置获取返回值
~~~

异常通知：@AfterThrowing
~~~
在目标方法抛出异常时执行的通知。
可以配置传入JoinPoint获取目标对象和目标方法相关信息，但必须处在参数列表第一位
另外，还可以配置参数，让异常通知可以接收到目标方法抛出的异常对象。
~~~

最终通知：@After
~~~
是在目标方法执行之后执行的通知。
和后置通知不同之处在于，后置通知是在方法正常返回后执行的通知，如果方法没有正常返-例如抛出异常，则后置通知不会执行。
而最终通知无论如何都会在目标方法调用过后执行，即使目标方法没有正常的执行完成。
另外，后置通知可以通过配置得到返回值，而最终通知无法得到。
最终通知也可以额外接收一个JoinPoint参数，来获取目标对象和目标方法相关信息，但一定要保证必须是第一个参数。
~~~


## 3.6 aop开发流程

导包：添加AspectJ：AspectJ 不是 Spring 组成部分，独立 AOP 框架，一般把 AspectJ 和 Spirng 框架一起使 用，进行 AOP 操作。
~~~
aop相关：
    com.springsource.net.sf.cglib-2.2.0.jar
    com.springsource.org.aopalliance-1.0.0.jar
    com.springsource.org.aspectj.weaver-1.6.8.RELEASE.jar
    spring-aop-5.3.15.jar
    spring-aspects-5.2.6.RELEASE.jar

spring基本包：
    spring-beans-5.3.15.jar
    spring-context-5.3.15.jar
    spring-core-5.3.15.jar
    spring-expression-5.3.15.jar
    commons-logging-1.1.1.jar
~~~

### 3.6.1 完全xml开发

开启aop动态代理：
~~~
添加名称空间：
    xmlns:aop="http://www.springframework.org/schema/aop"
    xsi:schemaLocation="http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.0.xsd"
xml添加配置：
    <aop:aspectj-autoproxy></aop:aspectj-autoproxy> // 开启生成代理对象
~~~

注册切面、配置切入点及各种通知：
~~~
<!-- 配置aop -->
<aop:config>
    <!-- 切入点 -->
    <aop:pointcut id="p" expression="execution(public int com.atguigu.spring5.a2aop.xml.User1.*(..))"/>
    <!-- 配置切面 -->
    <aop:aspect ref="userProxy">
        <!-- 增强在具体的方法上，before、after等等是userProxy的方法 -->
        <aop:before method="before" pointcut-ref="p"></aop:before>
        <aop:after method="after" pointcut-ref="p"></aop:after>
    </aop:aspect>
</aop:config>
~~~

### 3.6.2 完全anno开发

开启aop代理
~~~
在配置类上添加此注解
@EnableAspectJAutoProxy(proxyTargetClass = true) // 启动生成代理对象， 相当于xml添加生成代理对象，只要写了此注解，后面默认是true。
~~~

注册切面、配置切入点及各种通知：
~~~
切面：@Aspect修饰的类，@Order(数字类型)，数字越小，优先级越高
切入点：@Point(value = "execution(* void com.atguigu.spring5.a2aop.User.*(..))") 修饰的方法fn1()
各种通知添加在对应方法上：@Before(value = "fn1()")
~~~



# 4 JdbcTemplate
## 4.1 什么是JdbcTemplate

Spring 框架对 JDBC 进行封装，使用 JdbcTemplate 方便实现对数据库操

## 4.2 开发流程

导包：
~~~
spring基本包：
    spring-beans-5.3.15.jar
    spring-context-5.3.15.jar
    spring-core-5.3.15.jar
    spring-expression-5.3.15.jar
    commons-logging-1.1.1.jar

jdbc相关包jar包：
    druid-1.1.9.jar
    mysql-connector-java-5.1.7-bin.jar
    spring-jdbc-5.3.15.jar
    spring-orm-5.3.15.jar // orm映射
    spring-tx-5.3.15.jar // 事务
~~~

### 4.2.1 完全xml开发

配置dataSource：
~~~
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" destroy-method="close">
    <property name="url" value="jdbc:mysql:///xyb"></property>
    <property name="username" value="root"></property>
    <property name="password" value="root"></property>
    <property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
</bean>
~~~

配置JdbcTemplate:
~~~
<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
    <property name="dataSource" ref="dataSource"></property>
</bean>
~~~

查看：a1xml/a3jdbcTemplate/1jdbcTemplate.xml


### 4.2.2 完全anno开发

配置dataSource：
~~~
@Bean(value = "dataSource", initMethod = "", destroyMethod = "")
public DataSource createDataSource(@Value(value = "${jdbc.url}") String url,
                                   @Value(value = "${jdbc.driverClassName}") String driverClassName,
                                   @Value(value = "${jdbc.username}") String userName,
                                   @Value(value = "${jdbc.password}") String password) {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setUrl(url);
    dataSource.setDriverClassName(driverClassName);
    dataSource.setUsername(userName);
    dataSource.setPassword(password);
    return dataSource;
}
~~~

配置JdbcTemplate:
~~~
@Bean(value = "jdbcTemplateTx")
public JdbcTemplate createJdbcTemplate(@Qualifier(value = "dataSource beanId") DataSource dataSource) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    jdbcTemplate.setDataSource(dataSource);
    return jdbcTemplate;
}
~~~

查看：com/atguigu/spring5/a2anno/a3jdbcTemplate/JdbcTemplateConfig.java

### 4.2.3 使用

使用JdbcTemplate，创建一个Dao，注入JdbcTemplate，进行使用。
可以进行增删改查操作，也是用占位符，本质是把jdbc封装了下，增删改(都是用update操作)查看。
有2种操作，一种是针对一行参数的操作，一种是批量批作，即针对多行参数的操作。
查看：com/atguigu/spring5/a2anno/a3jdbcTemplate/dao/UserDaoImpl.java。



# 5 事务
## 5.1 什么是事务

事务是数据库操作最基本单元，逻辑上一组操作，要么都成功，如果有一个失败所有操作都失败。事务是恢复和并发控制的基本单位。spring的事务本质是aop。

## 5.2 事务的4个属性

事务应该具有4个属性：原子性、一致性、隔离性、持久性。这四个属性通常称为ACID特性。
~~~
原子性(atomicity)：一个事务是一个不可分割的工作单位，事务中包括的操作要么都做，要么都不做。
一致性(consistency)：事务必须是使数据库从一个一致性状态变到另一个一致性状态。一致性与原子性是密切相关的。
隔离性(isolation)：一个事务的执行不能被其他事务干扰。即一个事务内部的操作及使用的数据对并发的其他事务是隔离的，并发执行的各个事务之间不能互相干扰。
持久性(durability)：持久性也称永久性（permanence），指一个事务一旦提交，它对数据库中数据的改变就应该是永久性的。接下来的其他操作或故障不应该对其有任何影响。
~~~

## 5.3 开发流程（搭建事务操作环境）

导包：
~~~
spring基本包：
    spring-beans-5.3.15.jar
    spring-context-5.3.15.jar
    spring-core-5.3.15.jar
    spring-expression-5.3.15.jar
    commons-logging-1.1.1.jar

jdbc相关包jar包：
    druid-1.1.9.jar
    mysql-connector-java-5.1.7-bin.jar
    spring-jdbc-5.3.15.jar
    spring-orm-5.3.15.jar // orm映射
    spring-tx-5.3.15.jar // 事务
~~~

### 5.3.1 完全xml开发

导入名称空间：
~~~
xmlns:tx="http://www.springframework.org/schema/tx"
xsi:schemaLocation="http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd"
~~~

配置事务管理器：
~~~
每个操作数据库的方式有对应的事务管理器，它们都是"org.springframework.transaction.TransactionManager"的实现类，如JdbcTemplate对应的是DataSourceTransacionManager。
需要先注册DataSource、JdbcTemplate；
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <property name="dataSource" ref="dataSource"></property>
</bean>
~~~

开启事务管理：
~~~
<tx:annotation-driven transaction-manager="org.springframework.jdbc.datasource.DataSourceTransactionManager的beanId"></tx:annotation-driven>
~~~

使用事务及配置传播特性：
~~~
<!-- 配置事务的传播特性 -->
<tx:advice id="txadvice" transaction-manager="transactionManager">
    <!-- 配置事务参数 -->
    <tx:attributes>
        <!-- 在哪种规则的方法上添加事务 -->
        <tx:method name="account*" propagation="REQUIRED" isolation="REPEATABLE_READ"/>
        <tx:method name="save*" propagation="REQUIRED" isolation="REPEATABLE_READ"/>
    </tx:attributes>
</tx:advice>

<!-- 配置对哪些类或方法启用事务 -->
<!-- 配置aop -->
<aop:config>
    <!-- 切入点 -->
    <aop:pointcut id="p" expression="execution(* com.atguigu.spring5.a1xml.a4tx.dao.UserTxDaoImpl.*(..))"/>
    <!-- 配置切面 -->
    <aop:advisor advice-ref="txadvice" pointcut-ref="p"></aop:advisor>
</aop:config>
~~~

查看：a1xml/a4tx/1tx.xml

### 5.3.2 完全anno开发

配置事务管理器：
~~~
需要声明DataSource、JdbcTemplate；
@Bean(value = "transactionManager")
public DataSourceTransactionManager transactionManager(
        @Qualifier(value = "dataSourceTx") DataSource dataSource) {
    DataSourceTransactionManager tx = new DataSourceTransactionManager();
    tx.setDataSource(dataSource);
    return tx;
}
~~~

开启事务管理：
~~~
在配置类添加：
@EnableTransactionManagement(proxyTargetClass = true) // 开启事务管理，有这个注解就不用导入名称空间了
~~~

使用事务及配置传播特性：
~~~
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
这个注解添加到类上面，也可以添加方法上面，如果把这个注解添加类上面，这个类里面所有的方法都添加事务，如果把这个注解添加方法上面，为这个方法添加事务。
~~~

查看：com/atguigu/spring5/a2anno/a4tx/dao

## 5.4 事务注解原理

Spring 的注解方式的事务实现机制:在应用系统调用声明@Transactional 的目标方法时，Spring Framework 默认使用 AOP 代理，在代码运行时生成一个代理对象，根据@Transactional 的属性配置信息，这个代理对象决定该声明@Transactional 的目标方法是否由拦截器 TransactionInterceptor 来使用拦截，在 TransactionInterceptor 拦截时，会在在目标方法开始执行之前创建并加入事务，并执行目标方法的逻辑, 最后根据执行情况是否出现异常，利用抽象事务管理器。

## 5.5 事务传播行为及隔离级别
### 5.5.1 传播行为

~~~
PROPAGATION_REQUIRED – 支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择。
PROPAGATION_SUPPORTS – 支持当前事务，如果当前没有事务，就以非事务方式执行。
PROPAGATION_MANDATORY – 支持当前事务，如果当前没有事务，就抛出异常。
PROPAGATION_REQUIRES_NEW – 新建事务，如果当前存在事务，把当前事务挂起。
PROPAGATION_NOT_SUPPORTED – 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
PROPAGATION_NEVER – 以非事务方式执行，如果当前存在事务，则抛出异常。
PROPAGATION_NESTED – 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则进行与PROPAGATION_REQUIRED类似的操作。
~~~

### 5.5.2 事务隔离级别

3种读数据问题：A事务是要操作的事务，B事务是其它事务
~~~
脏读：A事务读取了B事务未提交的数据；
不可重复读：在A事务中，根据同一条件多次读取数据，由于B事务在A事务运行过程中修改了对应数据，导致A事务多次读取的值不一致；
幻读(Phantom Read)：在A事务中，先根据条件查询了一次数据(查出一篥)，B事物在A事务查询一次后添加了一条数据，当A事务再次查询时，会读取到2条数据。
    注意，在A事务中Select了一次后，如果不进行增删改操作，后面再次查询的时候快照读，就是在第一次查询的结果上进行查询，当进行了增删改后，再次查询时才是当前读。
~~~

4种隔离级别：
~~~
读未提交(read Uncommited): 在该隔离级别，所有的事务都可以读取到别的事务中未提交的数据，会产生脏读问题，在项 目中基本不怎么用， 安全性太差；
读已提交(read commited): 这是大多数数据库默认的隔离级别，但是不是 MySQL 的默认隔离级别；这个隔离级别满足 了简单的隔离要求：一个事务只能看见已经提交事务所做的改变，所以会避免脏读问题； 由于一个事务可以看到别的事务已经提交的数据，于是随之而来产生了不可重复读和虚读等 问题（下面详细介绍这种问题，结合问题来理解隔离级别的含义）；
可重复读(Repeatable read)： 这是 MySQL 的默认隔离级别，它确保了一个事务中多个实例在并发读取数据的时候会读取 到一样的数据；不过理论上，这会导致另一个棘手的问题：幻读(Phantom Read)。简 单的说，幻读指当用户读取某一范围的数据行时，另一个事务又在该范围内插入了新行，当 36 用户再读取该范围的数据行时，会发现有新的“幻影” 行。InnoDB 和 Falcon 存储引擎通 过多版本并发控制（MVCC，Multiversion Concurrency Control）机制解决了该问题。
可串行化(serializable)： 事物的最高级别，它通过强制事务排序，使之不可能相互冲突，从而解决幻读问题。简言之， 它是在每个读的数据行上加上共享锁。在这个级别，可能导致大量的超时现象和锁竞争，一 般为了提升程序的吞吐量不会采用这个；
~~~

# 6 Spring5框架新功能

整个 Spring5 框架的代码基于 Java8，运行时兼容 JDK9，许多不建议使用的类和方法在代码库中删除。

## 6.1 日志

Spring5 已经移除 Log4jConfigListener，官方建议使用 Log4j2 。

Spring5 框架整合 Log4j2整合步骤：
~~~
引入jar：
    log4j-api-2.11.2.jar
    log4j-core-2.11.2.jar
    log4j-slf4j-impl-2.11.2.jar
    slf4j-api-1.7.30.jar

创建 log4j2.xml 配置文件：
    具体查看本项目中的log4j2.xml(文件名必须是这个)。

使用logger输出：
    // 声明一个成员变量，用log.info(""); 输出即可
    private static final Logger log = LoggerFactory.getLogger(SpringConfig.class);
~~~

查看：com/atguigu/spring5/a3sp5newfunc/SpringConfig.java。

## 6.2 @Nullable注解

@Nullable 注解可以使用在方法上面，属性上面，参数上面，表示方法返回可以为空，属性值可以 为空，参数值可以为空。
注解用在方法上面，方法返回值可以为空。
注解使用在方法参数里面，方法参数可以为空。
注解使用在属性上面，属性值可以为空。

## 6.3 函数式风格 GenericApplicationContext

函数式风格创建对象，交给 spring 进行管理，具体查看：com/atguigu/spring5/a3sp5newfunc/SpringConfig.java。

## 6.4 spring5支持整合junit5
### 6.4.1 整合junit4

导包：
~~~
hamcrest-core-1.3.jar
junit-4.12.jar
spring-test-5.3.15.jar
~~~

创建测试类，使用注解完成：
~~~
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class}, locations = {""}) // 导入配置类(classes)，或配置文件(locations)，有了这个以后，在@Test修饰的方法内部，就不用再手动获取context，
public class A3Sp5NewFuncTest {
    @Autowired
    private SpringConfig springConfig;
    
    @Test
    public void f1() {
        springConfig.m1(); // 在测试类中直接可以使用，不用再先获取context，再获取注册的bean了。
    }
~~~

查看：com/xyb/A3Sp5NewFuncTest.java。

### 6.4.2 整合junit5

导包：
~~~
apiguardian-api-1.1.2.jar
junit-jupiter-5.8.1.jar
junit-jupiter-api-5.8.1.jar
junit-jupiter-engine-5.8.1.jar
junit-jupiter-params-5.8.1.jar
junit-platform-commons-1.8.1.jar
junit-platform-engine-1.8.1.jar
opentest4j-1.2.0.jar
spring-test-5.3.15.jar
~~~

创建测试类，使用注解完成：
~~~
方式1：
    类上加注解：
    @ExtendWith(SpringExtension.class)
    @ContextConfiguration(classes = {SpringConfig.class}) // 相当于spring加载配置类，加载配置文件用locations = {"classpath:", ""}
    
    方法上加注解，注意是org.junit.jupiter.api.Test;包下的
    @Test 

方式2：
    类上加注解，相当于方式1的2个注解合并
    @SpringJUnitConfig(classes = {SpringConfig.class}) // 相当于整合了方式2的2个注解
~~~



# 7 spring注解与xml配置对应关系

与xml配置文件相关：
~~~
@Configuration：
    位置：类；
    作用：被修饰的类，相当于xml文件。

@ComponentScan：
    位置：类；
    作用：组件扫描，告诉spring去扫哪些包；
    举例：@ComponentScan(value = {"com.atguigu.spring5.a1ioc.anno"})：
    等价：<context:component-scan base-package="com.atguigu.spring5.a1ioc.anno"></context:component-scan>

@Import：
    位置：类；
    作用：
        声明一个bean；
        导入@Configuration注解的配置类；
        导入ImportSelector的实现类；
        导入ImportBeanDefinitionRegistrar的实现类；
    举例：
        @Import(value = 类简单名称.class)
        @Import(value = JdbcConfig.class) // 导入另一个@Configuration注解的类
    等价：
        <import resource="1annoVersion.xml"></import>

@PropertySource：
    位置：类；
    作用：导入properties文件；指定文件地址。提供了一种方便的、声明性的机制，用于向Spring的环境添加PropertySource。与@configuration类一起使用。
    举例：@PropertySource(value = "a1ioc/xml/config.properties")
    等价：<context:property-placeholder location="classpath*:a1ioc/xml/config.properties"></context:property-placeholder>。

@EnableAsync:
    位置：注解在配置类上；
    作用：开启异步任务支持。
~~~

IOC相关
~~~
@Controller：
@Service：
@Repository：
@Component：
@Autowired：属性、方法参数；
@Resource：
@Qualifier：
    位置：被注入的属性(对象)上；
    作用：与@Autowire联用，可按byName注入；
    举例：@Qualifier(value = "dataSource")；

@Bean：
    位置：方法；
    作用：注解在方法上，声明当前方法的返回值为一个Bean。用于使用注解声明非自定义的bean和返回确定集合数据；
    举例：@Bean(name = "dataSource", destroyMethod = "", initMethod = "")
    属性：
        name：相当于beanId，如果没有此属性，则beanId = 方法名；
        destroyMethod：销毁方法，是被声明类中的方法；
        initMethod：初始化方法，是被声明类中的方法；
    等价：<bean id="user" class="com.atguigu.spring5.a1ioc.xml.a1create.User"/>
    注意：此注解所有的类"必须被@Configuration修饰，或被其它@Configuration修饰的类@Import"；

@Value：
    位置：属性、方法参数
    作用：值注入，经常与Sping EL表达式语言一起使用，注入普通字符，系统属性，表达式运算结果，其他Bean的属性，文件内容，网址请求内容，配置文件属性值等等；
    举例：
        @Value(value = "张三")
        @Value(value = "${jdbc.url}") // 从properties文件中取值；
        @Value(value = "#{userService.name}") // 注入bean为userService的name属性
        @Value("#{systemProperties['os.name']}") // 注入操作系统属性
        @Value("#{ T(java.lang.Math).random() * 100.0 }") //注入表达式结果
        @Value("classpath:com/hry/spring/configinject/config.txt") // 注入文件资源，Resource类型
        @Value("http://www.baidu.com") // 注入URL资源，Resource类型
    注意，从properties中通过key取值时，key尽量是a.b的形式，不然取${username}会取的是window登陆用户的用户名，建议用${jdbc.username}。
~~~

Aop
~~~
@Aspect：
    位置：类；
    作用：声明一个切面（就是说这是一个额外功能），即增加的功能。

@PointCut：
    位置：方法；
    作用：声明切点，即定义拦截规则，确定有哪些方法会被切入，其它的通知的切入点表达式引用此注解修饰的方法名如a()，即可使用此切入点表达式；
    举例：@Pointcut(value = "execution(public int com.atguigu.spring5.a2aop.anno.User.*(..))")
    属性：
        value：切入点表达式。

@After：
    位置：方法；
    作用：后置通过；
    举例：@After(value = "pointcut1()")。
@Before：
@Around：
@AfterReturning：
@AfterThrowing：
@EnableAspectJAutoProxy：
    位置：启动类上；
    作用：启动生成代理对象，启用后proxyTargetClass默认是true；
    举例：@EnableAspectJAutoProxy(proxyTargetClass = true)。

~~~

类相关：
~~~
@Async：
    位置：方法、类；
    作用：注解在方法上标示这是一个异步方法，在类上标示这个类所有的方法都是异步方法。
~~~

事务相关：
~~~
@EnableTransactionManagement
    位置：启动类；
    作用：使事务注解可以生效；
    举例：@EnableTransactionManagement(proxyTargetClass = true) 本质是aop，需要生成代理类

@Transactional
    位置：类、方法；
    作用：为被修饰的类或方法添加事务；
    属性：
        propagation：事务传播行为
        isolation：事务隔离级别
        timeout：超时时间
        readOnly：是否只读
        rollbackFor：回滚
        rollbackForClassName：
        noRollbackFor：不回滚
        noRollbackForClassName：
~~~








