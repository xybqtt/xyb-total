package com.xyb.servlet;

import com.google.gson.Gson;
import com.xyb.entity.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 服务器端Json的使用，需要gson包
 */
public class AjaxServlet13 extends BaseServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        Person person = new Person();
        person.setName("张三");
        person.setAge(14);

        Gson gson = new Gson();
        String jsonStr = gson.toJson(person);

        if ("jqPostAjax".equals(req.getParameter("action"))) {
            System.out.println("成功调用到jqPostAjax AjaxServlet13，返回字符串为：" + jsonStr);
            this.out.write(jsonStr);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        Person person = new Person();
        person.setName("张三");
        person.setAge(14);

        Gson gson = new Gson();
        String jsonStr = gson.toJson(person);

        if ("jsAjax".equals(req.getParameter("action")))
            writeln("成功调用到jsAjaxServlet13，返回字符串为：" + jsonStr);

        if ("jqAjax".equals(req.getParameter("action")))
            writeln("成功调用到jqAjaxServlet13，返回字符串为：" + jsonStr);

        if ("jqGetAjax".equals(req.getParameter("action"))) {
            System.out.println("成功调用到jqGetAjax AjaxServlet13，返回字符串为：" + jsonStr);
            this.out.write(jsonStr);
        }


        if ("jqJsonGetAjax".equals(req.getParameter("action"))) {
            System.out.println("成功调用到jqJsonGetAjax AjaxServlet13，返回字符串为：" + jsonStr);
            this.out.write(jsonStr);
        }

        if ("serializeAjax".equals(req.getParameter("action"))) {
            System.out.println("成功调用到serializeAjax AjaxServlet13，返回字符串为：" + jsonStr);
            this.out.write(jsonStr);
        }


    }


}
