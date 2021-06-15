@[TOC](第2章 信息的表示和处理)

# 0 Html、js、css、servlet、jsp产生的条件

## 0.0 格式要求
　　换行前要求2个全角空格；
　　问答句，答句前面加2个全角空格；
　　注意点、特点，如果换行，前面加2个全角空格；
　　标题和后面的内容不用空行；

## 0.1 为什么要学Html、js、css？
　　想要在前端页面展示一些信息，所以要学html、js、css；

## 0.2 为什么要有servlet？
　　当我们发布一个只有html代码的web项目时，我们可以通过uri直接从服务器读取这个静态html页面，比如登陆页面。
　　但是有时候，我们想要显示的页面，并不是确定的，比如我想显示这个公司的所有人名单到前端，由于人员一般不会一直不变，我们不可能提前知道哪个时间节点，有哪些人员还在公司，所以实际的情况，我们需要查询数据库，查出相应的人员，再返回给前面页面，这就需要使用Servlet
来向前端写入由当时的数据生成的代码。
　　所以servlet的主要功能是用response的输出流向前端页面写入html代码，但是这样操作起来会很麻烦，因为我们需要在java代码中写html代码。

## 0.3 为什么要有jsp？
　　为了简化servlet输出html代码的操作，所以有了jsp，我们可以在html代码中写java代码，虽然也很麻烦，但是总算稍微简单了一些。
　　Servlet是在java代码中写html代码，jsp是在html代码中写java代码。


# 1 javaweb的概念

## 1.1 什么是javaweb
　　javaweb是指，所有通过java语言编写可以通过浏览器访问的程序的总称；是基于请求和响应来开发的。

## 1.2 什么是请求
　　请求是指客户端给服务器发送数据，叫请求request。

## 1.3 什么是响应
　　响应是指服务器给客户端回传数据，叫响应response。

## 1.4 请求和响应的关系
　　请求和响应是成对出现的。

## 1.5 javaweb的三大组件
　　Servlet程序、Filter过滤器、Listener监听器。

## 1.6 web资源分类
　　按实现的技术和呈现的效果不同，分为静态资源和动态资源2种。
　　静态资源：html、css、js、txt、mp4、jpg等；
　　动态资源：jsp页面、servlet程序。

## 1.7 JavaEE项目的三层架构
　　浏览器请求服务器资源，并最终把结果展示到页面上，如Html、css、js、jq；
　　Web层/视图展现层作用
　　　　获取请求参数；
　　　　调用Service层处理业务；
　　　　响应数据给客户端、请求转发、重定向；
　　　　如Servlet、Webwork、Struts、SpringMVC；
　　Service业务层 作用
　　　　处理业务逻辑；
　　　　调用持久层保存到数据库；
　　　　如Spring框架；
　　Dao持久层 作用
　　　　只负责和数据库交互；
　　　　如Jdbc、DBUtils、JdbcTemplate、Mybatis、Hiberante、JPA；

## 1.8 MVC模型
　　MVC全称：Model模型、View视图、Controller控制器，理念是将软件代码拆分为单独的组件，单独开发，组合使用(降低耦合度)。
　　MVC最早出现在JavaEE三层的web层中，它可以有效指导web层的代码如何有效分离，单独工作；
　　View视图：只负责数据和界面的显示，不接受任何与显示数据无关的代码，便于程序员和美工的分工合作---jsp/html；
　　Controller控制器：只负责接收请求，调用业务层代码处理请求，然后派发页面，是一个"调度者"的角色---servlet。转到某个页面，或是重定向到某个页面；
　　Model模型：将与业务逻辑相关的数据封装为具体的JavaBean类，其中不掺杂任何与数据处理相关的代码---JavaBean/domain/entity。
　　
## 1.9 常用的web服务器
　　tomcat：由apache组织提供的一种web服务器，提供对jsp和servlet的支持，是一种轻量级的javaweb窗口(服务器)，也是当前应用最广的javaweb服务器(免费)；
　　jboss：是一个遵从javaEE规范、开放源代码的、纯java的ejb服务器，支持所有的javaEE规范(免费)；
　　GlassFish：是oracle公司开发的一款javaweb服务器，强健、达到产品质量级(应用少)；
　　Resin：是CAUCHO公司的产品，非常流行，对servlet和jsp提供了良好的支持，性能较好，resin自身采用java开发(收费、应用多)；
　　WebLogin：oracle产品，是目前最广泛的web服务器，支持javaEE规范，而且不断改进产品，适合大型项目(收费、用的不多、适合大公司)。

