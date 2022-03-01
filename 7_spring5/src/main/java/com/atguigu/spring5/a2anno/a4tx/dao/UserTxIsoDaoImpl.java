package com.atguigu.spring5.a2anno.a4tx.dao;

import com.atguigu.spring5.a2anno.a4tx.entity.UserTx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;


/**
 * 查看4种隔离级别
 */
@Service(value = "userTxIsoDaoImpl")
public class UserTxIsoDaoImpl implements UserTxIsoDao {

    @Autowired
    @Qualifier(value = "jdbcTemplateTx")
    private JdbcTemplate jdbcTemplate;

    // 手动控制事务
    @Autowired
    @Qualifier(value = "transactionManager")
    private DataSourceTransactionManager dataSourceTransactionManager;


    /**
     * 配合演示脏读、不可重复读、幻读
     *
     * @param user
     * @param waitTime
     * @param cb
     * @throws Exception
     */
    public void createExceptionData(UserTx user, int waitTime, CyclicBarrier cb) throws Exception {

        cb.await();

        createDirtyRead(user, waitTime, cb);
        cb.await();
        System.out.println();
        System.out.println();
        System.out.println();
        cb.await();

        createNotRepeatRead(user, waitTime, cb);
        cb.await();
        System.out.println();
        System.out.println();
        System.out.println();
        cb.await();


        createInsPhantomRead(user, waitTime, cb); //
        cb.await();
    }


    /**
     * 读未提交
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
    @Override
    public void readUncommited(UserTx user, int money, CyclicBarrier cb, String isolation) throws Exception {
        dealExceptionData(user, money, cb, isolation);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public void readCommited(UserTx user, int money, CyclicBarrier cb, String isolation) throws Exception {
        dealExceptionData(user, money, cb, isolation);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @Override
    public void repeatableRead(UserTx user, int money, CyclicBarrier cb, String isolation) throws Exception {
        dealExceptionData(user, money, cb, isolation);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    @Override
    public void serializable(UserTx user, int money, CyclicBarrier cb, String isolation) throws Exception {
        dealExceptionData(user, money, cb, isolation);
    }

    /**
     * 查看脏读、不可重复读、幻读问题。
     *
     * @param user
     * @param money
     * @param cb
     * @throws Exception
     */
    private void dealExceptionData(UserTx user, int money, CyclicBarrier cb, String isolation) throws Exception {
        cb.await();

        dealDirtyRead(user, money, cb, isolation);
        cb.await();
        cb.await();

        dealNotRepeatRead(user, cb, isolation);
        cb.await();
        cb.await();

        dealPhantomRead(user, cb, isolation);
        cb.await();
    }

    /**
     * 幻读(Phantom Read):在一个事务的两次查询中数据笔数不一致，例如有一个事务查询了几列(Row)数据，而另一个事务却在此时插入了新的几列数据，
     * 先前的事务在接下来的查询中，就会发现有几列数据是它先前所没有的。
     *
     * 注意，对于"可重复读"，sel时是通过快照读，所以不会出现幻读，增删改是当前读，才会产生幻读
     * 查name = 张三的数据，在一个事务中查询了2次，有另一个事务在第1、2次查询中间，又插入了一条张三，导致2次查询的数据量不一致。
     */
    private void dealPhantomRead(UserTx user, CyclicBarrier cb, String isolation) throws Exception {
        int firstRows;
        int secondRows;
        System.out.println(Thread.currentThread().getName() + " " + isolation + "演示：查看" + isolation + "隔离级别是否能读取“幻读”数据：");
        System.out.println("    " + Thread.currentThread().getName() + " 读取“幻读”数据开始：");

        List<UserTx> userTxList = jdbcTemplate.query(
                "SELECT userId, userName, money FROM UserTx WHERE userName = ?",
                new BeanPropertyRowMapper(UserTx.class),
                user.getUserName()
        );
        System.out.println("        " + Thread.currentThread().getName() + " 第一次根据userName读取的条数及数据：" + userTxList.toString());
        firstRows = userTxList.size();
        cb.await(); // 1、根据userName读取所有数据
        cb.await(); // 2、让其它事务添加一条userName数据

        jdbcTemplate.update(
                "UPDATE UserTx SET money = 200 WHERE userName = ?",
                user.getUserName()
        );
        System.out.println("        " + Thread.currentThread().getName() + " 根据userName更新money为200(在RR隔离级别下，sel是快照读，不会出现幻读，增删改是当前读，才会出现幻读)");
        userTxList = jdbcTemplate.query(
                "SELECT userId, userName, money FROM UserTx WHERE userName = ?",
                new BeanPropertyRowMapper(UserTx.class),
                user.getUserName()
        );
        secondRows = userTxList.size();
        System.out.println("        " + Thread.currentThread().getName() + " 第二次根据userName读取的条数及数据：" + userTxList.toString());
        cb.await(); // 3、让其它线程查询现在数据库数据

        if (firstRows != secondRows)
            System.out.println("        " + Thread.currentThread().getName() + " 在本事务中2次根据userName读取的数据量不一样，所以" + isolation + "无法处理“幻读”");
        else
            System.out.println("        " + Thread.currentThread().getName() + " 在本事务中2次根据userName读取的数据量一样，所以" + isolation + "可以解决“幻读”");

    }

