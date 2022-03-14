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
        
        // 第六步：前端控制器请求视图解析器（ViewResolver）去进行视图解析，并在解析处理完成后，
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

## 1.5 备注

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
~~~
<!-- 自动扫描包 -->
<context:component-scan base-package="com.atguigu.mvc.controller"/>

<!-- 配置Thymeleaf视图解析器 -->
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
   处理静态资源，例如html、js、css、jpg
  若只设置该标签，则只能访问静态资源，其他请求则无法访问
  此时必须设置<mvc:annotation-driven/>解决问题
 -->
<mvc:default-servlet-handler/>

<!-- 开启mvc注解驱动 -->
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
