## 1.10 tomcat服务器和servlet版本的对应关系
|tomcat版本|servlet/jsp版本|javaEE版本|运行环境|
|:---|:---|:---|:---|
|4.1 | 2.3/1.2 | 1.3  | jdk1.3 |
|5.0 | 2.4/2.0 | 1.4  | jdk1.4 |
|5.5/6.0 | 2.5/2.1 | 5.0  | jdk5.0 |
|7.0 | 3.0/2.2 | 7.0  | jdk6.0 |
|8.0 | 3.1/2.3 | 7.0  | jdk7.0 |

# 2 tomcat介绍
## 2.1 目录介绍
　　bin：存放tomcat自身的可执行程序；
　　conf：存放tomcat服务器的配置文件；
　　lib：存放tomcat服务器的jar包，即对javaEE规范的实现；
　　logs：存放tomcat服务器运行时输出的日志信息；
　　temp：存放tomcat运行时产生的临时数据；
　　webapps：存放需要部署的web程序，1个web项目，在其中有1个目录；
　　work：tomcat工作时的目录，存放tomcat运行时jsp翻译为servlet源码、session钝化(序列化)的目录。

## 2.2 如何启动tomcat
　　bin\startup.bat，双击；
　　在bin目录，打开命令窗口，catalina run，启动失败可以显示错误原因，需要配置CATALINA_HOME=tomcat安装目录。
　　在浏览器访问，可以访问如下即启动：
　　　　http://localhost:8080；
　　　　http://127.0.0.1:8080；
　　　　http://真实ip:8080；
　　启动注意点：
　　　　需要配置JAVA_HOME环境变量；

## 2.3 tomcat停止
　　关闭黑窗口；可在黑窗口按ctrl+c；
　　在bin目录下，双击shutdown.bat；(主要方式)

## 2.4 修改tomcat端口号
　　conf\server.xml；
　　找到Connector标签，修改port；
　　重启tomcat。
　　为什么在访问百度等网站时不用端口号？
　　　　因为http协议默认端口号是80(省略了)，实际访问百度的是http://www.baidu.com:80。

## 2.5 部署web工程到tomcat中的方式
　　1、只需要把web工程(例book工程)的目录复制到tomcat的webapps目录下即可。通过http://ip:port/book/html页面；
　　2、通过这种方式，我们就可以不必把工程放到tomcat中了，进入到"tomcat\conf\Catalina\localhost"目录下，创建xxx.xml，在xml中粘贴<Context path="/a" docBase="D:\IdeaProjects\Javaweb\out\artifacts\web03_war_exploded">其中
　　　　Context表示工程上下文；
　　　　path表示工程的访问路径；
　　　　docBase表示工程的目录在哪里；
　　但是我们访问的时候，是http://ip:port/path的值/index.html = http://ip:port/docBase的值/index.html，
　　不能再用http://ip:port/工程名访问了。
　　idea将编译后的存放tomcat运行时jsp翻译为servlet源码、session钝化(序列化)的目录：查看启动时的Using CATALINA_BASE。

## 2.6 在本地磁盘打开html和手拖打开html页面到浏览器
　　1、手拖打开html页面到浏览器，地址为file://...，用的是file协议，表示告诉浏览器直接读取file:协议后面的路径，解析在浏览器即可。不需要通过服务器。
　　2、如果在浏览器地址栏输入访问地址：http://ip:port/工程名/资源名，表示使用的是http协议，需要发送请求到服务器，再接收服务器返回的资源。

## 2.7 ROOT工程的访问，以及默认index.html页面的访问
　　启动tomcat后，输入http://localhost:8080/ 此时默认的访问工程就是ROOT工程，此时打开是那个雄猫页面。
　　当我们在浏览器输入http://localhost:8080/工程名/ 但是没有输入想要访问的资源时，默认访问index.html页面。

## 2.8 idea整合本地tomcat
　　settings -> Application Servers -> + -> Tomcat Server -> 选本地tomcat，ok；

# 3 javaweb工程创建等操作
## 3.1 idea的javaweb动态工程的创建
　　new module -> Java Enterprise -> 选sdk、java EE version、tomcat、勾选Web Application、勾选Create web.xml，接着往下走就行。
　　在创建好的WEB-INF目录下，再创建一个lib目录。

## 3.2 javaweb动态工程目录说明
　　工程名(对应开发过程中的web目录)
　　　　src：java代码开发目录
　　　　web：专门用来存放web工程的资源文件，如html、css、js等
　　　　　　WEB-INF：是一个受服务器保护的目录，浏览器无法直接访问到此目录内容
　　　　　　　　lib：存放第三方的jar包(idea还需要自己配置导入)
　　　　　　　　web.xml：是整个动态web工程的配置部署描述文件，可以在这里配置很多web工程的组件，如Servlet程序、Filter过滤器、Listener监听器、Session超时等。

