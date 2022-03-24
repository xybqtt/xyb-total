package com.atguitu.boot.a1config;

import com.atguitu.boot.a2annoshow.a2configProp.Class2;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = Class2.class)
public class BeanConfig {
}
