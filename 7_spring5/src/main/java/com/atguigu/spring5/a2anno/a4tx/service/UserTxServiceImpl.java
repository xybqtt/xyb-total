package com.atguigu.spring5.a2anno.a4tx.service;


import com.atguigu.spring5.a2anno.a4tx.dao.UserTxIsoDao;
import com.atguigu.spring5.a2anno.a4tx.dao.UserTxIsoDaoImpl;
import com.atguigu.spring5.a2anno.a4tx.dao.UserTxPropagationDao;
import com.atguigu.spring5.a2anno.a4tx.entity.UserTx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

@Service(value = "userTxServiceImpl")
public class UserTxServiceImpl implements UserTxService {

    @Autowired
    @Qualifier(value = "userTxPropagationDao1")
    private UserTxPropagationDao userTxDao;

    @Autowired
    @Qualifier(value = "userTxPropagationDao2")
    private UserTxPropagationDao userTxDao2;

    @Autowired
    @Qualifier(value = "userTxIsoDaoImpl")
    private UserTxIsoDao userTxIsoDao;

    /**
     * 演示7种事务传播行为
     *
     * @param prepareData
     * @param money
     * @param tx
     */
    @Override
    public void showTx(Map<String, List<UserTx>> prepareData, int money, String tx) {
        switch (tx) {
            case "tx":
                modifyMoneyWithTx(prepareData, money);
                break;
            case "nottx":
                modifyMoneyNotTx(prepareData, money);
                break;
        }
    }

    /**
     * 演示事务隔离级别
     *
     * @param choose
     */
    public void showisolation(UserTx userTx, int waitTime, String choose) throws Exception {
        CyclicBarrier cb = new CyclicBarrier(2);

        switch (choose) {
            case "1":
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            userTxIsoDao.cooperateShow(userTx, waitTime, cb);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                userTxIsoDao.readUncommited(userTx, waitTime, cb);
                break;
            default:

        }


    }

    /**
     * 无事务
     * 本方法中所有被调用方法只在本方法没有事务下考虑
     *
     * @param money
     */
    public void modifyMoneyNotTx(Map<String, List<UserTx>> userListMap, int money) {
        userTxDao.updateData(userListMap.get("begindata").get(0), money); // 验证事务是否回滚

        userTxDao.never(userListMap.get("never"), money);
        userTxDao.notSupported(userListMap.get("notsupport"), money);
        userTxDao.supports(userListMap.get("support"), money);
        try {
            userTxDao.mandatory(userListMap.get("mandatory"), money);
        } catch (Exception e) {
            System.out.println("mandatory必须要事务，当前无事务，抛出异常：" + e.getMessage());
        }

        try {
            userTxDao.requiresNew(userListMap.get("requiresnew"), money);
        } catch (Exception e) {
            System.out.println("requiresnew，有异常，则会让被修饰的类中的事务回滚：" + e.getMessage());
        }

        try {
            userTxDao.requires(userListMap.get("requires"), money);
        } catch (Exception e) {
            System.out.println("requires，有异常，则会让被修饰的类中的事务回滚：" + e.getMessage());
        }

        try {
            userTxDao.nested(userListMap.get("nested"), money);
        } catch (Exception e) {
            System.out.println("nested，有异常，则会让被修饰的类中的事务回滚：" + e.getMessage());
        }

        userTxDao.updateData(userListMap.get("enddata").get(0), money); // 验证事务是否回滚
    }


    /**
     * 有事务
     * 本方法中所有被调用方法只在本方法有事务下考虑
     *
     * @param money
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void modifyMoneyWithTx(Map<String, List<UserTx>> userListMap, int money) {
        userTxDao2.updateData(userListMap.get("begindata").get(0), money); // 验证事务是否回滚

        try {
            userTxDao2.never(userListMap.get("never"), money);
        } catch (Exception e) {
            System.out.println("调用方法有事务，抛出异常并回滚：" + e.getMessage());
        }

        // 以非事务方式运行，当前有事务，则挂起事务
        userTxDao2.notSupported(userListMap.get("notsupport"), money);

        // 按当前事务来，没有事务则按没有事务来
        userTxDao2.supports(userListMap.get("support"), money);

        // 支持当前事务
        userTxDao2.mandatory(userListMap.get("mandatory"), money);

        // 新建事务，并挂起当前事务
        userTxDao2.requiresNew(userListMap.get("requiresnew"), money);

        // 支持当前事务，当前事务回滚，则本方法也回滚
        userTxDao2.requires(userListMap.get("requires"), money);

        // 在当前事务下开启子事务，当前事务回滚，则子事务回滚。子事务回滚，不影响当前事务。
        userTxDao2.nested(userListMap.get("nested"), money);

        int i = 5 / 0;
        userTxDao2.updateData(userListMap.get("enddata").get(0), money); // 验证事务是否回滚
    }

    @Override
    public void showAllUserTx() {
        userTxDao.showAll();
    }

    @Override
    public void insertData(Map<String, List<UserTx>> prepareData) {
        userTxDao.insertBatchData(prepareData);
    }

    @Override
    public void insertOneData(UserTx user) {
        userTxIsoDao.insertOneData(user);
    }

    @Override
    public void deleteAllUser() {
        userTxDao.delAllUser();
    }


}
