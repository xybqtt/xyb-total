<%@ page import="com.xyb.util.Utils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Session的相关使用</title>
        <style type="text/css">
            .cls1 {
                border: solid 1px black;
                float: left;
            }

        </style>
    </head>
    <body>
        <%
            Utils.addWebUriToReq(request, "webUri");
            System.out.println(request.getAttribute("webUri"));
        %>
        <iframe name="target" width="500" height="500" class="cls1"></iframe>
        <div class="cls1" id="div1">
            <ul>
                <%-- 此处访问用http://127.0.0.1:8080/来访问，用因为webUri取得的是127.0.0.1，不用这个访问，
                 添加不了session--%>
                <li><a href="${requestScope.webUri.concat('sessionServlet11?action=createOrGetSession')}" target="target">Session的创建和获取(id号、是否为新创建)</a></li>
                <li><a href="${requestScope.webUri.concat('sessionServlet11?action=saveSessionScope')}" target="target">Session域数据的存储</a></li>
                <li><a href="${requestScope.webUri.concat('sessionServlet11?action=getSessionScope')}" target="target">Session域数据的获取</a></li>
                <li>Session的存活周期</li>
                <li>
                    <ul>
                        <li><a href="${requestScope.webUri.concat('sessionServlet11?action=sessionLife&type=1')}" target="target">Session的默认超时及配置</a></li>
                        <li><a href="${requestScope.webUri.concat('sessionServlet11?action=sessionLife&type=2')}" target="target">Session3秒超时销毁</a></li>
                        <li><a href="${requestScope.webUri.concat('sessionServlet11?action=sessionLife&type=3')}" target="target">Session马上销毁</a></li>
                    </ul>
                </li>
                <li><a href="${requestScope.webUri.concat('sessionServlet11?action=path')}" target="target">浏览器和Session绑定原理</a></li>
            </ul>
        </div>
    </body>
</html>
