package com.xyb;

import com.xyb.service.HelloService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class Client {

    public static void main(String[] args) {
        // 1、服务接口访问地址：http://localhost:8080/ws/hello

        // 1、创建cxf代理工厂
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

        // 2、设置远程访问服务端地址
        factory.setAddress("http://localhost:8080/ws/hello");

        // 3、设置接口类型
        factory.setServiceClass(HelloService.class);

        // 4、对接口生成代理对象
        HelloService helloService = factory.create(HelloService.class);

        // 5、代理对象对象
        System.out.println(helloService.getClass());

        // 6、远程访问服务端方法
        String content = helloService.sayHello("aaa");
        System.out.println(content);
    }

}
