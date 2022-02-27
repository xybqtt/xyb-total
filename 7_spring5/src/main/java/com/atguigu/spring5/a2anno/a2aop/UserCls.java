package com.atguigu.spring5.a2anno.a2aop;

import org.springframework.stereotype.Component;

@Component(value = "userCls")
public class UserCls {

    public void method() {
        System.out.println(this.getClass().getName() + ".method()...");
    }

}
