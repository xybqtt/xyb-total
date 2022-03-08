package com.xyb.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class BaseServlet extends HttpServlet {

    protected PrintWriter out;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1、先设置编码，免得乱码，只对请求体有用，不能对请求行和请求头生效
        req.setCharacterEncoding("UTF-8");

        // 防止输出乱码
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-type", "text/html; charset=UTF-8");
        out = resp.getWriter();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1、先设置编码，免得乱码，只对请求体有用，不能对请求行和请求头生效
//        req.setCharacterEncoding("UTF-8");

        // 防止输出乱码
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-type", "text/html; charset=UTF-8");
        out = resp.getWriter();
    }

    /**
     * 把内容输出到前端页面和控制台
     * @param str
     */
    public void writeln(String str){
        this.out.write(str + "<br/>");
        System.out.println(str);
    }

}
