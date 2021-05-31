package com.xyb.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * HttpServletRequest的使用
 */
public class RequestApiServlet5 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、先设置编码，免得乱码，只对请求体有用，不能对请求行和请求头生效
        req.setCharacterEncoding("UTF-8");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        System.out.println("获取默认的浏览器的字符集：" + request.getCharacterEncoding());
        // 1、先设置编码，免得乱码，只对请求体有用，不能对请求行和请求头生效
        request.setCharacterEncoding("UTF-8");

        // 2、获取常用的信息
        System.out.println("获取请求资源路径uri => " + request.getRequestURI());
        System.out.println("获取请求的统一资源定位符(绝对路径)url => " + request.getRequestURL());
        System.out.println("获取ip => " + request.getRemoteHost());
        System.out.println("获取请求头的User-Agent信息 => " + request.getHeader("User-Agent"));
        System.out.println("获取请求方式 => " + request.getMethod());

        // 2、获取get请求的请求参数
        System.out.println("获取name=username的标签的value => " + request.getParameter("username"));
        System.out.println("获取hobby复选框所有选中项 => " + Arrays.asList(request.getParameterValues("hobby")));

        // 3、可以自定义parameter
        request.setAttribute("key1", "value1");
        System.out.println("获取在Servlet中自定义的属性key1 = " + request.getParameter("key1"));
    }
}
