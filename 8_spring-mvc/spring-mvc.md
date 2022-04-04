@[TOC](第4章 jvm)

# 0 格式
## 0.0 格式要求
html代码后面一定加一行空行；
标题后面需要添加空行；
https://www.cnblogs.com/cndarren/p/14415213.html
视频地址：https://www.bilibili.com/video/BV1Ry4y1574R?p=7&spm_id_from=pageDriver
每个大标题之间空2行，每个小标题之间空1行
<span style="color: red;"></span>
<hr style="height: 10px; background: green;"/>

# 1 SpringMVC简介
## 1.1 什么是MVC

MVC是一种软件架构的思想，将软件按照模型、视图、控制器来划分

M：Model，模型层，指工程中的JavaBean，作用是处理数据
    JavaBean分为两类：
        一类称为实体类Bean：专门存储业务数据的，如 Student、User 等
        一类称为业务处理 Bean：指 Service 或 Dao 对象，专门用于处理业务逻辑和数据访问。

V：View，视图层，指工程中的html或jsp等页面，作用是与用户进行交互，展示数据

C：Controller，控制层，指工程中的servlet，作用是接收请求和响应浏览器

MVC的工作流程： 用户通过视图层发送请求到服务器，在服务器中请求被Controller接收，Controller调用相应的Model层处理请求，处理完毕将结果返回到Controller，Controller再根据请求处理的结果找到相应的View视图，渲染数据后最终响应给浏览器。

## 1.2 什么是SpringMVC

SpringMVC是Spring的一个后续产品，是Spring的一个子项目

SpringMVC 是 Spring 为表述层开发提供的一整套完备的解决方案。在表述层框架历经 Strust、WebWork、Strust2 等诸多产品的历代更迭之后，目前业界普遍选择了 SpringMVC 作为 Java EE 项目表述层开发的首选方案。

注：三层架构分为表述层（或表示层）、业务逻辑层、数据访问层，表述层表示前台页面和后台servlet

## 1.3 SpringMVC特点

Spring 家族原生产品，与 IOC 容器等基础设施无缝对接
**基于原生的Servlet，通过了功能强大的前端控制器DispatcherServlet，对请求和响应进行统一处理，本质还是Servlet，但是进行了简化，就像jsp对Servlet画界面的简化一样**
表述层各细分领域需要解决的问题全方位覆盖，提供全面解决方案
代码清新简洁，大幅度提升开发效率
内部组件化程度高，可插拔式组件即插即用，想要什么功能配置相应组件即可
性能卓著，尤其适合现代大型、超大型互联网项目要求

## 1.4 SpringMVC的运行流程

在tomcat启动时，初始化DispatcherServlet extends FrameworkServlet extends HttpServletBean，调用init()
~~~
init() {
    this.initServletBean() {
        this.webApplicationContext = this.initWebApplicationContext() {
            this.onRefresh(wac) {
                this.initStrategies(context) {
                    this.initMultipartResolver(context);
                    this.initLocaleResolver(context);
                    this.initThemeResolver(context);
                    this.initHandlerMappings(context);
                    this.initHandlerAdapters(context);
                    this.initHandlerExceptionResolvers(context);
                    this.initRequestToViewNameTranslator(context);
                    
                    // 获取配置的视图解析器，会查询ViewResolver接口的所有实现类，就会查到自己配置的实现类
                    this.initViewResolvers(context);
                    this.initFlashMapManager(context);
                }
            }
        }
    }
}

~~~


http://localhost:ip/path
~~~
第一步:用户发起请求到前端控制器(DispatcherServlet)

DispatcherServlet {
    // 调用service()方法，javaweb内容，请求servlet会默认运行service()方法
    service() {
        this.doService();
    }
    
    doService(){
        doDispatch();
    }
    
    doDispatch() {
        // 第二步：获取"执行链(HandlerExecutionChain)"，使用了责任链模式，里面包含了"拦截器、Handler(执行程序)"
        HandlerExecutionChain mappedHandler = getHandler(request) {
            if (this.handlerMappings != null) {
                Iterator var2 = this.handlerMappings.iterator();
                while(var2.hasNext()) {
                    // 第二步：遍历获取合适的HandlerMapping，因为不同类型的HandlerMapping
                    只能处理各自类型的请求，需要遍历获取合适的。
                    HandlerMapping mapping = (HandlerMapping)var2.next();
                    
                    // 第三步：且HandlerMapping根据request的访问路径找到对应的 Handler(执行程序)，
                    并将其与一堆 HandlerInterceptor(拦截器)封装到 HandlerExecutionChain 对象中。
                    HandlerExecutionChain handler = mapping.getHandler(request);
                    if (handler != null) {
                        return handler;
                    }
                }
            }
        }
        
        // 第四步："前端控制器(DispatcherServlet)"根据handle获取对应的
        "处理器适配器(HandlerAdapter)"，因为每一种处理器只能处理各自的handler，
        所以需要遍历看哪个能处理
        HandlerAdapter ha = this.getHandlerAdapter(mappedHandler.getHandler()) {
            if (this.handlerAdapters != null) {
                Iterator var2 = this.handlerAdapters.iterator();
        
                while(var2.hasNext()) {
                    HandlerAdapter adapter = (HandlerAdapter)var2.next();
                    // 遍历获取合适的处理器
                    if (adapter.supports(handler)) {
                        return adapter;
                    }
                }
            }
        }
        
        // 第4.5步 执行handler前进行拦截器校验
        mappedHandler.applyPreHandle(processedRequest, response)
        
        // 第五步：处理器适配器去执行Handler，并获取ModelAndView，此处即是调用被@RequestMapping注解的方法
        ModelAndView mv = ha.handle(request, response, mappedHandler.getHandler());
        
        // 执行handler后的拦截器校验
        mappedHandler.applyPostHandle(processedRequest, response, mv)
        
        // 第六步：前端控制器请求视图解析器（ViewResolver）去进行视图解析，并在渲染完成后进行拦截
        this.processDispatchResult(req, resp, mappedHandler, mv, (Exception)dispatchException) {
            render(mv, request, response) {
                // 第六步：进行视图解析及处理，对视图进行渲染，此时view就是SpringMVC.xml中配置的视图解析器
                view.render(mv.getModelInternal(), request, response);
            }
            
            // 第七步：进行渲染后的拦截器校验。
            mappedHandler.triggerAfterCompletion(request, response, (Exception)null);
        }
        
    }
    
}

第十一步：前端控制器向用户响应结果
~~~



# 2 工程创建
## 2.1 工程目录

~~~
src：
    main：
        java
        resources
        webapp
            WEB-INF:
                web.xml
~~~

## 2.2 创建流程

~~~
new -> module -> 创建一个maven工程

导入依赖：
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.3.1</version>
    </dependency>

    <!-- ServletAPI，provided是说开发的时候使用，打包的时候不会打进去，因为服务器容器中有 -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>3.1.0</version>
        <scope>provided</scope>
    </dependency>
    
    <!-- Spring5和Thymeleaf整合包 -->
    <dependency>
        <groupId>org.thymeleaf</groupId>
        <artifactId>thymeleaf-spring5</artifactId>
        <version>3.0.12.RELEASE</version>
    </dependency>

添加web.xml文件：
    在main包下，创建webapp(灰包中间偏右有蓝点)\WEB-INF；
    File -> project structure -> modules -> 选中项目下的Web；
    Deployment Descriptors -> + web.xml -> 选择路径将web.xml放到webapp\WEB-INF下，就会自动添加web.xml文件。
~~~

注：由于 Maven 的传递性，我们不必将所有需要的包全部配置依赖，而是配置最顶端的依赖，其他靠传递性导入。

## 2.3 配置web.xml

注册SpringMVC的前端控制器DispatcherServlet

### 2.3.1 默认配置方式(用得少)

