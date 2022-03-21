package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 域对象共享数据，最终所有的handler返回的都被封装为ModelAndView，
 * 所以可以设置mav.setViewName();相当于return viewName;。
 */
@Controller
@RequestMapping(value = "/scopeData")
public class A4ScopeDataController {

    /**
     * 使用servletApi向request域对象共享数据，转发到另一个页面，req已经设置了k-v。
     */
    @RequestMapping(value = "/servletApi")
    public String toGetParam(HttpServletRequest req, String k, String v) {
        req.setAttribute(k, v);
        return "target";
    }

    /**
     * 使用ModelAndView向request域对象共享数据
     */
    @RequestMapping(value = "/mav")
    public ModelAndView toGetParam(String k, String v) {
        ModelAndView mav = new ModelAndView();
        mav.addObject(k, v);
        mav.setViewName("target");
        return mav;
    }

    /**
     * 使用Model向request域对象共享数据
     */
    @RequestMapping(value = "/model")
    public String toGetParam(Model model, String k, String v) {
        model.addAttribute(k, v);
        return "target";
    }

    /**
     * 使用Map向request域对象共享数据
     */
    @RequestMapping(value = "/map")
    public String toGetParam(Map map, String k, String v) {
        map.put(k, v);
        return "target";
    }

    /**
     * 使用ModelMap向request域对象共享数据
     */
    @RequestMapping(value = "/modelMap")
    public String toGetParam(ModelMap modelMap, String k, String v) {
        modelMap.put(k, v);
        return "target";
    }

    /**
     * 使用Session向request域对象共享数据
     */
    @RequestMapping(value = "/session")
    public String toSession(HttpSession session, String k, String v) {
        session.setAttribute(k, v);
        return "target";
    }

    /**
     * 使用ServletContext向request域对象共享数据
     */
    @RequestMapping(value = "/application")
    public String toservletConfig(HttpSession session, String k, String v) {
        ServletContext sc = session.getServletContext();
        sc.setAttribute(k, v);
        return "target";
    }

}









