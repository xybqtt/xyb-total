package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 首页
 */
@Controller
public class A1IndexController {

    /**
     * 根据配置的ThymeleafViewResolver
     * 视前缀是"/WEB-INF/templates/"/>，
     * 视图后缀是".html"。
     * 所以最终访问的资源是：/WEB-INF/templates/index.html
     * @return
     */
    @RequestMapping(value = "/") // 此处的"/"代表："http://localhost:ip/上下文路径/"
    public String index() {
        // 返回视图名称
        return "A1Index";
    }

    /**
     * 测试RequestMapping注解的用法
     * @return
     */
    @RequestMapping(value = "/A2RequestMapping") // 此处的"/"代表："http://localhost:ip/上下文路径/"
    public String toReqMapping() {
        // 返回视图名称
        return "A2RequestMapping";
    }

    /**
     * 测试 获取 http请求参数
     * @return
     */
    @RequestMapping(value = "/A3ParamGet") // 此处的"/"代表："http://localhost:ip/上下文路径/"
    public String toParamsGet() {
        // 返回视图名称
        return "A3ParamGet";
    }

    /**
     * 测试 域对象共享数据
     * @return
     */
    @RequestMapping(value = "/A4ScopeData") // 此处的"/"代表："http://localhost:ip/上下文路径/"
    public String toScopeData() {
        // 返回视图名称
        return "A4ScopeData";
    }

    /**
     * 测试转发和重定向
     * @return
     */
    @RequestMapping(value = "/A5ForwardAndRedirect") // 此处的"/"代表："http://localhost:ip/上下文路径/"
    public String toFAR() {
        // 返回视图名称
        return "A5ForwardAndRedirect";
    }

    /**
     * RestFul案例测试
     * @return
     */
    @RequestMapping(value = "/A6RestFul") // 此处的"/"代表："http://localhost:ip/上下文路径/"
    public String toRestFul() {
        // 返回视图名称
        return "A6RestFul";
    }
}
