package com.xyb.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectServlet8 extends BaseServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        writeln("这是访问了原地址，准备返回重定向后的地址");
        // 请求重定向方法1
        // 1、设置响应状态码302，表示重定向
        resp.setStatus(302);

        // 2、设置响应头的Location属性，说明新的地址
        writeln("重定向后的地址：" + req.getServletContext().getContextPath());
        resp.setHeader("Location", req.getServletContext().getContextPath() + "/index.jsp");

        // 请求重定向方法2，推荐使用
        resp.sendRedirect(req.getServletContext().getContextPath() + "/index.jsp");

    }
}
