@[TOC](Spring boot)

# 0 格式
## 0.0 格式要求
html代码后面一定加一行空行；
标题后面需要添加空行；
https://www.cnblogs.com/cndarren/p/14415213.html
视频地址：https://www.bilibili.com/video/BV1o34y197fz?p=13&spm_id_from=pageDriver
每个大标题之间空2行，每个小标题之间空1行
![avatar](pictures/1-1.png)
<span style="color: red;"></span>
<hr style="height: 10px; background: green;"/>

# 7 Tomcat中jsp功能的实现
# 6 Tomcat中Session功能的实现
# 5 Tomcat启动过程
# 4 Tomcat请求处理详解
# 3 Tomcat中自定义类加载器的使用与源码实现



# 1 idea导入tomcat9源码及运行
## 1.1 导入

~~~
官网下载源码：
    https://tomcat.apache.org/download-90.cgi
    source code distributions
        zip (pgp, sha512)。

解压后根目录添加pom.xml：内容在最后面

根目录创建catalina-home，并将根目录下conf、webapps剪切到此目录下，放在这个目录下好管理。

导入idea：
    File -> open -> Project from existing sources。

配置main class
    Edit configurations -> + -> application：
    main class： org.apache.catalina.startup.Bootstrap
    VM options：将下面"{Tomcat根目录}"替换成本地目录(用/而不是\)，注意这个根目录是conf、webapps所在的目录：
        -Dcatalina.home={Tomcat根目录}
        -Dcatalina.base={Tomcat根目录}
        -Djava.endorsed.dirs={Tomcat根目录}/endorsed
        -Djava.io.tmpdir={Tomcat根目录}/temp
        -Duser.language=en  解决控制台乱码
        -Duser.region=US

解决问题1：
    org.apache.catalina.startup.ContextConfig#configureStart
    webConfig()下面添加：
    context.addServletContainerInitializer(new JasperInitializer(),null);

启动即可。

pom.xml文件内容
    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
         http://maven.apache.org/xsd/maven-4.0.0.xsd">

        <modelVersion>4.0.0</modelVersion>
        <groupId>org.apache.tomcat</groupId>
        <artifactId>apache-tomcat-9.0.22-src</artifactId>
        <name>apache-tomcat-9.0.22-src</name>
        <version>9.0.22</version>

        <build>
            <finalName>apache-tomcat-9.0.22-src</finalName>
            <sourceDirectory>java</sourceDirectory>
            <resources>
                <resource>
                    <directory>java</directory>
                </resource>
            </resources>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.5.1</version>
                    <configuration>
                        <encoding>UTF-8</encoding>
                        <source>1.8</source>
                        <target>1.8</target>
                    </configuration>
                </plugin>
            </plugins>
        </build>
        <dependencies>
            <dependency>
                <groupId>org.apache.ant</groupId>
                <artifactId>ant</artifactId>
                <version>1.10.6</version>
            </dependency>
            <dependency>
                <groupId>wsdl4j</groupId>
                <artifactId>wsdl4j</artifactId>
                <version>1.6.3</version>
            </dependency>
            <dependency>
                <groupId>javax.xml</groupId>
                <artifactId>jaxrpc</artifactId>
                <version>1.1</version>
            </dependency>
            <dependency>
                <groupId>org.eclipse.jdt</groupId>
                <artifactId>ecj</artifactId>
                <version>3.18.0</version>
            </dependency>
        </dependencies>
    </project>
~~~

## 1.2 运行web项目

~~~
打包一个web项目，打成war包、war包展开都可以。
    build -> build artifacts -> 选包(需要先在project structure中配置)
放入webapps目录下。
启动tomcat即可。
~~~



# 2 Tomcat整体架构和处理请求流程解析
## 2.1 server.xml文件分析

查看server.xml文件
![avatar](pictures/1-1tomcat完整架构图.jpg)

## 2.2 tomcat核心组件的关联

整体关系：
~~~
Server：代表tomcat容器
    Service * n：
        List<Connector>：接收请求，封装req、resp，交给Engine
        Engine：处理请求
            Pipeline：Engine接收到数据了，数据的处理流程由Pipeline确定，类似责任链模式，每个的处理节点叫Valve阀门
                List<Valve>：
            List<Host>：
                Pipeline：
                List<Context>：每个具体的web应用。
                    Pipeline：
                        List<Valve>：
                    List<Wrapper>：包含了所有某种servlet类型的所有实例
                        Pipeline：
                            List<Valve>：
                        List<Servlet>：n个相同Servlet类型的实例。
                        Servlet：可以这种Servlet类型是单例，就用这个属性。
