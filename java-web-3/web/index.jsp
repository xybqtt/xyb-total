<%@ page import="com.xyb.util.Utils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
    </head>
    <body>
        <%
            Utils.addWebUriToReq(request, "webUri");
            System.out.println(request.getAttribute("webUri"));
            // webUri = http://127.0.0.1:8080/java_web_3/
        %>
        <a href="${requestScope.webUri.concat('top/servletTop.jsp')}" target="_self">servlet学习页面</a>
        <a href="${requestScope.webUri.concat('top/jspTop.jsp')}" target="_self">jsp学习页面</a>
    </body>
</html>
