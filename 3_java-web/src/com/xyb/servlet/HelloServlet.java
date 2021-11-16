package com.xyb.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HelloServlet implements Servlet {

    public HelloServlet() {
        System.out.println("1、调用HelloServlet构造器");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("2、调用HelloServlet.init()初始化方法；");
        // 1)、获取Servlet程序的别名servlet-name；
        System.out.println(" HelloServlet的别名：" + servletConfig.getServletName());

        // 2)、获取初始化参数：init-param；
        System.out.println(" 获取ServletConfig的url初始化参数：" + servletConfig.getInitParameter("url"));
        System.out.println(" 获取ServletConfig的param2初始化参数：" + servletConfig.getInitParameter("param2"));

        // 3)、获取ServletContext对象。
        System.out.println(" 获取ServletConfig的ServletContext：" + servletConfig.getServletContext());

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 只要有请求，必定会调用这个方法，所有我们需要关键注意下这个方法
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("3、HelloServlet.service()被访问到了；");
        // 将ServletRequest强转为HttpServletRequest，可以获取一些信息
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if("GET".equals(request.getMethod()))
            doGet(request);
        else if("POST".equals(request.getMethod()))
            doPost(request);

    }

    private void doPost(HttpServletRequest request) {

        System.out.println("这是post请求");

    }

    private void doGet(HttpServletRequest request) {

        System.out.println("这是get请求");

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("4、HelloServlet.destroy()方法被调用。");
    }
}
