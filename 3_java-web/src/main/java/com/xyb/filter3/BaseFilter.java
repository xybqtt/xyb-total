package com.xyb.filter3;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class BaseFilter implements Filter {

    private PrintWriter out;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");

        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html; charset=UTF-8");

        this.out = servletResponse.getWriter();
    }

    protected void writeln(String str){
        this.out.write(str + "<br/>");
        System.out.println(str);
    }

    @Override
    public void destroy() {

    }
}
