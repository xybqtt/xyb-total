package com.xyb.filter3;

import com.xyb.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestFilterServlet extends BaseServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        writeln("访问到了TestFilterServlet类，username = " + new String(req.getParameter("username").getBytes("ISO8859-1"), "UTF-8"));
    }
}
