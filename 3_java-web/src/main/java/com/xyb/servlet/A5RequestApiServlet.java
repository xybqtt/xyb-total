package com.xyb.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * HttpServletRequest的使用
 */
public class A5RequestApiServlet extends BaseServlet {

    /**
     * 通过a.html跳转过来
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        writeln("获取默认的浏览器的字符集：" + req.getCharacterEncoding());


        // 2、获取常用的信息
        writeln("获取请求资源路径uri => " + req.getRequestURI());
        writeln("获取请求的统一资源定位符(绝对路径)url => " + req.getRequestURL());
        writeln("获取ip => " + req.getRemoteHost());
        writeln("获取请求头的User-Agent信息 => " + req.getHeader("User-Agent"));
        writeln("获取请求方式 => " + req.getMethod());
        writeln("getContextPath" + req.getContextPath());
        writeln("获取工程的绝对路径：" + req.getRealPath("/"));

        // 2、获取get请求的请求参数
        writeln("获取name=username的标签的value => " + req.getParameter("username"));
        writeln("获取name=username的标签的value => " + new String(req.getParameter("username").getBytes("ISO8859-1"), "UTF-8"));
        writeln("获取hobby复选框所有选中项 => " + Arrays.asList(req.getParameterValues("hobby")));

        // 3、可以自定义parameter
        req.setAttribute("key1", "value1");
        writeln("获取在Servlet中自定义的属性key1 = " + req.getAttribute("key1"));
    }
}
