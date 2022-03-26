package com.atguigu.boot.a2annoshow.a3configProp;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = A2AutoConfigProp.class)
public class ConfigPropConfig {
}
