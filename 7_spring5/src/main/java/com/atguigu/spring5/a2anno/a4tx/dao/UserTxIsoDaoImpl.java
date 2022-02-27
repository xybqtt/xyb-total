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
 * 查看4种隔离级别
 */
@Service(value = "userTxIsoDaoImpl")
public class UserTxIsoDaoImpl implements UserTxIsoDao {

    @Autowired
    @Qualifier(value = "jdbcTemplateTx")
    private JdbcTemplate jdbcTemplate;


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
