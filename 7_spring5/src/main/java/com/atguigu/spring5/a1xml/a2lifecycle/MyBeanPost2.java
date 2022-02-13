package com.atguigu.spring5.a1xml.a2lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

/**
 * 自定义的bean后置处理器
 */
public class MyBeanPost2 implements BeanPostProcessor, Ordered {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("第3步，bean后置处理器MyBeanPost2，在初始化之前执行");
        return bean;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("第5步，bean后置处理器MyBeanPost2，在初始化之后执行");
        return bean;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
