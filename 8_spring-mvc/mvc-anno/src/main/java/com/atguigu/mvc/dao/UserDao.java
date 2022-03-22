package com.atguigu.mvc.dao;

import com.atguigu.mvc.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDao {

    private static Map<Integer, User> users = null;

    static {
        users = new HashMap<>();

        users.put(1001, new User(1001, "张三1", "111", null));
        users.put(1002, new User(1002, "张三2", "222", null));
        users.put(1003, new User(1003, "张三3", "333", null));
        users.put(1004, new User(1004, "张三4", "444", null));
        users.put(1005, new User(1005, "张三5", "555", null));
    }

    private static Integer initId = 1006;

    public void save(User user) {
        if(user.getId() == null)
            user.setId(initId++);

        users.put(user.getId(), user);
    }

    public Collection<User> getAll() {
        return users.values();
    }

    public User get(Integer id) {
        return users.get(id);
    }

    public void delete(Integer id) {
        users.remove(id);
    }

}
