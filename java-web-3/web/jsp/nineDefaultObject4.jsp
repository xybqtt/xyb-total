<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.io.IOException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <%--
        一、jsp中的9大内置对象
            是指tomcat在翻译jsp页面成为Servlet源代码后，内部提供的九大对象，叫内置对
        象。
            内置对象其实就是在_jspService()已经存在的对象，我们可以查看生成的jsp.java
        文件
            前5个是servlet的：
            1、(javax.servlet.http.HttpServletRequest)request：请求对象；
            2、(javax.servlet.http.HttpServletResponse)response：响应对象；
            3、(javax.servlet.ServletConfig)config：ServletConfig对象；
            4、(javax.servlet.ServletContext)application：servletContext对象；
            5、(javax.servlet.http.HttpSession)session：会话对象

            后4个是jsp的：
            6、(java.lang.Object)page：指向当前jsp的对象；
            7、(javax.servlet.jsp.PageContext)pageContext：jsp的上下文对象；
            8、(javax.servlet.jsp.JspWriter)out：jsp的输出流对象；
            9、exception：异常对象，page指令的isErrorPage="true"才有。

        二、jsp中的4个域对象：
            域对象的作用：用于保存数据，获取数据，在不同资源之间共享数据。
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
        pageContext域是否有值：<%=pageContext.getAttribute("key")%><br/>
        request域是否有值：<%=pageContext.getAttribute("key")%><br/>
        session域是否有值：<%=pageContext.getAttribute("key")%><br/>
        application域是否有值：<%=pageContext.getAttribute("key")%><br/>
    </body>
</html>
