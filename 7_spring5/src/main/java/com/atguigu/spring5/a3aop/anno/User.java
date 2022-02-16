package com.atguigu.spring5.a3aop.anno;

import org.springframework.stereotype.Component;

@Component
public class User {

    public int div(int a, int b) {
        System.out.println("div()");
        return a / b;
    }

}
