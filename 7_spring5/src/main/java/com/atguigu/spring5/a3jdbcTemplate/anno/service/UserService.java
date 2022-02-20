package com.atguigu.spring5.a3jdbcTemplate.anno.service;

import com.atguigu.spring5.a3jdbcTemplate.anno.dao.UserDao;
import com.atguigu.spring5.a3jdbcTemplate.anno.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 对一条参数的curd
     * @param user
     */
    public void operaOneUser(User user) {
        userDao.operaOneUser(user);
    }

    /**
     * 参数为多条时的操作
     * @param insData
     * @param updData
     * @param delData
     */
    public void batchOperate(List<Object[]> insData, List<Object[]> updData, List<Object[]> delData) {
        userDao.batchOperate(insData, updData, delData);
    }

    public void deleteAllUser() {
        userDao.delAllUser();
    }


}
