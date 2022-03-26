@[TOC](Spring boot)

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

8、目录介绍

~~~
src
    main
        java：代码目录
        resources：资源目录
            static：静态资源
            templates：页面
            application.properties
~~~


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
**通过Spring容器直接获取本配置类中@Bean注解方法返回的对象，是单例对象。但是通过Spring容器先获取本配置类实例a，再通过a.f1()调用生成的对象并不一定是单例，通过proxyBeanMethods控制**
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
所有的派生类注解本质都是：@Conditional(Condition.class)，不过不同的派生注解有不同的Condition实现

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

@ConfigurationProperties：把properties配置文件的信息，读取并根据名称注入到对应的属性中。因为只有被ioc容器管理才能进行这样的操作，所以需要和"@Component、@Bean或@EnableConfigurationProperties"等一起使用。
    prefix：在application.properties中key的前缀

@EnableConfigurationProperties注解的作用是：使使用 @ConfigurationProperties 注解(因为不是自定义的类，没办法添加@Component注解)的类生效，即使其被ioc容器管理，当然被@EnableConfigurationProperties注解的类，也必须是组件。
    value：被@ConfigurationProperties注解了，但没被当作组件的类，beanName为"前缀-类全限定名"。

## 3.3 自动配置原理入门
### 3.3.1 引导加载自动配置类

如果类的一个注解由多个其它注解标注，那么相当于其它注解直接标注在类上。
@SpringBootApplication：放在主类上的注解，主要包含3个注解：
~~~
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
    excludeFilters = {@Filter(
    type = FilterType.CUSTOM,
    classes = {TypeExcludeFilter.class}
    ), @Filter(
    type = FilterType.CUSTOM,
    classes = {AutoConfigurationExcludeFilter.class}
    )}
)
~~~

对这3个注解进行说明：
~~~
@SpringBootConfiguration：
    @Configuration：表示这个类是个配置类。

@ComponentScan：扫包。

@EnableAutoConfiguration：
    @AutoConfigurationPackage： // 配置默认扫描本类所在的包，及其子包
        @Import(AutoConfigurationPackages.Registrar.class)：
            AutoConfigurationPackages.Registrar.class：默认扫描的包
                registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry)
                metadata：注解的信息，根据此信息可以获取注解所标注的类的包及其子包；
                registry：注册器，通过引注册器扫描通过metadata获取的所有包中的组件。
    @Import(AutoConfigurationImportSelector.class)： // 配置默认装载哪些组件，根据"META-INF/spring.factories"中的全限定名
        自动配置类
        AutoConfigurationImportSelector.class implements ImportSelector {
            String[] selectImports(AnnotationMetadata importingClassMetadata) {
                getAutoConfigurationEntry(annotationMetadata) {
                    // 获取所有的自动配置
                    getCandidateConfigurations(annotationMetadata, attributes) {
                        SpringFactoriesLoader.loadFactoryNames(getSpringFactoriesLoaderFactoryClass(), getBeanClassLoader()) {
                            loadSpringFactories(classLoader).getOrDefault(factoryTypeName, Collections.emptyList()) {
                                Enumeration<URL> urls = (classLoader != null ?
					                classLoader.getResources(FACTORIES_RESOURCE_LOCATION) : // 从这儿获取需要自动装配的类全限定名
					                ClassLoader.getSystemResources(FACTORIES_RESOURCE_LOCATION)); // 从这儿获取需要自动装配的类全限定名
					                // FACTORIES_RESOURCE_LOCATION = "META-INF/spring.factories"
					                默认扫描我们当前系统里面所有META-INF/spring.factories位置的文件
                                    spring-boot-autoconfigure-2.3.4.RELEASE.jar包里面也有META-INF/spring.factories
                            }
                        }
                    }
                }
            }
        }：
~~~

### 3.3.2 按需开启自动配置项

虽然我们127个场景的所有自动配置启动的时候默认全部加载。xxxxAutoConfiguration
按照条件装配规则（@Conditional），最终会按需配置。
在前一小节中，我们知道在"META-INF/spring.factories"中所有的类都会被装载，那么究竟是如何被装载的？
以MultipartAutoConfiguration类分析，在哪些情况下加载、哪些情况下不加载，加载完成后的bean如何设置为boot想要的名称。
~~~
@Configuration(proxyBeanMethods = false) 
@ConditionalOnClass({ Servlet.class, StandardServletMultipartResolver.class, MultipartConfigElement.class }) // 只有在有下面这3个类时才装载，如果是以Spring WebFlux开发，则不会装载
@ConditionalOnProperty(prefix = "spring.servlet.multipart", name = "enabled", matchIfMissing = true) // application.properties中如果有"spring.servlet.multipart.enabled"的k，则装载此类
@ConditionalOnWebApplication(type = Type.SERVLET) // 当为web工程时装载
@EnableConfigurationProperties(MultipartProperties.class) // 装载MultipartProperties.class，从properties文件中获取属性，赋值为MultipartProperties类
public class MultipartAutoConfiguration {

	private final MultipartProperties multipartProperties;

	public MultipartAutoConfiguration(MultipartProperties multipartProperties) {
		this.multipartProperties = multipartProperties;
	}

