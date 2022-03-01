package com.atguigu.spring5.a2anno.a4tx.dao;

import com.atguigu.spring5.a2anno.a4tx.entity.UserTx;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CyclicBarrier;

/**
 * 测试事务隔离级别
 */
public interface UserTxIsoDao {



    public void readUncommited(UserTx user, int money, CyclicBarrier cb, String isolation) throws Exception;

    public void readCommited(UserTx user, int money, CyclicBarrier cb, String isolation) throws Exception;

    public void repeatableRead(UserTx user, int money, CyclicBarrier cb, String isolation) throws Exception;

    public void serializable(UserTx user, int money, CyclicBarrier cb, String isolation) throws Exception;

    public void createExceptionData(UserTx user, int waitTime, CyclicBarrier cb) throws Exception;

    public void insertOneData(UserTx user);
    public void delAllUser();

}
