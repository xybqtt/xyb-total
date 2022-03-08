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

添加web.xml文件：
    在main包下，创建webapp(灰包中间偏右有蓝点)\WEB-INF；
    File -> project structure -> modules -> 选中项目下的Web；
    Deployment Descriptors -> + web.xml -> 选择路径将web.xml放到webapp\WEB-INF下，就会自动添加web.xml文件。
~~~

注：由于 Maven 的传递性，我们不必将所有需要的包全部配置依赖，而是配置最顶端的依赖，其他靠传递性导入。



# 3 配置web.xml






