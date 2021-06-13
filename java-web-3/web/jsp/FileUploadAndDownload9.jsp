<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>文件上传下载</title>
    </head>
    <body>
        <%--
            文件上传介绍：
                1、要有一个form标签，method=post请求；
                2、form标签的encType属性值必须为"multipart/form-data"值，
                真实的请求行内容如下：
                Content_type:multipart/form-data; boundary=----WebKitFormBoundaryQUT0Ww8BrLyKeWxl
                multipart/form-data表示提交的数据，以多段(每一个表单项一个数据段)的形式进行拼接，然后以二进制流
            的形式发送给服务器；
                boundary：表示多段的内容以什么作为分割符；

                对应请求体如下：
                ----WebKitFormBoundaryQUT0Ww8BrLyKeWxl
                一个表单项的内容
                空行
                ----WebKitFormBoundaryQUT0Ww8BrLyKeWxl
                另一个表单项的内容
                空行
                ----WebKitFormBoundaryQUT0Ww8BrLyKeWxl-- 此行表示结尾

                3、在form标签中使用input type=file 添加上传文件；
                4、编写服务器代码接收，处理上传的数据。
        --%>
        <%
            HttpServletRequest request1 = (HttpServletRequest) pageContext.getRequest();
            String fileUploadUri = "http://" +  request1.getRemoteHost() + ":" + request1.getServerPort() + "/" + request1.getContextPath() + "/fileUpdownload9";
            request1.setAttribute("fileUploadUri", fileUploadUri);
        %>
        <form action="${fileUploadUri}" method="post" enctype="multipart/form-data">
            username：<input type="text" name="username" />
            <input type="file" value="请选择上传的文件" name="photo" />
            <input type="submit" value="上传" />
        </form>

        <hr/>

        <form action="${fileUploadUri}" method="get">
            <input type="submit" value="下载" />
        </form>
    </body>
</html>
