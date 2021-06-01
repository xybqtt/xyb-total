一、javaweb的概念
    1、什么是javaweb
    javaweb是指，所有通过java语言编写可以通过浏览器访问的程序的总称；
    是基于请求和响应来开发的。

    2、什么是请求？
    请求是指客户端给服务器发送数据，叫请求request。

    3、什么是响应？
    响应是指服务器给客户端回传数据，叫响应response。

    4、请求和响应的关系？
    请求和响应是成对出现的。

二、web资源分类
    按实现的技术和呈现的效果不同，分为静态资源和动态资源2种。
    静态资源：html、css、js、txt、mp4、jpg等；
    动态资源：jsp页面、servlet程序。

三、常用的web服务器
    tomcat：由apache组织提供的一种web服务器，提供对jsp和servlet的支持，是一种轻量级
的javaweb窗口(服务器)，也是当前应用最广的javaweb服务器(免费)；
    jboss：是一个遵从javaEE规范、开放源代码的、纯java的ejb服务器，支持所有的javaEE规
范(免费)；
    GlassFish：是oracle公司开发的一款javaweb服务器，强健、达到产品质量级(应用少)；
    Resin：是CAUCHO公司的产品，非常流行，对servlet和jsp提供了良好的支持，性能较好，
resin自身采用java开发(收费、应用多)；
    WebLogin：oracle产品，是目前最广泛的web服务器，支持javaEE规范，而且不断改进产品，
适合大型项目(收费、用的不多、适合大公司)。

四、tomcat服务器和servlet版本的对应关系
    tomcat        servlet/jsp版本        javaEE版本        运行环境
    4.1           2.3/1.2               1.3               jdk1.3
    5.0           2.4/2.0               1.4               jdk1.4
    5.5/6.0       2.5/2.1               5.0               jdk5.0
    7.0           3.0/2.2               6.0               jdk6.0
    8.0           3.1/2.3               7.0               jdk7.0
    较为常用的是7.*/8.*版本。
    servlet程序从2.5(xml)版本是现在世面使用最多的版本，到了3.0之后，就是注解版本。

五、tomcat介绍
    1、目录介绍
        bin：存放tomcat自身的可执行程序；
        conf：存放tomcat服务器的配置文件；
        lib：存放tomcat服务器的jar包，即对javaEE规范的实现；
        logs：存放tomcat服务器运行时输出的日志信息；
        temp：存放tomcat运行时产生的临时数据；
        webapps：存放需要部署的web程序，1个web项目，在其中有1个目录；
        work：tomcat工作时的目录，存放tomcat运行时jsp翻译为servlet源码、session钝化
    (序列化)的目录。

    2、如何启动tomcat
        1)、bin\startup.bat，双击；
        2)、在bin目录，打开命令窗口，catalina run，启动失败可以显示错误原因，需要配置
    CATALINA_HOME=tomcat安装目录。
        在浏览器访问，可以访问如下即启动：
            http://localhost:8080；
            http://127.0.0.1:8080；
            http://真实ip:8080；
        启动注意点：
            需要配置JAVA_HOME环境变量；

    3、tomcat停止
        1)、关闭黑窗口；可在黑窗口按ctrl+c；
        2)、在bin目录下，双击shutdown.bat；(主要方式)

    4、修改tomcat端口号
        1)、conf\server.xml；
        2)、找到Connector标签，修改port；
        3)、重启tomcat。
        为什么在访问百度等网站时不用端口号？
        因为http协议默认端口号是80(省略了)，实际访问百度的是http://www.baidu.com:80

    5、部署web工程到tomcat中的方式
        1)、只需要把web工程(例book工程)的目录复制到tomcat的webapps目录下即可。通过
    http://ip:port/book/html页面；
        2)、通过这种方式，我们就可以不必把工程放到tomcat中了，进入到"tomcat\conf\Catalina\localhost"目录下，创建xxx.xml，在xml中
    粘贴<Context path="/a" docBase="D:\IdeaProjects\Javaweb\out\artifacts\web03_war_exploded">
    其中
        Context表示工程上下文；
        path表示工程的访问路径；
        docBase表示工程的目录在哪里；
    但是我们访问的时候，是http://ip:port/path的值/index.html = http://ip:port/docBase的值/index.html，
    不能再用http://ip:port/工程名访问了。
        idea将编译后的存放tomcat运行时jsp翻译为servlet源码、session钝化(序列化)的目录：查看启动时的Using CATALINA_BASE。

    6、在本地磁盘打开html和
        1)、手拖打开html页面到浏览器，地址为file://...，用的是file协议，表示告诉浏览器
    直接读取file:协议后面的路径，解析在浏览器即可。不需要通过服务器。
        2)如果在浏览器地址栏输入访问地址：http://ip:port/工程名/资源名
        表示使用的是http协议，需要发送请求到服务器，再接收服务器返回的资源。

    7、ROOT工程的访问，以及默认index.html页面的访问
        1)、启动tomcat后，输入http://localhost:8080/ 此时默认的访问工程就是ROOT工程，
    此时打开是那个雄猫页面。
        2)、当我们在浏览器输入http://localhost:8080/工程名/ 但是没有输入想要访问的
    资源时，默认访问index.html页面。

    8、idea整合本地tomcat
        settings -> Application Servers -> + -> Tomcat Server -> 选本地tomcat，ok；

