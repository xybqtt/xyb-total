<%@ page import="com.xyb.util.Utils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>filter学习</title>
        <style type="text/css">
            .cls1 {
                border: 3px solid #ff0000;
                margin: 10px;
                float: left;
                width: 40%;
            }

            .cls2 {
                float: right;
            }
        </style>
    </head>
    <body>
        <iframe name="target" width="40%" height="100%" class="cls1, cls2"></iframe>
        <div class="cls1">
            <%
                Utils.addWebUriToReq(request, "webUri");
                System.out.println(request.getAttribute("webUri"));
                // webUri = http://127.0.0.1:8080/java_web_3/
            %>
            <h1>filter</h1>
            <form action="${webUri}testFilterServlet" target="target">
                <p>访问地址${webUri}testFilterServlet</p>
                <p>下面2个如果为空，会被拦截器拦截</p>
                用户名:<input type="text" value="如果为空则被过滤器阻断" name="username" />
                tel:<input type="text" value="135 7777 8888" name="tel" />
                <input type="submit" value="提交" />
            </form>


        </div>
    </body>
</html>
