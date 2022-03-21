package com.atguigu.mvc.controller;

import com.atguigu.mvc.dao.UserDao;
import com.atguigu.mvc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * 测试RESTful风格
 */
@Controller
@RequestMapping(value = "/restFul")
public class A6RestFulController {

    @Autowired
    private UserDao userDao;

    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView getAllUser() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userDao.getAll());
        mav.setViewName("restful/ShowAllUser");
        return mav;
    }

    /**
     * 通过id删除，注意http只有get、post请求，为了让DispatcherServlet接收到的req为DELETE请求，必须在拦截器(hiddenHttpMethodFilter为已有实现的拦截器)进行如下操作：
     *      1、首先请求必须是Post，将Request赋值给另一个自定义的ARequest；
     *      2、request.getParameter("_method")，获取这个值，如果是DELETE，则将ARequest.method = RequestMethod.DELETE；
     *      3、将ARequest传给DispatcherServlet，会去匹配method = DELETE的方法，其它同理。
     *
     *      注意，超链接是不能发送post请求的，必须使用form表单发送，即单击超链接时，取消其本身的超链接功能，在写个方法，点击超链接时，提交表单。
     *
     * @return
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable(value = "id") Integer id) {
        userDao.delete(id);
        return "redirect:/restFul/users";
    }

    /**
     * 通过id删除，注意http只有get、post请求，为了让DispatcherServlet接收到的req为DELETE请求，必须在拦截器(hiddenHttpMethodFilter为已有实现的拦截器)进行如下操作：
     *      1、首先请求必须是Post，将Request赋值给另一个自定义的ARequest；
     *      2、request.getParameter("_method")，获取这个值，如果是DELETE，则将ARequest.method = RequestMethod.DELETE；
     *      3、将ARequest传给DispatcherServlet，会去匹配method = DELETE的方法，其它同理。
     *
     *      注意，超链接是不能发送post请求的，必须使用form表单发送，即单击超链接时，取消其本身的超链接功能，在写个方法，点击超链接时，提交表单。
     *
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String addUser(User user) {
        userDao.save(user);
        return "redirect:/restFul/users";
    }


    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ModelAndView getUserById(@PathVariable("id") Integer id) {
        User user = userDao.get(id);
        ModelAndView mav = new ModelAndView();
        mav.addObject("user", user);
        mav.setViewName("restful/UpdateUser");
        return mav;
    }

    /**
     * 修改user
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public String getUserById(User user) {
        userDao.delete(user.getId());
        userDao.save(user);
        return "redirect:/restFul/users";
    }
}









