package com.atguigu.spring5.a2anno.a4tx.service;


import com.atguigu.spring5.a2anno.a4tx.entity.UserTx;

import java.util.List;
import java.util.Map;

public interface UserTxService {

    public void deleteAllUser();

    public void modifyMoneyNotTx(Map<String, List<UserTx>> userListMap, int money);

    public void modifyMoneyWithTx(Map<String, List<UserTx>> userListMap, int money);

    public void showAllUserTx();

    public void insertData(Map<String, List<UserTx>> prepareData);
}