## 3.3 如何给动态web工程添加额外的jar包
　　被添加的jar包前面会有箭头可以往下选。
　　第1种方式：
　　　　将jar包放入lib中，选中所有的jar包并右键 -> Add as Library -> 选中此web的模块并ok。

　　第2种方式，本质是将一堆jar包，装在1个lib库，然后将这个lib库添加到某个模块中：
　　　　File -> Project Structure -> Libraries -> + -> java -> 选jar包 -> 选要添加到模块A -> 可以把这个lib库的名字改下 -> Artifacts -> 选择 模块A:war exploded -> Fix -> add lib库 to artifact。

## 3.4 如何将java模块A变成java web模块 Aweb
　　1、对A右键 -> Add Framework Support；
　　2、勾选Web Application -> 勾选 Create web.xml，项目中就会在src的同一级出现web目录(黑色目录、蹭2靠左有个蓝点、eclipse可能是webapp)；
　　3、在WEB-INF目录下创建lib目录。
　　4、File -> Project Structure ->
　　　　Modules：
　　　　　　点选A模块
　　　　　　　　Paths：勾选Use module compile out path，设置java代码的输出路径，建议Output path："A模块绝对路径\target\production\模块名"，建议Test output path："A模块绝对路径\target\test\模块名"；
　　　　　　　　Dependencies：需要的依赖，在Libraries中设置。
　　　　　　点击模块下的Web：(和Facets中需要配置的一模一样，在这儿配了就不用到Facets中配置了)
　　　　　　　　name：可自定义修改；
　　　　　　　　　　Deployment Descriptors：
　　　　　　　　　　　　Type：不可修改；
　　　　　　　　　　　　path：web.xml的绝对路径；
　　　　　　　　　　Web Resource Directories：
　　　　　　　　　　　　Web Resource Directory：2中的web目录的绝对路径，即资源路径的绝对路径；被设置了这个位置的目录就是web目录，不管叫什么；
　　　　　　　　　　　　Path Relative to Deployment Root：要把上面的资源放到发布的根目录的什么位置，/代表就把资源放到发布目录下(Artifacts的output directory)，建议别整妖娥子，就放在这个目录下，如果不放在这个目录下，比如放在/111目录下，那么，class的目录是/WEB-INF/class，资源的目录是/111/。本来资源和WEB-INF是同一级，现在资源所在的目录和WEB-INF才是同一级。
　　　　　　　　　　Source Roots：java源代码目录，选择"模块绝对路径/src"；

　　　　Libraries：
　　　　　　"+" -> java -> 选所有需要jar包 -> 选要添加到模块A(也可以生成后，右键Add to Modules) -> 可以把这个lib库的名字改下

　　　　Facets：
　　　　　　已经设置过了，不用设置了；

　　　　Artifacts：
　　　　　　选择模块 -> Fix -> Add '依赖名' to the artifact；
　　　　　　Type：Web Application: Exploded：相当于将打包后的war包解压的形式，比较推荐；
　　　　　　Output directory：建议"模块绝对路径\target\artifacts\模块名_war_exploded"，则build后资源文件和WEB-INF都在此目录下，相当于"五.5"的docBase。

## 3.4 配置对应的tomcat去启动
　　Edit Configurations -> Tomcat Local -> 点选刚才新生成的 -> 更改name；
　　Deployment：
　　　　"+" -> Artifact -> 选择对应模块；
　　　　Application context：/自定义工程名，浏览器输入http://ip:port/工程名，即可访问此工程，"/工程名"对应"Artifacts的Output directory"；

　　Server，相当于"2.5"的path的设置。
　　　　URL：启动后默认访问的url，为"http://localhost:8080/Application context的内容/"；
　　　　HTTP Port：设置端口。
　　　　On 'Update' action：Update classes and resources；
　　　　On frame deactivation：选择Update classes and resources； 这个和上面的选项配合，可以进行热部署。
　　启动访问即可。


# 4 HTTP协议
## 4.1 什么是HTTP协议
　　协议是指双方或多方相互约定好，大家都需要遵守的规则；
　　HTTP协议是指，客户端和服务端通信时，发送的数据，需要遵守的规则；
　　HTTP协议中的数据又叫报文；
　　客户端给服务器发送数据叫请求；
　　服务器给客户端回传的数据叫响应；
　　请求分为GET请求和POST请求。

## 4.2 chrome查看GET请求完整格式
　　F12 -> Network -> 在Name区域点击某个链接 -> View source即可；

## 4.3 HTTP部分说明：
### 4.3.1、常用响应码：
　　200：请求成功；
　　302：表示请求重定向；
　　404：表示请求服务器已经收到了，但是你要的数据不存在(请求地址错误)；
　　500：表示服务器已经收到请求，但是服务器内部错误(代码错误)。