    /**
     * 配合演示幻读
     */
    @Transactional(propagation = Propagation.REQUIRED)
    private void createInsPhantomRead(UserTx user, int waitTime, CyclicBarrier cb) throws Exception {

        // 手动控制事务
        TransactionStatus status = getTs();
        cb.await(); // 1、让其它事务先根据userName读取所有数据

        System.out.println("    " + Thread.currentThread().getName() + "开始第一次添加“幻读”数据：(即添加一条数据，导致其它事务在多次查询时，查到不同的数据量)");
        String sql = "INSERT INTO UserTx (userId, userName, money) VALUES ((SELECT a.userId FROM (SELECT (MAX(userId) + 1) userId from UserTx) a), ?, ?)";
        jdbcTemplate.update(sql, user.getUserName(), user.getMoney());
        dataSourceTransactionManager.commit(status); // 提交
        System.out.println("        " + Thread.currentThread().getName() + "制造“幻读”数据结束，提交事务，新增了一条数据：" + jdbcTemplate.query(
                "SELECT userId, userName, money FROM UserTx WHERE userName = ?", new BeanPropertyRowMapper<>(UserTx.class), user.getUserName()
        ));
        cb.await(); // 2、添加一条userName数据
        cb.await(); // 3、让其它事务查询数据库数据

    }


    /**
     * 不可重复读：事务A多次读取同一数据，事务B在事务A多次读取的过程中，对数据进行了更新操作，导致事务A多次读取同一数据时，结果不一致；
     * 即在一个事务中，虽然读取的数据是已提交的，但n次查询的时候，有可能在任意2次查询中间，数据被另一个事务修改了。
     */
    private void dealNotRepeatRead(UserTx user, CyclicBarrier cb, String isolation) throws Exception {
        int firstMoney;
        int secondMoney;
        System.out.println(Thread.currentThread().getName() + " " + isolation + "演示：查看" + isolation + "隔离级别是否能读取“不可重复读”数据：");
        System.out.println("    " + Thread.currentThread().getName() + " 读取“不可重复读”数据开始：");

        String sql = "SELECT userId, userName, money FROM UserTx WHERE userId = ?";
        UserTx userTx = (UserTx) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(UserTx.class), user.getUserId());
        System.out.println("        " + Thread.currentThread().getName() + " 第一次读取的money值：" + userTx.toString());
        firstMoney = userTx.getMoney();
        cb.await(); // 1、
        cb.await(); // 2

