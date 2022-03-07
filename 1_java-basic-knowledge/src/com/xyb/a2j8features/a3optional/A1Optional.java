package com.xyb.a2j8features.a3optional;

import org.junit.Test;

import java.util.Optional;

import static javafx.scene.input.KeyCode.T;

/**
 * optional类的使用
 * 创建：
 *  Optional.of(T t) : 创建一个 Optional 实例，t 必须非空；
 *  Optional.empty() : 创建一个空的 Optional 实例
 *  Optional.ofNullable(T t)：t可以为 null
 *
 * 判断Optional容器是否包含对象：
 *  boolean isPresent()：判断是否包含对象
 *  void ifPresent(Consumer<? super T> consumer)：如果有值，就执行 Consumer 接口的实现代码，并且该值会作为参数传给它。
 */
public class A1Optional {

    @Test
    public void test() {
        // Optional容器对象的创建
        createOptional();

        // 判断optional容器是否包含对象
        judgeNotNull();

        // 获取optional容器的对象
        getOptionalObj();


    }

    private void getOptionalObj() {
        System.out.println();
        System.out.println("获取容器内的对象：");
        Optional<User> user = Optional.of(new User("张三"));
        Optional<Object> o = Optional.ofNullable(null);

        System.out.println("get()：" + user.get());
        try {
            System.out.println("get()：" + o.get());
        } catch (Exception e) {
            System.out.println("容器中没有值，通过get()方法获取会抛出空指针");
        }

        System.out.println("user.orElse(T)：如果容器中有值，则返回此值，" + user.orElse(new User("李四")));
        System.out.println("o.orElse(T)：如果容器中没有值，则返回此方法参数中的值，" + o.orElse(new User("李四")));

        System.out.println("user.orElseGet(Supplier<? extends T>)：如果容器中有值，则返回此值，" + user.orElseGet(User::new));
        System.out.println("o.orElseGet(Supplier<? extends T>)：如果容器中没有值，则返回Supplier接口实现提供的对象，" + o.orElseGet(User::new));

        System.out.println("user.orElseThrow(Supplier<? extends T>)：如果容器中有值，则返回此值，" + user.orElseThrow(RuntimeException::new));
        try {
            System.out.println("o.orElseThrow(Supplier<? extends T>)：如果容器中没有值，则抛出Supplier接口实现抛出的异常，" + o.orElseThrow(RuntimeException::new));
        } catch (Exception e) {
            System.out.println("o.orElseThrow(Supplier<? extends T>)：如果容器中没有值，则抛出Supplier接口实现抛出的异常，" + e);
        }







    }

    /**
     * 判断是否包含对象
     */
    private void judgeNotNull() {
        System.out.println();
        System.out.println("判断Optional容器是否有值：");

        Optional<User> user = Optional.of(new User("张三"));
        Optional<Object> o = Optional.ofNullable(null);

        // 判断是否包含对象
        System.out.println(user.isPresent());
        System.out.println(o.isPresent());

        // 如果有值，就执行 Consumer 接口的实现代码，并且该值会作为参数传给它。
        user.ifPresent(System.out::println);
        o.ifPresent(System.out::println);


        //


    }

    /**
     * Optional对象创建的3种方式
     */
    private void createOptional() {

        System.out.println("3种Optional的创建方式：");

        // 创建一个 Optional 实例，t 必须非空
        Optional<User> user = Optional.of(new User("张三"));
        System.out.println(user);

        // 创建一个空的 Optional 实例
        Optional<Object> empty = Optional.empty();
        System.out.println(empty);

        // 创建一个 Optional 实例，t 可以为null
        Optional<Object> o = Optional.ofNullable(null);
        System.out.println(o);
    }

    private class User {
        private String username;

        private User friend;

        public User() {
        }

        public User(String username) {
            this.username = username;
        }

        public User(String username, User friend) {
            this.username = username;
            this.friend = friend;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public User getFriend() {
            return friend;
        }

        public void setFriend(User friend) {
            this.friend = friend;
        }


        @Override
        public String toString() {
            return "User{" +
                    "username='" + username + '\'' +
                    ", friend=" + friend +
                    '}';
        }
    }

}






















