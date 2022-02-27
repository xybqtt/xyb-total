package com.atguigu.spring5.a2anno.a4tx.dao;

import com.atguigu.spring5.a2anno.a4tx.entity.UserTx;

import java.util.List;
import java.util.Map;

/**
 * 测试事务隔离级别
 */
public interface UserTxIsoDao {


//    public void readUncommited();
//
//    public void readCommited();
//
//    public void repeatableRead();
//
//    public void serializable();


    public void showAll();
    public void delAllUser();
    public void insertBatchData(Map<String, List<UserTx>> prepareData);

}