### 4.3.2 MIME类型说明
　　MIME的英文全称是"Multipurpost Internet Mail Extensions"，多功能Internet邮件扩充服务，其格式类型是"大类型/小类型"，并与某一种文件的扩展名相对应。
　　常用的MIME类型如下：

|文本|后缀|MIME类型|
|:---|:---|:---|
|超文本标记语言文本 | .html、.htm | text/html |
|普通文本 | .txt | text/plain |
|RTF文本 | .rtf | application/rtf |
|GIF图形 | .gif | image/gif |
|JPEG图形 | .jpeg、.jpg | image/jpeg |
|au声音文件 | .au | audio/basic |
|MIDI音乐文件 | mid、.midi | audio/midi、audio/x-midi |
|RealDudio音乐文件 | .ra、.ram | audio/x-pn-realaudio |
|MPEG文件 | .mpg、.mpeg | video/mpeg |
|AVI文件 | .avi | video/x-msvideo |
|GZIP文件 | .gz | application/x-gzip |
|TAR文件 | .tar | application/x-tar |

## 4.4 GET、POST请求的使用时机
　　GET：
　　　　form标签 method=get；
　　　　a标签；
　　　　link标签引入css；
　　　　script标签引入js文件；
　　　　img标签引入图片；
　　　　iframe引入html页面；
　　　　在浏览器地址栏输入地址后敲回车。

　　POST：
　　　　form标签 method=post。

## 4.5、GET请求
### 4.5.1 GET请求的HTTP协议格式
　　请求行
　　　　请求的方式                   GET
　　　　请求的资源路径[?请求参数]
　　　　请求的协议版本号              HTTP/1.1
　　请求头
　　　　k:v 组成
　　请求参数
　　　　username=a&hobby=b&hobby=c

### 4.5.2 GET请求实例

| 实例 | 说明 |
|:------ |:------ |
|请求行 |  |
|GET /java_web_3/contextServlet4 HTTP/1.1 | 1、请求方式 2、请求的资源路径 3、请求的协议和版本号 |
|请求头|  |
|Host: localhost:8080 | Host: 表示请求的服务器ip和端口号 |
|Connection: keep-alive | Connection：告诉服务器请求连接如何处理。keep-alive表示服务器回传数据不要马上关闭，保持一小段时间连接。closes表示马上关闭 |
|sec-ch-ua: " Not A;Brand";v="99", "Chromium";v="90", "Google Chrome";v="90" |  |
|sec-ch-ua-mobile: ?0 |  |
|Upgrade-Insecure-Requests: 1 |  |
|User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36 | User-Agent: 浏览器的信息 |
|Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/<br/>webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9 | Accept：告诉服务器，客户端可以接收的数据类型 |
|Sec-Fetch-Site: same-origin |  |
|Sec-Fetch-Mode: navigate |  |
|Sec-Fetch-User: ?1 |  |
|Sec-Fetch-Dest: document |  |
|Referer: http://localhost:8080/java_web_3/a.html |  |
|Accept-Encoding: gzip, deflate, br | Accept-Encoding: 告诉服务器，客户端可以接收的数据编码(压缩)格式 |
|Accept-Language: zh-CN,zh;q=0.9 | Accept-Language：告诉服务器客户端可以接收的语言类型 |
|Cookie: JSESSIONID=B504D8ACD4B5972C5941BF79FFC0A34E; Idea-71feb1f0=80b67c67-bcd2-45b5-b7b1-386160294d79 |  |

## 4.6 GET响应
### 4.6.1 GET响应的HTTP协议格式
　　响应行
　　　　响应的协议和版本号
　　　　响应的状态码
　　　　响应状态描述符
　　响应头
　　　　k:v
　　空行
　　响应体
　　　　就是回传给客户端的数据
    
### 4.6.2 GET响应实例
| 实例 | 说明 |
|:------ |:------ |
| 响应行 |  |
| HTTP/1.1 200 OK | 响应的协议 响应的状态码 响应状态描述符 |
| 响应头 |  |
| Server: Apache-Coyote/1.1 | Server：表示服务器的信息 |
| Date: Sat, 29 May 2021 08:35:14 GMT | Date：请求响应的时间(格林时间) |
| Accept-Ranges: bytes |  |
| ETag: W/"1613-1622272759059" |  |
| Last-Modified: Sat, 29 May 2021 07:19:19 GMT |  |
| Content-Type: text/html | Content-Type：表示响应体的数据类型 |
| Content-Length: 1613 | Content-Length：响应体的长度 |
| 空行 |  |
| 响应体 |  |
| 比如是一个html，则这块返回的就是这个html页面的所有内容。 |  |

