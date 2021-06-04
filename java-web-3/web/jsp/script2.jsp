<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <%!
            /*
            * 声明脚本
            * 这个声明标签的作用是给jsp翻译出来的java类定义属性、方法、静态块、内部类等。
            * 1、声明类属性；
            * 2、声明static静态代码块；
            * 3、声明类方法；
            * 4、声明内部类。
            * */
            private String a;
            private static int b;
            public static Map<String, Object> map;

            static {
                map = new HashMap<String, Object>();
                map.put("key1", "value1");
                map.put("key2", "value2");
            }

            public void _fn1(){
                System.out.println("这是方法fn1");
            }

            public void init(){
                System.out.println("这是init");
            }

            public void _jspInit(){
                System.out.println("jsp的_jspInit");
            }


            public class Acls {
                public Acls() {
                    System.out.println("调用jsp的内部类构造方法");
                }
            }

        %>

        <%--
            表达式脚本，在jsp页面上输出数据：输出整型、输出浮点型、输出字符串、输出对象；
            特点：
                所有表达式脚本都会被翻译到_jspService()方法中；
                表达式脚本都会被翻译成为out.print()输出到页面上；
                由于表达式脚本翻译的内容都在_jspService()方法中，所以_jspService()
            方法中都可以直接使用。比如HttpServletRequest request, HttpServletResponse response
                表达式脚本中的表达式不能以分号结束，因为是用out.print()来进行输出的，如果加了
            分号，就像sout(12;);这样的结束，会报错。
        --%>

        <%=12 %><br/>
        <%=12.2 %><br/>
        <%="字符串" %><br/>
        <%=map%><br/>
        <%=request.getParameter("username")%>


        <%--
            代码脚本：
                翻译之后的内容都在_jspService()方法中；
                代码脚本由于翻译到_jspService()中，所以在_jspService()方法中的现有
            对象都可以直接使用；
                还可以由多个代码组合成一个完整的java语句；
                代码脚本还可以和表达脚本一起使用，在jsp页面输出数据；

            代码脚本、声明脚本、表达式脚本可以嵌套使用。

        --%>
        <%
            // 代码脚本可以直接写代码
            int i = 13;
            if(i == 12){
                System.out.println("12");
            } else {
                System.out.println("13");
            }

            for(i = 0; i < 13; i++){
                System.out.println(i);
            }

            String username = request.getParameter("username");

        %>
    </body>
</html>