        userTx = (UserTx) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(UserTx.class), user.getUserId());
        secondMoney = userTx.getMoney();
        System.out.println("        " + Thread.currentThread().getName() + " 第二次读取的money值：" + userTx.toString());
        if (firstMoney != secondMoney)
            System.out.println("        " + Thread.currentThread().getName() + " 在本事务中2次读取money的值不一样，所以" + isolation + "无法处理“不可重复读”");
        else
            System.out.println("        " + Thread.currentThread().getName() + " 在本事务中2次读取money的值一样，所以" + isolation + "可以处理“不可重复读”");
    }


    /**
     * 配合演示不可重复读
     */
    private void createNotRepeatRead(UserTx user, int money, CyclicBarrier cb) throws Exception {

        // 手动控制事务
        TransactionStatus status = getTs();
        cb.await(); // 1

        System.out.println("    " + Thread.currentThread().getName() + "开始第一次修改“不可重复读”数据：(即多次修改值，让其它事务在多次读取时，值不一样)");
        String sql = "UPDATE UserTx SET money = ? WHERE userId = ?";
        jdbcTemplate.update(sql, money, user.getUserId());
        dataSourceTransactionManager.commit(status); // 提交
        System.out.println("        " + Thread.currentThread().getName() + "制造“不可重复读”数据结束，提交事务，此时数据为：" + (jdbcTemplate.query(
                "SELECT userId, userName, money FROM UserTx WHERE userId = ?", new BeanPropertyRowMapper<>(UserTx.class), user.getUserId()
        )));
        cb.await(); // 2
    }

    /**
     * 脏读：事务A读取了事务B更新的数据，然后事务B进行回滚操作，那么事务A读取到的数据是脏数据；
     * 即读取了未提交的数据，会出现读了一条数据，但这条数据并未提交，其它事务又回滚了，那刚才读到的就是错误数据。
     *
     * @param user
     */
    private void dealDirtyRead(UserTx user, int money, CyclicBarrier cb, String isolation) throws Exception {

        System.out.println(Thread.currentThread().getName() + " " + isolation + "演示：查看" + isolation + "隔离级别是否能读取“脏读”数据：");
        cb.await(); // 1
        cb.await(); // 2

        System.out.println("    " + Thread.currentThread().getName() + " 读取“脏读”数据开始：");
        String sql = "SELECT userId, userName, money FROM UserTx WHERE userId = ?";
        UserTx userTx = (UserTx) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(UserTx.class), user.getUserId());
        if (userTx.getMoney() == money)
            System.out.println("        " + Thread.currentThread().getName() + " 读取“脏读”数据结束：读取到了脏数据，即其它事务已经修改，但未提交的money，" + userTx.toString());
        else if (userTx.getMoney() == 0)
            System.out.println("        " + Thread.currentThread().getName() + " 读取“脏读”数据结束：未读取到脏数据，即其它事务已经修改，但未提交的money，" + userTx.toString());
        cb.await(); // 3 读取”脏读“数据
        cb.await(); // 4 等待"脏读"回滚

        if (userTx.getMoney() == money)
            System.out.println("        " + Thread.currentThread().getName() + " 结论：" + isolation + "无法处理”脏读“问题。");
        else if (userTx.getMoney() == 0)
            System.out.println("        " + Thread.currentThread().getName() + " 结论：" + isolation + "可以处理”脏读“问题。");
    }

    /**
     * 配合演示脏读
     */
    private void createDirtyRead(UserTx user, int money, CyclicBarrier cb) throws Exception {

        cb.await(); // 1
        // 手动控制事务
        TransactionStatus status = getTs();

        try {
            System.out.println("    " + Thread.currentThread().getName() + "开始制造“脏读”数据：");
            String sql = "UPDATE UserTx SET money = ? WHERE userId = ?";
            jdbcTemplate.update(sql, money, user.getUserId());
            System.out.println("        " + Thread.currentThread().getName() + "制造“脏读”数据，修改money，但不提交(等待其它线程读取修改但未提交的值)：修改money为" + money + "。");
            cb.await(); // 2 制造”脏读“数据
            cb.await(); // 3 让其它线程读取”脏读“数据

            throw new RuntimeException();
        } catch (Exception e) {
            dataSourceTransactionManager.rollback(status); // 手动回滚
            String sql = "SELECT userId, userName, money FROM UserTx WHERE userId = ?";
            UserTx userTx = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(UserTx.class), user.getUserId());
            System.out.println("        " + Thread.currentThread().getName() + "抛出异常，回滚“脏读”数据：回滚money = " + user.getMoney() + "。当前数据库数据为：" + userTx.toString());
            cb.await(); // 4 回滚”脏读“数据
        }
    }

    @Override
    public void delAllUser() {
        String sql = "DELETE FROM UserTx";
        jdbcTemplate.update(sql);
    }

    @Override
    public void insertOneData(UserTx user) {
        String sql = "INSERT UserTx (userId, userName, money) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getUserId(), user.getUserName(), user.getMoney());
    }

    private TransactionStatus getTs() {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
        TransactionStatus status = dataSourceTransactionManager.getTransaction(def); // 获得事务状态
        return status;
    }

}