六、javaweb工程创建等操作
    1、idea的javaweb动态工程的创建
        new module -> Java Enterprise -> 选sdk、java EE version、tomcat(上面8
    中配置的)、勾选Web Application、勾选Create web.xml，接着往下走就行。
        在创建好的WEB-INF目录下，再创建一个lib目录。
        java模块如何变成一个javaweb模块：对模块右键 -> Add Framework Support ->
    勾选Web Application、勾选Create web.xml。

    2、javaweb动态工程目录说明
        工程名(对应开发过程中的web目录)
            src：java代码开发目录
            web：专门用来存放web工程的资源文件，如html、css、js等
                WEB-INF：是一个受服务器保护的目录，浏览器无法直接访问到此目录内容
                    lib：存放第三方的jar包(idea还需要自己配置导入)
                    web.xml：是整个动态web工程的配置部署描述文件，可以在这里配置很多
                        web工程的组件，如Servlet程序、Filter过滤器、Listener监听
                        器、Session超时等。

    3、如何给动态web工程添加额外的jar包
        被添加的jar包前面会有箭头可以往下选。
        第1种方式：将jar包放入lib中，选中所有的jar包并右键 -> Add as Library ->
    选中此web的模块并ok。

        第2种方式：本质是将一堆jar包，装在1个lib库，然后将这个lib库添加到某个模块中；
            File -> Project Structure -> Libraries -> + -> java ->
        选jar包 -> 选要添加到模块A -> 可以把这个lib库的名字改下 -> Artifacts ->
        选择 模块A:war exploded -> Fix -> add lib库 to artifact。


七、如何将java模块A变成java web模块 Aweb
    1、对A右键 -> Add Framework Support；
    2、勾选Web Application -> 勾选 Create web.xml，项目中就会在src的同一级出现
