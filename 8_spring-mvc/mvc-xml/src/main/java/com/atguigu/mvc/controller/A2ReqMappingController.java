package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @RequestMapping注解测试
 */
@Controller
@RequestMapping(value = "/reqMapping")
public class A2ReqMappingController {

    /**
     * 测试value属性
     * @return
     */
    @RequestMapping(value = {"/a?a", "/a*b", "/a/**"})
    public String valueTest() {
        System.out.println("访问到了/valueTest");
        return "target";
    }

    /**
     * 测试method属性，注意，这块的参数是DispatcherServlet通过反射传进来的。
     * @return
     */
    @RequestMapping(value = "/methodTest", method = {RequestMethod.GET, RequestMethod.POST})
    public String methodTest(HttpServletRequest request) {
        System.out.println("访问到了/methodTest");
        System.out.println(request.getMethod());
        return "target";
    }

    /**
     * 测试params属性
     * @return
     */
    @RequestMapping(value = "/paramsTest", params = {"param1", "!param2", "param3=c", "param4!=d"})
    public String paramsTest(HttpServletRequest request) {
        System.out.println("访问到了/paramTest，查看参数：" + request.getParameter("param1").toString() + "，" +
                request.getParameter("param2") + "，" +
                request.getParameter("param3") + "，" +
                request.getParameter("param4"));
        return "target";
    }

    /**
     * 测试headers属性，这个和params一样
     * @return
     */
    @RequestMapping(value = "/headersTest", headers = {"header", "!header", "header=localhost:8080", "!header!=localhost:8080"})
    public String headersTest() {
        System.out.println("访问到了/headersTest");
        return "target";
    }

    /**
     * 测试headers属性，这个和params一样
     * @return
     */
    @RequestMapping(value = "/voidRet")
    public void voidRet(HttpServletResponse response) throws IOException {
        System.out.println("访问到了/voidRet");
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("这是返回参数为void，或返回值为null时的情况，就不会跳转页面了。");
    }
}
