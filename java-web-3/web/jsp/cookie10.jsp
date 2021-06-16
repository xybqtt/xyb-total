<%@ page import="com.xyb.util.Utils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Cookie的相关使用</title>
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
                 添加不了cookie--%>
                <li><a href="${requestScope.webUri.concat('cookieServlet10?action=createCookie')}" target="target">Cookie的创建</a></li>
                <li><a href="${requestScope.webUri.concat('cookieServlet10?action=getCookie')}" target="target">Cookie的获取</a></li>
                <li><a href="${requestScope.webUri.concat('cookieServlet10?action=updCookie')}" target="target">Cookie值的修改</a></li>
                <li>Cookie的存活周期</li>
                <li>
                    <ul>
                        <li><a href="${requestScope.webUri.concat('cookieServlet10?action=cookieLife&type=1')}" target="target">Cookie的默认存活时间(会话)</a></li>
                        <li><a href="${requestScope.webUri.concat('cookieServlet10?action=cookieLife&type=2')}" target="target">Cookie立即删除</a></li>
                        <li><a href="${requestScope.webUri.concat('cookieServlet10?action=cookieLife&type=3')}" target="target">Cookie存活3600s</a></li>
                    </ul>
                </li>
                <li><a href="${requestScope.webUri.concat('cookieServlet10?action=path')}" target="target">Cookie的路径设置</a></li>
                <li><a href="" target="target">Cookie的用户免登陆练习</a></li>
            </ul>
        </div>
    </body>
</html>