	@Bean
	@ConditionalOnMissingBean({ MultipartConfigElement.class, CommonsMultipartResolver.class })
	public MultipartConfigElement multipartConfigElement() {
		return this.multipartProperties.createMultipartConfig();
	}

    // 注册bean，并将beanName设置为multipartResolver。这样springboot就会把beanName设置它默认的名称
	@Bean(name = DispatcherServlet.MULTIPART_RESOLVER_BEAN_NAME)
	// 当容器中没有MultipartResolver类型的bean时进行装载，并
	@ConditionalOnMissingBean(MultipartResolver.class)
	public StandardServletMultipartResolver multipartResolver() {
		StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
		multipartResolver.setResolveLazily(this.multipartProperties.isResolveLazily());
		return multipartResolver;
	}

}
~~~

### 3.3.3 2种修改默认配置方式

1、自己注册一个相同类开的bean；
2、修改application.properties中对应的配置参数。

总结：
SpringBoot先加载所有的自动配置类  xxxxxAutoConfiguration
每个自动配置类按照条件进行生效，默认都会绑定配置文件指定的值。xxxxProperties里面拿。xxxProperties和配置文件进行了绑定
生效的配置类就会给容器中装配很多组件
只要容器中有这些组件，相当于这些功能就有了
定制化配置
    用户直接自己@Bean替换底层的组件
    用户去看这个组件是获取的配置文件什么值就去修改，xxxxxAutoConfiguration ---> 组件  ---> xxxxProperties里面拿值  ----> application.properties。

### 3.3.4 最佳实践

引入场景依赖
    https://docs.spring.io/spring-boot/docs/current/reference/html/using-spring-boot.html#using-boot-starter
查看自动配置了哪些（选做）
    自己分析，引入场景对应的自动配置一般都生效了
    application.properties中debug=true开启自动配置报告。Negative(不生效的配置)\Positive(生效的配置)
是否需要修改
    参照文档修改配置项
        https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html#common-application-properties
        自己分析。xxxxProperties绑定了配置文件的哪些。
    自定义加入或者替换组件
        @Bean、@Component。。。
    自定义器  XXXXXCustomizer；
    ......

## 3.4 开发技巧
### 3.4.1 Lombok

导包：
~~~
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
~~~

常用注解：
~~~
@NonNull：可以用在成员方法或者构造方法的参数前面，会自动产生一个关于此参数的非空检查，如果参数为空，则抛出一个空指针异常。
@Cleanup：注解用在变量前面，可以保证此变量代表的资源会被自动关闭，默认是调用资源的close()方法，如果该资源有其它关闭方法，可使用@Cleanup(“methodName”)来指定要调用的方法。
@Getter/@Setter：成员变量前面，相当于为成员变量生成对应的get和set方法，同时还可以为生成的方法指定访问修饰符，当然，默认为public。
@ToString、@EqualsAndHashCode：这两个注解也比较好理解，就是生成toString，equals和hashcode方法，同时后者还会生成一个canEqual方法，用于判断某个对象是否是当前类的实例，生成方法时只会使用类中的非静态和非transient成员变量，这些都比较好理解，就不举例子了。
当然，这两个注解也可以添加限制条件，例如用@ToString(exclude={“param1”，“param2”})来排除param1和param2两个成员变量，或者用@ToString(of={“param1”，“param2”})来指定使用param1和param2两个成员变量，@EqualsAndHashCode注解也有同样的用法。
@NoArgsConstructor/@RequiredArgsConstructor /@AllArgsConstructor：这三个注解都是用在类上的，第一个和第三个都很好理解，就是为该类产生无参的构造方法和包含所有参数的构造方法，第二个注解则使用类中所有带有@NonNull注解的或者带有final修饰的成员变量生成对应的构造方法，当然，和前面几个注解一样，成员变量都是非静态的，另外，如果类中含有final修饰的成员变量，是无法使用@NoArgsConstructor注解的。
@Data、@Value：综合了3,4,5和6里面的@RequiredArgsConstructor注解，其中@RequiredArgsConstructor使用了类中的带有@NonNull注解的或者final修饰的成员变量，它可以使用@Data(staticConstructor=”methodName”)来生成一个静态方法，返回一个调用相应的构造方法产生的对象。
@SneakyThrows：这个注解用在方法上，可以将方法中的代码用try-catch语句包裹起来，捕获异常并在catch中用Lombok.sneakyThrow(e)把异常抛出，可以使用@SneakyThrows(Exception.class)的形式指定抛出哪种异常。
@Synchronized：注解用在类方法或者实例方法上，效果和synchronized关键字相同，区别在于锁对象不同，对于类方法和实例方法，synchronized关键字的锁对象分别是类的class对象和this对象，而@Synchronized得锁对象分别是私有静态final对象LOCK和私有final对象LOCK和私有final对象lock，当然，也可以自己指定锁对象.
@Log：注解用在类上，可以省去从日志工厂生成日志对象这一步，直接进行日志记录，具体注解根据日志工具的不同而不同，同时，可以在注解中使用topic来指定生成log对象时的类名。
~~~

### 3.4.2 dev-tools

项目或者页面修改以后：Ctrl+F9；
~~~
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional>
</dependency>
~~~

### 3.4.3 Spring Initailizr(项目初始化向导)

~~~
File
new Module
Spring Initializr，修改右侧内容
选boot版本，勾选需要的内容，如web、mysql、redis等。
~~~













