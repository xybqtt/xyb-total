package com.xyb.filter3;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 实现自定义的Filter方法
 */
public class MyFilter2 extends BaseFilter {



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 此方法专门用于拦截请求，可做权限检查
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        super.doFilter(servletRequest, servletResponse, filterChain);
        writeln("3、进入MyFilter2的doFilter方法");
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        String tel = new String(req.getParameter("tel").getBytes("ISO-8859-1"), "UTF-8");

        if(tel == null || "".equals(tel)) {
            writeln("MyFilter2过滤器拦截：请输入tel");
            return;
        }
        writeln("MyFilter2得到：tel = " + tel);
        //  让程序继续访问他原来该访问的目标资源
        filterChain.doFilter(servletRequest, servletResponse);
        writeln("3、退出MyFilter2的doFilter方法");


    }

    @Override
    public void destroy() {

    }
}
