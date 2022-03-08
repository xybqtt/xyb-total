package com.xyb.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class A7ResponseApiServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("获取默认的响应字符集：" + response.getCharacterEncoding());

        // 可以在方法内，第一步就设置，现在的方案1和方案2不能同时设置，不然不生效。

        // 1、先设置响应的服务器端的字符集，不然可能会乱码
        response.setCharacterEncoding("UTF-8");

        // 解决乱码方案1
        // 2、通过响应头，设置浏览器也使用UTF-8
        response.setHeader("Content-type", "text/html; charset=UTF-8");

        // 解决乱码方案2，它会同时设置服务器和客户端都使用UTF-8，还设置了响应头，
        // 此方法一定要在获取输出流之前使用，推荐使用此方法
        response.setContentType("text/html; charset=UTF-8");

        // 往客户端回传字符串数据
        PrintWriter pw = response.getWriter();
        pw.write("测试中文乱码");

    }
}
