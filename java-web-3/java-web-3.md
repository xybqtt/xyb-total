@[TOC](第2章 信息的表示和处理)

# 0 Html、js、css、servlet、jsp产生的条件

## 0.0 格式要求
　　换行前要求2个全角空格；
　　问答句，答句前面加2个全角空格；
　　注意点、特点，如果换行，前面加2个全角空格；
　　标题和后面的内容不用空行；
　　视频地址：https://www.bilibili.com/video/BV1Y7411K7zz?p=323&spm_id_from=pageDriver

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
　　settings -> Application Servers ->  "+"  -> Tomcat Server -> 选本地tomcat，ok；

# 3 javaweb工程创建等操作
## 3.1 idea的javaweb动态工程的创建
　　new module -> Java Enterprise -> 选sdk、java EE version、tomcat、勾选Web Application、勾选Create web.xml，接着往下走就行。
　　在创建好的WEB-INF目录下，再创建一个lib目录。

## 3.2 javaweb动态工程目录说明

1. 工程名(对应开发过程中的web目录)
    - src：java代码开发目录
    - web：专门用来存放web工程的资源文件，如html、css、js等
        - WEB-INF：是一个受服务器保护的目录，浏览器无法直接访问到此目录内容
            - lib：存放第三方的jar包(idea还需要自己配置导入)
            - web.xml：是整个动态web工程的配置部署描述文件，可以在这里配置很多web工程的组件，如Servlet程序、Filter过滤器、Listener监听器、Session超时等。

## 3.3 如何给动态web工程添加额外的jar包

　　被添加的jar包前面会有箭头可以往下选。
　　第1种方式：
　　　　将jar包放入lib中，选中所有的jar包并右键 -> Add as Library -> 选中此web的模块并ok。

　　第2种方式，本质是将一堆jar包，装在1个lib库，然后将这个lib库添加到某个模块中：
　　　　File -> Project Structure -> Libraries ->  "+"  -> java -> 选jar包 -> 选要添加到模块A -> 可以把这个lib库的名字改下 -> Artifacts -> 选择 模块A:war exploded -> Fix -> add lib库 to artifact。

## 3.4 如何将非java模块A变成java web模块 Aweb或导入已有的web模块

1. 变为web模块
	- 非web项目：
		- 创建web.xml：
			- 对A右键，Add Framework Support；
			- 勾选Web Application，勾选 Create web.xml；
			- 项目中就会在src的同一级出现web目录(黑色目录、蹭2靠左有个蓝点、eclipse可能是webapp)；
			- 在web/WEB-INF/下创建lib目录，用于存放依赖的jar包。
	- web项目：在Facets中设置。
