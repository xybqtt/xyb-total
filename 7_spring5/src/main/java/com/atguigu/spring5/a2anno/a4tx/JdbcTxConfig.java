package com.atguigu.spring5.a2anno.a4tx;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * dataSource如何注入
 */
@Configuration(value = "jdbcTxConfig")
@PropertySource(value = "classpath:a2anno/config.properties") // 导入properties文件，只能有一个prop文件
@ComponentScan(value = {"com.atguigu.spring5.a2anno.a4tx"})
@EnableTransactionManagement // 启动事务管理，等同于xml配置文件中的 <tx:annotation-driven />
@EnableAspectJAutoProxy // 开启aop代理
public class JdbcTxConfig {

    @Value(value = "${jdbc.url}")
    private String url;

    @Value(value = "${jdbc.driverClassName}")
    private String driverClassName;

    @Value(value = "${jdbc.username}")
    private String userName;

    @Value(value = "${jdbc.password}")
    private String password;

    /**
     * 通过beanId获取，如果没有写value，默认是方法名，写了，则按value来
     * @return
     */
    @Bean(value = "dataSourceTx", initMethod = "", destroyMethod = "")
    public DataSource createDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(this.url);
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUsername(this.userName);
        dataSource.setPassword(this.password);
        return dataSource;
    }

    /**
     *
     * @param dataSource 如果配置了dataSource，会被自动注入的。
     * @return
     */
    @Bean(value = "jdbcTemplateTx")
    public JdbcTemplate createJdbcTemplate(@Qualifier(value = "dataSourceTx") DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    @Bean(value = "transactionManager")
    public TransactionManager transactionManager(@Qualifier(value = "dataSourceTx") DataSource dataSource) {
        DataSourceTransactionManager tx = new DataSourceTransactionManager();
        tx.setDataSource(dataSource);
        return tx;
    }

}
