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
        
        // 第4.5步 执行前进行拦截器校验
        mappedHandler.applyPreHandle(processedRequest, response)
        
        // 第五步：处理器适配器去执行Handler，并获取ModelAndView，此处即是调用被@RequestMapping注解的方法
        ModelAndView mv = ha.handle(request, response, mappedHandler.getHandler());
        
        // 第六步：前端控制器请求视图解析器（ViewResolver）去进行视图解析，并在解析处理完成后进行拦截
        this.processDispatchResult(req, resp, mappedHandler, mv, (Exception)dispatchException) {
            render(mv, request, response) {
                // 第六步：进行视图解析及处理，对视图进行渲染，此时view就是SpringMVC.xml中配置的视图解析器
                view.render(mv.getModelInternal(), request, response);
            }
            
            // 第七步：进行后拦截器校验。
            mappedHandler.triggerAfterCompletion(request, response, (Exception)null);
        }
        
    }
    
}

第十一步：前端控制器向用户响应结果
~~~

## 1.5 名词解释

Handler：处理器，真正负责处理请求的方法，UserController.f1();
HandlerMapping的实现类的作用：将请求映射到带@RequestMapping注释的控制器方法，将URL路径映射到控制器bean名称。
HandlerAdapter的实现类的作用：实现类RequestMappingHandlerAdapter，处理请求的适配器，确定调用哪个类的哪个方法，并且构造方法参数，返回值。
HandlerExecutionChain：将handler与所有匹配的 HandlerInterceptor(拦截器)绑定到创建的 HandlerExecutionChain 对象上并返回。

视图解析器：视图解析器会根据ModelAndView中的view，拼接设定的前后缀得到资源路径，去渲染资源，不同的解析器对资源的解释不同，就像使用ThymeleafViewResolver渲染html，就可以在html使用Thymeleaf语法，不同的视图解析器有各自的语法。



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

此配置作用下，SpringMVC的配置文件默认位于WEB-INF下，**默认名称为<servlet-name>-servlet.xml(必须以这个结尾)**，例如，以下配置所对应SpringMVC的配置文件位于WEB-INF下，文件名为springMVC-servlet.xml。
~~~
<!-- 配置SpringMVC的前端控制器，对浏览器发送的请求统一进行处理 -->
<servlet>
    <servlet-name>springMVC</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
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

