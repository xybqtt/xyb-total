<%@ page import="com.xyb.util.Utils" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Servlet学习</title>
        <style type="text/css">
            .cls1 {
                border: 3px solid #ff0000;
                margin: 10px;
                float: left;
                width: 50%;
            }

            .cls2 {
                float: right;
            }
        </style>
    </head>
    <body>
        <iframe name="target" width="500" height="500" class="cls1, cls2"></iframe>
        <div class="cls1">
            <%
                Utils.addWebUriToReq(request, "webUri");
                System.out.println(request.getAttribute("webUri"));
                // webUri = http://127.0.0.1:8080/java_web_3/
            %>
            <h1>Servlet</h1>
            <h2>1、servlet初使用</h2>
            <form action="${requestScope.webUri.concat('lifeOfServlet1')}" method="get">
                通过get请求访问HelloServlet
                <p>访问地址：${requestScope.webUri.concat('lifeOfServlet1')}</p>
                <button type="submit">get请求提交</button>
            </form>
            <form action="${requestScope.webUri.concat('lifeOfServlet1')}" method="post">
                <input type="hidden" name="hidden1" value="hdd">
                <input type="hidden" name="hidden2" value="hdd2">
                <p>访问地址：${requestScope.webUri.concat('lifeOfServlet1')}</p>
                <button type="submit">post请求提交</button>
            </form>
            <hr/>


            <h2>2、HttpServlet</h2>
            <form action="${requestScope.webUri.concat('helloHttpServlet2')}" method="get">
                <input type="text" name="key" value="key1">
                <p>访问地址：${requestScope.webUri.concat('helloHttpServlet2')}</p>
                <button type="submit">get请求提交</button>
            </form>
            <form action="${requestScope.webUri.concat('helloHttpServlet2')}" method="post">
                <input type="hidden" name="hidden1" value="hdd">
                <input type="hidden" name="hidden2" value="hdd2">
                <p>访问地址：${requestScope.webUri.concat('helloHttpServlet2')}</p>
                <button type="submit">post请求提交</button>
            </form>
            <hr/>


            <h2>3、Idea生成的Servlet</h2>
            <form action="${requestScope.webUri.concat('ideaGenerateServlet3')}" method="get">
                <p>访问地址：${requestScope.webUri.concat('ideaGenerateServlet3')}</p>
                <button type="submit">提交</button>
            </form>
            <form action="${requestScope.webUri.concat('ideaGenerateServlet3')}" method="post">
                <p>访问地址：${requestScope.webUri.concat('ideaGenerateServlet3')}</p>
                <button type="submit">提交</button>
            </form>
            <hr/>


            <h2>4、ServletContext的使用</h2>
            <form action="${requestScope.webUri.concat('contextServlet4')}" method="get">
                <p>访问地址：${requestScope.webUri.concat('contextServlet4')}</p>
                <button type="submit">提交</button>
            </form>
            <hr/>


            <h2>5、RequestApi的使用</h2>
            <form action="${requestScope.webUri.concat('requestApiServlet5')}" method="get">
                <p>访问地址：${requestScope.webUri.concat('requestApiServlet5')}</p>
                用户名：<input type="text" name="username" value="用户名">
                爱好：<input type="checkbox" name="hobby" value="java">
                <input type="checkbox" name="hobby" value="c++" checked="checked">
                <input type="checkbox" name="hobby" value="aaa">
                <button type="submit">提交</button>
            </form>
            <hr/>


            <h2>6、请求转发</h2>
            <form action="${requestScope.webUri.concat('requestForwardServlet6')}" method="get">
                <p>访问地址：${requestScope.webUri.concat('requestForwardServlet6')}</p>
                <button type="submit">提交</button>
            </form>
            <hr/>


            <h2>7、ResponseApi的使用</h2>
            <form action="${requestScope.webUri.concat('responseApiServlet7')}" method="get">
                <p>访问地址：${requestScope.webUri.concat('responseApiServlet7')}</p>
                <button type="submit">提交</button>
            </form>
            <hr/>


            <h2>8、重定向</h2>
            <form action="${requestScope.webUri.concat('redirectServlet8')}" method="get">
                <p>访问地址：${requestScope.webUri.concat('redirectServlet8')}</p>
                <button type="submit">提交</button>
            </form>
            <hr/>

        </div>
    </body>
</html>
