package com.atguigu.spring5.a2anno;

import com.atguigu.spring5.a2anno.a1ioc.IocConfig;
import com.atguigu.spring5.a2anno.a2aop.AopConfig;
import com.atguigu.spring5.a2anno.a3jdbcTemplate.JdbcTemplateConfig;
import com.atguigu.spring5.a2anno.a4tx.JdbcTxConfig;
import org.springframework.context.annotation.*;

/**
 * 使用注解代替xml文件
 */

@Configuration // 相当于 xml文件中的配置文件
@ComponentScan(value = {"com.atguigu.spring5.a2anno"}) // 部件扫描，可以写多个包，用逗号隔开
@Import(value = {IocConfig.class, AopConfig.class, JdbcTemplateConfig.class, JdbcTxConfig.class}) // 导入其它用@Configuration修饰的类，或导入其它的类
@PropertySource(value = "classpath:a2anno/config.properties") // 导入properties文件，只能有一个prop文件
public class SpringConfig {
}
