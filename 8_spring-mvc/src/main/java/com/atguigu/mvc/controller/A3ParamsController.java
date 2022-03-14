package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 测试Controller获取http请求参数
 */
@Controller
@RequestMapping(value = "/paramGet")
public class A3ParamsController {

    /**
     * 访问多种获取http参数页面
     */
    @RequestMapping(value = "/ParamGetTest")
    public String toGetParam(HttpServletRequest req) {
        return "A2ParamGetTest";
    }


    /**
     * 通过servletApi获取参数值
     */
    @RequestMapping(value = "/getByServletAPI")
    public String testParamByServletAPI(HttpServletRequest req) {
        req.getParameter("username");

        System.out.println("");
        return "A2ParamGetTest";
    }

}









