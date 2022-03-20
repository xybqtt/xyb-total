package com.atguigu.mvc.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;


/**
 * 拦截器的测试
 */
@Controller
public class A9InterceptorController {

    @RequestMapping(value = "/a9interceptor/testInterceptor")
    @ResponseBody
    public String testInterceptor() {
        System.out.println("A9InterceptorController.testInterceptor()");
        return "success";
    }

}









