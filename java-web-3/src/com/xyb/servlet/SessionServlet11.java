package com.xyb.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Session相关操作，
 * 不要用localhost访问，用127.0.0.1访问
 */
public class SessionServlet11 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 防止输出乱码
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html; charset=UTF-8");

        switch (request.getParameter("action")){
            case "createOrGetSession":
                createOrGetSession(request, response);
                break;
            case "saveSessionScope":
                saveSessionScope(request, response);
                break;
            case "getSessionScope":
                getSessionScope(request, response);
                break;
            case "sessionLife":
                sessionLife(request, response);
                break;

        }

    }

    /**
     * 从Session域中获取数据
     * @param request
     * @param response
     */
    private void getSessionScope(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Object value1 = request.getSession().getAttribute("key1");
        response.getWriter().write("从Session域中获取key1对应的为：" + value1);
    }

    /**
     * 向Session域中保存数据
     * @param request
     * @param response
     */
    private void saveSessionScope(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute("key1", "value1");
        response.getWriter().write("已在Session域中保存了数据key1=value1");
    }

    private void sessionLife(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**
         * 设置Session最大存活时间：setMaxInactiveInterval(int a)
         *      正数：设置多少秒后超时。
         *      负数：永不超时。
         */
        HttpSession session = request.getSession();
        switch (request.getParameter("type")){
            case "1":
                response.getWriter().write("默认session存活时间" + session.getMaxInactiveInterval() + "s");
                break;
            case "2":
                session.setMaxInactiveInterval(3);
                response.getWriter().write("设置当前session 3s后超时");
                break;
            case "3":
                // 让当前session马上超时。
                session.invalidate();
                response.getWriter().write("设置当前session=马上超时(无效)");
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    /**
     * Session的创建或获取
     * @param req
     * @param resp
     * @throws IOException
     */
    private void createOrGetSession(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        // 1、创建和获取Session会话对象
        HttpSession session = req.getSession();

        // 2、判断 当前Session会话，是否是新创建出来的
        boolean isNew = session.isNew();

        // 3、获取Session会话的唯一标识
        String id = session.getId();

        resp.getWriter().write("此Session的id是：" + id + "，是否新创建：" + isNew);

    }
}
