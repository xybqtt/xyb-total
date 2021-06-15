<%@ page import="com.xyb.util.Utils" %><%--
  Created by IntelliJ IDEA.
  User: xuyabiao
  Date: 2021/6/16
  Time: 7:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>过滤器的使用</title>
    </head>
    <body>
        <%
            Utils.addWebUriToReq(request, "webUri");
            System.out.println(request.getAttribute("webUri"));
        %>
        <a href="${requestScope.webUri.concat('')}" target="_self"></a>
    </body>
</html>