此配置作用下，SpringMVC的配置文件默认位于WEB-INF下，**默认名称为\<servlet-name\>-servlet.xml(必须以这个结尾)**，例如，以下配置所对应SpringMVC的配置文件位于WEB-INF下，文件名为springMVC-servlet.xml。
~~~
<!-- 配置SpringMVC的前端控制器，对浏览器发送的请求统一进行处理 -->
<servlet>
    <servlet-name>springMVC</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>springMVC</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
~~~

### 2.3.2 扩展配置方式

可通过init-param标签设置SpringMVC配置文件的位置和名称，通过load-on-startup标签设置SpringMVC前端控制器DispatcherServlet的初始化时间。
即使DispatcherServlet控制所有servlet的请求，但其本质还是servlet，所以需要配置到web.xml中
~~~
<!-- 配置SpringMVC的前端控制器，对浏览器发送的请求统一进行处理 -->
<servlet>
    <servlet-name>springMVC</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <!-- 通过初始化参数指定SpringMVC配置文件的位置和名称 -->
    <init-param>
        <!-- contextConfigLocation为固定值 -->
        <param-name>contextConfigLocation</param-name>
        <!-- 使用classpath:表示从类路径查找配置文件，例如maven工程中的src/main/resources -->
        <param-value>classpath:springMVC.xml</param-value>
    </init-param>
    <!-- 
        作为框架的核心组件，在启动过程中有大量的初始化操作要做
        而这些操作放在第一次请求时才执行会严重影响访问速度
        因此需要通过此标签将启动控制DispatcherServlet的初始化时间提前到服务器启动时
    -->
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>springMVC</servlet-name>
    <!--
        设置springMVC的核心控制器所能处理的请求的请求路径
        /所匹配的请求可以是/login或.html或.js或.css方式的请求路径
        但是/不能匹配.jsp请求路径的请求
    -->
    <url-pattern>/</url-pattern>
</servlet-mapping>
~~~

## 2.4 创建请求控制器

由于前端控制器对浏览器发送的请求进行了统一的处理，但是具体的请求有不同的处理过程，因此需要创建处理具体请求的类，即请求控制器。
请求控制器中每一个处理请求的方法称为控制器方法。
因为SpringMVC的控制器由一个POJO（普通的Java类）担任，因此需要通过@Controller注解将其标识为一个控制层组件，交给Spring的IOC容器管理，此时SpringMVC才能够识别控制器的存在
~~~
@Controller
public class HelloController {
    
    @RequestMapping(value = "/hello")
    public String hello() {
        // 前缀 + hello + 后缀，是需要返回的页面。
        return "hello";
    }

}
~~~


## 2.5 创建springMVC的配置文件

springMVC.xml
配置自动扫包：
~~~
<!-- 自动扫描包 -->
<context:component-scan base-package="com.atguigu.mvc.controller"/>
~~~

配置Thymeleaf视图解析器或其它视图解析器(**只配置一个，不然MVC不知道使用的是哪一个**)：
~~~
<!-- Thymeleaf视图解析器 -->
<bean id="viewResolver" class="org.thymeleaf.spring5.view.ThymeleafViewResolver">
    <property name="order" value="1"/>
    <property name="characterEncoding" value="UTF-8"/>
    <property name="templateEngine">
        <bean class="org.thymeleaf.spring5.SpringTemplateEngine">
            <property name="templateResolver">
                <bean class="org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver">
    
                    <!-- 视图前缀 -->
                    <property name="prefix" value="/WEB-INF/templates/"/>
    
                    <!-- 视图后缀 -->
                    <property name="suffix" value=".html"/>
                    <property name="templateMode" value="HTML5"/>
                    <property name="characterEncoding" value="UTF-8" />
                </bean>
            </property>
        </bean>
    </property>
</bean>


<!-- 
    配置内部资源解析器：InternalResourceViewResolver为"内部资源视图解析器"，是日常开发中最常用的视图解析器类型。它是 URLBasedViewResolver 的子类，拥有 URLBasedViewResolver 的一切特性。InternalResourceViewResolver 能自动将返回的视图名称解析为 InternalResourceView 类型的对象。InternalResourceView 会把 Controller 处理器方法返回的模型属性都存放到对应的 request 属性中，然后通过 RequestDispatcher 在服务器端把请求 forword 重定向到目标 URL。也就是说，使用 InternalResourceViewResolver 视图解析时，无需再单独指定 viewClass 属性。
-->
<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="viewClass" value="org.springframework.web.servlet.view.InternalResourceViewResolver"/> <!--可以省略-->
    <!--前缀-->
    <property name="prefix" value="/WEB-INF/jsp/"/>
    <!--后缀-->
    <property name="suffix" value=".jsp"/>  
 </bean>
~~~

配置默认servlet处理器：
注解的目的：开放对静态资源的访问，例如html、js、css、jpg
~~~
<mvc:default-servlet-handler />
~~~

开启mvc注解驱动：
~~~
<mvc:annotation-driven>
    <mvc:message-converters>
        <!-- 处理响应中文内容乱码 -->
        <bean class="org.springframework.http.converter.StringHttpMessageConverter">
            <property name="defaultCharset" value="UTF-8" />
            <property name="supportedMediaTypes">
                <list>
                    <value>text/html</value>
                    <value>application/json</value>
                </list>
            </property>
        </bean>
    </mvc:message-converters>
</mvc:annotation-driven>
~~~

## 2.6 注意
### 2.6.1 url-pattern中使用/和/*的区别：

~~~
首先为了DispatcherServlet能处理所有的请求，我们必须能匹配所有请求，那么必须使用"/"(默认匹配)或"/*"(路径匹配)，为什么不能使用"/*"。
相关内容可查看java-web中路径匹配内容，首先这不是正则表达式。
匹配模式优先级从高到低依次为(http://localhost:8080/上下文路径context/path?params)，：
    精确匹配："url-pattern"与"/path"一模一样；
    路径匹配："/*"，会匹配"http://localhost:8080/context/"后所有的请求；
    后缀匹配："*.jsp"，会匹配所有的".jsp"请求，如果没有显式配置，则tomcat/conf/web.xml中有默认JspServlet去匹配并处理。且路径匹配不能与后缀匹配一起使用，即/*.jsp、/user/*.action是会启动报错的。
    默认匹配："/"，会匹配所有请求，如果没有显式配置，则tomcat/conf/web.xml中有默认DefaultServlet去匹配并处理，但我们这里显式配置了DispatcherServlet。
如果我们DispatcherServlet用"/*"来匹配，则会在路径匹配那里匹配所有的请求，包括jsp请求，但DispatcherServlet不会为jsp页面去配置一个RequestMapping，那么匹配不到就会报404，那么就不能访问jsp文件了，所以要使用"/"，这样会先进行后缀匹配，将jsp页面处理交给tomcat/conf/web.xml中的JspServlet处理，如果不是jsp页面，再由DispatcherServlet处理。
但是设置为了"/"，又会有另一个问题，无法处理静态页面。
~~~

### 2.6.2 <mvc:default-servlet-handler />作用

在2.6.1我们对DispatcherServlet使用的"/"去匹配，但是无法访问静态页面，如css、html、js等 。
因为tomcat/conf/web.xml里面使用了"/"匹配DefaultServlet来处理静态资源数据，但现在"/"被DispatcherServlet使用了，DispatcherServlet不会为任何一个资源页面去配置一个RequestMapping，那么匹配不到就会报404。
解决方案：需要在SpringMVC.xml加上配置"\<mvc:default-servlet-handler/\>"，启动时会注册DefaultServletHttpRequestHandler到dispatcherServlet的handlerMapping中，当有访问资源的请求时，被DefaultServletHttpRequestHandler匹配，就可以处理，它内部会调用tomcat的DefaultServlet去处理静态页面。

### 2.6.3 <mvc:resources />

也可以设置访问静态资源，<mvc:resources location="/static/" mapping="/resources/**"/>，
location：静态资源的项目中的位置；
mapping：请求路径为resources时，都会去location对应的类去查找。
和<mvc:default-servlet-handler />只使用一个就行，不要同时使用。

### 2.6.4 \<mvc:annotation-driven\>作用

\<mvc:annotation-driven\>会自动注册RequestMappingHandlerMapping与RequestMappingHandlerAdapter两个Bean,这是Spring MVC为@Controller分发请求所必需的，并且提供了数据绑定支持。
即有了这个注解，mvc就会自动扫描，@Controller和@RequestMapping注解的类和方法，进行请求时，就能根据path访问对应的handler，进而转发到页面。

## 2.7 总结

浏览器发送请求，若请求地址符合前端控制器的url-pattern，该请求就会被前端控制器DispatcherServlet处理。前端控制器会读取SpringMVC的核心配置文件，通过扫描组件找到控制器，将请求地址和控制器中@RequestMapping注解的value属性值进行匹配，若匹配成功，该注解所标识的控制器方法就是处理请求的方法。处理请求的方法需要返回一个字符串类型的视图名称，该视图名称会被视图解析器解析，加上前缀和后缀组成视图的路径，通过Thymeleaf对视图进行渲染，最终转发到视图所对应页面。



# 3 @RequestMapping、@PathVariable注解
## 3.1 功能

从注解名称上我们可以看到，@RequestMapping注解的作用就是将请求和处理请求的控制器方法关联起来，建立映射关系。 SpringMVC 接收到指定的请求，就会来找到在映射关系中对应的控制器方法来处理这个请求。

## 3.2 位置

@RequestMapping标识一个类：设置映射请求的请求路径的初始信息；
@RequestMapping标识一个方法：设置映射请求请求路径的具体信息。

## 3.3 @RequestMapping属性

~~~
@RequestMapping(
        value = {"/a?a", "/a*b", "/**/aa"},
        method = {RequestMethod.GET, RequestMethod.POST},
        params = {"param1", "!param2", "param3=c", "param4!=d"},
        headers = {"header", "!header", "header=localhost:8080", "!header!=localhost:8080"}
)
public String f1() {
    return "abc";
}
~~~

