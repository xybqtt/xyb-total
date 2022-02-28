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

        createNotRepeatRead(user,waitTime, cb);
        System.out.println(Thread.currentThread().getName() + "开始演示“幻读”：");
        cb.await();
//
//        createPhantomRead(user,waitTime);
//        cb.await();
    }


    /**
     * 读未提交
     */
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void readUncommited(UserTx user, int waitTime, CyclicBarrier cb) throws Exception {
        System.out.println(Thread.currentThread().getName() + " readUncommited演示：查看READ_UNCOMMITTED隔离级别是否能读取“脏读”数据：");
        cb.await();

        showDirtyRead(user, waitTime, cb);
        cb.await();
//
//        showNotRepeatRead(user, waitTime);
//        cb.await();
//
//        showPhantomRead(user, waitTime);
//        cb.await();
    }

    /**
     * 幻读(Phantom Read):在一个事务的两次查询中数据笔数不一致，例如有一个事务查询了几列(Row)数据，而另一个事务却在此时插入了新的几列数据，
     * 先前的事务在接下来的查询中，就会发现有几列数据是它先前所没有的。
     * 查name = 张三的数据，在一个事务中查询了2次，有另一个事务在第1、2次查询中间，又插入了一条张三，导致2次查询的数据量不一致。
     */
    private void showPhantomRead(UserTx user, int waitTime) throws InterruptedException {
        System.out.println("幻读演示开始：");

        String sql = "SELECT userId, userName, money FROM UserTx WHERE userName = ?";
        List<UserTx> userTxList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(UserTx.class), user.getUserName());
        System.out.println("    (幻读)第一次读取的数据量：" + userTxList.toString());

        TimeUnit.SECONDS.sleep(waitTime); // 等3s，等待其它线程添加数据

        userTxList = jdbcTemplate.query(sql, new BeanPropertyRowMapper(UserTx.class), user.getUserName());
        System.out.println("    (幻读)第二次读取的数据量：" + userTxList.toString());
        System.out.println("幻读演示结束：在2次获取中间，其它事务对此userName的数据做了新增或删除，导致每次通过userName获取的数据量不一致。。");
    }

    /**
     * 配合演示幻读
     */
    @Transactional(propagation = Propagation.REQUIRED)
    private void createPhantomRead(UserTx user, int waitTime) throws InterruptedException {
        System.out.println("配合演示幻读开始：");

        TimeUnit.SECONDS.sleep(waitTime / 2); // 等待其它事务读取添加另一条数据前的值

        String sql = "INSERT INTO UserTx (userId, userName, money) VALUE (?, ?, ?)";
        jdbcTemplate.update(sql, (user.getUserId() + 1), user.getUserName(), user.getMoney());
        System.out.println("配合演示幻读结束：添加了一条userId = " + (user.getUserId() + 11) + "的数据。");

    }


    /**
     * 不可重复读：事务A多次读取同一数据，事务B在事务A多次读取的过程中，对数据进行了更新操作，导致事务A多次读取同一数据时，结果不一致；
     * 即在一个事务中，虽然读取的数据是已提交的，但n次查询的时候，有可能在任意2次查询中间，数据被另一个事务修改了。
     */
    private void showNotRepeatRead(UserTx user, int waitTime) throws InterruptedException {
        System.out.println("不可重复读演示开始：");

        String sql = "SELECT userId, userName, money FROM UserTx WHERE userId = ?";
        UserTx userTx = (UserTx) jdbcTemplate.query(sql, new BeanPropertyRowMapper(UserTx.class), user.getUserId());
        System.out.println("    (不可重复读)第一次读取的money值：" + userTx.toString());

        TimeUnit.SECONDS.sleep(waitTime); // 等3s，等待其它线程修改money的值

        userTx = (UserTx) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(UserTx.class), user.getUserId());
        System.out.println("    (不可重复读)第二次读取的money值：" + userTx.toString());
        System.out.println("不可重复读演示结束：在2次获取中间，其它事务对此money做了修改，导致每次读的money都不一定准确，因为2次查询间，有可能其它事务对数据进行了修改并提交。");
    }


    /**
     * 配合演示不可重复读
     */
    @Transactional(propagation = Propagation.REQUIRED)
    private void createNotRepeatRead(UserTx user, int money, CyclicBarrier cb) throws Exception {
//        System.out.println("配合演示不可重复读开始：");
//
//        TimeUnit.SECONDS.sleep(waitTime / 2); // 等待其它事务读取修改前的值
//
//        String sql = "UPDATE UserTx SET money = ? WHERE userId = ?";
//        jdbcTemplate.update(sql, user.getMoney() + 100, user.getUserId());
//        System.out.println("配合演示不可重复读结束：修改money为" + (user.getMoney() + 100));

        // ----------------------------

        // 手动控制事务
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
        TransactionStatus status = dataSourceTransactionManager.getTransaction(def); // 获得事务状态

        try {
            System.out.println("    " + Thread.currentThread().getName() + "开始制造“不可重复读”数据：(即同一事务中多次读取同一事务，但读取的数据不一致)");
            String sql = "UPDATE UserTx SET money = ? WHERE userId = ?";
            jdbcTemplate.update(sql, money, user.getUserId());
            System.out.println("        " + Thread.currentThread().getName() + "制造“脏读”数据，修改money，但不提交(等待其它线程读取修改但未提交的值)：修改money为" + money + "。");
            cb.await(); // 1
            cb.await(); // 2

            throw new RuntimeException();
        } catch (Exception e) {
            dataSourceTransactionManager.rollback(status); // 手动回滚
            String sql = "SELECT userId, userName, money FROM UserTx WHERE userId = ?";
            UserTx userTx = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(UserTx.class), user.getUserId());
            System.out.println("    " + Thread.currentThread().getName() + "抛出异常，回滚“脏读”数据：回滚money = " + user.getMoney() + "。当前数据库数据为：" + userTx.toString());
            cb.await(); // 3
        }
    }

    /**
     * 脏读：事务A读取了事务B更新的数据，然后事务B进行回滚操作，那么事务A读取到的数据是脏数据；
     * 即读取了未提交的数据，会出现读了一条数据，但这条数据并未提交，其它事务又回滚了，那刚才读到的就是错误数据。
     *
     * @param user
     */
    private void showDirtyRead(UserTx user, int money, CyclicBarrier cb) throws Exception {

        cb.await(); // 1
        System.out.println("    " + Thread.currentThread().getName() + " 读取“脏读”数据开始：");

        String sql = "SELECT userId, userName, money FROM UserTx WHERE userId = ?";
        UserTx userTx = (UserTx) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(UserTx.class), user.getUserId());
        if(userTx.getMoney() == money)
            System.out.println("    " + Thread.currentThread().getName() + " 读取“脏读”数据结束：读取到了脏数据，即其它事务已经修改，但未提交的money，" + userTx.toString());
        else if(userTx.getMoney() == 0)
            System.out.println("    " + Thread.currentThread().getName() + " 读取“脏读”数据结束：未读取到脏数据，即其它事务已经修改，但未提交的money，" + userTx.toString());
        cb.await(); // 2
        cb.await(); // 3

        if(userTx.getMoney() == money)
            System.out.println("    " + Thread.currentThread().getName() + " 结论：READ_UNCOMMITTED无法处理”脏读“问题。");
         else if(userTx.getMoney() == 0)
            System.out.println("    " + Thread.currentThread().getName() + " 结论：READ_UNCOMMITTED可以处理”脏读“问题。");
    }

    /**
     * 配合演示脏读
     */
    private void createDirtyRead(UserTx user, int money, CyclicBarrier cb) throws Exception {

        // 手动控制事务
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 事物隔离级别，开启新事务，这样会比较安全些。
        TransactionStatus status = dataSourceTransactionManager.getTransaction(def); // 获得事务状态

        try {
            System.out.println("    " + Thread.currentThread().getName() + "开始制造“脏读”数据：");
            String sql = "UPDATE UserTx SET money = ? WHERE userId = ?";
            jdbcTemplate.update(sql, money, user.getUserId());
            System.out.println("        " + Thread.currentThread().getName() + "制造“脏读”数据，修改money，但不提交(等待其它线程读取修改但未提交的值)：修改money为" + money + "。");
            cb.await(); // 1
            cb.await(); // 2

            throw new RuntimeException();
        } catch (Exception e) {
            dataSourceTransactionManager.rollback(status); // 手动回滚
            String sql = "SELECT userId, userName, money FROM UserTx WHERE userId = ?";
            UserTx userTx = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(UserTx.class), user.getUserId());
            System.out.println("    " + Thread.currentThread().getName() + "抛出异常，回滚“脏读”数据：回滚money = " + user.getMoney() + "。当前数据库数据为：" + userTx.toString());
            cb.await(); // 3
        }
    }


    @Override
    public void showAll() {
        String sql = "SELECT userId, userName, money FROM UserTx ORDER BY userId ASC";
        List<UserTx> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(UserTx.class));
        Iterator<UserTx> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }
    }


    public void updateData(UserTx user, int money) {
        String sql = "UPDATE UserTx SET money = ? WHERE userId = ?";
        Object[] args = new Object[]{money, user.getUserId()};
        jdbcTemplate.update(sql, args);
    }

    @Override
    public void delAllUser() {
        String sql = "DELETE FROM UserTx";
        jdbcTemplate.update(sql);
    }

    @Override
    public void insertBatchData(Map<String, List<UserTx>> prepareData) {
        String sql = "INSERT UserTx VALUES (?, ?, ?)";

        List<Object[]> paramList = new ArrayList<>();
        Set entrySet = prepareData.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            List oneList = (List) ((Map.Entry) iterator.next()).getValue();
            for (int i = 0; i < oneList.size(); i++) {
                UserTx userTx = (UserTx) oneList.get(i);
                paramList.add(new Object[]{userTx.getUserId(), userTx.getUserName(), userTx.getMoney()});
            }
        }

        jdbcTemplate.batchUpdate(sql, paramList);
    }

    @Override
    public void insertOneData(UserTx user) {
        String sql = "INSERT UserTx (userId, userName, money) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getUserId(), user.getUserName(), user.getMoney());
    }

}
