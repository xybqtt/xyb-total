<%@ page import="com.xyb.util.Utils" %>
<%@ page import="com.xyb.entity.*" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>json的使用</title>
        <style type="text/css">
            .cls1 {
                border: solid 1px black;
                float: left;
            }
        </style>
        <script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js" type="text/javascript"></script>
        <script type="text/javascript">
            // 1、json的定义
            var jsonObj = {
                "key1": 12,
                "key2": "abc",
                "key3": true,
                "key4": [11, "arr", false],
                "key5": {
                    "key5_1": 551,
                    "key5_2": "key5_2_value"
                },
                "key6": [{
                    "key6_1_1": 6611,
                    "key6_1_2": "key6_1_2_value"
                }, {
                    "key6_2_1": 6621,
                    "key6_2_2": "key6_2_2_value"
                }]
            };

            console.log(typeof(jsonObj));

            // 2、json的访问
            console.log("key1 = " + jsonObj.key1);
            console.log("获取json.key4数组的所有元素")
            for(var i = 0; i < jsonObj.key4.length; i++){
                console.log("    第" + i + "个元素值：" + jsonObj.key4[i]);
            }

            // 3、json的2个常用方法
            // 将json对象转换为json字符串
            var jsonStr = JSON.stringify(jsonObj);
            console.log("json字符串：" + jsonStr);

            // 将json字符串转换为json对象
            jsonObj = JSON.parse(jsonStr);
            console.log("json对象：" + jsonObj);


            $(function () {
                $("#createJsonStr").click(function () {
                    var person = {
                        "name" : "张三",
                        "age" : 27,
                        "hobby" : ["女", "钱", "玩"],
                        "schoolList" : ["sc1", "sc2"]
                    };
                    $("#jsonstr").val(JSON.stringify(person));

                    var persons = [person, person];
                    $("#listJsonStr").val(JSON.stringify(persons));

                    var personMap = {1 : person, 2 : person};
                    $("#mapJsonStr").val(JSON.stringify(personMap));



                });
            });
        </script>
    </head>
    <body>
        <%
            Utils.addWebUriToReq(request, "webUri");
            System.out.println(request.getAttribute("webUri"));
        %>
        <iframe name="target" width="500" height="500" class="cls1"></iframe>
        <div class="cls1">
            <form action="${webUri}jsonServlet12" method="get" target="target">
                <input type="textarea" name="jsonstr" style="width: 300px; height: 300px;" id="jsonstr" value="存放1个json字符串，且不是数组">
                <input type="textarea" name="listJsonStr" style="width: 300px; height: 300px;" id="listJsonStr" value="存放1个json字符串，是个数组">
                <input type="textarea" name="mapJsonStr" style="width: 300px; height: 300px;" id="mapJsonStr" value="存放1个json字符串，1个key对应一个person对象">
                <button type="button" id="createJsonStr">生成json字符串</button>
                <button type="submit">提交</button>
            </form>
        </div>
    </body>
</html>