@value属性(选一个满足即可)：
~~~
通过请求的请求地址匹配请求映射，即前台输入请求地址，找到@RequestMapping中@value的数组中包含此地址注解对应的方法；
属性值是一个字符串类型的数组，表示该请求映射能够匹配多个请求地址所对应的请求，即只要请求符合数组中的任一一个就能进行匹配；
必须设置，至少通过请求地址匹配请求映射。
SpringMVC支持ant风格的路径：
    ?：表示任意的单个字符；
    *：表示任意的0个或多个字符；
    **：表示任意的一层或多层目录，注意：在使用时，只能使用/**的方式，不能使用/a**b/c。
~~~

@method属性(选一个满足即可)：
~~~
method属性通过请求的请求方式（get或post）匹配请求映射；
method属性是一个RequestMethod类型的数组，表示该请求映射能够匹配多种请求方式的请求；
若当前请求的请求地址满足请求映射的value属性，但是请求方式不满足method属性，则浏览器报错405：Request method ‘POST’ not supported。
注：
1、对于处理指定请求方式的控制器方法，SpringMVC中提供了@RequestMapping的派生注解
    处理get请求的映射–>@GetMapping
    处理post请求的映射–>@PostMapping
    处理put请求的映射–>@PutMapping
    处理delete请求的映射–>@DeleteMapping
2、常用的请求方式有get，post，put，delete
    但是目前浏览器只支持get和post，若在form表单提交时，为method设置了其他请求方式的字符串（put或delete），则按照默认的请求方式get处理
    若要发送put和delete请求，则需要通过spring提供的过滤器HiddenHttpMethodFilter，在RESTful部分会讲到
~~~

@params属性(必须都满足)：
~~~
@RequestMapping注解的params属性通过请求的请求参数匹配请求映射
@RequestMapping注解的params属性是一个字符串类型的数组，可以通过四种表达式设置请求参数和请求映射的匹配关系
    "param"：要求请求映射所匹配的请求必须携带param请求参数
    "!param"：要求请求映射所匹配的请求必须不能携带param请求参数
    "param=value"：要求请求映射所匹配的请求必须携带param请求参数且param=value
    "param!=value"：要求请求映射所匹配的请求必须携带param请求参数但是param!=value
注：
    若当前请求满足@RequestMapping注解的value和method属性，但是不满足params属性，此时页面回报错400：Parameter conditions “username, password!=123456” not met for actual request parameters: username={admin}, password={123456}
~~~

@headers属性(必须都满足)(了解)：
~~~
@RequestMapping注解的headers属性通过请求的请求头信息匹配请求映射
@RequestMapping注解的headers属性是一个字符串类型的数组，可以通过四种表达式设置请求头信息和请求映射的匹配关系
    "header"：要求请求映射所匹配的请求必须携带header请求头信息
    "!header"：要求请求映射所匹配的请求必须不能携带header请求头信息
    "header=value"：要求请求映射所匹配的请求必须携带header请求头信息且header=value
    "header!=value"：要求请求映射所匹配的请求必须携带header请求头信息且header!=value
若当前请求满足@RequestMapping注解的value和method属性，但是不满足headers属性，此时页面显示404错误，即资源未找到
~~~

## 3.4 @RequestMapping注解的方法的返回值

如果有@ResponseBody修饰：类似用resp.getWrite().println()输出，不会返回页面
    返回对象，会被转为json，或json数组(需要导jackson包)；
    返回字符串，则会返回字符串。
如果没有@ResponseBody修饰：
    返回字符串，则进行转发和重定向；
    返回void或null，用一个空白网页展示，想要回显的内容。



# 4 RESTful
## 4.1 RESTful简介

REST：Representational State Transfer，表现层资源状态转移。

资源：
资源是一种看待服务器的方式，即，将服务器看作是由很多离散的资源组成。每个资源是服务器上一个可命名的抽象概念。因为资源是一个抽象的概念，所以它不仅仅能代表服务器文件系统中的一个文件、数据库中的一张表等等具体的东西，可以将资源设计的要多抽象有多抽象，只要想象力允许而且客户端应用开发者能够理解。与面向对象设计类似，资源是以名词为核心来组织的，首先关注的是名词。一个资源可以由一个或多个URI来标识。URI既是资源的名称，也是资源在Web上的地址。对某个资源感兴趣的客户端应用，可以通过资源的URI与其进行交互。

资源的表述：
资源的表述是一段对于资源在某个特定时刻的状态的描述。可以在客户端-服务器端之间转移（交换）。资源的表述可以有多种格式，例如HTML/XML/JSON/纯文本/图片/视频/音频等等。资源的表述格式可以通过协商机制来确定。请求-响应方向的表述通常使用不同的格式。

状态转移：
状态转移说的是：在客户端和服务器端之间转移（transfer）代表资源状态的表述。通过转移和操作资源的表述，来间接实现操作资源的目的。

## 4.2 RESTful的实现

具体说，就是 HTTP 协议里面，四个表示操作方式的动词：GET、POST、PUT、DELETE。
它们分别对应四种基本操作：GET 用来获取资源，POST 用来新建资源，PUT 用来更新资源，DELETE 用来删除资源。
REST 风格提倡 URL 地址使用统一的风格设计，从前到后各个单词使用斜杠分开，不使用问号键值对方式携带请求参数，而是将要发送给服务器的数据作为 URL 地址的一部分，以保证整体风格的一致性。

|操作|传统方式|REST风格|
|:--|:--|:--|
|查询|getUserById?id=1|user/1->get请求方式|
|保存|saveUser|user->post请求方式|
|删除|deleteUser?id=1|user/1->delete请求方式|
|更新|updateUser|user->put请求方式|

## 4.3 HiddenHttpMethodFilter

由于浏览器只支持发送get和post方式的请求，那么该如何发送put和delete请求呢？
SpringMVC 提供了 HiddenHttpMethodFilter 帮助我们将 POST 请求转换为 DELETE 或 PUT 请求
HiddenHttpMethodFilter 处理put和delete请求的条件：
a>当前请求的请求方式必须为post
b>当前请求必须传输请求参数"\_method"
满足以上条件，HiddenHttpMethodFilter 过滤器就会将当前请求的请求方式转换为请求参数_method的值，因此请求参数_method的值才是最终的请求方式。

在web.xml中注册HiddenHttpMethodFilter
~~~
<filter>
    <filter-name>HiddenHttpMethodFilter</filter-name>
    <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
</filter>
<filter-mapping>
    <filter-name>HiddenHttpMethodFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
~~~

~~~
注：
目前为止，SpringMVC中提供了两个过滤器：CharacterEncodingFilter和HiddenHttpMethodFilter

在web.xml中注册时，必须先注册CharacterEncodingFilter，再注册HiddenHttpMethodFilter
原因：
    在 CharacterEncodingFilter 中通过 request.setCharacterEncoding(encoding) 方法设置字符集，
    request.setCharacterEncoding(encoding) 方法要求前面不能有任何获取请求参数的操作
    而HiddenHttpMethodFilter 恰恰有一个获取请求方式的操作：
        String paramValue = request.getParameter(this.methodParam);
~~~

## 4.4 实现原理

注意http只有get、post请求，为了让DispatcherServlet接收到的req为DELETE请求，必须在拦截器(hiddenHttpMethodFilter为已有实现的拦截器)进行如下操作：
1、首先请求必须是Post，将Request赋值给另一个自定义的ARequest；
2、request.getParameter("_method")，获取这个值，如果是DELETE，则将ARequest.method = RequestMethod.DELETE；
3、将ARequest传给DispatcherServlet，会去匹配method = DELETE的方法，其它同理。



# 4 SpringMVC获取请求信息
## 4.1 通过ServletAPI获取Req信息

DispatcherServlet通过handler处理时，会通过反射获取request参数类型，如果是HttpServletRequest，则会将DispatcherServlet.service()中的req参数传进去，resp类似。request的操作查看java-web。
~~~
@RequestMapping("/testParam")
public String testParam(HttpServletRequest request){
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    System.out.println("username:"+username+",password:"+password);
    return "success";
}
~~~

## 4.2 通过RequestEntity获取Req信息

在参数位置使用RequestEntity类接收，则会将request信息封装到此类中。
可获取req的其它信息：entity.getHeaders()、getUrl()、getMethod()、getBody()等。
~~~
@RequestMapping(value = "/3restful/reqInfo/RequestEntity", method = RequestMethod.POST)
public String getReqEntity(RequestEntity<String> requestEntity) {
    return sb.toString();
}
~~~

## 4.3 @RequestHeader获取请求头信息

使用@RequestHeader注解形参，获取请求头信息。
@RequestHeader Map<String,String> headerMap：将所有请求头信息封装到map中；
@RequestHeader("HOST") String host：获取某个具体请求头信息。
~~~
@RequestMapping(value = "/3restful/reqInfo/RequestHeader", method = RequestMethod.POST)
public String getReqHeader(@RequestHeader Map<String,String> headerMap, @RequestHeader("HOST") String host) {
    return sb.toString();
}
~~~

## 4.4 @RequestParam

@RequestParam是将请求参数和控制器方法的形参创建映射关系
@RequestParam注解一共有三个属性：
value：指定为形参赋值的请求参数的参数名
required：设置是否必须传输此请求参数，默认值为true
    若设置为true时，则当前请求必须传输value所指定的请求参数，若没有传输该请求参数，且没有设置defaultValue属性，则页面报错400：Required String parameter ‘xxx’ is not present；若设置为false，则当前请求不是必须传输value所指定的请求参数，若没有传输，则注解所标识的形参的值为null
defaultValue：不管required属性值为true或false，当value所指定的请求参数没有传输或传输的值为""时，则使用默认值为形参赋值。
用于get请求或"Content-Type: application/x-www-form-urlencoded"的post请求。
~~~
@RequestMapping(value = "/3restful/reqInfo/ParamName", method = RequestMethod.POST)
public String getParamName(String username, String[] hobby, User user) {
    return sb.toString();
}
~~~

## 4.5 形参或pojo类获取请求参数

在控制器方法的形参位置，设置和请求参数同名的形参，当浏览器发送请求，匹配到请求映射时，在DispatcherServlet中就会将请求参数赋值给相应的形参。
可以在控制器方法的形参位置设置一个实体类类型的形参，此时若浏览器传输的请求参数的参数名和实体类中的属性名一致，那么请求参数就会为此属性赋值。

~~~
http://localhost:8080/testParam?username=admin&password=123456

@RequestMapping("/testParam")
public String testParam(String username, String[] password, User user){
    return "success";
}
~~~
注：
    若请求所传输的请求参数中有多个同名的请求参数，此时可以在控制器方法的形参中设置字符串数组或者字符串类型的形参接收此请求参数。
    若使用字符串数组类型的形参，此参数的数组中包含了每一个数据。
    若使用字符串类型的形参，此参数的值为每个数据中间使用逗号拼接的结果。

## 4.6 @RequestBody获取请求体

@RequestBody可以获取请求体，需要在控制器方法设置一个形参，使用@RequestBody进行标识，当前请求的请求体就会为当前注解所标识的形参赋值。
@RequestBody接收的是一个字符串。如果"Content-Type: application/x-www-form-urlencoded"，则中文会被转码，所以一般用"Content-Type: application/json"的格式。
~~~
@RequestMapping("/testRequestBody")
public String testRequestBody(@RequestBody String requestBody){
    System.out.println("requestBody:"+requestBody);
    return "success";
}
~~~

## 4.7 @CookieValue获取cookie中的值

@CookieValue是将cookie数据和控制器方法的形参创建映射关系，必须有value值，不能像@RequestParam一样用map接收所有cookie信息。
~~~
@RequestMapping(value = "/3restful/reqInfo/CookieValue", method = RequestMethod.POST)
public String getCookieValue(@CookieValue(value = "JSESSIONID", required = true, defaultValue = "没有cookie") String sessionId) {
    return sb.toString();
}
~~~

## 4.8 @RequestAttribute接收request域信息

@RequestAttribute：req.setAttribute("a", "b")，则进行转发后，在转发后的方法或页面中就可以通过req.getAttribute("a")获取b。
~~~
@RequestMapping(value = "/3restful/reqInfo/showReqAttr", method = RequestMethod.POST)
@ResponseBody
public String getReqAttr(@RequestAttribute("field") String field) {
    return sb.toString();
}
~~~

## 4.9 rest风格获取路径参数@PathVariable

对于原始的请求方式：原始方式：/deleteUser?id=1
对于restful的请求方式：/deleteUser/1
SpringMVC路径中的占位符常用于RESTful风格中，当请求路径中将某些数据通过路径的方式传输到服务器中，就可以在相应的@RequestMapping注解的value属性中通过占位符{xxx}表示传输的数据，在通过@PathVariable注解，将占位符所表示的数据赋值给控制器方法的形参

~~~
原始方式"http://localhost:8080/testRest?id=1&username=xuaa"
请求路径"http://localhost:8080/testRest/1/xuaa"
@RequestMapping("/testRest/{id}/{username}")
public String testRest(@PathVariable("id") String id, @PathVariable("username") String username){
    return "success";
}
~~~

@PathVariable(value = "id")，这个根据value属性的id找到@RequestMapping对应的{id}的值，就会直接赋值给参数如的String id。

## 4.10 @MatrixVariable矩阵变量

属性说明：
~~~
value：和属性pathVar的别名;
pathVar：用于指定name-value参数所在的路径片段名称
name：用于指定name-value参数的参数名
required：是否为必填值，默认为false
defaultValue：设置默认值
~~~

启动矩阵变量：
~~~
注解：
@Configuration                                                                                                              
public class SpringBootConfig implements WebMvcConfigurer {                                                                 
   @Override      
   public void configurePathMatch(PathMatchConfigurer configurer) {     
      UrlPathHelper urlPathHelper = new UrlPathHelper();
      urlPathHelper.setRemoveSemicolonContent(false);
      configurer.setUrlPathHelper(urlPathHelper);
  }            
}    

xml版：
<mvc:annotation-driven enable-matrix-variables="true"></mvc:annotation-driven>
~~~

/3restful/rest/path1;a=1,2;b=1;b=张三/path2=4;c=1;d=2
~~~
path可以=一个值，
path1的矩阵变量为：a=1,2;b=1;b=张三。如果一个参数对应多个值，也可以"b=1,张三"这样写。
获取方式：
@RequestMapping(value = "/3restful/rest/{path1}/{path2}", method = RequestMethod.GET)
public String getReqAttr(@MatrixVariable(pathVar = "path1") MultiValueMap path1Map,
                         @MatrixVariable(pathVar = "path1", value = "a") String[] path1aValue
                         ) {
    return sb.toString();
}
~~~

## 4.11 解决获取请求参数的乱码问题

get请求：Tomcat_HOME/conf/server.xml，为Connector添加属性URIEncoding="UTF-8"，或者用String的转码方法。
post请求：必须在使用request前进行设置编码格式，但是当进入到@RequestMapping修饰的方法时，request已经被使用了，再设置编码已经来不及了，所以只有在web过滤器中设置(顺序：监听器->过滤器->servlet)，spring有默认的过滤器，配置即可。或者自定义一个过滤器也可以。**SpringMVC中处理编码的过滤器一定要配置到其他过滤器之前，否则无效。**
~~~
<!--配置springMVC的编码过滤器-->
<filter>
    <filter-name>CharacterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
    <init-param>
        <param-name>forceResponseEncoding</param-name>
        <param-value>true</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>CharacterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
~~~



# 5 SpringMVC进行响应
## 5.1 @ResponseBody

@ResponseBody用于标识一个控制器方法，可以将该方法的返回值直接作为响应报文的响应体响应到浏览器，即返回值的含义不再是跳转页面，需要使用ModelAndView去跳转页面。可以将对象返回，最终在前端变为一个json字符串或json数组。
微服务的交互都是用的json，所以此注解基本上所有的微服务都使用。
标注在类上，则相当于为类中的每个方法添加@ResponseBody注解。
~~~
@RequestMapping("/testResponseBody")
@ResponseBody
public String testResponseBody(){
    return "success";
}

@RequestMapping(value = "/3restful/respObject")
@ResponseBody
public User testRequestBodyAnno() {
    return new User(1001, "张三", "123", null);
}
~~~

## 5.2 @RestController注解

@RestController注解是springMVC提供的一个复合注解，标识在控制器的类上，就相当于为类添加了@Controller注解，并且为其中的每个方法添加了@ResponseBody注解。

## 5.2 ResponseEntity

ResponseEntity用于控制器方法的返回值类型，该控制器方法的返回值就是响应到浏览器的响应报文。主要作用是用于文件下载。这个相当于自定义了一份响应。



# 5 域对象共享数据

查看A4ScopeDataController类。
Model、ModelMap、Map类型的参数其实本质上都是 BindingAwareModelMap 类型的。
~~~
ModelAndView有Model和View的功能
Model主要用于向请求域共享数据
View主要用于设置视图，实现页面跳转
~~~



# 6 SpringMVC的视图

SpringMVC中的视图是View接口，视图的作用渲染数据，将模型Model中的数据展示给用户。
SpringMVC视图的种类很多，默认有转发视图和重定向视图。
当工程引入jstl的依赖，转发视图会自动转换为JstlView。
若使用的视图技术为Thymeleaf，在SpringMVC的配置文件中配置了Thymeleaf的视图解析器，由此视图解析器解析之后所得到的是ThymeleafView。

## 6.1 常用View
## 6.1.1 ThymeleafView

当控制器方法中所设置的视图名称没有任何前缀时，此时的视图名称会被SpringMVC配置文件中所配置的视图解析器解析，视图名称拼接视图前缀和视图后缀所得到的最终路径，会通过转发的方式实现跳转。

## 6.1.2 InternalResourceViewResolver

我们在使用SpringMVC的时候，想必都知道，为了安全性考虑，我们的JSP文件都会放在WEB-INF下，但是我们在外部是不可以直接访问/WEB-INF/目录下的资源对吧，只能通过内部服务器进行转发的形式进行访问，那么InternalResourceViewResolver底层通过转发形式帮我们解决了这个问题！
~~~
<!--  自定义视图解析器  -->
<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="prefix" value="/WEB-INF/"/>
    <property name="suffix" value=".jsp"/>
</bean>
~~~


## 6.2 转发视图

SpringMVC中默认的转发视图是InternalResourceView。
SpringMVC中创建转发视图的情况：
    **当控制器方法中所设置的视图名称以"forward:"为前缀时，创建InternalResourceView视图，**此时的视图名称不会被SpringMVC配置文件中所配置的ThymeleafView视图解析器解析，而是会将前缀"forward:"去掉，剩余部分作为最终路径通过转发的方式实现跳转，例如"forward:/"，“forward:/employee”，注意此处的forward后面的是RequestMapping的value属性，相当于前端http访问。

## 6.3 重定向视图

SpringMVC中默认的重定向视图是RedirectView。
    **当控制器方法中所设置的视图名称以"redirect:"为前缀时，创建RedirectView视图，**此时的视图名称不会被SpringMVC配置文件中所配置的ThymeleafView视图解析器解析，而是会将前缀"redirect:"去掉，剩余部分作为最终路径通过重定向的方式实现跳转，例如"redirect:/"，“redirect:/employee”。注意此处的redirect后面的是RequestMapping的value属性，相当于前端http访问。
**注：重定向视图在解析时，会先将redirect:前缀去掉，然后会判断剩余部分是否以/开头，若是则会自动拼接上下文路径。**

## 6.4 视图控制器view-controller

当控制器方法中，仅仅用来实现页面跳转，即只需要设置视图名称时，可以将处理器方法使用view-controller标签进行表示。
~~~
<!--
    path：设置处理的请求地址
    view-name：设置请求地址所对应的视图名称
-->
<mvc:view-controller path="/testView" view-name="success"></mvc:view-controller>
~~~

**注：当SpringMVC中设置任何一个view-controller时，其他控制器中的请求映射将全部失效，此时需要在SpringMVC的核心配置文件中设置开启mvc注解驱动的标签，<mvc:annotation-driven />。**



# 8 HttpMessageConverter

HttpMessageConverter，报文信息转换器，将请求报文转换为Java对象，或将Java对象转换为响应报文。
HttpMessageConverter提供了两个注解和两个类型：@RequestBody、@ResponseBody、RequestEntity、ResponseEntity。


## 8.1 SpringMVC处理json

@ResponseBody处理json的步骤：
~~~
1、导入jackson的依赖
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.12.1</version>
</dependency>

2、在SpringMVC的核心配置文件中开启mvc的注解驱动，此时在HandlerAdaptor中会自动装配一个消息转换器：MappingJackson2HttpMessageConverter，可以将响应到浏览器的Java对象转换为Json格式的字符串
<mvc:annotation-driven />

3、在处理器方法上使用@ResponseBody注解进行标识

4、将Java对象直接作为控制器方法的返回值返回，就会自动转换为Json格式的字符串
@RequestMapping("/testResponseUser")
@ResponseBody
public User testResponseUser(){
    return new User(1001,"admin","123456",23,"男");
}

浏览器的页面中展示的结果：{“id”:1001,“username”:“admin”,“password”:“123456”,“age”:23,“sex”:“男”}
~~~

## 8.2 SpringMVC处理ajax



# 9 文件上载和下载
## 9.1 文件下载

使用ResponseEntity实现下载文件的功能。查看A8FileUpAndDowController.java。

## 9.2 文件上载

文件上传要求form表单的请求方式必须为post，并且添加属性enctype=“multipart/form-data”。
SpringMVC中将上传的文件封装到MultipartFile对象中，通过此对象可以获取文件相关信息。

步骤：
~~~
1、添加依赖：
    <dependency>
        <groupId>commons-fileupload</groupId>
        <artifactId>commons-fileupload</artifactId>
        <version>1.3.1</version>
    </dependency>

2、在SpringMVC的配置文件中添加配置：
    <!--必须通过文件解析器的解析才能将文件转换为MultipartFile对象，id必须是multipartResolver-->
    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver"></bean>

3、控制器庐江
    @RequestMapping("/testUp")
    public String testUp(MultipartFile photo, HttpSession session) throws IOException {
        //获取上传的文件的文件名
        String fileName = photo.getOriginalFilename();
        //处理文件重名问题
        String hzName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID().toString() + hzName;
        //获取服务器中photo目录的路径
        ServletContext servletContext = session.getServletContext();
        String photoPath = servletContext.getRealPath("photo");
        File file = new File(photoPath);
        if(!file.exists()){
            file.mkdir();
        }
        String finalPath = photoPath + File.separator + fileName;
        //实现上传功能
        photo.transferTo(new File(finalPath));
        return "success";
    }

~~~



# 10 拦截器
## 10.1 拦截器的配置

SpringMVC中的拦截器用于**拦截控制器方法**的执行。
SpringMVC中的拦截器需要实现HandlerInterceptor。
SpringMVC的拦截器必须在SpringMVC的配置文件中进行配置：
~~~
<!-- 添加拦截器方式1 -->
<mvc:interceptors>
    <mvc:interceptor>
        <!-- 设置对哪些请求进行拦截 -->
        <mvc:mapping path="/**"/>
        <!-- 设置对哪些请求不拦截 -->
        <mvc:exclude-mapping path="/testRequestEntity/user"/>
        <!-- 哪个拦截器处理，也可以通过bean标签 -->
        <ref bean="firstInterceptor"></ref>
    </mvc:interceptor>
</mvc:interceptors>

<!-- 添加拦截器方式2 -->
<mvc:interceptors>
    <ref bean="secondInterceptor"></ref>
    <ref bean="firstInterceptor"></ref>
</mvc:interceptors>
~~~

## 10.2 拦截器的三个抽象方法

SpringMVC中的拦截器有三个抽象方法：
~~~
preHandle：控制器方法执行之前执行preHandle()，其boolean类型的返回值表示是否拦截或放行，返回true为放行，即调用控制器方法；返回false表示拦截，即不调用控制器方法
postHandle：控制器方法执行之后执行postHandle()
afterComplation：处理完视图和模型数据，渲染视图完毕之后执行afterComplation()
~~~

## 10.3 多个拦截器的执行顺序

若每个拦截器的preHandle()都返回true：
    此时多个拦截器的执行顺序和拦截器在SpringMVC的配置文件的配置顺序有关：
    preHandle()会按照配置的顺序执行，而postHandle()和afterComplation()会按照配置的反序执行

若某个拦截器的preHandle()返回了false：
    preHandle()返回false和它之前的拦截器的preHandle()都会执行，postHandle()都不执行(因为handler没执行，执行post没意义)，返回false的拦截器之前的拦截器的afterComplation()会执行。



# 11 异常处理器
## 11.1 基于配置的异常处理

SpringMVC提供了一个处理控制器方法执行过程中所出现的异常的接口：HandlerExceptionResolver
HandlerExceptionResolver接口的实现类有：DefaultHandlerExceptionResolver和SimpleMappingExceptionResolver
SpringMVC提供了自定义的异常处理器SimpleMappingExceptionResolver(即配置异常与出现此异常需要跳转的页面的对应关系)，使用方式：
~~~
<bean class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
    <property name="exceptionMappings">
        <props>
            <!--
                properties的键表示处理器方法执行过程中出现的异常
                properties的值表示若出现指定异常时，设置一个新的视图名称，跳转到指定页面
            -->
            <prop key="java.lang.ArithmeticException">error</prop>
        </props>
    </property>
    <!--
        exceptionAttribute属性设置一个属性名，将出现的异常信息在请求域中进行共享
    -->
    <property name="exceptionAttribute" value="ex"></property>
</bean>
~~~

## 11.2 基于注解的异常处理

~~~
//@ControllerAdvice将当前类标识为异常处理的组件
@ControllerAdvice
public class ExceptionController {

    //@ExceptionHandler用于设置所标识方法处理的异常
    @ExceptionHandler(ArithmeticException.class)
    //ex表示当前请求处理中出现的异常对象
    public String handleArithmeticException(Exception ex, Model model){
        model.addAttribute("ex", ex);
        return "error";
    }

}
~~~



# 12 注解配置SpringMVC

使用配置类和注解代替web.xml和SpringMVC配置文件的功能

## 12.1 创建初始化类，代替web.xml

在**Servlet3.0**环境中，容器会在类路径中查找实现javax.servlet.ServletContainerInitializer接口的类，如果找到的话就用它来配置Servlet容器。
Spring提供了这个接口的实现，名为SpringServletContainerInitializer，这个类反过来又会查找实现WebApplicationInitializer的类并将配置的任务交给它们来完成。
Spring3.2引入了一个便利的WebApplicationInitializer基础实现，名为AbstractAnnotationConfigDispatcherServletInitializer，当我们的类扩展了AbstractAnnotationConfigDispatcherServletInitializer并将其部署到Servlet3.0容器的时候，容器会自动发现它，并用它来配置Servlet上下文。

~~~
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 指定spring的配置类
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    /**
     * 指定SpringMVC的配置类
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     * 指定DispatcherServlet的映射规则，即url-pattern
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 添加过滤器
     * @return
     */
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceRequestEncoding(true);
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        return new Filter[]{encodingFilter, hiddenHttpMethodFilter};
    }
}
~~~