~~~
Engine、Host、Context、Wrapper都是servlet窗口。
一个engine可包含多个host；
一个Host可包含多个context(具体应用)；
一个wrapper可包含**多个同类型**的servlet。

为什么不直接用context包含servlet？
直接用context管理servlet：相当于一个校长管理所有学生。
context<Wrapper<Servlet>>：相当于把所有学生分到各自的年级，由各自年级的老师管理，校长只用管理各年级的老师就行。
context只用管理每种Servlet对应的Wrapper，Wrapper只用管理这种Servlet中的所有实例。

## 2.3 如何确定请求由谁处理？

当请求被发送到Tomcat所在的主机时，如何确定最终哪个Web应用来处理该请求呢？
http://域名:端口/context/path

**根据协议和端口号选定Service和Engine**
~~~
Service中的Connector组件可以接收特定端口的请求，因此，当Tomcat启动时，Service组件就会监听特定的端口。
根据端口可以确定Connector，再根据Connector确定其所属的Service，找到Service下唯一的Engine。
通过在Server中配置多个Service，可以实现通过不同的端口号来访问同一台机器上部署的不同应用。
~~~

**根据域名或IP地址选定Host**
~~~
将域名或ip与Engine中所有Host的name进行匹配，就可以确定Host，如果没有匹配成功，则用Engine配置的defaultHost对应的Host处理。
~~~

**根据URI选定Context/Web应用**
~~~
在选定Host后，Tomcat根据应用的 path属性与URI的匹配程度来选择Web应用处理相应请求。
~~~

举例：以请求http://localhost:8080/app1/index.html为例，首先通过协议和端口号（http和8080）选定Service；然后通过主机名（localhost）选定Host；然后通过uri（/app1/index.html）选定Web应用。

## 2.4 如何配置多个服务

在Server中配置多个Service服务，可以实现通过不同的端口号来访问同一台机器上部署的不同Web应用。就是把上一次Service复制一份，相应的配置改一改。

## 2.5 其它组件

除核心组件外，server.xml中还可以配置很多其他组件。下面只介绍第一部分例子中出现的组件，如果要了解更多内容，可以查看https://tomcat.apache.org/tomcat-8.0-doc/config/index.html。

### 2.5.1 Listener

Listener(即监听器)定义的组件，可以在特定事件发生时执行特定的操作；被监听的事件通常是Tomcat的启动和停止。

监听器可以在Server、Engine、Host或Context中，本例中的监听器都是在Server中。实际上，本例中定义的6个监听器，都只能存在于Server组件中。监听器不允许内嵌其他组件。

监听器需要配置的最重要的属性是className，该属性规定了监听器的具体实现类，该类必须实现了org.apache.catalina.LifecycleListener接口。

下面依次介绍例子中配置的监听器：

VersionLoggerListener：当Tomcat启动时，该监听器记录Tomcat、Java和操作系统的信息。该监听器必须是配置的第一个监听器。
AprLifecycleListener：Tomcat启动时，检查APR库，如果存在则加载。APR，即Apache Portable Runtime，是Apache可移植运行库，可以实现高可扩展性、高性能，以及与本地服务器技术更好的集成。
JasperListener：在Web应用启动之前初始化Jasper，Jasper是JSP引擎，把JVM不认识的JSP文件解析成java文件，然后编译成class文件供JVM使用。
JreMemoryLeakPreventionListener：与类加载器导致的内存泄露有关。
GlobalResourcesLifecycleListener：通过该监听器，初始化< GlobalNamingResources>标签中定义的全局JNDI资源；如果没有该监听器，任何全局资源都不能使用。< GlobalNamingResources>将在后文介绍。
ThreadLocalLeakPreventionListener：当Web应用因thread-local导致的内存泄露而要停止时，该监听器会触发线程池中线程的更新。当线程执行完任务被收回线程池时，活跃线程会一个一个的更新。只有当Web应用(即Context元素)的renewThreadsWhenStoppingContext属性设置为true时，该监听器才有效。

### 2.5.2 GlobalNamingResources与Realm

