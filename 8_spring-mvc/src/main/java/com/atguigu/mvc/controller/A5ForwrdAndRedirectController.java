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
 * 转发和重定向
 */
@Controller
@RequestMapping(value = "/forwardAndRedirect")
public class A5ForwrdAndRedirectController {

    /**
     * 使用转发请求
     */
    @RequestMapping(value = "/forward")
    public ModelAndView toForward() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("mes", "这是通过forward进行转发");
        // 转发到/forwardAndRedirect/target请求上，注意此处的viewName是"@RequestMapping(value = "/forward")"中的value参数
        mav.setViewName("forward:/forwardAndRedirect/target");
        return mav;
    }


    /**
     * 使用重定向请求页面
     */
    @RequestMapping(value = "/redirect")
    public ModelAndView toRedirect(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        // 重定向会有一个新的request，所以这个会传给下一个request，但是不会传给下一个response
        mav.addObject("mes", "这是通过redirect进行重定向");
        // 转发到/forwardAndRedirect/target请求上，注意此处的viewName是"@RequestMapping(value = "/forward")"中的value参数
        mav.setViewName("redirect:/a.html");
        return mav;
    }



    /**
     * 使用servletApi向request域对象共享数据
     */
    @RequestMapping(value = "/target")
    public String toTarget() {

        return "target";
    }


}









