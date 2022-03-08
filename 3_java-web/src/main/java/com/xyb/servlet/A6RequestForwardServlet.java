package com.xyb.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class A6RequestForwardServlet extends BaseServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        writeln("访问了requestForwardServlet6的doGet方法");
        req.setCharacterEncoding("UTF-8");

        // 1、设置一个属性，看请求转发的Servlet能获取不
        req.setAttribute("key1", "value1");

        /**
         *  2、设置转发的目的地
         *  / 代表 工程路径：http://ip:port/工程名/ 对应web目录；
         *  可以访问WEB-INF下的文件，注意，记得加文件的后缀
         */

        RequestDispatcher rd = req.getRequestDispatcher("/helloHttpServlet2");

        writeln("请求转发到/helloHttpServlet2");
        // 3、进行转发
        rd.forward(req, resp);
    }
}