2. 模块设置：File -> Project Structure ->
    - Modules：
        - 点选A模块
			- Name：模块名；
			- Sources：让idea区分哪些是源代码
				- Language level：jdk版本；
				- Sources：源代码目录，会对此目录文件进行编译；
				- Tests：测试目录；
				- Resources：资源目录，会将此目录文件copy到jar包中；
				- Test Resources：测试资源目录；
				- Exclude：忽略标记为Exluded的内容；
				- Exclude files：不进行编译文件。
			- Paths：设置编译输出文件目录
				- Compiler output：编译文件输出路径
					- Inherit ...：使用默认输出路径，即Project中的输出路径；
					- Use module ...：编译后的文件使用本模块的输出路径，可以自定义；
						- Output path：模块绝对路径\target\production\java-web-3；
						- Test output path：模块绝对路径\target\test\java-web-3；
						- Exclude output paths：排除输出路径，选中此复选框可以排除输出目录；
				- Java doc：使用可用控件组合与模块关联的外部JavaDocs存储位置的列表。(可空)
				- External Annotations：外部注释。使用+、-管理与模块关联的外部注释的位置（目录）列表。(可空)
			- Dependencies：可以定义模块SDK并形成模块依赖关系列表
				- Module SDK：模块sdk
					- 如果选择Project SDK，则模块的sdk根据项目改变而改变；
					- 可以选择自定义的模块SDK，则不随project改变；
				- 依赖列表：可以添加或删除自己依赖；
				- Dependencies storage format：选择用于存储依赖关系的格式(作为IntelliJ IDEA模块或Eclipse项目)；
				- 如果勾选的导入jar包ajar的前面的export，表示如果新建模块B依赖了模块A，则模块B中Dependencies也会有这个依赖ajar。
	
	- Libraries：定义模块SDK并形成模块依赖关系列表
		-  "+"  -> java -> 选依赖，可以打成一个依赖组，可以修改依赖组名，再在Aritifacts中进行fix设置到具体模块。
	
	- Facets： module有什么特征，比如Web、Spring和Hibernate等，非maven项目可以配置在此处达到项目的资源配置
		-  "+"  -> web -> 选择要变为web模块的模块A(与modules模块中的设置一样的，修改这2个任何1个地方都可以)
		- Name：设置web名；
		- Deployment Descriptors：发布说明
			- Type：不可修改；
			- path：web.xml的绝对路径；
		- Web Resource Directories：web资源路径，html、js、jsp、css等
			- Web Resource Directory：web目录的绝对路径，比如lis系统里面是ui；
			- Path Relative to Deployment Root：要把上面的资源放到发布的根目录的什么位置，/代表就把资源放到发布目录下(Artifacts的output directory)，不要动，就放在/下。如果不放在这个目录下，比如放在/111目录下，那么，class的目录是/WEB-INF/class，资源的目录是/111/。本来资源和WEB-INF是同一级，现在资源所在的目录和WEB-INF才是同一级。
			- Source Roots：java源代码目录和资源目录，选择"模块绝对路径/src/main/java"、选择"模块绝对路径/src/main/resources"。lis项目的源代码路径是src，没有资源路径。
	
	- Artifacts：Artifact是maven中的一个概念，表示某个module要如何打包，例如 war exploded、war、jar、ear 等等这种打包形式；一个 module有了Artifacts 就可以部署到应用服务器中了！在给项目配置 Artifacts 的时候有好多个type的选项，exploded可以理解为展开，不压缩的意思。也就是war、jar 等产出物没压缩前的目录结构。建议在开发的时候使用这种模式，便于修改了文件的效果立刻显现出来。默认情况下，IDEA 的 Modules 和 Artifacts 的 output 目录已经设置好了，不需要更改，打成war包的时候会自动在Output directory的WEB-INF目录下生产classes 目录 ，然后把编译后的文件放进去。
		-  "+"  -> Web Application exploded -> From Modules -> 选择模块(只有在Facets中设置了web.xml的模块才会显示)
		- Name：模块打包名；
		- Type：打包类型，就是上面选的；
		- Output directory：模块绝对路径\target\artifacts\模块名_war_exploded；
		- Include in project build：打勾会生成war包、不打不会生成war包。

## 3.4 配置对应的tomcat去启动

1. Edit Configurations -> Tomcat Server -> Local
	- Name：自定义名称；
	- Deployment：
		- "+" -> Artifact -> 选择对应模块；
		- Application context：/自定义工程名，浏览器输入http://ip:port/工程名，即可访问此工程，"/工程名"对应"Artifacts的Output directory"；
	- Server：
		- Application server：
			- Configure：配置tomcat；
		- URL：启动后默认访问的url，为"http://localhost:8080/Application context的内容/"；
		- VM options：jvm启动参数设置；
		- On 'Update' action：重新部署的时候进行什么操作；
		- On frame deactivation：切换窗口的时候进行什么操作do nothing； 
		- JRE：设置jdk版本；
		- HTTP Port：设置端口。
	- Before launch：启动前设置。
	- Logs：日志
		- Is Active：勾选哪个，则显示哪个；
		- Save console output to file：将控制台的输出写到具体文件中；
		- 其它自己看着选。
	- Code Coverage：不知道；
	- Startup/Connection：
		- Startup script：启动脚本，默认是tomcat的catalina.bat run；
		- Shutdown script：停止脚本，默认是tomcat的catalina.bat stop；
	- Pass environment variables：激活环境变量，不知道怎么用。

## 3.5 lis工程启动需要修改的地方

1. 上面说的先设置了；
2. 修改编码格式为GBK。
3. Edit Configurations -> A tomcat -> Before launch -> 删除所有内容，即启动前什么也不要做；
4. File -> settings -> Compiler：
	- Use compiler：eclipse，lis系统用eclipse编译器编译；
	- Proceed on errors：勾选，编译报错接着编；
	- Path to ECJ batch compiler tool(leave empty to use bundled)：选择eclipse编译器(idea自带的ecj编译器版本高，编译会出错)，选择tomcat\lib\ecj-4.4.2jar(不同tomcat的版本不一样)。
