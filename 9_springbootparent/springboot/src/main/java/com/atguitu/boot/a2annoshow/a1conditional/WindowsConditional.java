package com.atguitu.boot.a2annoshow.a1conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 在windows条件下再装配类
 */
public class WindowsConditional implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        Environment env = conditionContext.getEnvironment();

        String property = env.getProperty("os.name");
        if(property.contains("window")) {
            return true;
        }
        return false;
    }
}
