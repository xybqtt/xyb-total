package com.atguigu.boot.a2annoshow.a2conditional;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

/**
 * window系统才装载此类
 */
@Component(value = "a1Conditional")
@Conditional(WindowsConditional.class)
public class A1Conditional {

}
