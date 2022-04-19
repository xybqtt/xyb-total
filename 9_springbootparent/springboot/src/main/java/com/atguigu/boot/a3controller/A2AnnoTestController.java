package com.atguigu.boot.a3controller;

import com.atguigu.boot.a2annoshow.a1configuration.A1FullConfig;
import com.atguigu.boot.a2annoshow.a1configuration.A2LiteConfig;
import com.atguigu.boot.a2annoshow.a1configuration.Class1;
import com.atguigu.boot.a2annoshow.a1configuration.Class2;
import com.atguigu.boot.a2annoshow.a2conditional.A1Conditional;
import com.atguigu.boot.a2annoshow.a2conditional.A2CondiOnBean;
import com.atguigu.boot.a2annoshow.a2conditional.A3CondiOnJava;
import com.atguigu.boot.a2annoshow.a3configProp.A1AutoConfigProp;
import com.atguigu.boot.a2annoshow.a3configProp.A2AutoConfigProp;
import com.atguigu.boot.a2annoshow.a3configProp.A3YmlAutoConfig;
import com.atguigu.boot.utils.SpringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        StringBuilder sb = new StringBuilder("");
        Class2 fullCls20 = (Class2) SpringUtil.context.getBean("fullClass2");
        Class2 fullCls21 = (Class2) SpringUtil.context.getBean("fullClass2");
        Class2 fullCls22 = SpringUtil.context.getBeansOfType(A1FullConfig.class).get("a1FullConfig").createClass2();

        Class1 fullCls10 = (Class1) SpringUtil.context.getBean("fullClass1");
        Class1 fullCls11 = SpringUtil.context.getBeansOfType(A1FullConfig.class).get("a1FullConfig").createClass1();

        sb.append("通过Spring容器直接获取bean，fullCls20 = " + fullCls20 + "。<br/>\n");
        sb.append("通过Spring容器直接获取bean，fullCls21 = " + fullCls21 + "。<br/>\n");
        sb.append("通过Spring容器直接获取bean，fullCls10 = " + fullCls10 + "。<br/>\n");

        sb.append("通过Spring容器@Configuration修饰的组件，(Full模式)调用组件的@Bean修饰的方法获取bean，fullCls22：" + fullCls22 + "。<br/>\n");
        sb.append("通过Spring容器@Configuration修饰的组件，(Full模式)调用组件的@Bean修饰的方法获取bean，fullCls11：" + fullCls11 + "。<br/>\n");

        sb.append("fullCls20(Ioc) == fullCls21(Ioc)(直接通过Spring容器获取的是单例)：" + (fullCls20 == fullCls21) + "。<br/>\n");
        sb.append("fullCls20(Ioc) == fullCls22(Ioc.get@Configuration(Full模式).getCls())：" + (fullCls20 == fullCls22) + "。<br/>\n");
        sb.append("fullCls20(Ioc) == fullCls10.getClass2()(Ioc.getCls())：" + (fullCls20 == fullCls10.getClass2()) + "。<br/>\n");
        sb.append("fullCls20(Ioc) == fullCls11.getClass2()()(Ioc.get@Configuration(Full模式).getCls())：" + (fullCls20 == fullCls11.getClass2()) + "。<br/>\n");

        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * 查看@Configuration(proxyBeanMethods = false)模式下，每次
     * 获取的本配置类中的对象是不是同一个。
     * @return
     */
    @RequestMapping(value = "/configLiteTest")
    public String configLiteTest() {

        StringBuilder sb = new StringBuilder("");
        Class2 liteCls20 = (Class2) SpringUtil.context.getBean("liteClass2");
        Class2 liteCls21 = (Class2) SpringUtil.context.getBean("liteClass2");
        Class2 liteCls22 = SpringUtil.context.getBeansOfType(A2LiteConfig.class).get("a2LiteConfig").createClass2();

        Class1 liteCls10 = (Class1) SpringUtil.context.getBean("liteClass1");
        Class1 liteCls11 = SpringUtil.context.getBeansOfType(A2LiteConfig.class).get("a2LiteConfig").createClass1();

        sb.append("通过Spring容器直接获取bean，liteCls20 = " + liteCls20 + "。<br/>\n");
        sb.append("通过Spring容器直接获取bean，liteCls21 = " + liteCls21 + "。<br/>\n");
        sb.append("通过Spring容器直接获取bean，liteCls10 = " + liteCls10 + "。<br/>\n");

        sb.append("通过Spring容器@Configuration修饰的组件，(Lite模式)调用组件的@Bean修饰的方法获取bean，liteCls22：" + liteCls22 + "。<br/>\n");
        sb.append("通过Spring容器@Configuration修饰的组件，(Lite模式)调用组件的@Bean修饰的方法获取bean，liteCls11：" + liteCls11 + "。<br/>\n");

        sb.append("liteCls20(Ioc) == liteCls21(Ioc)(直接通过Spring容器获取的是单例)：" + (liteCls20 == liteCls21) + "。<br/>\n");
        sb.append("liteCls20(Ioc) == liteCls22(Ioc.get@Configuration(Lite模式).getCls())：" + (liteCls20 == liteCls22) + "。<br/>\n");
        sb.append("liteCls20(Ioc) == liteCls10.getClass2()(Ioc.getCls())：" + (liteCls20 == liteCls10.getClass2()) + "。<br/>\n");
        sb.append("liteCls20(Ioc) == liteCls11.getClass2()()(Ioc.get@Configuration(Lite模式).getCls())：" + (liteCls20 == liteCls11.getClass2()) + "。<br/>\n");

        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * 测试装载类的情况
     * @return
     */
    @RequestMapping(value = "/conditional")
    public String conditional() {

        StringBuilder sb = new StringBuilder("");

        try {
            A1Conditional a1 = (A1Conditional) SpringUtil.context.getBean("a1Conditional");
            A2CondiOnBean a2 = (A2CondiOnBean) SpringUtil.context.getBean("a2CondiOnBean");
            A3CondiOnJava a3 = (A3CondiOnJava) SpringUtil.context.getBean("a3CondiOnJava");

            sb.append("在windows才能装载此类，a1：" + a1 + "。<br/>\n");
            sb.append("有a1才装载此类，a2：" + a2 + "。<br/>\n");
            sb.append("java1.8以上才装载此类：a3：" + a3 + "。<br/>\n");
            System.out.println(sb.toString());
        } catch (Exception e) {
            return "无法从IOC容器获取bean，因为bean不符合装载条件。";
        }
        return sb.toString();
    }

    /**
     * 测试自动注入属性
     * @return
     */
    @RequestMapping(value = "/autoConfigProp")
    public String autoConfigProp() {

        A1AutoConfigProp a1 = (A1AutoConfigProp) SpringUtil.context.getBean("a1AutoConfigProp");
        A2AutoConfigProp a2 = (A2AutoConfigProp) SpringUtil.context.getBean("a2.auto.config.prop2-com.atguigu.boot.a2annoshow.a3configProp.A2AutoConfigProp");

        StringBuilder sb = new StringBuilder("");
        sb.append("通过@ConfigurationProperties、@Component通过前缀和属性名自动从application.properties读取配置：" + a1.toString() + "。<br/>\n");
        sb.append("通过@EnableConfigurationProperties引入@ConfigurationProperties注角的类，通过前缀和属性名自动从application.properties读取配置：" + a2.toString() + "。<br/>\n");

        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * 通过yml配置文件给属性赋值
     * @return
     */
    @RequestMapping(value = "/autoConfigYml")
    public String autoConfigYml() {

        A3YmlAutoConfig a3 = (A3YmlAutoConfig) SpringUtil.context.getBean("a3YmlAutoConfig");
        String str = "通过yml注入属性：" + a3.toString();
        System.out.println(str);

        return str;
    }
}
