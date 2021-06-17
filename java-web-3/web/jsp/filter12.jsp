<%@ page import="com.xyb.util.Utils" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>过滤器的使用</title>
    </head>
    <body>
        <%
            Utils.addWebUriToReq(request, "webUri");
            write((String) request.getAttribute("webUri"));
        %>
        <a href="${requestScope.webUri.concat('')}" target="_self"></a>
    </body>
</html>