web目录(黑色目录、蹭2靠左有个蓝点、eclipse可能是webapp)；
    在WEB-INF目录下创建lib目录。
    3、File -> Project Structure ->
        Modules：
            点选A模块
                Paths：
                    勾选Use module compile out path，设置java代码的输出路径，建议Output path："A模块绝对路径\target\production\模块名"，建议Test output path："A模块绝对路径\target\test\模块名"；
                Dependencies：
                    需要的依赖，在Libraries中设置。
            点击模块下的Web：(和Facets中需要配置的一模一样，在这儿配了就不用到Facets中配置了)
                name：可自定义修改；
                Deployment Descriptors：
                    Type：不可修改；
                    path：web.xml的绝对路径；
                Web Resource Directories：
                    Web Resource Directory：2中的web目录的绝对路径，即资源路径的绝对路径；被设置了这个位置的目录就是web目录，不管叫什么；
                    Path Relative to Deployment Root：要把上面的资源放到发布的根目录的什么位置，/代表就把资源放到发布目录下(Artifacts的output directory)，建议别整妖娥子，就放在这个目录下，如果不放在这个目录下，比如放在/111目录下，
                    那么，class的目录是/WEB-INF/class，资源的目录是/111/。本来资源和WEB-INF是同一级，现在资源所在的目录和WEB-INF才是同一级。
                Source Roots：java源代码目录，选择"模块绝对路径/src"；

        Libraries：
            + -> java -> 选所有需要jar包 -> 选要添加到模块A(也可以生成后，右键Add to Modules) -> 可以把这个lib库的名字改下

        Facets：
            已经设置过了，不用设置了；

        Artifacts：
            选择模块 -> Fix -> Add '依赖名' to the artifact；
            Type：
                Web Application: Exploded：相当于将打包后的war包解压的形式，比较推荐；
            Output directory：建议"模块绝对路径\target\artifacts\模块名_war_exploded"，则build后资源文件和WEB-INF都在此目录下，相当于"五.5"的docBase。

    4、配置对应的tomcat去启动
        Edit Configurations -> Tomcat Local -> 点选刚才新生成的 -> 更改name；
        Deployment：
            + -> Artifact -> 选择对应模块；
            Application context：/自定义工程名，浏览器输入http://ip:port/工程名，即可访问此工程，"/工程名"对应"Artifacts的Output directory"；
        Server，相当于"五.5"的path的设置。
            URL：启动后默认访问的url，为"http://localhost:8080/Application context的内容/"；
            HTTP Port：设置端口。
            On 'Update' action：Update classes and resources；
            On frame deactivation：选择Update classes and resources； 这个和上面的选项配合，可以进行热部署。

    5、启动访问即可。


