package com.atguigu.mvc.a2controller;

import com.atguigu.mvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 异常与页面映射
 */
@Controller
public class A8ExceptionController {

    @RequestMapping(value = "/a10exception/testNPEEx")
    @ResponseBody
    public String testNPEEx() {
        User user = null;
        user.getId();
        return "success";
    }

    @RequestMapping(value = "/a10exception/testArithEx")
    @ResponseBody
    public String testArithEx() {
        int i = 5 / 0;
        return "success";
    }

}









