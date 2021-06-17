<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.io.IOException" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <style type="text/css">
            div {
                border: 1px solid black;
                margin-bottom: 5px;
            }
        </style>
    </head>
    <body>
        <%--
            jsp中的常用标签：
            一、静态包含标签：
                <%@ include file=""%>
                file：表示需要包含的jsp文件的路径；/代表：http://ip:port/工程名/ 映射web目录

                特点：
                    1、静态包含不会翻译被包含的jsp页面；
                    2、静态包含其实是把被包含的jsp页面的代码copy到包含的位置进行输出。

            二、动态包含标签：
                <jsp:include page=""></jsp:include>是动态包含标签
                page属性：指定要包含的jsp页面的路径；
                动态包含也可以像静态包含一样，把被包含的内容执行输出到包含位置。

                特点：
                    1、动态包含会把包含的jsp页面翻译为java代码；
                    2、动态包含底层代码使用如下：
                        org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/jsp/include/footer.jsp", out, false);
                        使用这个方法在调用footer.jsp时，会将此jsp的request、response传送给footer.jsp页面，所以
                    被包含的页面在最终html输出的顺序和代码中导入的时候一致，即2个jsp页面共用out缓冲区；
                    3、动态包含，还可以传递参数。


            三、jsp转发标签：
                <jsp:forward page=""></jsp:forward>功能是请求转发
                page是请求转发的标签


        --%>
        <%
            request.setAttribute("key", "value");
        %>

        <div>
            <h1>静态包含：导入/jsp/include/header.jsp</h1><br/>
            <%@ include file="/jsp/include/header.jsp"%>
        </div>

        <div>
            <h1>动态包含：导入/jsp/include/footer.jsp</h1><br/>
            <jsp:include page="/jsp/include/footer.jsp"></jsp:include>
        </div>
        
        <div>
            <h1>请求转发，把这个注释掉，才能看到前2个</h1>
            <jsp:forward page="/jsp/include/footer.jsp"></jsp:forward>
        </div>


    </body>
</html>
