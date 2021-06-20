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
public class CookieServlet10 extends BaseServlet {

    private Map<String, Cookie> cookieMap = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        for (Cookie cookie : req.getCookies())
            this.cookieMap.put(cookie.getName(), cookie);



        switch (req.getParameter("action")){
            case "createCookie":
                createCookie(req, resp);
                break;
            case "getCookie":
                getCookie(req, resp);
                break;
            case "updCookie":
                updCookie(req, resp);
                break;
            case "cookieLife":
                cookieLife(req, resp);
                break;
            case "path":
                path(req, resp);
                break;

        }

    }

    private void path(HttpServletRequest req, HttpServletResponse resp) {

        Cookie cookie = new Cookie("path1", "path1");
        Cookie cookie2 = new Cookie("path2", "path2");

        // cookie只有在请求地址为http://ip:port/工程名/abc及以下时，服务器才能接收
        cookie.setPath(req.getContextPath() + "/abc");

        // cookie2只有在请求地址为http://ip:port/工程名及以下时，服务器才能接收
        cookie2.setPath(req.getContextPath() + "");

        resp.addCookie(cookie);
        resp.addCookie(cookie2);



    }

    private void cookieLife(HttpServletRequest req, HttpServletResponse resp) {
        /**
         * 设置Cookie最大存活时间：setMaxAge
         *      负数：表示浏览器一关，Cookie就会被删除；默认情况就是这种；
         *      正数：表示在指定的秒数后过期；浏览器关闭也不会过期；
         *      0：表示马上删除Cookie。
         */
        Cookie cookie;
        switch (req.getParameter("type")){
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
        resp.addCookie(cookie);
    }

    private void updCookie(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        writeln("原来的Cookies");
        writeln("Cookie：[" + this.cookieMap.get("key1").getName() + "-" + this.cookieMap.get("key1").getValue() + "] ");
        writeln("Cookie：[" + this.cookieMap.get("key2").getName() + "-" + this.cookieMap.get("key2").getValue() + "] ");

        // 修改方法1，创建一个name相同的，并添加到response中，则会覆盖掉前1个
        Cookie newCookie = new Cookie(this.cookieMap.get("key1").getName(), this.cookieMap.get("key1").getValue() + "1");
        this.cookieMap.put(this.cookieMap.get("key1").getName(), newCookie);
        resp.addCookie(newCookie);

        // 修改方法2，填的修改Cookie的值
        this.cookieMap.get("key2").setValue(this.cookieMap.get("key2").getValue() + "2");
        resp.addCookie(this.cookieMap.get("key2"));


        writeln("修改后的Cookies");
        writeln("Cookie：[" + this.cookieMap.get("key1").getName() + "-" + this.cookieMap.get("key1").getValue() + "] ");
        writeln("Cookie：[" + this.cookieMap.get("key2").getName() + "-" + this.cookieMap.get("key2").getValue() + "] ");


        // 修改方法2



    }

    private void getCookie(HttpServletRequest req, HttpServletResponse response) throws IOException {
        // 1、获取客户端传送的Cookies
        Cookie[] cookies = req.getCookies();

        // 2、查看下
        for (Cookie cookie : cookies)
            writeln("Cookie：[" + cookie.getName() + "-" + cookie.getValue() + "] ");



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

        writeln("Cookie创建成功");

    }
}
