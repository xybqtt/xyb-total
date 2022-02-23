package com.atguigu.spring5.a1xml.service;



import com.atguigu.spring5.a1xml.entity.User;

import java.util.List;

public interface UserService {

    /**
     * jdbcTemplate对一条参数的curd
     * @param user
     */
    public void operaOneUser(User user);

    /**
     * jdbcTemplate参数为多条时的操作
     * @param insData
     * @param updData
     * @param delData
     */
    public void batchOperate(List<Object[]> insData, List<Object[]> updData, List<Object[]> delData);

    public void deleteAllUser();

    public void aopTest();

}
