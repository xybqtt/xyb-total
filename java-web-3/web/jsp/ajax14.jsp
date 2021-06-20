<%@ page import="com.xyb.util.Utils" %>
<%@ page import="com.xyb.entity.*" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>ajax的使用</title>
        <style type="text/css">
            .cls1 {
                border: solid 1px black;
                float: left;
            }
        </style>
        <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js" type="text/javascript"></script>
        <script type="text/javascript">
            // js发起ajax请求，访问服务器
            function ajaxRequest() {

                // 1、创建XMLHttpRequest
                var xmlHttpRequest = new XMLHttpRequest();

                // 2、调用open方法设置请求参数，true表示异步
                xmlHttpRequest.open("GET", "http://127.0.0.1:8080/java_web_3/ajaxServlet13?action=jsAjax", true);

                // 3、在send方法前绑定onreadystatechange事件，处理请求完成后的操作
                xmlHttpRequest.onreadystatechange = function () {
                    if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200){
                        alert(xmlHttpRequest.responseText);
                    }
                }

                // 4、调用send方法发送请求
                xmlHttpRequest.send();

            }

            // jq的ajax请求
            $(function () {
                $("#jqajax").click(function () {
                    /**
                     * 参数说明：
                     *      url：访问地址；
                     *      data：发送给服务器的数据，可以是a=b&c=d这种格式，也可以是{a:b, c:d}的格式(最终会转化为前一种格式)
                     *      type：GET或POST请求；
                     *      success：请求成功，响应的回调函数；
                     *      dataType：响应的数据类型，常用的有
                     *          text：表示纯文本；
                     *          xml：表示xml；
                     *          json：表示json对象。
                     */
                    $.ajax({
                        url : "http://127.0.0.1:8080/java_web_3/ajaxServlet13",
                        data : "action=jqAjax",
                        type : "GET",
                        success : function (data) {
                            alert(data);
                        },
                        dataType : "text"
                    });
                });

                /**
                 * jq的get类型的ajax请求，是对上面的简化4个参数分别为：url、data、回调函数、返回类型
                 */
                $("#jqGetAjax").click(function () {
                    $.get("http://127.0.0.1:8080/java_web_3/ajaxServlet13", "action=jqGetAjax", function (data) {
                        alert(JSON.stringify(data));
                    }, "json");
                });

                /**
                 * jq的post类型的ajax请求，是对上面的简化4个参数分别为：url、data、回调函数、返回类型
                 */
                $("#jqPostAjax").click(function () {
                    $.post("http://127.0.0.1:8080/java_web_3/ajaxServlet13", "action=jqPostAjax", function (data) {
                        alert(JSON.stringify(data));
                    }, "json");
                });


                /**
                 * jq的get类型、返回为json的ajax请求，是对上面的简化4个参数分别为：url、data、回调函数
                 */
                $("#jqJsonGetAjax").click(function () {
                    $.getJSON("http://127.0.0.1:8080/java_web_3/ajaxServlet13", "action=jqJsonGetAjax", function (data) {
                        alert(JSON.stringify(data));
                    });
                });

                /**
                 * 表单序列化，是为了解决，如果我们有很多个表单项，我们需要在ajax的data参数上一个个手动拼接，现在有了这个方法会自动拼接
                 */
                $("#serializeAjax").click(function () {
                    console.log($("#form1").serialize());
                    $.getJSON("http://127.0.0.1:8080/java_web_3/ajaxServlet13", "action=serializeAjax&" + $("#form1").serialize(), function (data) {
                        alert(JSON.stringify(data));
                    });
                });

            })
        </script>
    </head>
    <body>
        <%
            Utils.addWebUriToReq(request, "webUri");
            System.out.println(request.getAttribute("webUri"));
        %>
        <iframe name="target" width="500" height="500" class="cls1"></iframe>
        <div class="cls1">
            <button type="button" id="jsajax" onclick="ajaxRequest()">js的ajax请求</button>
            <button type="button" id="jqajax">jq的ajax请求</button>
            <button type="button" id="jqGetAjax">jq的get型ajax请求</button>
            <button type="button" id="jqPostAjax">jq的post型ajax请求</button>
            <button type="button" id="jqJsonGetAjax">jq的get型返回为json的ajax请求</button>
            <button type="button" id="serializeAjax">serialize表单项测试</button>
            <form id="form1">
                用户名：<input type="text" name="username" value="123">
                年龄：<input type="text" name="age" value="12">
            </form>
        </div>
    </body>
</html>
