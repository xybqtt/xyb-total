package com.atguigu.spring5.a2anno.a4tx.dao;


import com.atguigu.spring5.a2anno.a4tx.entity.UserTx;

import java.util.List;
import java.util.Map;

public interface UserTxPropagationDao {

    public void never(List<UserTx> userList, int money);

    public void notSupported(List<UserTx> userList, int money);

    public void supports(List<UserTx> userList, int money);

    public void mandatory(List<UserTx> userList, int money);

    public void requiresNew(List<UserTx> userList, int money);

    public void requires(List<UserTx> userList, int money);

    public void nested(List<UserTx> userList, int money);

    public void updateData(UserTx user, int money);

    public void showAll();

    public void delAllUser();

    public void insertBatchData(Map<String, List<UserTx>> prepareData);
}
