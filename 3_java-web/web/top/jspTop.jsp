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
            <p>访问地址${webUri}jsp/1pageCommand.jsp</p>
            <a href="${webUri}jsp/1pageCommand.jsp" target="target">常用page指令</a>
            <hr/>


            <h2>2、jsp脚本</h2>
            <p>访问地址${webUri}jsp/2script.jsp</p>
            <a href="${webUri}jsp/2script.jsp" target="target">jsp脚本</a>
            <hr/>


            <h2>3、jsp中的注释</h2>
            <p>访问地址${webUri}jsp/3note.jsp</p>
            <a href="${webUri}jsp/3note.jsp" target="target">jsp中的注释</a>
            <hr/>


            <h2>4、jsp9个内置对象</h2>
            <p>访问地址${webUri}jsp/4nineDefaultObject.jsp</p>
            <a href="${webUri}jsp/4nineDefaultObject.jsp" target="target">jsp的9个内置对象</a>
            <hr/>


            <h2>5、jsp常用标签</h2>
            <p>访问地址${webUri}jsp/5usuallyLabel.jsp</p>
            <a href="${webUri}jsp/5usuallyLabel.jsp" target="target">jsp常用标签</a>
            <hr/>


            <h2>6、el表达式</h2>
            <p>访问地址${webUri}jsp/6elCal.jsp</p>
            <a href="${webUri}jsp/6elCal.jsp" target="target">el表达式</a>
            <hr/>


            <h2>7、el隐藏对象</h2>
            <p>访问地址${webUri}jsp/7elHiddenObject.jsp</p>
            <a href="${webUri}jsp/7elHiddenObject.jsp" target="target">el隐藏对象</a>
            <hr/>


            <h2>8、jstl的使用</h2>
            <p>访问地址${webUri}jsp/8jstl.jsp</p>
            <a href="${webUri}jsp/8jstl.jsp" target="target">jstl的使用</a>
            <hr/>


            <h2>9、文件上传下载</h2>
            <p>访问地址${webUri}jsp/9FileUploadAndDownload.jsp</p>
            <a href="${webUri}jsp/9FileUploadAndDownload.jsp" target="target">文件上传下载</a>
            <hr/>


            <h2>10、Cookie的使用</h2>
            <p>访问地址${webUri}jsp/10cookie.jsp</p>
            <a href="${webUri}jsp/10cookie.jsp" target="target">Cookie的使用</a>
            <hr/>


            <h2>11、Session的使用</h2>
            <p>访问地址${webUri}jsp/11session.jsp</p>
            <a href="${webUri}jsp/11session.jsp" target="target">Session的使用</a>
            <hr/>

            <h2>12、json的使用</h2>
            <p>访问地址${webUri}jsp/13json.jsp</p>
            <a href="${webUri}jsp/13json.jsp" target="_blank">json的使用</a>
            <hr/>

            <h2>13、ajax的使用</h2>
            <p>访问地址${webUri}jsp/14ajax.jsp</p>
            <a href="${webUri}jsp/14ajax.jsp" target="_blank">json的使用</a>
            <hr/>
        </div>
    </body>
</html>
