package com.xyb.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloHttpServlet2 extends HttpServlet {


    /**
     * doGet()请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("这是HelloHttpServlet2的doGet");

        String value1 = req.getParameter("key1");
        if(value1 != null)
            System.out.println("这是RequestForwardServlet6进行请求转发的，其key1 = " + value1);
    }

    /**
     * doPost()请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("这是HelloHttpServlet2的doPost");
    }
}
