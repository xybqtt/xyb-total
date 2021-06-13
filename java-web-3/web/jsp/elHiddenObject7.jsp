<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
    <head>
        <title>el的11个隐藏对象</title>
        <style type="text/css">
            div {
                border: 1px solid black;
                margin-bottom: 10px;
            }
        </style>
    </head>
    <body>
        <%--
            本jsp可以用"http://localhost:8080/java_web_3/jsp/elHiddenObject7.jsp?username=555&hobby=a&hobby=2"访问
            el表达式中11个隐含对象，是el自己定义的，可以直接使用，
            注意el表达式仅可获取内置对象的属性和值，不能获取在java代码中自己定义的变量，除非将其设置到域对象中。

            1、pageContext：PageContextImpl类，可以获取jsp中的9大内置对象，注意el表达式中只有pageContext对象，没有jsp中的九
        大对象，且这个pageContext就是jsp中的内置pageContext对象，我们可以通过jsp的pageContext内置对象，获取其它的jsp内置对象；

            2、pageScope：Map<String, Object>，获取pageContext域属性，相当于pageContext.getAttribute("xxx")；
            3、requestScope：Map<String, Object>，获取request域属性，相当于request.getAttribute("xxx")；
            4、sessionScope：Map<String, Object>，获取session域属性，相当于session.getAttribute("xxx")；
            5、applicationScope：Map<String, Object>，获取application域属性，相当于application.getAttribute("xxx")；
                注意对于域对象的value，我们可以直接使用${key}获取，当4个域中都有相同的key的数据的时候，el表达式会
            按照4个域的从小到大的顺序进行搜索，找到就输出。pageContext、request、session、
            application。不建议省略域对象名。

            6、param：Map<String, String>，获取请求参数的值，相当于request.getParameter("xxx")；
            7、paramValues：Map<String, String[]>，获取请求参数的值，获取多个值的时候使用，相当于request.getParameterValues("xxx")；

            8、header：Map<String, String>，获取请求头参数的值，相当于request.getHeader("xxx")；
            9、headerValues：Map<String, String[]>，获取请求头参数的值，获取多个值的时候使用，相当于request.getHeaders("xxx")；

            10、cookie：Map<String, Cookie>，获取当前请求的Cookie信息；
            11、initParam：Map<String, Cookie>，获取web.xml中<context-param>内的参数，${ initParam.xxx}，xxx就是<param-name>标签内的值，进而得到<param-value>中的值。
        --%>
        <%
            pageContext.setAttribute("pageContext", "pageContext1");
            request.setAttribute("request", "request1");
            session.setAttribute("session", "session1");
            application.setAttribute("application", "servletContext1");
        %>

        <div>
            <h1>在el表达式获取jsp的9大内置对象</h1>
            <p>获取request对象：${pageContext.request}</p>
            <p>获取response对象：${pageContext.response}</p>
            <p>获取session对象：${pageContext.session}</p>
            <p>获取ServletConfig对象：${pageContext.servletConfig}</p>
            <p>获取servletContext对象：${pageContext.servletContext}</p>
            <p>获取out对象：${pageContext.out}</p>
            <p>获取page对象：${pageContext.page}</p>
            <p>获取exception对象：${pageContext.exception}</p>
        </div>


        <%
            pageContext.setAttribute("key", "pageScope");
            request.setAttribute("key", "requestScope");
            session.setAttribute("key", "sessionScope");
            application.setAttribute("key", "applicationScope");

        %>
        <div>
            <h1>el表达式的11个内置对象的使用</h1>
            <p>1、pageContext.request：${pageContext["request"]}</p>

            <h2>获取域对象的属性值</h2>
            <p>2、pageScope.key：${pageScope["key"]}</p>
            <p>3、requestScope.key：${requestScope.key}</p>
            <p>4、sessionScope.key：${sessionScope.get("key")}</p>
            <p>5、applicationScope.key：${applicationScope.key}</p>

            <h2>获取el内置对象的param，相当于$ {pageContext.request.username}</h2>
            <p>6、param.username = pageContext.getRequest().getParameter("username")：${param.username}</p>
            <p>7、paramValues.hobby = pageContext.getRequest().request.getParameters("hobby")：${paramValues.hobby}</p>
            <p>8、header["sec-ch-ua"]：${header["sec-ch-ua"]}</p>
            <p>9、headerValues：${headerValues}</p>
            <p>10、cookie：${cookie}</p>
            <p> 10.1、cookie名称：${cookie.JSESSIONID.name}</p>
            <p> 10.2、cookie值：${cookie.JSESSIONID.value}</p>
            <p>11、initParam.username = pageContext.getServletContext().getInitParameter("username")：${initParam.username}</p>
        </div>



    </body>
</html>
