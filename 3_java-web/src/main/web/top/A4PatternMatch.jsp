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
            <h1>精确匹配</h1>
            <div>



            </div>
            <hr />

            <h1>目录匹配</h1>

            <hr />

            <h1>后缀名匹配</h1>



        </div>
    </body>
</html>
