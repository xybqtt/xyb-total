package com.atguitu.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 相当于@Controller + @ResponseBody
public class A1HelloController {

    @RequestMapping(value = "/")
    public String hello() {
        return "hello, String boot 2!";
    }

}
