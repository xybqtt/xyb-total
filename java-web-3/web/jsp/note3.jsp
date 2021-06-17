<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.io.IOException" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <!--jsp的3种注释 -->
        <!-- html注释会被翻译到java源代码中，在_jspService()方法中，以out.writer输出到客户端 -->
        <%
            // java注释会被翻译到java源代码中
            // 这是java注释
            /**
             * 多行注释
             */
        %>

        <%-- 这是jsp注释，可以注掉一切，是真正的注释，不会显示在源代码，也不会输出到客户端 --%>
    </body>
</html>
