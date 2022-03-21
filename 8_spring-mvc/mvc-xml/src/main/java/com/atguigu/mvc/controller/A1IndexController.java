package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页，除了这2个，其它的在SpringMVC.xml的<mvc:view-controller></mvc:view-controller>进行配置
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
    @RequestMapping(value = "/a2RequestMapping") // 此处的"/"代表："http://localhost:ip/上下文路径/"
    public String toReqMapping() {
        // 返回视图名称
        return "A2RequestMapping";
    }

}