## 4.7 POST请求
### 4.7.1 POST请求的HTTP协议格式
　　请求行
　　　　请求的方式                   POST
　　　　请求的资源路径[?请求参数]
　　　　请求的协议版本号              HTTP/1.1
　　请求头
　　　　k:v
　　空行：区分请求头和请求体
　　请求体
　　　　发送给服务器的数据
    
### 4.7.2 POST请求实例
| 实例 | 说明 |
|:------ |:------ |
| 请求行 |  |
| POST /java_web_3/hello HTTP/1.1 | 1、请求方式 2、请求的资源路径 3、请求的协议和版本号 |
| 请求头 |  |
| Host: localhost:8080 |  |
| Connection: keep-alive |  |
| Content-Length: 24 | Content-Length：表示发送的数据(请求体)的长度 |
| Cache-Control: max-age=0 | Cache-Control：表示如何控制缓存。no-cache表示不缓存。 |
| sec-ch-ua: " Not A;Brand";v="99", "Chromium";v="90", "Google Chrome";v="90" |  |
| sec-ch-ua-mobile: ?0 |  |
| Upgrade-Insecure-Requests: 1 |  |
| Origin: http://localhost:8080 |  |
| Content-Type: application/x-www-form-urlencoded | Content-Type：表示发送的数据类型。application/x-www-form-urlencoded表示提交的数据格式是name=value&name=value，然后对其进行url编码(将非英文内容转换为：%xx%xx)multipart/form-data表示以多段的形式提交数据给服务器(以流的形式提交，用于上传) |
| User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36 |  |
| Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/<br/>avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9 |  |
| Sec-Fetch-Site: same-origin |  |
| Sec-Fetch-Mode: navigate |  |
| Sec-Fetch-User: ?1 |  |
| Sec-Fetch-Dest: document |  |
| Referer: http://localhost:8080/java_web_3/a.html | Referer：请求发起时，浏览器地址栏中的地址(从哪来) |
| Accept-Encoding: gzip, deflate, br |  |
| Accept-Language: zh-CN,zh;q=0.9 |  |
| Cookie: Idea-71feb1f0=80b67c67-bcd2-45b5-b7b1-386160294d79 |  |
| 请求体 |  |
| hidden1=hdd&hidden2=hdd2 |  |

# 5 Servlet
## 5.1 什么是servlet？
　　是javaEE规范之一，规范就是接口；
　　是javaWeb三大组件之一。另外2个组件是Filter过滤器、Listener监听器；
　　是运行在服务器上的一个java程序，可以接收客户端发送过来的Http请求，并响应数据给客户端。

## 5.2 手动实现servlet程序
　　编写一个类去实现Servlet接口；
　　实现service方法，处理请求，并响应数据；
　　到web.xml中配置servlet程序的访问地址，
　　当我们从浏览器访问：http://ip:port/java_web_3/hello时，最终是如何访问到写servlet的实现类？
　　　　http://ip:port/java_web_3/hello代表的含义如下
　　　　http：http协议；
　　　　ip：服务器的ip，ip唯一，可以通过ip确定访问哪个服务器；
　　　　port：服务器端口，1个端口只能被1个程序占用，所以根据port可以确定程序；
　　　　/java_web_3：工程路径，即tomcat\webapps\java_web_3；
　　　　/hello：资源路径，会从servlet-mapping匹配其中的url-pattern，如果匹配到了，会获取其servlet-name，再根据此name从servlet标签中查找与此servlet-name一致的那个servlet，再调用servlet-class所写的全限定名的类，调用其service方法。
　　　　<servlet>
　　　　　　<servlet-name>HelloServlet</servlet-name>
　　　　　　<servlet-class>com.xyb.servlet.HelloServlet</servlet-class>
　　　　</servlet>
　　　　<servlet-mapping>
　　　　　　<servlet-name>HelloServlet</servlet-name>
　　　　　　<url-pattern>/hello</url-pattern>
　　　　</servlet-mapping>

　　为什么url-pattern不直接写在servlet的子标签位置呢？
　　因为1个servlet实现类，可能会设置多个url-pattern。

　　配置的注意点：
　　　　url-pattern的内容必须以/开头，/代表http://ip:port/工程路径/；
　　　　servlet标签和servlet-mapping标签的子标签"的servlet-name"需保持一致。

## 5.3 idea生成servlet
　　想要在包A下生成，对包A：右键 -> New -> create new servlet -> 根据要求设置，create java EE 6 annotated class勾选会生成注解形式。

