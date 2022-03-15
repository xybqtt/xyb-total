package com.atguigu.mvc.controller;

import com.atguigu.mvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;

/**
 * 域对象共享数据，最终所有的handler返回的都被封装为ModelAndView，
 * 所以可以设置mav.setViewName();相当于return viewName;。
 */
@Controller
@RequestMapping(value = "/scopeData")
public class A4ScopeDataController {

    /**
     * 使用servletApi向request域对象共享数据
     */
    @RequestMapping(value = "/servletApi")
    public String toGetParam(HttpServletRequest req) {
        req.setAttribute("testScope", "hello, ServletApi");
        return "target";
    }

    /**
     * 使用ModelAndView向request域对象共享数据
     */
    @RequestMapping(value = "/mav")
    public ModelAndView toGetParam() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("testScope", "hello, ModelAndView");
        mav.setViewName("target");
        return mav;
    }

    /**
     * 使用Model向request域对象共享数据
     */
    @RequestMapping(value = "/model")
    public String toGetParam(Model model) {
        model.addAttribute("testScope", "hello, Model");
        return "target";
    }

    /**
     * 使用Map向request域对象共享数据
     */
    @RequestMapping(value = "/map")
    public String toGetParam(Map map) {
        map.put("testScope", "hello, Map");
        return "target";
    }

    /**
     * 使用ModelMap向request域对象共享数据
     */
    @RequestMapping(value = "/modelMap")
    public String toGetParam(ModelMap modelMap) {
        modelMap.put("testScope", "hello, ModelMap");
        return "target";
    }

    /**
     * 使用Session向request域对象共享数据
     */
    @RequestMapping(value = "/session")
    public String toSession(HttpSession session) {
        session.setAttribute("testScope", "hello, session");
        return "target";
    }

    /**
     * 使用ServletContext向request域对象共享数据
     */
    @RequestMapping(value = "/application")
    public String toservletConfig(HttpSession session) {
        ServletContext sc = session.getServletContext();
        sc.setAttribute("testScope", "hello, ServletContext");
        return "target";
    }

}









