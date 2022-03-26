package com.atguigu.boot.a2annoshow.a2conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

/**
 * 装载了A1Conditional.class才能装载此类
 */
@Component
@ConditionalOnBean(value = A1Conditional.class)
public class A2CondiOnBean {
}
