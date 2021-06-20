<%@ page import="com.xyb.util.Utils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>JSP的学习</title>
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
            <h1>jsp</h1>
            <h2>1、常用的page指令</h2>
            <p>访问地址${webUri}jsp/pageCommand1.jsp</p>
            <a href="${webUri}jsp/pageCommand1.jsp" target="target">常用page指令</a>
            <hr/>


            <h2>2、jsp脚本</h2>
            <p>访问地址${webUri}jsp/script2.jsp</p>
            <a href="${webUri}jsp/script2.jsp" target="target">jsp脚本</a>
            <hr/>


            <h2>3、jsp中的注释</h2>
            <p>访问地址${webUri}jsp/note3.jsp</p>
            <a href="${webUri}jsp/note3.jsp" target="target">jsp中的注释</a>
            <hr/>


            <h2>4、jsp9个内置对象</h2>
            <p>访问地址${webUri}jsp/nineDefaultObject4.jsp</p>
            <a href="${webUri}jsp/nineDefaultObject4.jsp" target="target">jsp的9个内置对象</a>
            <hr/>


            <h2>5、jsp常用标签</h2>
            <p>访问地址${webUri}jsp/usuallyLabel5.jsp</p>
            <a href="${webUri}jsp/usuallyLabel5.jsp" target="target">jsp常用标签</a>
            <hr/>


            <h2>6、el表达式</h2>
            <p>访问地址${webUri}jsp/elCal6.jsp</p>
            <a href="${webUri}jsp/elCal6.jsp" target="target">el表达式</a>
            <hr/>


            <h2>7、el隐藏对象</h2>
            <p>访问地址${webUri}jsp/elHiddenObject7.jsp</p>
            <a href="${webUri}jsp/elHiddenObject7.jsp" target="target">el隐藏对象</a>
            <hr/>


            <h2>8、jstl的使用</h2>
            <p>访问地址${webUri}jsp/jstl8.jsp</p>
            <a href="${webUri}jsp/jstl8.jsp" target="target">jstl的使用</a>
            <hr/>


            <h2>9、文件上传下载</h2>
            <p>访问地址${webUri}jsp/FileUploadAndDownload9.jsp</p>
            <a href="${webUri}jsp/FileUploadAndDownload9.jsp" target="target">文件上传下载</a>
            <hr/>


            <h2>10、Cookie的使用</h2>
            <p>访问地址${webUri}jsp/cookie10.jsp</p>
            <a href="${webUri}jsp/cookie10.jsp" target="target">Cookie的使用</a>
            <hr/>


            <h2>11、Session的使用</h2>
            <p>访问地址${webUri}jsp/session11.jsp</p>
            <a href="${webUri}jsp/session11.jsp" target="target">Session的使用</a>
            <hr/>

            <h2>12、json的使用</h2>
            <p>访问地址${webUri}jsp/json13.jsp</p>
            <a href="${webUri}jsp/json13.jsp" target="_blank">json的使用</a>
            <hr/>

            <h2>13、ajax的使用</h2>
            <p>访问地址${webUri}jsp/ajax14.jsp</p>
            <a href="${webUri}jsp/ajax14.jsp" target="_blank">json的使用</a>
            <hr/>
        </div>
    </body>
</html>
