package com.atguigu.boot.utils;

import org.springframework.context.ApplicationContext;

public class SpringUtil {

    public static ApplicationContext context;

    public static void setContext(ApplicationContext context) {
        SpringUtil.context = context;
    }

}
