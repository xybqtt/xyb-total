package com.xyb.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectServlet8 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("这是访问了原地址，准备返回重定向后的地址");
        // 请求重定向方法1
        // 1、设置响应状态码302，表示重定向
        response.setStatus(302);

        // 2、设置响应头的Location属性，说明新的地址
        System.out.println("重定向后的地址：" + request.getServletContext().getContextPath());
        response.setHeader("Location", request.getServletContext().getContextPath() + "/a.html");

        // 请求重定向方法2，推荐使用
        response.sendRedirect(request.getServletContext().getContextPath() + "/a.html");

    }
}
