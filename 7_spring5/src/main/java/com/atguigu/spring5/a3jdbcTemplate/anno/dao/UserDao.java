package com.atguigu.spring5.a3jdbcTemplate.anno.dao;

import com.atguigu.spring5.a3jdbcTemplate.anno.entity.User;

import java.util.List;

public interface UserDao {

    /**
     * 只有一条参数的crud
     * @param user
     */
    public void operaOneUser(User user);

    /**
     * 参数是多条数据的crud
     * @param insData
     * @param updData
     * @param delData
     */
    public void batchOperate(List<Object[]> insData, List<Object[]> updData, List<Object[]> delData);

    public void delAllUser();

}
