package com.atguigu.mvc.controller;

import com.atguigu.mvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 测试Controller获取http请求参数
 */
@Controller
@RequestMapping(value = "/paramGet")
public class A3ParamsController {

    /**
     * 访问多种获取http参数页面
     */
    @RequestMapping(value = "/ParamGetTest")
    public String toGetParam(HttpServletRequest req) {
        return "target";
    }


    /**
     * 通过servletApi获取参数值
     */
    @RequestMapping(value = "/getByServletAPI")
    public String testParamByServletAPI(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String[] hobby = req.getParameterValues("hobby");
        System.out.println("通过原始servletApi获取参数：username=" + username + "，password=" + password + "，hobby=" + Arrays.toString(hobby));
        return "target";
    }

    /**
     * 通过MVC获取参数，在控制器方法的形参位置，设置和请求参数同名的形参，当浏览器发送请求，匹配到请求映射时，在DispatcherServlet中就会将请求参数赋值给相应的形参
     */
    @RequestMapping(value = "/getByMVCAPI")
    public String testParamByServletAPI(String username, String password, String[] hobby) {
        System.out.println("通过MVC的形参名与请求参数中的参数名匹配获取参数值：username=" + username + "，password=" + password + "，hobbys=" + Arrays.toString(hobby));
        return "target";
    }

    /**
     * 通过MVC的参数注解：@RequestParam将请求参数和控制器方法的形参创建映射关系。
     * @RequestParam(value = "username", required = true, defaultValue = "aa")
     *      value：指定为形参赋值的请求参数的参数名；
     *      required：设置是否必须传输此请求参数，默认值为true；
     *          若设置为true时，则当前请求必须传输value所指定的请求参数，若没有传输该请求参数，且没有设置defaultValue属性，则页面报错400：Required String parameter ‘xxx’ is not present；若设置为false，则当前请求不是必须传输value所指定的请求参数，若没有传输，则注解所标识的形参的值为null
     *      defaultValue：不管required属性值为true或false，当value所指定的请求参数没有传输或传输的值为""时，则使用默认值为形参赋值
     *  @RequestHeader、@CookieValue与@RequestParam用法一样，不过一个是获取请求头中的参数值，一个是获取cookie中的参数值。
     */
    @RequestMapping(value = "/getByRequestParam")
    public String testParamByRequestParam(
            @RequestParam(value = "username", required = true, defaultValue = "aa") String username,
            @RequestParam(value = "password", required = true, defaultValue = "aa")String password,
            @RequestParam(value = "hobby", required = true, defaultValue = "aa")String[] hobby
    ) {
        System.out.println("通过MVC的@RequestParam注解获取参数值：username=" + username + "，password=" + password + "，hobbys=" + Arrays.toString(hobby));
        return "target";
    }


    /**
     * 通过MVC获取参数，在控制器方法的形参位置，设置和请求参数同名的形参，当浏览器发送请求，匹配到请求映射时，在DispatcherServlet中就会将请求参数赋值给相应的形参
     */
    @RequestMapping(value = "/getByEntity")
    public String testParamByEntity(User user) {
        System.out.println("通过MVC的将参数名与实体类中的属性名一致，可以直接创建对象并为对象赋值：User=" + user.toString());
        return "target";
    }

}









