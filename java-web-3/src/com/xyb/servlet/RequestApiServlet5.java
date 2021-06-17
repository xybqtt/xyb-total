package com.xyb.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;

/**
 * HttpServletRequest的使用
 */
public class RequestApiServlet5 extends BaseServlet {

    /**
     * 通过a.html跳转过来
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        write("获取默认的浏览器的字符集：" + req.getCharacterEncoding());


        // 2、获取常用的信息
        write("获取请求资源路径uri => " + req.getRequestURI() + "<br/>");
        write("获取请求的统一资源定位符(绝对路径)url => " + req.getRequestURL() + "<br/>");
        write("获取ip => " + req.getRemoteHost() + "<br/>");
        write("获取请求头的User-Agent信息 => " + req.getHeader("User-Agent") + "<br/>");
        write("获取请求方式 => " + req.getMethod() + "<br/>");
        write("getContextPath" + req.getContextPath() + "<br/>");
        write("获取工程的绝对路径：" + req.getRealPath("/") + "<br/>");

        // 2、获取get请求的请求参数
        write("获取name=username的标签的value => " + req.getParameter("username") + "<br/>");
        write("获取name=username的标签的value => " + new String(req.getParameter("username").getBytes("ISO8859-1"), "UTF-8") + "<br/>");
        write("获取hobby复选框所有选中项 => " + Arrays.asList(req.getParameterValues("hobby")) + "<br/>");

        // 3、可以自定义parameter
        req.setAttribute("key1", "value1");
        write("获取在Servlet中自定义的属性key1 = " + req.getAttribute("key1"));
    }
}