Realm，可以把它理解成“域”；Realm提供了一种用户密码与web应用的映射关系，从而达到角色安全管理的作用。在本例中，Realm的配置使用name为UserDatabase的资源实现。而该资源在Server元素中使用GlobalNamingResources配置：

GlobalNamingResources元素定义了全局资源，通过配置可以看出，该配置是通过读取$TOMCAT_HOME/ conf/tomcat-users.xml实现的。

https://www.cnblogs.com/xing901022/p/4552843.html

### 2.5.3 Valve

单词Valve的意思是“阀门”，在Tomcat中代表了请求处理流水线上的一个组件；Valve可以与Tomcat的容器(Engine、Host或Context)关联。

AccessLogValve的作用是通过日志记录其所在的容器中处理的所有请求，在本例中，Valve放在Host下，便可以记录该Host处理的所有请求。AccessLogValve记录的日志就是访问日志，每天的请求会写到一个日志文件里。AccessLogValve可以与Engine、Host或Context关联；在本例中，只有一个Engine，Engine下只有一个Host，Host下只有一个Context，因此AccessLogValve放在三个容器下的作用其实是类似的。

属性如下：
~~~
className：规定了Valve的类型，是最重要的属性；本例中，通过该属性规定了这是一个AccessLogValve。
directory：指定日志存储的位置，本例中，日志存储在$TOMCAT_HOME/logs目录下。
prefix：指定了日志文件的前缀。
suffix：指定了日志文件的后缀。通过directory、prefix和suffix的配置，在$TOMCAT_HOME/logs目录下，可以看到如下所示的日志文件。
pattern：指定记录日志的格式，本例中各项的含义如下：
    %h：远程主机名或IP地址；如果有nginx等反向代理服务器进行请求分发，该主机名/IP地址代表的是nginx，否则代表的是客户端。后面远程的含义与之类似，不再解释。
    %l：远程逻辑用户名，一律是”-”，可以忽略。
    %u：授权的远程用户名，如果没有，则是”-”。
    %t：访问的时间。
    %r：请求的第一行，即请求方法(get/post等)、uri、及协议。
    %s：响应状态，200,404等等。
    %b：响应的数据量，不包括请求头，如果为0，则是”-”。
    %D，含义是请求处理的时间(单位是毫秒)，对于统计分析请求的处理速度帮助很大。
~~~

开发人员可以充分利用访问日志，来分析问题、优化应用。例如，分析访问日志中各个接口被访问的比例，不仅可以为需求和运营人员提供数据支持，还可以使自己的优化有的放矢；分析访问日志中各个请求的响应状态码，可以知道服务器请求的成功率，并找出有问题的请求；分析访问日志中各个请求的响应时间，可以找出慢请求，并根据需要进行响应时间的优化。










## 2.6 tomcat处理请求流程

服务器A ------> 服务器B
在A点击提交后，浏览器将http请求转换为字节码，通过socket，调用A的操作系统中的方法，通过TCP协议传送到B。
B接收到后，也通过socket调用操作系统的方法，获取字节数据。

注意，所有程序不能直接操作操作系统，所以操作系统留了一些接口，程序可以通过这些接口调用操作系统方法，socket就是操作TCP的。类似Java中有一个私有内部类C，其它类无法调用，但是可以在内部类所在的类A写一些方法，里面调用内部类的方法，然后外部类通过A.f1()调用C的方法。



# 3 Tomcat中关于长连接的底层原理与源码实现
## 3.1 应用程序间的通信

服务器(浏览器)A ------> 服务器B
~~~
浏览器点击提交，对http信息的字节数组通过socket调用操作系统的方法；
操作系统A通过ip:port和TCP协议和服务器B建立连接；
A将字节通过网络传输给B；
B上的应用程序通过socket监听port消息，并通过IO进行字节读取。
~~~

![avatar](pictures/3-数据从Client到Server.png)

## 3.2 长连接和短连接
### 3.2.1 定义
长连接(long connnection)：指在一个连接上可以连续发送多个数据包，在连接保持期间，如果没有数据包发送，需要双方发链路检测包。
短连接(short connnection)：是相对于长连接而言的概念，指的是在数据传送过程中，只在需要发送数据时才去建立一个连接，数据发送完成后则断开此连接，即每次连接只完成一项业务的发送。

通俗一点：
长连接：连接->传输数据->保持连接 -> 传输数据-> …->直到一方关闭连接，客户端关闭连接。长连接指建立SOCKET连接后无论使用与否都要保持连接。
短连接：连接->传输数据->关闭连接。下次一次需要传输数据需要再次连接。

