package com.atguigu.mvc.controller;

import com.atguigu.mvc.entity.User;
import com.sun.deploy.util.ArrayUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 转发和重定向
 */
@Controller
@RequestMapping(value = "/restFul")
public class A6RestFulController {

    private Map<Integer, User> userMap = new HashMap<>();

    private Integer id = 5;

    public A6RestFulController() {
        userMap.put(1, new User(1, "张三1", "1", new String[]{"1", "2"}));
        userMap.put(2, new User(2, "张三2", "2", new String[]{"1", "2"}));
        userMap.put(3, new User(3, "张三3", "3", new String[]{"1", "2"}));
        userMap.put(4, new User(4, "张三4", "4", new String[]{"1", "2"}));
    }

    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    public ModelAndView getAllUser() {
        ModelAndView mav = new ModelAndView();
        List<User> list = new ArrayList<>();

        Set set = userMap.keySet();
        List userList = (List) set.stream().map((userMap::get)).collect(Collectors.toList());
        mav.addObject("users", userList);
        mav.setViewName("A6RestFul");
        return mav;
    }


    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable("id") int id) {
        userMap.remove(id);
        return "forward:/getAllUser";
    }

}