## 5.4 servlet生命周期
　　执行servlet构造器方法，会在第一次访问，创建servlet时调用，后面不会再被调用；
　　执行init(ServletConfig var1)初始化方法，会在第一次访问，创建servlet时调用，后面不会再被调用；
　　执行service(ServletRequest var1, ServletResponse var2)方法，每次访问都会被调用；
　　执行destroy销毁方法。停止web应用的时候才会被调用。

## 5.5 Servlet和HttpServlet的关系？
　　1、当初设计Servlet的时候，认为可能不止http一种协议，所以是现在这样设计接口的；只负责定义Servlet程序的访问规范；
　　2、GenericServlet是对Servlet的抽象实现类，这个方法重写了Servlet的接口方法，但是同样有一个service(ServletRequest var1, ServletResponse var2)的抽象方法。做了很多空实现，并持有一个ServletConfig类的引用，对ServletConfig的使用做一些方法。
　　3、HttpServlet extends GenericServlet，这个类实现service(ServletRequest var1, ServletResponse var2)方法，且其中还调用了doGet()、doPost()等方法，我们只要extends这个类，重写这些方法就可以，专注于业务逻辑。
　　其中doGet()、doPost()方法还可以抛出异常。
　　如果需要重写init(ServletConfig config)方法，一定要调用super.init，不然无法获取ServletConfig对象，重写init方法，则不需要，查看GenericServlet.init(ServletConfig config)即可知道原因。
　　4、自定义的Servlet程序：一般我们写Servlet只要extends HttpServlet即可，重写其中的doGet()、doPost()。

## 5.6 ServletConfig类，Servlet程序配置信息类，作用如下
　　1、获取Servlet程序的别名servlet-name；
　　2、获取初始化参数：init-param；
　　3、获取ServletContext对象。
　　ServletConfig和Servlet对象都是tomcat负责创建的，我们负责使用；Servlet程序默认是第一次访问的时候创建，ServletConfig是每个Servlet程序创建时，就创建一个对应的ServletConfig对象。

## 5.7 ServletContext
　　是一个接口，表示Servlet上下文对象；
　　一个web工程，只有一个ServletContext对象实例，类似全局变量；
　　是一个域对象，可以像Map一样存取数据的对象；这里的域指的是存取数据的操作范围，是整个web工程类似Map，我们可以用setAttribute()、getAttribute()、removeAttribute()来存、取、删数据。
　　在web工程部署启动时创建，在web工程停止时销毁。
　　4个常用作用：
　　　　1、获取web.xml中配置的上下文参数context-param；
　　　　2、获取当前的工程路径，格式：/工程路径；
　　　　3、获取工程部署后在服务器的绝对路径；
　　　　4、像Map一样存储数据。

## 5.8 HttpServletRequest类

　　每次只要有请求进入Tomcat服务器，Tomcat服务器就会把请求过来的HTTP协议信息解析好封装到Request对象中。然后传递到service方法(doGet、doPost)中给我们使用。我们可能通过HttpServletRequest对象，获取到所有请求的信息。
　　HTTP请求有什么内容，可以查看"九、HTTP协议"。
　　接收乱码问题，查看"RequestApiServlet5"。

## 5.9 HttpServletResponse类
　　每次只要有请求进入Tomcat服务器，Tomcat都会创建一个Response对象传递给Servlet程序去使用。HttpServletResponse表示所有响应的信息。
　　如果需要设置返回给客户端的信息，都可以通过HttpServletResponse对象来设置。

### 5.9.1 2个输出流的说明

　　字节流：getOutputStream(); 常用于下载(传递二进制文件)；
　　字符流：getWriter()；常用于回传字符串；
　　两个流同时只能使用一个，强行使用2个会报错。
　　Response返回乱码问题，查看"ResponseApiServlet7"。


### 5.9.2 往客户端回传数据
　　使用PrintWriter，具体查看ResponseApiServlet7。

## 5.10 Servlet请求转发
　　浏览器访问Servlet1，在Servlet1中对Servlet进行访问。
　　特点：
　　　　浏览器地址栏没有变化；
　　　　请求和请求转发对于浏览器来说是一次请求；
　　　　请求和请求转发共享Request域中的数据；
　　　　可以请求转发到WEB-INF目录下(通过浏览器访问是无法访问到WEB-INF目录下)。

## 5.11、Servlet请求重定向
　　是指客户端给服务器发请求，然后服务器告诉客户端说，我给你一些地址，你去新地址访问。
　　响应码为302，再设置请求头的Location。
　　特点：
　　　　浏览器地址栏会变化；
　　　　2次访问Servlet是2次请求；
　　　　不共享Request域中的数据；
　　　　不能访问WEB-INF目录下(通过浏览器访问是无法访问到WEB-INF目录下)；
　　　　可以访问工程外的资源。