![avatar](pictures/2长短连接.png)

### 3.2.3 应用场景

长连接
长连接多用于操作频繁，点对点的通讯，而且连接数不能太多情况。每个TCP连接都需要三步握手，这需要时间，如果每个操作都是先连接，再操作的话那么处理速度会降低很多，所以每个操作完后都不断开，次处理时直接发送数据包就OK了，不用新建立TCP连接。
例如：数据库的连接用长连接， 如果用短连接频繁的通信会造成socket错误，而且频繁的socket 创建也是对资源的浪费。

短连接
而像WEB网站的http服务一般都用短链接，因为长连接对于服务端来说会耗费一定的资源，而像WEB网站这么频繁的成千上万甚至上亿客户端的连接用短连接会更省一些资源，如果用长连接，而且同时有成千上万的用户，如果每个用户都占用一个连接的话，那可想而知。
所以并发量大，但每个用户无需频繁操作情况下需用短连好。

### 3.2.4 TCP长短连接的优势

**TCP短连接**
模拟一下TCP短连接的情况，client向server发起连接请求，server接到请求，然后双方建立连接。client向server发送消息，server回应client，然后一次读写就完成了，这时候双方任何一个都可以发起close操作，不过一般都是client先发起close操作。
从上面的描述看，短连接一般只会在client/server间传递一次读写操作。
短连接优点：管理起来方便，存在的连接都是有效的连接，不需要额外的控制手段。

**TCP长连接**
接下来我们再模拟一下长连接的情况，client向server发起连接，server接受client连接，双方建立连接。Client与server完成一次读写之后，它们之间的连接并不会主动关闭，后续的读写操作会继续使用这个连接。**注意永远是一个请求、响应结束了，client才会发另一个请求**。下面介绍一下TCP的保活功能。

**TCP的保活功能**主要为服务器应用提供。如果客户端已经消失而连接未断开，则会使得服务器上保留一个半开放的连接，而服务器又在等待来自客户端的数据，此时服务器将永远等待客户端的数据。保活功能就是试图在服务端器端检测到这种半开放的连接。就像2个人微信聊天，B过了一会不说话，A就得问下还在不，问了好几次B没答话，A就下线了。

**如果一个给定的连接在两小时内没有任何的动作，则服务器就向客户发一个探测报文段，客户主机必须处于以下4个状态之一：**
~~~~
客户主机依然正常运行，并从服务器可达。客户的TCP响应正常，而服务器也知道对方是正常的，服务器在两小时后将保证定时器复位。
客户主机已经崩溃，并且关闭或者正在重新启动。在任何一种情况下，客户的TCP都没有响应。服务端将不能收到对探测的响应，并在75秒后超时。服务器总共发送10个这样的探测 ，每个间隔75秒。如果服务器没有收到一个响应，它就认为客户主机已经关闭并终止连接。
客户主机崩溃并已经重新启动。服务器将收到一个对其保证探测的响应，这个响应是一个复位，使得服务器终止这个连接。
客户机正常运行，但是服务器不可达，这种情况与2类似，TCP能发现的就是没有收到探查的响应。
~~~~

### 3.2.5 HTTP协议与TCP/IP协议的关系

HTTP协议的长连接和短连接，本质上是TCP协议的长连接和短连接。HTTP属于应用层协议，在传输层使用TCP协议，在网络层使用IP协议。 IP协议主要解决网络路由和寻址问题，TCP协议主要解决如何在IP层之上可靠地传递数据包，使得网络上接收端收到发送端所发出的所有包，并且接受顺序与发送顺序一致。TCP协议是可靠的、面向连接的。TCP才负责连接，只有负责传输的这一层才需要建立连接

在HTTP/1.0中默认使用短连接。也就是说，客户端和服务器每进行一次HTTP操作，就建立一次连接，任务结束就中断连接。当客户端浏览器访问的某个HTML或其他类型的Web页中包含有其他的Web资源（如JavaScript文件、图像文件、CSS文件等），每遇到这样一个Web资源，浏览器就会重新建立一个HTTP会话。

而从HTTP/1.1起，默认使用长连接，用以保持连接特性。使用长连接的HTTP协议，会在响应头加入这行代码（但要服务器和客户端都设置）：
~~~
Connection:keep-alive
~~~







