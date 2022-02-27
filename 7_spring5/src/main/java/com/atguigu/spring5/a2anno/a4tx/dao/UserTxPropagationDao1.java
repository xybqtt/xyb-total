package com.atguigu.spring5.a2anno.a4tx.dao;

import com.atguigu.spring5.a2anno.a4tx.entity.UserTx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * 当A调用B时，查看方法A为没有事务时，事务传播行为
 * 事务分为3种大情况，假设A调用B：
 * 当B不想要事务时，但A存在事务时：
 *      PROPAGATION_NEVER：以非事务方式执行，如果当前存在事务，则抛出异常；(有事务就不运行)
 *      PROPAGATION_NOT_SUPPORTED：以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。(有事务我不运行事务)
 * 当B对事务没有要求时，A存在事务时：
 *      PROPAGATION_SUPPORTS：支持当前事务，如果当前没有事务，就以非事务方式执行。(有没有事务按你的走)
 * 当B一定要事务时：
 *      PROPAGATION_NESTED：如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则进行与PROPAGATION_REQUIRED
 *          类似的操作；(有事务就新建子事务，没有事务就新建事务，只有主事务提交，子事务才会提交，子事务失败不影响主事务)。
 *      PROPAGATION_REQUIRED：支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择；(有事务就用你的，没有就新建)
 *      PROPAGATION_REQUIRES_NEW：新建事务，如果当前存在事务，把当前事务挂起。(要新事务，并挂起当前事务如果有)
 *      PROPAGATION_MANDATORY：支持当前事务，如果当前没有事务，就抛出异常。(没有事务就不运行)
 */
@Service(value = "userTxPropagationDao1")
public class UserTxPropagationDao1 implements UserTxPropagationDao {

    @Autowired
    @Qualifier(value = "jdbcTemplateTx")
    private JdbcTemplate jdbcTemplate;

    /**
     * ROPAGATION_NEVER：以非事务方式执行，如果当前存在事务，则抛出异常；(有事务就不运行)
     *
     * @param money
     */
    @Transactional(propagation = Propagation.NEVER)
    public void never(List<UserTx> userList, int money) {
        updateData(userList.get(0), money);
    }

    /**
     * PROPAGATION_NOT_SUPPORTED：以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。(有事务我不运行事务)
     *
     * @param money
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void notSupported(List<UserTx> userList, int money) {
        updateData(userList.get(0), money);
    }

    /**
     * PROPAGATION_SUPPORTS：支持当前事务，如果当前没有事务，就以非事务方式执行。(有没有事务按你的走)
     *
     * @param money
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public void supports(List<UserTx> userList, int money) {
        updateData(userList.get(0), money);
    }

    /**
     * PROPAGATION_MANDATORY：支持当前事务，如果当前没有事务，就抛出异常。(没有事务就不运行)
     *
     * @param money
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void mandatory(List<UserTx> userList, int money) {
        updateData(userList.get(0), money);
    }

    /**
     * PROPAGATION_REQUIRES_NEW：新建事务，如果当前存在事务，把当前事务挂起。(要新事务，并挂起当前事务如果有)
     * @param money
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiresNew(List<UserTx> userList, int money) {
        updateData(userList.get(0), money);
        throw new RuntimeException();
    }

    /**
     * PROPAGATION_REQUIRED：支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择；(有事务就用你的，没有就新建)
     *
     * @param money
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void requires(List<UserTx> userList, int money) {
//        updateData(userList.get(0), money);
//        int i = 5 / 0;

        String sql = "UPDATE UserTx SET money = ? WHERE userId = ?";
        Object[] args = new Object[]{money, userList.get(0).getUserId()};
        jdbcTemplate.update(sql, args);
        int i = 5 / 0;
    }

    /**
     * PROPAGATION_NESTED：如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则进行与
     *     PROPAGATION_REQUIRED类似的操作；(有事务就新建子事务，没有事务就新建事务，只有主事务
     *     提交，子事务才会提交，子事务失败不影响主事务)。
     *
     * @param money
     */
    @Transactional(propagation = Propagation.NESTED)
    public void nested(List<UserTx> userList, int money) {
        updateData(userList.get(0), money);
        throw new RuntimeException();
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
            for(int i = 0; i < oneList.size(); i++) {
                UserTx userTx = (UserTx) oneList.get(i);
                paramList.add(new Object[]{userTx.getUserId(), userTx.getUserName(), userTx.getMoney()});
            }
        }

        jdbcTemplate.batchUpdate(sql, paramList);
    }

}