注：
标签中使用/和/*的区别：
    /所匹配的请求可以是/login或.html或.js或.css方式的请求路径，但是/不能匹配.jsp请求路径的请求，因此就可以避免在访问jsp页面时，该请求被DispatcherServlet处理，从而找不到相应的页面，/*则能够匹配所有请求，例如在使用过滤器时，若需要对所有请求进行过滤，就需要使用/*的写法。

## 2.4 创建请求控制器

由于前端控制器对浏览器发送的请求进行了统一的处理，但是具体的请求有不同的处理过程，因此需要创建处理具体请求的类，即请求控制器。
请求控制器中每一个处理请求的方法称为控制器方法。
因为SpringMVC的控制器由一个POJO（普通的Java类）担任，因此需要通过@Controller注解将其标识为一个控制层组件，交给Spring的IOC容器管理，此时SpringMVC才能够识别控制器的存在
~~~
@Controller
public class HelloController {
    
    @RequestMapping(value = "/hello")
    public String hello() {
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
<!-- 
    
  若只设置该标签，则只能访问静态资源，其他请求则无法访问
  此时必须设置<mvc:annotation-driven/>解决问题。
  此标签作用是，当SpringMVC(只有MVC有这个问题)的RequestMapping无法匹配访问资源时，使用默认的servlet处理，返回静态资源。
  当访问时，先由SpringMVC处理，处理不了会使用默认Servlet处理。
 -->
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

## 2.6 总结

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

## 3.4 @PathVariable注解

对于原始的请求方式：原始方式：/deleteUser?id=1
对于restful的请求方式：/deleteUser/1
SpringMVC路径中的占位符常用于RESTful风格中，当请求路径中将某些数据通过路径的方式传输到服务器中，就可以在相应的@RequestMapping注解的value属性中通过占位符{xxx}表示传输的数据，在通过@PathVariable注解，将占位符所表示的数据赋值给控制器方法的形参

~~~
原始方式"http://localhost:8080/testRest?id=1&username=xuaa"
请求路径"http://localhost:8080/testRest/1/xuaa"
@RequestMapping("/testRest/{id}/{username}")
public String testRest(@PathVariable("id") String id, @PathVariable("username") String username){
    System.out.println("id:"+id+",username:"+username);
    return "success";
}
~~~

@PathVariable(value = "id")，这个根据value属性的id找到@RequestMapping对应的{id}的值，就会直接赋值给参数如的String id。



# 4 SpringMVC获取请求参数
## 4.1 通过ServletAPI获取

DispatcherServlet通过handler处理时，会通过反射获取request参数类型，如果是HttpServletRequest，则会将DispatcherServlet.service()中的req参数传进去，resp类似。
~~~
@RequestMapping("/testParam")
public String testParam(HttpServletRequest request){
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    System.out.println("username:"+username+",password:"+password);
    return "success";
}
~~~

## 4.2 通过控制器方法的形参获取请求参数

在控制器方法的形参位置，设置和请求参数同名的形参，当浏览器发送请求，匹配到请求映射时，在DispatcherServlet中就会将请求参数赋值给相应的形参。
~~~
http://localhost:8080/testParam?username=admin&password=123456

@RequestMapping("/testParam")
public String testParam(String username, String password){
    System.out.println("username:"+username+",password:"+password);
    return "success";
}
~~~
注：
    若请求所传输的请求参数中有多个同名的请求参数，此时可以在控制器方法的形参中设置字符串数组或者字符串类型的形参接收此请求参数。
    若使用字符串数组类型的形参，此参数的数组中包含了每一个数据。
    若使用字符串类型的形参，此参数的值为每个数据中间使用逗号拼接的结果。

## 4.3 @RequestParam

@RequestParam是将请求参数和控制器方法的形参创建映射关系
@RequestParam注解一共有三个属性：
    value：指定为形参赋值的请求参数的参数名
    required：设置是否必须传输此请求参数，默认值为true
        若设置为true时，则当前请求必须传输value所指定的请求参数，若没有传输该请求参数，且没有设置defaultValue属性，则页面报错400：Required String parameter ‘xxx’ is not present；若设置为false，则当前请求不是必须传输value所指定的请求参数，若没有传输，则注解所标识的形参的值为null
    defaultValue：不管required属性值为true或false，当value所指定的请求参数没有传输或传输的值为""时，则使用默认值为形参赋值

## 4.4 @RequestHeader、@CookieValue

@RequestHeader是将请求头信息和控制器方法的形参创建映射关系
@RequestHeader注解一共有三个属性：value、required、defaultValue，用法同@RequestParam
@CookieValue是将cookie数据和控制器方法的形参创建映射关系
@CookieValue注解一共有三个属性：value、required、defaultValue，用法同@RequestParam

## 4.5 通过POJO获取请求参数

可以在控制器方法的形参位置设置一个实体类类型的形参，此时若浏览器传输的请求参数的参数名和实体类中的属性名一致，那么请求参数就会为此属性赋值。

## 4.6 解决获取请求参数的乱码问题

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



# 7 RESTful
## 7.1 RESTful简介

REST：Representational State Transfer，表现层资源状态转移。

资源：
资源是一种看待服务器的方式，即，将服务器看作是由很多离散的资源组成。每个资源是服务器上一个可命名的抽象概念。因为资源是一个抽象的概念，所以它不仅仅能代表服务器文件系统中的一个文件、数据库中的一张表等等具体的东西，可以将资源设计的要多抽象有多抽象，只要想象力允许而且客户端应用开发者能够理解。与面向对象设计类似，资源是以名词为核心来组织的，首先关注的是名词。一个资源可以由一个或多个URI来标识。URI既是资源的名称，也是资源在Web上的地址。对某个资源感兴趣的客户端应用，可以通过资源的URI与其进行交互。

资源的表述：
资源的表述是一段对于资源在某个特定时刻的状态的描述。可以在客户端-服务器端之间转移（交换）。资源的表述可以有多种格式，例如HTML/XML/JSON/纯文本/图片/视频/音频等等。资源的表述格式可以通过协商机制来确定。请求-响应方向的表述通常使用不同的格式。

状态转移：
状态转移说的是：在客户端和服务器端之间转移（transfer）代表资源状态的表述。通过转移和操作资源的表述，来间接实现操作资源的目的。

## 7.2 RESTful的实现

具体说，就是 HTTP 协议里面，四个表示操作方式的动词：GET、POST、PUT、DELETE。
它们分别对应四种基本操作：GET 用来获取资源，POST 用来新建资源，PUT 用来更新资源，DELETE 用来删除资源。
REST 风格提倡 URL 地址使用统一的风格设计，从前到后各个单词使用斜杠分开，不使用问号键值对方式携带请求参数，而是将要发送给服务器的数据作为 URL 地址的一部分，以保证整体风格的一致性。

|操作|传统方式|REST风格|
|:--|:--|:--|
|查询|getUserById?id=1|user/1->get请求方式|
|保存|saveUser|user->post请求方式|
|删除|deleteUser?id=1|user/1->delete请求方式|
|更新|updateUser|user->put请求方式|

## 7.3 HiddenHttpMethodFilter

由于浏览器只支持发送get和post方式的请求，那么该如何发送put和delete请求呢？
SpringMVC 提供了 HiddenHttpMethodFilter 帮助我们将 POST 请求转换为 DELETE 或 PUT 请求
HiddenHttpMethodFilter 处理put和delete请求的条件：
    a>当前请求的请求方式必须为post
    b>当前请求必须传输请求参数_method
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

# 8 RESTful案例
记录tomcat.conf.web.xml和本项目web.xml的关系



















