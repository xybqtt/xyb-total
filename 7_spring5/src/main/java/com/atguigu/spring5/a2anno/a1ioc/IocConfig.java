package com.atguigu.spring5.a2anno.a1ioc;

import com.atguigu.spring5.a2anno.a1ioc.a3importProp.UserConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(value = {"com.atguigu.spring5.a2anno.a1ioc.a1beanCreate",
        "com.atguigu.spring5.a2anno.a1ioc.a2lifecycle",
        "com.atguigu.spring5.a2anno.a1ioc.a3importProp"
}) // 部件扫描
public class IocConfig {
}
