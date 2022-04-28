package com.xyb.a3jmx;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class User extends NotificationBroadcasterSupport implements UserMBean {


    private int seq = 0;

    private String userName;

    private Integer age;

    public User() {
    }

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[]{
                AttributeChangeNotification.ATTRIBUTE_CHANGE
        };

        String name = AttributeChangeNotification.class.getName();
        String desc = "An attribute of this MBean has changed";
        MBeanNotificationInfo info = new MBeanNotificationInfo(types, name, desc);

        return new MBeanNotificationInfo[]{info};
    }

    public String getUserName() {
        return userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        int oldAge = this.age;
        this.age = age;

        Notification notification = new AttributeChangeNotification(
                this, seq++, System.currentTimeMillis(), "age changed",
                "Age", "int", oldAge, this.age);
        sendNotification(notification);
    }

    public void sayHello() {
        System.out.println(this.userName + "，hello");
    }

    public int add(Integer x, Integer y) {
        return x + y;
    }

}


