## 5.12 /的含义
　　如果被浏览器解析，则得到的地址是http://ip:port/  <a href="/">aaa</a>
　　如果被服务器解析，则得到的地址是http://ip:port/工程名
　　　　<url-pattern>/aaa</url-pattern>；
　　　　servletContext.getRealPath("/")；
　　　　request.getRequestDispatcher("/")；
　　特殊情况，如果使用重定向到"/"，把/发送给浏览器，则会得到http://ip:port/。

# 6 jsp
## 6.1 什么是jsp?
　　全称是Java Server Pages。Java的服务器页面。
　　主要作用是代替Servlet程序回传HTML页面！！！！！！
　　因为Servlet程序回传html页面是一件非常烦琐的事情，开发和维护成功极高，是使用流输出每一行html的内容。
　　jsp的本质就是Servlet，我们看待jsp的时候，需要直接将它看作Servlet。

## 6.2 创建jsp和访问
　　New => jsp/jspx
　　和html页面一样，都存放在web目录下，和html的访问方式一样。

## 6.3 jsp的本质
　　jsp页面本质是一个HttpServlet的子类，因为Tomcat会将jsp翻译为一个java源文件，idea中将翻译后的jsp源文件存放在启动时的Using CATALINA_BASE目录，并将其编译为.class字节码程序(类名_jsp.java)。
　　我们打开此文件，发现其extends org.apache.jasper.runtime.HttpJspBase extends HttpServlet。所以jsp翻译后的java文件本质是HttpServlet的子类。

## 6.4 jsp中的声明
　　既然jsp的本质是Servlet，那我们自己创建的Servlet有什么功能，则tomcat将jsp翻译后的Servlet应该有同样的内容。
　　jsp中的所有html代码在翻译后都会在_jspService()中，用resp.out进行输出，想在service()方法中写代码，需要用"<% 代码脚本 %>"来包围，则其中的代码都会在service()中；
　　想在jsp的servlet中写成员变量、静态块、内部类、方法等代码，用"<%! 声明脚本 %>"包围；
　　表达式脚本：<%=值 %>，可以输出值。         
            

# 7 Listener监听器
## 7.1 监听器
　　Listener是javaEE规范，就是接口，作用是监听某种事物的变化，通过回调函数，反馈给客户(程序)去做一些相应的处理。

## 7.2 ServletContextListener监听器
　　可以监听ServletContext对象的创建和销毁；
　　ServletContext对象在web工程启动的时候创建，在web工程终止的时候销毁；
　　只要实现ServletContextListener接口即可，在web.xml配置监听器；

# 8 Cookie和Session
## 8.1 Cookie
### 8.1.1 Cookie是什么
　　Cookie是服务器通知客户端保存k-v的一种技术；
　　客户端有了Cookie后，每次请求都发送给服务器；
　　每个Cookie的大小不能超过4kb。
　　查看\xyb-total-project\java-web-3\web\jsp\cookie10.jsp；

### 8.1.2 Cookie的查看
　　chrome：F12 -> Application -> storage -> Cookies -> http://ip:port -> 可以查看name和value值。
　　还可以删除Cookie。

### 8.1.3 Cookie的创建流程
　　1、客户端(浏览器)一开始没有Cookie；
　　2、服务器(Tomcat)创建Cookie对象，再通知客户端保存Cookie；
　　3、通过响应头Set-Cookie通知客户端保存Cookie；
　　4、客户端收到响应后，发布有Set-Cookie响应头，就去看一下，有没有这个Cookie，没有就创建，有就修改。

### 8.1.4 服务器端Cookie的获取
　　1、客户端(浏览器)有了Cookie；
　　2、通过请求头：Cookie:key1=value，把Cookie信息发送给服务器；
　　3、服务器(Tomcat)通过客户端发送过来的Cookie，通过getCookies()获取所有的Cookie。
　　
### 8.1.5 Cookie值的修改
　　查看代码
　　1、创建一个name相同的Cookie，再添加到response中，后面的会覆盖前面的；
　　2、调整Cookie的setValue()方法，设置新值，再添加到response中。

### 8.1.6 Cookie生命周期
　　查看代码

### 8.1.7 Cookie的有效路径path的设置
　　Cookie的path属性可以有效设置过滤哪些Cookie可以发送给服务器，哪些不发。path属性是通过请求的地址来进行有效过滤。
　　path表示，在此路径下的所有请求，都可以发送Cookie。可以用于免用户登陆访问，如登陆页面。
　　例：
　　CookieA path=/工程路径
　　CookieB path=/工程路径/abc
　　
　　请求地址如下：
　　　　http://ip:port/工程路径/a.html
　　　　　　CookieA发送
　　　　　　CookieB不发送
　　　　http://ip:port/工程路径/abc/a.html
　　　　　　CookieA发送
　　　　　　CookieB发送

