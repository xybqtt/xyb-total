package com.atguigu.spring5.a4tx.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository(value = "userDaoImpl")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addMoney(String userName, int money) {
        String sql = "UPDATE User SET money = money - ? WHERE userId = ?";
        jdbcTemplate.update(sql, money, userName);
    }

}