八、servlet
    1、什么是servlet？
        1)、是javaEE规范之一，规范就是接口；
        2)、是javaWeb三大组件之一。另外2个组件是Filter过滤器、Listener监听器；
        3)、是运行在服务器上的一个java程序，可以接收客户端发送过来的Http请求，并响应
    数据给客户端。

    2、手动实现servlet程序
        1)、编写一个类去实现Servlet接口；
        2)、实现service方法，处理请求，并响应数据；
        3)、到web.xml中配置servlet程序的访问地址，
            当我们从浏览器访问：http://ip:port/java_web_3/hello时，最终是如何访问到写
        servlet的实现类？
            http://ip:port/java_web_3/hello代表的含义如下
                http：http协议；
                ip：服务器的ip，ip唯一，可以通过ip确定访问哪个服务器；
                port：服务器端口，1个端口只能被1个程序占用，所以根据port可以确定程序；
                /java_web_3：工程路径，即tomcat\webapps\java_web_3；
                /hello：资源路径，会从servlet-mapping匹配其中的url-pattern，如果匹配
            到了，会获取其servlet-name，再根据此name从servlet标签中查找与此servlet-name
            一致的那个servlet，再调用servlet-class所写的全限定名的类，调用其service方法。
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
    3、idea生成servlet
        想要在包A下生成，对包A：右键 -> New -> create new servlet ->
        根据要求设置，create java EE 6 annotated class勾选会生成注解形式。


    4、servlet生命周期
        1)、执行servlet构造器方法，会在第一次访问，创建servlet时调用，后面不会再被调用；
        2)、执行init(ServletConfig var1)初始化方法，会在第一次访问，创建servlet时调用，后面不会再被调用；
        3)、执行service(ServletRequest var1, ServletResponse var2)方法，每次访问都会被调用；
        4)、执行destroy销毁方法。停止web应用的时候才会被调用。

    5、Servlet和HttpServlet的关系？
        1)、当初设计Servlet的时候，认为可能不止http一种协议，所以是现在这样设计接口的；
    只负责定义Servlet程序的访问规范；
        2)、GenericServlet是对Servlet的抽象实现类，这个方法重写了Servlet的接口方
    法，但是同样有一个service(ServletRequest var1, ServletResponse var2)的抽
    象方法。
        做了很多空实现，并持有一个ServletConfig类的引用，对ServletConfig的使用做
    一些方法
        3)、HttpServlet extends GenericServlet，这个类实现service(ServletRequest var1, ServletResponse var2)
    方法，且其中还调用了doGet()、doPost()等方法，我们只要extends这个类，重写这些方法
    就可以，专注于业务逻辑。
        其中doGet()、doPost()方法还可以抛出异常。
        如果需要重写init(ServletConfig config)方法，一定要调用super.init，不然无法获
    取ServletConfig对象，重写init方法，则不需要，查看GenericServlet.init(ServletConfig config)
    即可知道原因。
        4)、自定义的Servlet程序
        一般我们写Servlet只要extends HttpServlet即可，重写其中的doGet()、doPost()。

    6、ServletConfig类，Servlet程序配置信息类，作用如下
        1)、获取Servlet程序的别名servlet-name；
        2)、获取初始化参数：init-param；
        3)、获取ServletContext对象。
        ServletConfig和Servlet对象都是tomcat负责创建的，我们负责使用；
        Servlet程序默认是第一次访问的时候创建，ServletConfig是每个Servlet程序创建
    时，就创建一个对应的ServletConfig对象。

    7、ServletContext
        是一个接口，表示Servlet上下文对象；
        一个web工程，只有一个ServletContext对象实例，类似全局变量；
        是一个域对象，可以像Map一样存取数据的对象；这里的域指的是存取数据的操作范围，是
    整个web工程类似Map，我们可以用setAttribute()、getAttribute()、removeAttribute()
    来存、取、删数据。
        在web工程部署启动时创建，在web工程停止时销毁。
        4个常用作用：
            1)、获取web.xml中配置的上下文参数context-param；
            2)、获取当前的工程路径，格式：/工程路径；
            3)、获取工程部署后在服务器的绝对路径；
            4)、像Map一样存储数据。

    8、HttpServletRequest类
        每次只要有请求进入Tomcat服务器，Tomcat服务器就会把请求过来的HTTP协议信息解
    析好封装到Request对象中。然后传递到service方法(doGet、doPost)中给我们使用。我
    们可能通过HttpServletRequest对象，获取到所有请求的信息。
        HTTP请求有什么内容，可以查看"九、HTTP协议"。
        接收乱码问题，查看"RequestApiServlet5"。

    9、HttpServletResponse类
        每次只要有请求进入Tomcat服务器，Tomcat都会创建一个Response对象传递给Servlet
    程序去使用。HttpServletResponse表示所有响应的信息。
        如果需要设置返回给客户端的信息，都可以通过HttpServletResponse对象来设置。
        1)、2个输出流的说明
            字节流：getOutputStream(); 常用于下载(传递二进制文件)；
            字符流：getWriter()；常用于回传字符串；
            两个流同时只能使用一个，强行使用2个会报错。
            Response返回乱码问题，查看"ResponseApiServlet7"。


        2)、往客户端回传数据
            使用PrintWriter，具体查看ResponseApiServlet7。

    10、Servlet请求转发
        浏览器访问Servlet1，在Servlet1中对Servlet进行访问。
        特点：
            浏览器地址栏没有变化；
            请求和请求转发对于浏览器来说是一次请求；
            请求和请求转发共享Request域中的数据；
            可以请求转发到WEB-INF目录下(通过浏览器访问是无法访问到WEB-INF目录下)。

    11、Servlet请求重定向
        是指客户端给服务器发请求，然后服务器告诉客户端说，我给你一些地址，你去新地址
    访问。
        响应码为302，再设置请求头的Location。
        特点：
            浏览器地址栏会变化；
            2次访问Servlet是2次请求；
            不共享Request域中的数据；
            不能访问WEB-INF目录下(通过浏览器访问是无法访问到WEB-INF目录下)；
            可以访问工程外的资源。

    12、/的含义
        如果被浏览器解析，则得到的地址是http://ip:port/
            <a href="/">aaa</a>
        如果被服务器解析，则得到的地址是http://ip:port/工程名
            <url-pattern>/aaa</url-pattern>；
            servletContext.getRealPath("/")；
            request.getRequestDispatcher("/")；
        特殊情况，如果使用重定向到"/"，把/发送给浏览器，则会得到http://ip:port/。

