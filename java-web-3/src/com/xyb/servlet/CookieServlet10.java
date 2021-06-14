package com.xyb.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Cookie相关操作
 */
public class CookieServlet10 extends HttpServlet {

    private Map<String, Cookie> cookieMap = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 防止输出乱码
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html; charset=UTF-8");

        for (Cookie cookie : request.getCookies())
            this.cookieMap.put(cookie.getName(), cookie);



        switch (request.getParameter("action")){
            case "createCookie":
                createCookie(request, response);
                break;
            case "getCookie":
                getCookie(request, response);
                break;
            case "updCookie":
                updCookie(request, response);
                break;
            case "cookieLife":
                cookieLife(request, response);
                break;
            case "path":
                path(request, response);
                break;

        }

    }

    private void path(HttpServletRequest request, HttpServletResponse response) {

        Cookie cookie = new Cookie("path1", "path1");
        Cookie cookie2 = new Cookie("path2", "path2");

        // cookie只有在请求地址为http://ip:port/工程名/abc及以下时，服务器才能接收
        cookie.setPath(request.getContextPath() + "/abc");

        // cookie2只有在请求地址为http://ip:port/工程名及以下时，服务器才能接收
        cookie2.setPath(request.getContextPath() + "");

        response.addCookie(cookie);
        response.addCookie(cookie2);



    }

    private void cookieLife(HttpServletRequest request, HttpServletResponse response) {
        /**
         * 设置Cookie最大存活时间：setMaxAge
         *      负数：表示浏览器一关，Cookie就会被删除；默认情况就是这种；
         *      正数：表示在指定的秒数后过期；浏览器关闭也不会过期；
         *      0：表示马上删除Cookie。
         */
        Cookie cookie;
        switch (request.getParameter("type")){
            case "1":
                cookie = new Cookie("defaultLife", "defaultLife");
                cookie.setMaxAge(-1);
                break;
            case "2":
                cookie = new Cookie("delCookieLife", "delCookieLife");
                cookie.setMaxAge(0);
                break;
            case "3":
                cookie = new Cookie("20sCookieLife", "20sCookieLife");
                cookie.setMaxAge(20);
                break;
            default:
                cookie = new Cookie("defaultLife", "defaultLife");
                cookie.setMaxAge(-1);
                break;
        }
        response.addCookie(cookie);
    }

    private void updCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.getWriter().write("原来的Cookies");
        response.getWriter().write("Cookie：[" + this.cookieMap.get("key1").getName() + "-" + this.cookieMap.get("key1").getValue() + "] <br/>");
        response.getWriter().write("Cookie：[" + this.cookieMap.get("key2").getName() + "-" + this.cookieMap.get("key2").getValue() + "] <br/>");

        // 修改方法1，创建一个name相同的，并添加到response中，则会覆盖掉前1个
        Cookie newCookie = new Cookie(this.cookieMap.get("key1").getName(), this.cookieMap.get("key1").getValue() + "1");
        this.cookieMap.put(this.cookieMap.get("key1").getName(), newCookie);
        response.addCookie(newCookie);

        // 修改方法2，填的修改Cookie的值
        this.cookieMap.get("key2").setValue(this.cookieMap.get("key2").getValue() + "2");
        response.addCookie(this.cookieMap.get("key2"));


        response.getWriter().write("修改后的Cookies");
        response.getWriter().write("Cookie：[" + this.cookieMap.get("key1").getName() + "-" + this.cookieMap.get("key1").getValue() + "] <br/>");
        response.getWriter().write("Cookie：[" + this.cookieMap.get("key2").getName() + "-" + this.cookieMap.get("key2").getValue() + "] <br/>");


        // 修改方法2



    }

    private void getCookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1、获取客户端传送的Cookies
        Cookie[] cookies = request.getCookies();

        // 2、查看下
        for (Cookie cookie : cookies)
            response.getWriter().write("Cookie：[" + cookie.getName() + "-" + cookie.getValue() + "] <br/>");



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void createCookie(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // 1、 创建Cookie对象
        Cookie cookie = new Cookie("key1", "value1");
        Cookie cookie2 = new Cookie("key2", "value2");
        Cookie cookie3 = new Cookie("key3", "value3");


        // 2、通知客户端保存Cookie
        resp.addCookie(cookie);
        resp.addCookie(cookie2);
        resp.addCookie(cookie3);

        resp.getWriter().write("Cookie创建成功");

    }
}
