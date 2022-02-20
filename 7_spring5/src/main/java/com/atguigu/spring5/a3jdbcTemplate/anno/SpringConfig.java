package com.atguigu.spring5.a3jdbcTemplate.anno;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan(value = "com.atguigu.spring5.a3jdbcTemplate.anno")
@PropertySource(value = "a3jdbcTemplate/jdbc.properties")
public class SpringConfig {

    @Value(value = "${jdbc.driverClassName}")
    private String driverClassName;

    @Value(value = "${jdbc.url}")
    private String url;

    @Value(value = "${jdbc.username}")
    private String username;

    @Value(value = "${jdbc.password}")
    private String password;

    @Autowired
    private DataSource dataSource;

    @Bean(value = "dataSource")
    public DataSource createDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(value = "jdbcTemplate")
    public JdbcTemplate createJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

}