## 8.2 Session
### 8.2.1 Session是什么
　　1、Session是一个接口(HttpSession)；
　　2、Session就是会话，是用来维护一个客户端和服务器之间关联的一种技术；
　　3、每个客户端都有自己的一个Session会话；
　　4、Session会话中，我们经常用来保存用户登陆之后的信息。
　　查看\xyb-total-project\java-web-3\web\jsp\session11.jsp

### 8.2.3 Session的创建和获取
　　request.getSession()：
　　　　第一次调用是创建Session会话；
　　　　之后调用都是：获取前面创建的Session会话对象；
　　
　　isNew()：判断是不是刚创建的Session会话对象，true为新创建，false则相反；
　　
　　getId()：获取Session的id，每个会话都有一个身份证号。也就是id值。而且这个id是唯一的。
　　
### 8.2.4 Session生命周期
　　查看代码
　　默认超时时间是30分钟，因为在Tomcat服务器配置文件web.xml中有以下配置，表示当前tomcat服务器下所有session超时配置时间为：30min。
　　<session-config>
　　　　<session-timeout>30</session-timeout>
　　</session-config>
　　也可以在web.xml自己配置。
　　注意，session超时是指客户端再次请求服务器的最大间隔时间，比如设置1s，如果不停访问服务器，则会每次访问服务器时都重置超时时间。

### 8.2.5 浏览器和Session的关联
　　Session技术是基于Cookie技术的，流程如下：
　　第一次请求和响应：
　　1、客户端(没有Cookie)，发送请求；
　　2、服务器通过request.getSession()，发现不能从Cookie中获取SessionId(唯一)，则会新创建一个Session对象，并保存到内存中；
　　3、服务器每次创建Session时，都会创建一个Cookie{JESSIONID:SessionId的值}，处理完数据后，给客户端返回消息的前，会将此Cookie一并返回给客户端；
　　4、客户端收到响应后，解析其中的Cookie，并创建Cookie对象；

　　第二次请求和响应：
　　5、客户端再次发送请求(此时已经有Cookie{JSSSIONID:SessionId}了)，会将上面的JSSSIONID:SessionId作为其中的一个kv，发送给服务器；
　　6、服务器通过request.getSession()，发现可以从Cookie中获取JSSSIONID，于是根据SessionId从内存中读取对应的Session对象；
　　7、服务器返回消息给客户端，依然会返回一个Cookie{JSSSIONID:SessionId}；
　　8、服务器就是通过客户端的Cookie知道这是同一个Session的内容的。
　　
　　为什么关闭浏览器后，客户端再次请求就是一个新的Session？
　　因为关闭浏览器后，Cookie失效，也就不会给服务器发送JESSIONID，服务器就会重新创建一个Session。
　　
# 9 Filter过滤器
## 9.1 什么是过滤器
　　Filter过滤器是JavaWeb的三大组件之一；
　　是JavaEE的规范，也就是接口；
　　作用是拦截请求，过滤响应；
　　常用的拦截请求的应用场景：
　　　　权限检查、日记操作、事务管理等。
　　
## 9.2 拦截流程
　　1、客户端请求；
　　2、服务器通过过滤器，查看是否符合规则；
　　3、符合则接着访问原来的资源，不符合则跳转其它页面或做其它操作。
　　
## 9.3 代码编写流程
　　1、需要进行过滤的代码
　　实现javax.servlet.Filter类；
　　
　　2、配置过滤器
　　在web.xml中
　　<filter>
　　　　<!-- 给filter起一个别名，与filter-mapping和filter-name一样 -->
　　　　<filter-name>MyFilter</filter-name>
　　　　<!-- 具体的拦截类 -->
　　　　<filter-class>com.xyb.filter3.MyFilter</filter-class>
　　</filter>

　　3、配置需要对哪些路径进行过滤
　　<filter-mapping>
　　　　<!-- 与filter-name为此的filter匹配 -->
　　　　<filter-name>MyFilter</filter-name>
　　　　<!--
　　　　　　url-pattern配置拦截路径
　　　　　　/表示http://ip:port/工程路径 映射idea的web目录
　　　　　　/*：表示请求地址为:http://ip:port/工程路径/*
　　　　-->
　　　　<url-pattern>/</url-pattern>
　　</filter-mapping>
　　
　　
　　
　　
　　
　　
　　
　　

　　
　　
　　
　　
　　
　　
　　
　　
　　
　　

　　
　　
　　
　　
　　
　　
　　
　　
　　
　　

　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
