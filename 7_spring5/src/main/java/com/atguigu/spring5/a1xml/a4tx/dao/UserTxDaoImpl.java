package com.atguigu.spring5.a1xml.a4tx.dao;

import com.atguigu.spring5.a1xml.a4tx.entity.UserTx;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class UserTxDaoImpl implements UserTxDao {

    private JdbcTemplate jdbcTemplate;

    public UserTxDaoImpl() {
    }

    public UserTxDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void delAllUser() {
        String sql = "DELETE FROM UserTx";
        jdbcTemplate.update(sql);
    }

}
