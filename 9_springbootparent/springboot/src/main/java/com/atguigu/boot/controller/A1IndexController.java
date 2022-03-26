package com.atguigu.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController // 相当于@Controller + @ResponseBody
@Controller
public class A1IndexController {

    @RequestMapping(value = "/")
    public String showIndex() {
        return "index";
    }

    /**
     * 注解演示
     * @return
     */
    @RequestMapping(value = "/a2AnnoShow")
    public String annoShow() {
        return "a2AnnoShow";
    }

}