## 12.2 创建SpringConfig配置类，代替spring的配置文件

~~~
@Configuration
public class SpringConfig {
    //ssm整合之后，spring的配置信息写在此类中
}
~~~

## 12.3 创建WebConfig配置类，代替SpringMVC的配置文件

~~~
@Configuration 
@ComponentScan("com.atguigu.mvc.controller") //扫描组件
@EnableWebMvc //开启MVC注解驱动
public class WebConfig implements WebMvcConfigurer {

    //使用默认的servlet处理静态资源
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    //配置文件上传解析器
    @Bean
    public CommonsMultipartResolver multipartResolver(){
        return new CommonsMultipartResolver();
    }

    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        FirstInterceptor firstInterceptor = new FirstInterceptor();
        registry.addInterceptor(firstInterceptor).addPathPatterns("/**");
    }
    
    //配置视图控制
    
    /*@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }*/
    
    //配置异常映射
    /*@Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        Properties prop = new Properties();
        prop.setProperty("java.lang.ArithmeticException", "error");
        //设置异常映射
        exceptionResolver.setExceptionMappings(prop);
        //设置共享异常信息的键
        exceptionResolver.setExceptionAttribute("ex");
        resolvers.add(exceptionResolver);
    }*/

    //配置生成模板解析器
    @Bean
    public ITemplateResolver templateResolver() {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        // ServletContextTemplateResolver需要一个ServletContext作为构造参数，可通过WebApplicationContext 的方法获得
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(
                webApplicationContext.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        return templateResolver;
    }

    //生成模板引擎并为模板引擎注入模板解析器
    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    //生成视图解析器并未解析器注入模板引擎
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
    }


}
~~~



