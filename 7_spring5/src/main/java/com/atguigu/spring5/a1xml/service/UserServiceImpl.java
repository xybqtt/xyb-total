package com.atguigu.spring5.a1xml.service;


import com.atguigu.spring5.a1xml.dao.UserDao;
import com.atguigu.spring5.a1xml.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * jdbcTemplate对一条参数的curd
     * @param user
     */
    @Override
    public void operaOneUser(User user) {
        userDao.operaOneUser(user);
    }

    /**
     * jdbcTemplate参数为多条时的操作
     * @param insData
     * @param updData
     * @param delData
     */
    @Override
    public void batchOperate(List<Object[]> insData, List<Object[]> updData, List<Object[]> delData) {
        userDao.batchOperate(insData, updData, delData);
    }

    @Override
    public void deleteAllUser() {
        userDao.delAllUser();
    }

    /**
     * 测试aop能否执行
     */
    @Override
    public void aopTest() {
        System.out.println("测试aop能否执行");
    }

}
