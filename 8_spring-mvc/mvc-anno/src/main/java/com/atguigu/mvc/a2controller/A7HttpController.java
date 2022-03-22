package com.atguigu.mvc.a2controller;

import com.atguigu.mvc.entity.User;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * 查看@RequestBody、RequestEntity、@ResponseBody、ResponseEntity的使用
 */
@Controller
public class A7HttpController {


    /**
     * 测试使用"@RequestBody"注解获取请求体
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/httpController/reqBodyAnno")
    public ModelAndView testRequestBodyAnno(@RequestBody String requestBody) {
        System.out.println("requestBody：" + requestBody);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("success");
        mav.addObject("mes", "通过@RequestBody获取Request的请求体：" + requestBody);
        return mav;
    }

    /**
     * 测试使用"RequestEntity"注解获取请求信息
     * @param requestEntity
     * @return
     */
    @RequestMapping(value = "/httpController/reqEntity")
    public ModelAndView testRequestBodyAnno(RequestEntity<String> requestEntity) {
        System.out.println("requestEntity.headers：" + requestEntity.getHeaders());
        System.out.println("requestEntity.url：" + requestEntity.getUrl());
        System.out.println("requestEntity.body：" + requestEntity.getBody());
        System.out.println("requestEntity.method：" + requestEntity.getMethod());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("success");
        mav.addObject("mes", "通过requestEntity通过请求信息：" + requestEntity.toString());
        return mav;
    }


    /**
     * 测试使用"@ResponseBody"将java对象转换为json对象或json数组对象，返回前端
     * @return
     */
    @RequestMapping(value = "/httpController/respBodyAnno")
    @ResponseBody
    public User testRequestBodyAnno() {
        System.out.println("直接返回java对象");
        return new User(1001, "张三", "123", null);
    }

    /**
     * 测试使用"@ResponseBody"直接返回字符串，而不是页面显示在前端
     * @return
     */
    @RequestMapping(value = "/httpController/respStr")
    @ResponseBody
    public String testRequestBodyStr() {
        System.out.println("直接返回java对象");
        return "返回字符串";
    }

    /**
     * 测试ajax
     * @return
     */
    @RequestMapping(value = "/httpController/ajax")
    @ResponseBody
    public String testAjax(String username) {
        return "hello，ajax，" + username;
    }

}









