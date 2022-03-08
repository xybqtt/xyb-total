package com.xyb.filter3;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 实现自定义的Filter方法
 */
public class MyFilter extends BaseFilter {

    public MyFilter() {

        System.out.println("1、调用了MyFilter的默认构造方法");

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("2、调用了MyFilter的init方法");
        // FilterConfig的作用
        // 1、获取Filter标签内，filter-name标签的内容
        System.out.println("filter-name的值是：" + filterConfig.getFilterName());
        // 2、获取在web.xml中配置的init-param初始化参数
        System.out.println("init-param的name为filterKey1 = " + filterConfig.getInitParameter("filterKey1"));
        // 3、获取ServletContext对象
        System.out.println("获取ServletContext对象：" + filterConfig.getServletContext());
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
        writeln("3、进入MyFilter的doFilter方法");
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        String username = new String(req.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");

        if(username == null || "".equals(username)) {
            writeln("MyFilter过滤器拦截：请输入username");
            return;
        }
        writeln("MyFilter得到：username = " + username);
        //  让程序继续访问他原来该访问的目标资源
        filterChain.doFilter(servletRequest, servletResponse);
        writeln("3、退出MyFilter的doFilter方法");
    }

    @Override
    public void destroy() {
        System.out.println("4、调用了MyFilter的destroy方法");

    }
}