# 13 SpringMVC执行流程
## 13.1 SpringMVC常用组件

DispatcherServlet：前端控制器，不需要工程师开发，由框架提供
作用：统一处理请求和响应，整个流程控制的中心，由它调用其它组件处理用户的请求

HandlerMapping：处理器映射器，不需要工程师开发，由框架提供
作用：根据请求的url、method等信息查找Handler，即控制器方法

Handler：处理器，需要工程师开发
作用：在DispatcherServlet的控制下Handler对具体的用户请求进行处理

HandlerAdapter：处理器适配器，不需要工程师开发，由框架提供
作用：通过HandlerAdapter对处理器（控制器方法）进行执行

ViewResolver：视图解析器，不需要工程师开发，由框架提供
作用：进行视图解析，得到相应的视图，例如：ThymeleafView、InternalResourceView、RedirectView，视图解析器可以将html、css中的代码解析，比如使用ThymeleafView，会将<a th:href=@{/}>中的th:href=@{/}解析为href=http://ip:port/context/。

View：视图
作用：将模型数据通过页面展示给用户

## 13.2 DispatcherServlet初始化过程

DispatcherServlet 本质上是一个 Servlet，所以天然的遵循 Servlet 的生命周期。所以宏观上是 Servlet 生命周期来进行调度。
找DispatcherServlet的init()方法一步步看下去就是初始化过程。