5. Edit Configurations，运行配置的tomcat configurations即可。
6. 编译的时候可能会oom，堆内存溢出，在settings -> Compiler -> Build process heap size(Mbytes)，设置堆内存大小为4096。
7. 如何在运行时特别卡，百度调整idea内存。

   

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

## 5.13 乱码原因及如何解决乱码(有时间再搞吧)
### 5.13.1 乱码的原因
　　在整个Servlet访问过程中牵扯到"浏览器(gbk)、Tomcat(iso-8859-1)、Java程序(utf-8)"，造成乱码问题的原因只有一个：即客户端与服务端的字符编码不一致所导致。
　　我们都知道，get和post请求是不一样的，当get请求时，其传递给服务器的数据是附加在URL地址之后的；而post的请求时，其传递给服务器的数据是作为请求体的一部分传递给服务器。所以两者处理乱码的方式也是不一样的。
　　下面分别对post请求和get请乱码问题进行处理。

### 5.13.2 get请求编码解码过程
　　1、get提交url1：
　　　　http://localhost:8080/java_web_3/responseApiServlet7?a=%中文

　　2、浏览器编码：
　　　　目的是根据网页编码或浏览器相关的编码将url中非ASCII字符转换为ASCII字符；
　　　　URLencode(gbk、utf-8等)，编码后的形式如下url2 = "http://localhost:8080/java_web_3/responseApiServlet7?a=%%E4%B8%AD%E6%96%87"；
　　　　
　　3、根据iso-8859-1转换二进制：
　　　　iso-8859-1是单字节编码，在0x00-0x7f的位置上和ASCII是一样的，在0x80-0xFF上是其自己规定的。
　　　　将编码后的字符用iso-8859-1转换为二进制，每一个编码后的字节都会对应一个iso-8859-1的二进制，转换后将二进制发往服务器；
　　　　url2的每个字符都在ASCII码内，为什么不直接使用将相关的二进制发送给服务器，还要进行iso-8859-1的编码？
　　　　浏览器传送url2的时候，是根据字节来传送，不是字符，例如%9f，如果

　　4、Tomcat接收到数据后，以iso-8859-1的方式解码二进制，解码后的url："http://ip:port/servlet?a=&#x676D;&#x5DDE;"
　　5、

### 5.13.3 解决get请求乱码
　　new String(req.getParameter("username").getBytes("tomcat/conf/server.xml/<Connector URIEncoding属性的值>"), "想要解析成什么格式")

### 5.13.4 解决post请求乱码
　　req.setCharacterEncoding("想要解析成什么格式");

### 5.13.5 解决响应乱码
　　// 1、先设置响应的服务器端的字符集，不然可能会乱码
　　response.setCharacterEncoding("UTF-8");
　　
　　// 解决乱码方案1，方案1和方案2只能写1个，写2个则还有可能乱码。
　　// 2、通过响应头，设置浏览器也使用UTF-8
　　response.setHeader("Content-type", "text/html; charset=UTF-8");
　　
　　// 解决乱码方案2，它会同时设置服务器和客户端都使用UTF-8，还设置了响应头，
　　// 此方法一定要在获取输出流之前使用，推荐使用此方法
　　response.setContentType("text/html; charset=UTF-8");



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


## 9.4 filter生命周期
　　1、构造器方法；
　　2、init初始化方法；
　　　　前2步在工程启动时运行；
　　3、doFilter过滤方法；
　　　　在每次拦截时运行；
　　4、destroy销毁
　　　　停止web工程时调用。

## 9.5 FilterConfig类
　　FilterConfig类对Filter的作用，相当于ServletConfig类对Servlet的作用，都是读取filter中的所有信息。
　　1、获取Filter的名称filter-name的内容；
　　2、获取在Filter中配置的init-param初始化参数；
　　3、获取ServletContext对象。

