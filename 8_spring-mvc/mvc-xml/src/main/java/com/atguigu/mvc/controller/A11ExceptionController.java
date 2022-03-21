package com.atguigu.mvc.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class A11ExceptionController {

    @ExceptionHandler(value = {ArithmeticException.class, NullPointerException.class})
    public ModelAndView dealException(Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("ex", "aaa" + ex);
        mav.setViewName("error");
        return mav;
    }

}
