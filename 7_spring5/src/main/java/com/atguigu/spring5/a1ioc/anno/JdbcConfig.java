package com.atguigu.spring5.a1ioc.anno;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {

    @Value(value = "${jdbc.driverClass}")
    private String driver;


    /**
     * @Bean注解：只能写在方法上，表明使用此方法创建一个对象，并放入spring容器
     *      name属性：给当前@Bean注解方法创建的对象指定一个id，如果没有写此属性，则默认是方法名。
     * @return
     */
    @Bean(name = "dataSource")
    public DataSource createDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.driver);
        return dataSource;
    }

}
