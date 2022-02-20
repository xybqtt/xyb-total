package com.atguigu.spring5.a4tx;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan(value = "com.atguigu.spring5.a4tx")
@EnableTransactionManagement(proxyTargetClass = true) // 开启事务管理
public class SpringConfig {

    @Value(value = "com.mysql.jdbc.Driver")
    private String driverClassName;

    @Value(value = "jdbc:mysql://localhost:3306/xyb")
    private String url;

    @Value(value = "root")
    private String username;

    @Value(value = "root")
    private String password;

    @Bean(value = "dataSource")
    public DataSource createDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    /**
     *
     * @param dataSource 传的这个dataSource，spring会自动注入上面createDataSource创建的
     * @return
     */
    @Bean(value = "jdbcTemplate")
    public JdbcTemplate createJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


    /**
     * 声明专用于jdbcTemplate的事务管理类
     * @return
     */
    @Bean(value = "transactionManager")
    public DataSourceTransactionManager createJdbcTx(DataSource dataSource) {
        DataSourceTransactionManager tx = new DataSourceTransactionManager();
        tx.setDataSource(dataSource);
        return tx;
    }
}