## 13.2.1 初始化WebApplicationContext

所在类：org.springframework.web.servlet.FrameworkServlet
~~~
protected WebApplicationContext initWebApplicationContext() {
    WebApplicationContext rootContext =
        WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    WebApplicationContext wac = null;

    if (this.webApplicationContext != null) {
        // A context instance was injected at construction time -> use it
        wac = this.webApplicationContext;
        if (wac instanceof ConfigurableWebApplicationContext) {
            ConfigurableWebApplicationContext cwac = (ConfigurableWebApplicationContext) wac;
            if (!cwac.isActive()) {
                // The context has not yet been refreshed -> provide services such as
                // setting the parent context, setting the application context id, etc
                if (cwac.getParent() == null) {
                    // The context instance was injected without an explicit parent -> set
                    // the root application context (if any; may be null) as the parent
                    cwac.setParent(rootContext);
                }
                configureAndRefreshWebApplicationContext(cwac);
            }
        }
    }
    if (wac == null) {
        // No context instance was injected at construction time -> see if one
        // has been registered in the servlet context. If one exists, it is assumed
        // that the parent context (if any) has already been set and that the
        // user has performed any initialization such as setting the context id
        wac = findWebApplicationContext();
    }
    if (wac == null) {
        // No context instance is defined for this servlet -> create a local one
        // 创建WebApplicationContext
        wac = createWebApplicationContext(rootContext);
    }

    if (!this.refreshEventReceived) {
        // Either the context is not a ConfigurableApplicationContext with refresh
        // support or the context injected at construction time had already been
        // refreshed -> trigger initial onRefresh manually here.
        synchronized (this.onRefreshMonitor) {
            // 刷新WebApplicationContext
            onRefresh(wac);
        }
    }

    if (this.publishContext) {
        // Publish the context as a servlet context attribute.
        // 将IOC容器在应用域共享
        String attrName = getServletContextAttributeName();
        getServletContext().setAttribute(attrName, wac);
    }

    return wac;
}
~~~

