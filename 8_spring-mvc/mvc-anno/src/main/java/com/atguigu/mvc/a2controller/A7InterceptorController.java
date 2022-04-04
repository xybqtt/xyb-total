package com.atguigu.mvc.a2controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 拦截器的测试
 */
@Controller
public class A7InterceptorController {

    @RequestMapping(value = "/a9interceptor/testInterceptor")
    @ResponseBody
    public String testInterceptor() {
        System.out.println("A9InterceptorController.testInterceptor()");
        return "通过后台，查看拦截器的信息";
    }

}









