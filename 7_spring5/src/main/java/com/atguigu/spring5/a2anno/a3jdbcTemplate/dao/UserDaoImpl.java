package com.atguigu.spring5.a2anno.a3jdbcTemplate.dao;

import com.atguigu.spring5.a2anno.a3jdbcTemplate.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Repository(value = "userDaoImpl")
public class UserDaoImpl implements UserDao {

    @Autowired
    @Qualifier(value = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * 对参数只有一行数据进行的curd操作
     * @param user
     */
    @Override
    public void operaOneUser(User user) {
        // 插入单条数据，sql中?占位符与数组中对应数据要一致
        String sql = "INSERT INTO User (userId, userName, sex) VALUES (?, ?, ?)";
        Object[] objects = new Object[]{user.getUserId(), user.getUserName(), user.getSex()};
        int rows = jdbcTemplate.update(sql, objects);
        System.out.println("插入了" + rows + "条。");

        // 查询一条数据的一个字段
        sql = "SELECT userName FROM User WHERE userId = ?";
        String username = jdbcTemplate.queryForObject(sql, String.class, user.getUserId());
        System.out.println("查询插入的userName：" + username);

        // 通过一条参数更新
        user.setUserName(user.getUserName() + "1");
        sql = "UPDATE User SET userName = ? WHERE userId = ?";
        objects = new Object[]{user.getUserName(), user.getUserId()};
        rows = jdbcTemplate.update(sql, objects);
        System.out.println("更新了" + rows + "条。");

        // 查询对象，更新后的数据，查询多条数据，如果 bean的属性名和数据库一致，直接使用BeanPropertyRowMapper即可，如果不一致可以重写mapRow方法，在里面设置对应关系。
        sql = "SELECT * FROM User WHERE userId = ?";
        User user1 = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getUserId());
        System.out.println("更新后的单条数据：" + user1);

        // 通过一条参数删除
        sql = "DELETE FROM User WHERE userId = ?";
        rows = jdbcTemplate.update(sql, user.getUserId());
        System.out.println("删除了" + rows + "条。");
    }

    /**
     * 批量操作
     * @param insData 批量插入数据
     * @param updData 批量更新数据
     * @param delData 批量删除数据
     */
    public void batchOperate(List<Object[]> insData, List<Object[]> updData, List<Object[]> delData) {
        String insSql = "INSERT INTO User (userId, userName, sex) VALUES(?, ?, ?)";
        int[] nums = jdbcTemplate.batchUpdate(insSql, insData);
        System.out.println("批量插入了" + Arrays.toString(nums) + "条");

        String updSql = "UPDATE User SET userName = ?, sex = ? WHERE userId = ?";
        nums = jdbcTemplate.batchUpdate(updSql, updData);
        System.out.println("批量更新了" + Arrays.toString(nums) + "条");

        String delSql = "DELETE FROM User WHERE userId = ?";
        nums = jdbcTemplate.batchUpdate(delSql, delData);
        System.out.println("批量删除了" + Arrays.toString(nums) + "条");

        // 查询结果为多条数据，如果 bean的属性名和数据库一致，直接使用BeanPropertyRowMapper即可，如果不一致可以重写mapRow方法，在里面设置对应关系。
        String selSql = "SELECT * FROM User";
        List<User> list = jdbcTemplate.query(selSql, new RowMapper(){
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user1 = new User();
                user1.setUserId(rs.getInt("userid"));
                user1.setUserName(rs.getString("username"));
                user1.setSex(rs.getString("sex"));
                return user1;
            }
        });
        System.out.println("多条数据：" + list);
    }

    @Override
    public void delAllUser() {
        String sql = "DELETE FROM User";
        jdbcTemplate.update(sql);
    }

}
