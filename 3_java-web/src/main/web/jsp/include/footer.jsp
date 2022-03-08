<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>使用动态包含标签</title>
    </head>
    <body>
        <div>
            这是被动态导入的footer.jsp页面，获取request.getAttribute("key") = <%=request.getAttribute("key")%>
        </div>
    </body>
</html>