## 9.6 FilterChain过滤器链
　　在Filter的doFilter方法中调用，有2个作用：
　　1、如果后面还有过滤器，则接着调用后面的过滤器；
　　2、如果后面没有过滤器，则接着调用客户端请求的资源。
　　每个filterChain.doFilter(servletRequest, servletResponse);方法都会有前置代码和后置代码，其运行顺序如下：
　　filter1前置代码 -> filter1.filterChain.doFilter -> filter2前置代码 -> filter2.filterChain.doFilter -> 目标资源 -> filter2后置代码 -> filter1后置代码。
　　对同一个资源的多个过滤器的过滤顺序，由其在web.xml中的配置顺序决定。
　　FilterChain过滤器链上的所有过滤器默认是在同一个线程内依次执行的，且共用req和resp。

## 9.7 过滤器拦截路径
　　url-pattern的匹配，过滤器匹配只关心请求的url是否与url-pattern匹配，不关心此资源是否真的存在。
### 9.7.1 精确匹配
　　/target.jsp 这种具体到某个文件的匹配叫精确匹配，表示请求地址必须为http://ip:port/工程名/target.jsp；

### 9.7.2 目录匹配
　　/admin/*：此路径表示，请求地址必须为http://ip:port/工程名/admin/*；

### 9.7.3 后缀名匹配
　　*.html：表示请求地址，必须以.html结尾，才会拦截；
　　这个html不是固定的，可以是自定义的，如*.do；
　　注意后缀名匹配不能以/开头。


# 10 JSON
## 10.1 什么是JSON
　　JSON(JavaScript Object Notation)是一种轻量级的数据交换格式。易于人阅读和编写。同时也易于机器解析和生成。JSON采用完全独立于语言的文本格式，而且很多语言都提供了对json的支持。使得JSON成为理想的数据交换语言。
　　JSON是一种轻量级的数据交换格式。
　　轻量级指的是跟xml做比较。
　　数据交换指的是客户端和服务器之间业务数据的传递格式。

## 10.2 json在js中的使用
### 10.2.1 json的定义
　　json是由键值对组成，并且由{}包围。每个键由引号引起来，键值之间用冒号分隔，多组键值对之间进行逗号进行分隔。

### 10.2.2 json的访问
　　json本身是一个对象；
　　json中的key我们可能理解为是对象中的一个属性；
　　json中的key访问就跟访问对象的属性一样：json对象.key。

### 10.2.3 json的2个常用方法
　　json的存在有2种形式。
　　1、对象形式存在，我们称为json对象，一般在我们要操作json数据的时候使用；
　　2、字符串形式存在，称为json字符串。一般在客户端和服务器进行数据交换时使用；
　　JSON.stringify()：把json对象转换为json字符串；
　　JSON.parse()：把json字符串转换为json对象。

## 10.3 json在java中的使用(需要gson包)
　　查看这个类com.xyb.servlet.JsonServlet12
　　

# 11 AJAX请求
　　AJAX即"Asynchronous JavaScripte And XML"(异步JavaScript和XML)，是指一种创建交互式网页应用的网页开发技术。
　　ajax是一种浏览器通过js异步发起请求，局部更新页面的技术。
　　参考web\jsp\ajax14.jsp
## 11.1 ajax特点
　　1、ajax请求是局部更新，浏览器地址栏不会变化；
　　2、局部更新不会舍弃原来页面的内容；

## 11.2 ajax请求需要的参数
　　url：访问地址；
　　data：发送给服务器的数据，可以是a=b&c=d这种格式，也可以是{a:b, c:d}的格式(最终会转化为前一种格式)
　　type：GET或POST请求；
　　success：请求成功，响应的回调函数；
　　dataType：响应的数据类型，常用的有
　　　　text：表示纯文本；
　　　　xml：表示xml；
　　　　json：表示json对象。

## 11.3 几种特殊的jq访问
　　$.ajax(url, data, type, successFn, dataType);与js原生的参数一样；
　　$.get(url, data, successFn, dataType);因为确定是使用get请求，所以省略type参数；
　　$.post(url, data, successFn, dataType);因为确定是使用post请求，所以省略type参数；
　　$.getJSON(url, data, successFn);因为确定是使用get请求、且返回类型是json，所以省略type、dataType参数；
　　
## 11.4 表单序列化
　　使用ajax输入data时，我们需要把每个表单项和其值一一写进去，比较麻烦，使用$("表单").serialize()可以自动把所有表单项弄成a=b&c=d的形式，简化操作。
　　
# 12 i18n国际化(页面上选择语言的那个，以后有需要再看吧)
　　p320 - 325
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