九、HTTP协议
    1、什么是HTTP协议
        协议是指双方或多方相互约定好，大家都需要遵守的规则；
        HTTP协议是指，客户端和服务端通信时，发送的数据，需要遵守的规则；
        HTTP协议中的数据又叫报文；
        客户端给服务器发送数据叫请求；
        服务器给客户端回传的数据叫响应；
        请求分为GET请求和POST请求。

    2、chrome查看GET请求完整格式
        F12 -> Network -> 在Name区域点击某个链接 -> View source即可；

    3、HTTP部分说明：
        1)、常用响应码
           200：请求成功；
           302：表示请求重定向；
           404：表示请求服务器已经收到了，但是你要的数据不存在(请求地址错误)；
           500：表示服务器已经收到请求，但是服务器内部错误(代码错误)。

        2)、MIME类型说明
            MIME的英文全称是"Multipurpost Internet Mail Extensions"，多功能
        Internet邮件扩充服务，其格式类型是"大类型/小类型"，并与某一种文件的扩展名相
        对应。
            常用的MIME类型如下：
                文本：                     后缀          MIME类型
                超文本标记语言文本：         .html、.htm   text/html
                普通文本：                 .txt          text/plain
                RTF文本：                 .rtf          application/rtf
                GIF图形：                 .gif          image/gif
                JPEG图形：                .jpeg、.jpg   image/jpeg
                au声音文件：               .au           audio/basic
                MIDI音乐文件：             mid、.midi    audio/midi、audio/x-midi
                RealDudio音乐文件：        .ra、.ram     audio/x-pn-realaudio
                MPEG文件：                .mpg、.mpeg   video/mpeg
                AVI文件：                 .avi          video/x-msvideo
                GZIP文件：                .gz           application/x-gzip
                TAR文件：                 .tar          application/x-tar

    3、GET、POST请求的使用时机
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

    4、GET请求
        1)、GET请求的HTTP协议格式
            a、请求行
                请求的方式                   GET
                请求的资源路径[?请求参数]
                请求的协议版本号              HTTP/1.1
            b、请求头
                k:v 组成
            c、请求参数
                username=a&hobby=b&hobby=c

        2)、GET请求实例
            请求行
            //1、请求方式 2、请求的资源路径 3、请求的协议和版本号
            GET /java_web_3/contextServlet4 HTTP/1.1

            请求头
            // Host: 表示请求的服务器ip和端口号
            Host: localhost:8080
            // Connection：告诉服务器请求连接如何处理。keep-alive表示服务器回传数据不要马上关闭，保持一小段时间连接。closes表示马上关闭
            Connection: keep-alive
            sec-ch-ua: " Not A;Brand";v="99", "Chromium";v="90", "Google Chrome";v="90"
            sec-ch-ua-mobile: ?0
            Upgrade-Insecure-Requests: 1
            // User-Agent: 浏览器的信息
            User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36
            // Accept：告诉服务器，客户端可以接收的数据类型
            Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
            Sec-Fetch-Site: same-origin
            Sec-Fetch-Mode: navigate
            Sec-Fetch-User: ?1
            Sec-Fetch-Dest: document
            Referer: http://localhost:8080/java_web_3/a.html
            // Accept-Encoding: 告诉服务器，客户端可以接收的数据编码(压缩)格式
            Accept-Encoding: gzip, deflate, br
            // Accept-Language：告诉服务器客户端可以接收的语言类型
            Accept-Language: zh-CN,zh;q=0.9
            Cookie: JSESSIONID=B504D8ACD4B5972C5941BF79FFC0A34E; Idea-71feb1f0=80b67c67-bcd2-45b5-b7b1-386160294d79

    5、GET响应
        1)、GET响应的HTTP协议格式
            a、响应行
                响应的协议和版本号
                响应的状态码
                响应状态描述符

            b、响应头
                k:v
            空行
            c、响应体
                就是回传给客户端的数据

        2)、GET响应实例
            响应行
            // 响应的协议 响应的状态码 响应状态描述符
            HTTP/1.1 200 OK

            响应头
            // Server：表示服务器的信息
            Server: Apache-Coyote/1.1
            // Date：请求响应的时间(格林时间)
            Date: Sat, 29 May 2021 08:35:14 GMT
            Accept-Ranges: bytes
            ETag: W/"1613-1622272759059"
            Last-Modified: Sat, 29 May 2021 07:19:19 GMT
            // Content-Type：表示响应体的数据类型
            Content-Type: text/html
            // Content-Length：响应体的长度
            Content-Length: 1613

            空行

            // 响应体
            比如是一个html，则这块返回的就是这个html页面的所有内容。

    6、POST请求
        1)、POST请求的HTTP协议格式
            a、请求行
                请求的方式                   POST
                请求的资源路径[?请求参数]
                请求的协议版本号              HTTP/1.1
            b、请求头
                k:v
            空行：区分请求头和请求体
            c、请求体
                发送给服务器的数据

        2)、POST请求实例
            请求行
            //1、请求方式 2、请求的资源路径 3、请求的协议和版本号
            POST /java_web_3/hello HTTP/1.1

            请求头
            Host: localhost:8080
            Connection: keep-alive
            // Content-Length：表示发送的数据(请求体)的长度
            Content-Length: 24
            // Cache-Control：表示如何控制缓存。no-cache表示不缓存。
            Cache-Control: max-age=0
            sec-ch-ua: " Not A;Brand";v="99", "Chromium";v="90", "Google Chrome";v="90"
            sec-ch-ua-mobile: ?0
            Upgrade-Insecure-Requests: 1
            Origin: http://localhost:8080
            // Content-Type：表示发送的数据类型。application/x-www-form-urlencoded表示提交的数据格式是name=value&name=value，然后对其进行url编码(将非英文内容转换为：%xx%xx)
            //          multipart/form-data表示以多段的形式提交数据给服务器(以流的形式提交，用于上传)
            Content-Type: application/x-www-form-urlencoded
            User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36
            Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
            Sec-Fetch-Site: same-origin
            Sec-Fetch-Mode: navigate
            Sec-Fetch-User: ?1
            Sec-Fetch-Dest: document
            // Referer：请求发起时，浏览器地址栏中的地址(从哪来)
            Referer: http://localhost:8080/java_web_3/a.html
            Accept-Encoding: gzip, deflate, br
            Accept-Language: zh-CN,zh;q=0.9
            Cookie: Idea-71feb1f0=80b67c67-bcd2-45b5-b7b1-386160294d79

            请求体
            hidden1=hdd&hidden2=hdd2

