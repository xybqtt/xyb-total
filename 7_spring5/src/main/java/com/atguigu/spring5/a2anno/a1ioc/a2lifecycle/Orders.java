package com.atguigu.spring5.a2anno.a1ioc.a2lifecycle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component(value = "orders")
@Lazy(value = true)
public class Orders {

    @Value(value = "aaa")
    private String oname;

    //无参数构造
    public Orders() {
        System.out.println("第1步 执行Orders无参数构造创建 bean 实例(@Component修饰的类无法指定init和destroy方法)");
    }

    public void setOname(String oname) {
        this.oname = oname;
        System.out.println("第2步 调用Orders的 set 方法设置属性值");
    }

    //创建执行的初始化的方法
    @PostConstruct
    public void initMethod() {
        System.out.println("第4步 执行Orders初始化的方法");
    }

    //创建执行的销毁的方法
    @PreDestroy
    public void destroyMethod() {
        System.out.println("第7步 执行Orders销毁的方法");
    }

    public void user() {
        System.out.println("第6步 使用Orders" + this.toString());
    }


    @Override
    public String toString() {
        return "Orders{" +
                "oname='" + oname + '\'' +
                '}';
    }
}
