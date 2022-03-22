package com.atguigu.mvc.a3advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 映射出现异常后，需要跳转的页面，及相关操作
 */
@ControllerAdvice
public class A1ExceptionController {

    @ExceptionHandler(value = {ArithmeticException.class, NullPointerException.class})
    public ModelAndView dealException(Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("ex", "aaa" + ex);
        mav.setViewName("error");
        return mav;
    }

}
