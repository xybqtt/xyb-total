package com.atguitu.boot.controller;

import com.atguitu.boot.a2annoshow.a1configuration.A1FullConfig;
import com.atguitu.boot.a2annoshow.a1configuration.A2LiteConfig;
import com.atguitu.boot.a2annoshow.a1configuration.Class1;
import com.atguitu.boot.a2annoshow.a1configuration.Class2;
import com.atguitu.boot.utils.SpringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/a2annoTest")
public class A2AnnoTestController {

    /**
     * 查看@Configuration(proxyBeanMethods = true)模式下，每次
     * 获取的本配置类中的对象是不是同一个。
     * @return
     */
    @RequestMapping(value = "/configFullTest")
    public String configFullTest() {
        Class2 fullCls20 = (Class2) SpringUtil.context.getBean("fullClass2");
        Class2 fullCls21 = (Class2) SpringUtil.context.getBean("fullClass2");

        Map<String, A1FullConfig> map = SpringUtil.context.getBeansOfType(A1FullConfig.class);
        Class2 fullCls22 = map.get("a1FullConfig").createClass2();


        System.out.println(fullCls20);
        System.out.println(fullCls21);
        System.out.println("全模式通过同样方式获取的bean是否为同一个：" + (fullCls20 == fullCls21));
        System.out.println("全模式通过同样方式获取的bean是否为同一个：" + (fullCls20 == fullCls21));
        System.out.println("全模式通过同样方式获取的bean是否为同一个：" + (fullCls20 == fullCls22));

        Class1 fullCls10 = (Class1) SpringUtil.context.getBean("fullClass1");
        Class1 fullCls11 = (Class1) SpringUtil.context.getBean("fullClass1");

        System.out.println(fullCls10);
        System.out.println(fullCls11);
        System.out.println("全模式通过同样方式注入到其它bean的bean是否为同一个：" + (fullCls10.getClass2() == fullCls11.getClass2()));

        return "";
    }

    /**
     * 查看@Configuration(proxyBeanMethods = false)模式下，每次
     * 获取的本配置类中的对象是不是同一个。
     * @return
     */
    @RequestMapping(value = "/configLiteTest")
    public String configLiteTest() {

        Class2 liteCls20 = (Class2) SpringUtil.context.getBean("liteClass2");
        Class2 liteCls21 = (Class2) SpringUtil.context.getBean("liteClass2");

        Map<String, A2LiteConfig> map = SpringUtil.context.getBeansOfType(A2LiteConfig.class);
        Class2 liteCls22 = map.get("a2LiteConfig").createClass2();

        System.out.println(liteCls20);
        System.out.println(liteCls21);
        System.out.println(liteCls22);
        System.out.println("Lite模式通过同样方式获取的bean是否为同一个：" + (liteCls20 == liteCls21));
        System.out.println("Lite模式通过同样方式获取的bean是否为同一个：" + (liteCls20 == liteCls22));

        Class1 liteCls10 = (Class1) SpringUtil.context.getBean("liteClass1");
        Class1 liteCls11 = (Class1) SpringUtil.context.getBean("liteClass1");
        System.out.println(liteCls10);
        System.out.println(liteCls11);
        System.out.println("Lite模式通过同样方式注入到其它bean的bean是否为同一个：" + (liteCls10.getClass2() == liteCls11.getClass2()));

        return "";
    }
}
