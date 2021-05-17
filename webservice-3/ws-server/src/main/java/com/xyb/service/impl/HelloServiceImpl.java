package com.xyb.service.impl;

import com.xyb.service.HelloService;

public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return name + ", welcome";
    }

}
