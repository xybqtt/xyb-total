package com.xyb.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {

    public static SqlSessionFactory factory;

    static {
        // 使用mybatis操作数据库
        try {
            // 1、读取主配置文件，文件位置是从classes的下级开始
            InputStream in = Resources.getResourceAsStream("mybatis.xml");
            // 2、创建SqlSessionFactoryBuilder
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            // 3、创建SqlSessionFactory
            factory = builder.build(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession(){
        if(factory != null)
            return factory.openSession();
        return null;
    }

}
