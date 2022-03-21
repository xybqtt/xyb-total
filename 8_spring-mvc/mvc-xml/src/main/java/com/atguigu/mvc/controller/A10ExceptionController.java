package com.atguigu.mvc.controller;

import com.atguigu.mvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 异常与页面映射
 */
@Controller
public class A10ExceptionController {

    @RequestMapping(value = "/a10exception/testException")
    @ResponseBody
    public String testInterceptor() {
        User user = null;
        user.getId();
        return "success";
    }

}









