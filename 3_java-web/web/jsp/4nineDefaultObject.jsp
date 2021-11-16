<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ page pageEncoding="UTF-8" %>
<html>
    <head>
        <title>Title</title>
        <style type="text/css">
            div {
                border: 1px solid black;
                margin-bottom: 5px;
            }
        </style>
    </head>
    <body>
        <%--
            一、jsp中的9大内置对象
                是指tomcat在翻译jsp页面成为Servlet源代码后，内部提供的九大对象，叫内置对
            象。
                内置对象其实就是在_jspService()已经存在的对象，我们可以查看生成的jsp.java
            文件
                1、(javax.servlet.jsp.PageContext)pageContext：jsp的上下文对象，pageContext
            对象是JSP技术中最重要的一个对象，它代表JSP页面的运行环境，这个对象不仅封装了对其它8大隐式对
            象的引用，它自身还是一个域对象(容器)，可以用来保存数据。并且，这个对象还封装了web开发中经常
            涉及到的一些常用操作，例如引入和跳转其它资源、检索其它域对象中的属性等；
                2、(javax.servlet.http.HttpServletRequest)request：请求对象；
                3、(javax.servlet.http.HttpServletResponse)response：响应对象；
                4、(javax.servlet.http.HttpSession)session：会话对象；
                5、(javax.servlet.ServletConfig)config：ServletConfig对象；
                6、(javax.servlet.ServletContext)application：servletContext对象；
                7、(javax.servlet.jsp.JspWriter)out：jsp的输出流对象；
                8、(java.lang.Object)page：指向当前jsp的对象，几乎不用；
                9、exception：异常对象，page指令的isErrorPage="true"才有。

            二、jsp中的4个域对象：
                域对象的作用：用于保存数据，获取数据，在不同资源之间共享数据通过setAttribute和getAttribute方法
                pageContext域： 处于同一个jsp页面中数据共享是有效的，即运行pageContext.getAttribute("key")
            的时候，这个获取属性的代码如果还是在那个jsp页面，就是同一个page域；
                request域：处于同一个请求中数据共享是有效的，如请求转发后，另一个jsp页面
            中也可以获取在此页面设置的属性；
                session域：处于同一个会话中数据共享是有效的，如果浏览器关闭后、或session
            超时，就不能再获取原来设置的属性了；
                application域：处于同一个web应用中数据共享是有效的；

                这4个域的建议使用顺序，pageContext > request > session > application，
            建议在可行的情况下，使用最小的域。

            三、response.getWriter().writer()和out.writer()的区别
                out.writer()的本质是获取response.getWriter()；
                jsp中的所有代码执行完成后会做2个操作：
                    1、执行out.flush()操作，把out缓冲区的数据追加写入到response缓冲区
                现在的末尾；
                    2、会执行response的刷新操作，把全部数据写给客户端。

                所以如果不主动调用out.flush()，则使用out进行输出的内容，一定在使用response.getWriter().writer()
            之后输出，如果想让out的某行输出在response的输出之前，需要在这行代码后面执行out.flush()；

                out.writer()、out.print()输出的区别：
                    1、out.print()任何时候都可以输出任何类型，本质是将传送的参数转化为字符串，
                再调用out.writer()方法；
                    2、 out.writer()如果参数不是字符串，则会获取参数的ascii，就容易出现问题，
                因为有ascii的只有128个，在输出的时候就会乱码，但是参数是字符串，则无此问题。
        --%>

        <%
            pageContext.setAttribute("key", "pageContext");
            request.setAttribute("key", "request");
            session.setAttribute("key", "session");
            application.setAttribute("key", "application");
        %>
        <div>
            <h1>pageContext对象的使用</h1>
            <h2>获取其它8个内置对象</h2>
            <p>通过pageContext获取的8个内置对象，就是jsp的8个内置对象</p>
            <p>获取request对象：<%=pageContext.getRequest()%></p>
            <p>获取response对象：<%=pageContext.getResponse()%></p>
            <p>获取session对象：<%=pageContext.getSession()%></p>
            <p>获取ServletConfig(变量名为config)对象：<%=pageContext.getServletConfig()%></p>
            <p>获取servletContext(变量名为application)对象：<%=pageContext.getServletContext()%></p>
            <p>获取out对象：<%=pageContext.getOut()%></p>
            <p>获取page对象：<%=pageContext.getPage()%></p>
            <p>获取exception对象：<%=pageContext.getException()%></p>
        </div>
        <div>
            <h1>4个域对象的使用</h1>
            <p>pageContext.getAttribute("key")：<%=pageContext.getAttribute("key")%></p>
            <p>request.getAttribute("key")：<%=request.getAttribute("key")%></p>
            <p>session.getAttribute("key")：<%=session.getAttribute("key")%></p>
            <p>application.getAttribute("key")：<%=application.getAttribute("key")%></p>
        </div>
        <div>
            <h1>9个内置对象部分功能使用举例</h1>
            <p>pageContext.getELContext()：<%=pageContext.getELContext()%></p>
            <p>request.getRequestURI()：<%=request.getRequestURI()%></p>
            <p>response.getStatus()：<%=response.getStatus()%></p>
            <p>session.getId()：<%=session.getId()%></p>
            <p>config.getInitParameter("key")：<%=config.getInitParameter("key")%></p>
            <p>application.getInitParameter("username")：<%=application.getInitParameter("username")%></p>
            <p>out.getClass()：<%=out.getClass()%></p>
            <p>page.getClass()(代表了当前的jsp翻译后的servlet类)：<%=page.getClass()%></p>
            <p>exception == null：<%=exception == null%></p>
        </div>



    </body>
</html>
