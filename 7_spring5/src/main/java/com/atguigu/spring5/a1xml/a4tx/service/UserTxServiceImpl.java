package com.atguigu.spring5.a1xml.a4tx.service;


import com.atguigu.spring5.a1xml.a4tx.dao.UserTxDao;
import com.atguigu.spring5.a1xml.a4tx.entity.UserTx;

import java.util.List;

public class UserTxServiceImpl implements UserTxService {

    private UserTxDao userTxDao;

    public UserTxServiceImpl() {
    }

    public UserTxServiceImpl(UserTxDao userDao) {
        this.userTxDao = userDao;
    }


    @Override
    public void deleteAllUser() {
        userTxDao.delAllUser();
    }

}
