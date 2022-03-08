<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>使用静态包含标签</title>
    </head>
    <body>
        <div>
            这是被静态导入的header.jsp页面，获取request.getAttribute("key") = <%=request.getAttribute("key")%>
        </div>
    </body>
</html>