## 13.2.2 创建WebApplicationContext

所在类：org.springframework.web.servlet.FrameworkServlet
~~~
protected WebApplicationContext createWebApplicationContext(@Nullable ApplicationContext parent) {
    Class<?> contextClass = getContextClass();
    if (!ConfigurableWebApplicationContext.class.isAssignableFrom(contextClass)) {
        throw new ApplicationContextException(
            "Fatal initialization error in servlet with name '" + getServletName() +
            "': custom WebApplicationContext class [" + contextClass.getName() +
            "] is not of type ConfigurableWebApplicationContext");
    }
    // 通过反射创建 IOC 容器对象
    ConfigurableWebApplicationContext wac =
        (ConfigurableWebApplicationContext) BeanUtils.instantiateClass(contextClass);

    wac.setEnvironment(getEnvironment());
    // 设置父容器
    wac.setParent(parent);
    String configLocation = getContextConfigLocation();
    if (configLocation != null) {
        wac.setConfigLocation(configLocation);
    }
    configureAndRefreshWebApplicationContext(wac);

    return wac;
}
~~~

## 13.2.3 DispatcherServlet初始化策略

FrameworkServlet创建WebApplicationContext后，刷新容器，调用onRefresh(wac)，此方法在DispatcherServlet中进行了重写，调用了initStrategies(context)方法，初始化策略，即初始化DispatcherServlet的各个组件

所在类：org.springframework.web.servlet.DispatcherServlet
~~~
protected void initStrategies(ApplicationContext context) {
   initMultipartResolver(context);
   initLocaleResolver(context);
   initThemeResolver(context);
   initHandlerMappings(context);
   initHandlerAdapters(context);
   initHandlerExceptionResolvers(context);
   initRequestToViewNameTranslator(context);
   initViewResolvers(context);
   initFlashMapManager(context);
}
~~~

## 13.3 DispatcherServlet调用组件处理请求
### 13.3.1 processRequest()

FrameworkServlet重写HttpServlet中的service()和doXxx()，这些方法中调用了processRequest(request, response)

所在类：org.springframework.web.servlet.FrameworkServlet
~~~
protected final void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    long startTime = System.currentTimeMillis();
    Throwable failureCause = null;

    LocaleContext previousLocaleContext = LocaleContextHolder.getLocaleContext();
    LocaleContext localeContext = buildLocaleContext(request);

    RequestAttributes previousAttributes = RequestContextHolder.getRequestAttributes();
    ServletRequestAttributes requestAttributes = buildRequestAttributes(request, response, previousAttributes);

    WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);
    asyncManager.registerCallableInterceptor(FrameworkServlet.class.getName(), new RequestBindingInterceptor());

    initContextHolders(request, localeContext, requestAttributes);

    try {
        // 执行服务，doService()是一个抽象方法，在DispatcherServlet中进行了重写
        doService(request, response);
    }
    catch (ServletException | IOException ex) {
        failureCause = ex;
        throw ex;
    }
    catch (Throwable ex) {
        failureCause = ex;
        throw new NestedServletException("Request processing failed", ex);
    }

    finally {
        resetContextHolders(request, previousLocaleContext, previousAttributes);
        if (requestAttributes != null) {
            requestAttributes.requestCompleted();
        }
        logResult(request, response, failureCause, asyncManager);
        publishRequestHandledEvent(request, response, startTime, failureCause);
    }
}
~~~

### 13.3.2 doService()

所在类：org.springframework.web.servlet.DispatcherServlet
~~~
@Override
protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
    logRequest(request);

    // Keep a snapshot of the request attributes in case of an include,
    // to be able to restore the original attributes after the include.
    Map<String, Object> attributesSnapshot = null;
    if (WebUtils.isIncludeRequest(request)) {
        attributesSnapshot = new HashMap<>();
        Enumeration<?> attrNames = request.getAttributeNames();
        while (attrNames.hasMoreElements()) {
            String attrName = (String) attrNames.nextElement();
            if (this.cleanupAfterInclude || attrName.startsWith(DEFAULT_STRATEGIES_PREFIX)) {
                attributesSnapshot.put(attrName, request.getAttribute(attrName));
            }
        }
    }

    // Make framework objects available to handlers and view objects.
    request.setAttribute(WEB_APPLICATION_CONTEXT_ATTRIBUTE, getWebApplicationContext());
    request.setAttribute(LOCALE_RESOLVER_ATTRIBUTE, this.localeResolver);
    request.setAttribute(THEME_RESOLVER_ATTRIBUTE, this.themeResolver);
    request.setAttribute(THEME_SOURCE_ATTRIBUTE, getThemeSource());

    if (this.flashMapManager != null) {
        FlashMap inputFlashMap = this.flashMapManager.retrieveAndUpdate(request, response);
        if (inputFlashMap != null) {
            request.setAttribute(INPUT_FLASH_MAP_ATTRIBUTE, Collections.unmodifiableMap(inputFlashMap));
        }
        request.setAttribute(OUTPUT_FLASH_MAP_ATTRIBUTE, new FlashMap());
        request.setAttribute(FLASH_MAP_MANAGER_ATTRIBUTE, this.flashMapManager);
    }

    RequestPath requestPath = null;
    if (this.parseRequestPath && !ServletRequestPathUtils.hasParsedRequestPath(request)) {
        requestPath = ServletRequestPathUtils.parseAndCache(request);
    }

    try {
        // 处理请求和响应
        doDispatch(request, response);
    }
    finally {
        if (!WebAsyncUtils.getAsyncManager(request).isConcurrentHandlingStarted()) {
            // Restore the original attribute snapshot, in case of an include.
            if (attributesSnapshot != null) {
                restoreAttributesAfterInclude(request, attributesSnapshot);
            }
        }
        if (requestPath != null) {
            ServletRequestPathUtils.clearParsedRequestPath(request);
        }
    }
}
~~~

