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
public class SessionServlet11 extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        switch (req.getParameter("action")){
            case "createOrGetSession":
                createOrGetSession(req, resp);
                break;
            case "saveSessionScope":
                saveSessionScope(req, resp);
                break;
            case "getSessionScope":
                getSessionScope(req, resp);
                break;
            case "sessionLife":
                sessionLife(req, resp);
                break;

        }

    }

    /**
     * 从Session域中获取数据
     * @param req
     * @param resp
     */
    private void getSessionScope(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Object value1 = req.getSession().getAttribute("key1");
        writeln("从Session域中获取key1对应的为：" + value1);
    }

    /**
     * 向Session域中保存数据
     * @param req
     * @param resp
     */
    private void saveSessionScope(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().setAttribute("key1", "value1");
        writeln("已在Session域中保存了数据key1=value1");
    }

    private void sessionLife(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /**
         * 设置Session最大存活时间：setMaxInactiveInterval(int a)
         *      正数：设置多少秒后超时。
         *      负数：永不超时。
         */
        HttpSession session = req.getSession();
        switch (req.getParameter("type")){
            case "1":
                writeln("默认session存活时间" + session.getMaxInactiveInterval() + "s");
                break;
            case "2":
                session.setMaxInactiveInterval(3);
                writeln("设置当前session 3s后超时");
                break;
            case "3":
                // 让当前session马上超时。
                session.invalidate();
                writeln("设置当前session=马上超时(无效)");
                break;
            default:
                break;
        }
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

        writeln("此Session的id是：" + id + "，是否新创建：" + isNew);

    }
}
