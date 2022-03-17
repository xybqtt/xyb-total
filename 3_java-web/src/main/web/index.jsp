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
            // webUri = http://127.0.0.1:8080/
        %>
        <a href="${webUri}top/A1ServletTop.jsp" target="_self">servlet测试页面</a>
        <a href="${webUri}top/A2JspTop.jsp" target="_self">jsp测试页面</a>
        <a href="${webUri}top/A3Filter.jsp" target="_self">过滤器测试页面</a>
        <a href="${webUri}top/A4PatternMatch.jsp" target="_self">路径匹配测试页面</a>
    </body>
</html>
