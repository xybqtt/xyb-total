package com.atguigu.spring5.a2anno.a3jdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * dataSource如何注入
 */
@Configuration(value = "jdbcTemplateConfig")
@ComponentScan(value = {"com.atguigu.spring5.a2anno.a3jdbcTemplate"})
@PropertySource(value = "classpath:a2anno/config.properties") // 导入properties文件，只能有一个prop文件
public class JdbcTemplateConfig {

    /**
     * 通过beanId获取，如果没有写value，默认是方法名，写了，则按value来
     * @return
     * initMethod、destroyMethod必须是被声明的类的方法
     */
    @Bean(value = "dataSource", initMethod = "", destroyMethod = "")
    public DataSource createDataSource(@Value(value = "${jdbc.url}") String url,
                                       @Value(value = "${jdbc.driverClassName}") String driverClassName,
                                       @Value(value = "${jdbc.username}") String userName,
                                       @Value(value = "${jdbc.password}") String password) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    /**
     *
     * @param dataSource 如果配置了dataSource，会被自动注入的。
     * @return
     */
    @Bean(value = "jdbcTemplate")
    public JdbcTemplate createJdbcTemplate(@Qualifier(value = "dataSource") DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

}