十、JavaEE项目
    1、三层架构
        浏览器请求服务器资源，并最终把结果展示到页面上，如Html、css、js、jq；
        1)、Web层/视图展现层 作用
            获取请求参数；
            调用Service层处理业务；
            响应数据给客户端、请求转发、重定向；
            如Servlet、Webwork、Struts、SpringMVC；
        2)、Service业务层 作用
            处理业务逻辑；
            调用持久层保存到数据库；
            如Spring框架；
        3)、Dao持久层 作用
            只负责和数据库交互；
            如Jdbc、DBUtils、JdbcTemplate、Mybatis、Hiberante、JPA；

十一、jsp
    1、什么是jsp?
        全称是Java Server Pages。Java的服务器页面。
        主要作用是代替Servlet程序回传HTML页面。
        因为Servlet程序回传html页面是一件非常烦琐的事情，开发和维护成功极高，是使用
    流输出每一行html的内容。

    2、创建jsp
        New => jsp/jspx

    3、jsp如何访问
        和html页面一样，都存放在web目录下，和html的访问方式一样。

    4、jsp的本质
        jsp页面本质是一个Servlet程序，因为Tomcat会将jsp翻译为一个java源文件，idea
    中将翻译后的jsp源文件存放在启动时的Using CATALINA_BASE目录，并将其编译为.class
    字节码程序。
        我们打开此文件，发现其extends org.apache.jasper.runtime.HttpJspBase
    extends HttpServlet。所以jsp翻译后的java文件本质是HttpServlet的子类。














