package com.atguigu.spring5.a2anno.a1ioc.a2lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 自定义的bean后置处理器
 */
@Component(value = "myBeanPost2")
@Order(2)
public class MyBeanPost2 implements BeanPostProcessor, Ordered {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(this.getClass().getPackage() == bean.getClass().getPackage())
            System.out.println("第3步，bean后置处理器MyBeanPost2，在初始化之前执行，" + bean);
        return bean;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(this.getClass().getPackage() == bean.getClass().getPackage())
            System.out.println("第5步，bean后置处理器MyBeanPost2，在初始化之后执行，" + bean);
        return bean;
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
