<%@ page import="java.util.Map" %>
<%--<%@ page extends="org.apache.jasper.runtime.HttpJspBase" %>--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page pageEncoding="utf-8" %>
<%@ page language="java" %>
<%@ page errorPage="/jsp/errorPage.jsp" %>
<%@ page isErrorPage="true" %>
<%@ page session="true" %>
<%@ page autoFlush="true" %>
<%@ page buffer="8kb" %>

<%--
    page指令：可以修改jsp页面中一些重要属性，或者行为。
    contentType属性：表示jsp返回的数据类型是什么，也就是源码中response.setContentType()；
    language属性：暂时只支持java；
    pageEncoding属性：表示当前jsp页面文件本身的字符集，一般与contentType中的字符
    集保持一致；
    import属性：跟java中的作用一样，用于导包、导类；每导入一个需要专门写一行；
    errorPage属性：表示错误后自动跳转去的路径，一般以/开头，代表http://ip:port/工程路径.
    映射到web目录
    isErrorPage属性：设置当前页面是否是错误信息页面，默认是false，为true则可以
    获取异常信息；
    session属性：设置访问当前jsp页面，是否会创建HttpSession对象。默认是true；
    extends属性：设置翻译出来的java类默认继承此类，每继承一个需要专门写一行；被继承的类
应该是org.apache.jasper.runtime.HttpJspBase的子类，否则会异常；

    以下2个属性是针对response.getWriter()设置的
    autoFlush属性：设置当out输出流缓冲区满了之后，是否自动刷新缓冲区；
    buffer属性：设置response.getWriter()的缓冲区大小，默认是8kb。
--%>

<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        这是jsp页面，查看page指令。
        <%=12%>
    </body>
</html>
