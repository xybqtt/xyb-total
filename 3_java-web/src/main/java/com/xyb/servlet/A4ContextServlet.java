package com.xyb.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ServletContext的获取和用法
 */
public class A4ContextServlet extends BaseServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        // 功能1：获取web.xml中配置的上下文参数context-param；
        // 1、获取web.xml中配置的上下文参数context-param
        ServletContext sc = getServletContext();

        String username = sc.getInitParameter("username");
        String password = sc.getInitParameter("password");

        writeln("context-param参数username = " + username + "，password = " + password);

        // 功能2、获取当前的工程路径，格式：/工程路径；
        String contextPath = sc.getContextPath();
        writeln("工程路径 = " + contextPath);

        // 功能3、获取工程部署后在服务器的绝对路径；参数"/"代表工程路径，可以直接定位到具体工程下的某个
        // 目录，比如/css目录
        String realPath = sc.getRealPath("/");
        writeln("工程部署的路径是 = " + realPath);


        // 功能4：像Map一样存储数据。
        sc.setAttribute("key", "value");
        String value = (String) sc.getAttribute("key");
        writeln("通过ServletContext获取key对应的value = " + value);

    }
}