### 13.3.3 doDispatch()

所在类：org.springframework.web.servlet.DispatcherServlet
~~~
protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
    HttpServletRequest processedRequest = request;
    HandlerExecutionChain mappedHandler = null;
    boolean multipartRequestParsed = false;

    WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);

    try {
        ModelAndView mv = null;
        Exception dispatchException = null;

        try {
            processedRequest = checkMultipart(request);
            multipartRequestParsed = (processedRequest != request);

            // Determine handler for the current request.
            /*
                mappedHandler：调用链
                包含handler、interceptorList、interceptorIndex
                handler：浏览器发送的请求所匹配的控制器方法
                interceptorList：处理控制器方法的所有拦截器集合
                interceptorIndex：拦截器索引，控制拦截器afterCompletion()的执行
            */
            mappedHandler = getHandler(processedRequest);
            if (mappedHandler == null) {
                noHandlerFound(processedRequest, response);
                return;
            }

            // Determine handler adapter for the current request.
               // 通过控制器方法创建相应的处理器适配器，调用所对应的控制器方法
            HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());

            // Process last-modified header, if supported by the handler.
            String method = request.getMethod();
            boolean isGet = "GET".equals(method);
            if (isGet || "HEAD".equals(method)) {
                long lastModified = ha.getLastModified(request, mappedHandler.getHandler());
                if (new ServletWebRequest(request, response).checkNotModified(lastModified) && isGet) {
                    return;
                }
            }
            
            // 调用拦截器的preHandle()
            if (!mappedHandler.applyPreHandle(processedRequest, response)) {
                return;
            }

            // Actually invoke the handler.
            // 由处理器适配器调用具体的控制器方法，最终获得ModelAndView对象
            mv = ha.handle(processedRequest, response, mappedHandler.getHandler());

            if (asyncManager.isConcurrentHandlingStarted()) {
                return;
            }

            applyDefaultViewName(processedRequest, mv);
            // 调用拦截器的postHandle()
            mappedHandler.applyPostHandle(processedRequest, response, mv);
        }
        catch (Exception ex) {
            dispatchException = ex;
        }
        catch (Throwable err) {
            // As of 4.3, we're processing Errors thrown from handler methods as well,
            // making them available for @ExceptionHandler methods and other scenarios.
            dispatchException = new NestedServletException("Handler dispatch failed", err);
        }
        // 后续处理：处理模型数据和渲染视图
        processDispatchResult(processedRequest, response, mappedHandler, mv, dispatchException);
    }
    catch (Exception ex) {
        triggerAfterCompletion(processedRequest, response, mappedHandler, ex);
    }
    catch (Throwable err) {
        triggerAfterCompletion(processedRequest, response, mappedHandler,
                               new NestedServletException("Handler processing failed", err));
    }
    finally {
        if (asyncManager.isConcurrentHandlingStarted()) {
            // Instead of postHandle and afterCompletion
            if (mappedHandler != null) {
                mappedHandler.applyAfterConcurrentHandlingStarted(processedRequest, response);
            }
        }
        else {
            // Clean up any resources used by a multipart request.
            if (multipartRequestParsed) {
                cleanupMultipart(processedRequest);
            }
        }
    }
}
~~~

### 13.3.4 processDispatchResult()

~~~
private void processDispatchResult(HttpServletRequest request, HttpServletResponse response,
                                   @Nullable HandlerExecutionChain mappedHandler, @Nullable ModelAndView mv,
                                   @Nullable Exception exception) throws Exception {

    boolean errorView = false;

    if (exception != null) {
        if (exception instanceof ModelAndViewDefiningException) {
            logger.debug("ModelAndViewDefiningException encountered", exception);
            mv = ((ModelAndViewDefiningException) exception).getModelAndView();
        }
        else {
            Object handler = (mappedHandler != null ? mappedHandler.getHandler() : null);
            mv = processHandlerException(request, response, handler, exception);
            errorView = (mv != null);
        }
    }

    // Did the handler return a view to render?
    if (mv != null && !mv.wasCleared()) {
        // 处理模型数据和渲染视图
        render(mv, request, response);
        if (errorView) {
            WebUtils.clearErrorRequestAttributes(request);
        }
    }
    else {
        if (logger.isTraceEnabled()) {
            logger.trace("No view rendering, null ModelAndView returned.");
        }
    }

    if (WebAsyncUtils.getAsyncManager(request).isConcurrentHandlingStarted()) {
        // Concurrent handling started during a forward
        return;
    }

    if (mappedHandler != null) {
        // Exception (if any) is already handled..
        // 调用拦截器的afterCompletion()
        mappedHandler.triggerAfterCompletion(request, response, null);
    }
}
~~~

## 13.4 SpringMVC的执行流程

~~~
用户向服务器发送请求，请求被SpringMVC 前端控制器 DispatcherServlet捕获。

DispatcherServlet对请求URL进行解析，得到请求资源标识符（URI），判断请求URI对应的映射：
    不存在：
        再判断是否配置了mvc:default-servlet-handler
        如果没配置，则控制台报映射查找不到，客户端展示404错误
        如果有配置，则访问目标资源（一般为静态资源，如：JS,CSS,HTML），找不到客户端也会展示404错误

存在则执行下面的流程：
    根据该URI，调用HandlerMapping获得该Handler配置的所有相关的对象（包括Handler对象以及Handler对象对应的拦截器），最后以HandlerExecutionChain执行链对象的形式返回。
    DispatcherServlet 根据获得的Handler，选择一个合适的HandlerAdapter。
    如果成功获得HandlerAdapter，此时将开始执行拦截器的preHandler(…)方法【正向】
    提取Request中的模型数据，填充Handler入参，开始执行Handler（Controller)方法，处理请求。在填充Handler的入参过程中，根据你的配置，Spring将帮你做一些额外的工作：
        HttpMessageConveter： 将请求消息（如Json、xml等数据）转换成一个对象，将对象转换为指定的响应信息
        数据转换：对请求消息进行数据转换。如String转换成Integer、Double等
        数据格式化：对请求消息进行数据格式化。 如将字符串转换成格式化数字或格式化日期等
        数据验证： 验证数据的有效性（长度、格式等），验证结果存储到BindingResult或Error中

    Handler执行完成后，向DispatcherServlet 返回一个ModelAndView对象。
    此时将开始执行拦截器的postHandle(…)方法【逆向】。
    根据返回的ModelAndView（此时会判断是否存在异常：如果存在异常，则执行HandlerExceptionResolver进行异常处理）选择一个适合的ViewResolver进行视图解析，根据Model和View，来渲染视图。
    渲染视图完毕执行拦截器的afterCompletion(…)方法【逆向】。
    将渲染结果返回给客户端。
         
~~~
