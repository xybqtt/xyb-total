package com.atguigu.spring5.a2lifecycle;

public class Orders {

    private String oname;

    //无参数构造
    public Orders() {
        System.out.println("第一步 执行Orders无参数构造创建 bean 实例");
    }

    public void setOname(String oname) {
        this.oname = oname;
        System.out.println("第二步 调用Orders的 set 方法设置属性值");
    }

    //创建执行的初始化的方法
    public void initMethod() {
        System.out.println("第三步 执行Orders初始化的方法");
    }

    //创建执行的销毁的方法
    public void destroyMethod() {
        System.out.println("第五步 执行Orders销毁的方法");
    }

}
