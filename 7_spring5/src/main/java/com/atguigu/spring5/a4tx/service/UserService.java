package com.atguigu.spring5.a4tx.service;

import com.atguigu.spring5.a4tx.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "userService")
@Transactional // 事务注解
public class UserService {

    @Autowired
    private UserDao userDao;

    public void accountMoney() {
        // a钱加100
        userDao.addMoney("a", 100);

        // b - 100
        userDao.addMoney("b", -100);
    }

}
