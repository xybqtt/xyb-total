package com.atguigu.spring5.a2anno.a4tx.service;


import com.atguigu.spring5.a2anno.a4tx.entity.UserTx;

import java.util.List;
import java.util.Map;

public interface UserTxService {

    public void deleteAllUser();

    public void showTx(Map<String, List<UserTx>> prepareData, int i, String tx);

    public void showisolation(UserTx userTx, int money, String choose) throws Exception;

    public void showAllUserTx();

    public void insertData(Map<String, List<UserTx>> prepareData);

    public void insertOneData(UserTx java);


}
