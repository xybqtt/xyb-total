package com.xyb.a3jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * 将MBean注册到MBeanServer上，阻塞，等待外部程序调用。
 * JMX的Server端
 */
public class A1JmxServer {

    public static void main(String[] args) throws Exception {
        // 获取MBeanServer
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();

        /*
        * 创建可以被MBeanServer接受的ObjectName，ObjectName 是 MBean 的唯一标示，一个 MBeanServer 不能有重复。
        * 完整的格式「自定义命名空间:type=自定义类型,name=自定义名称」。当然你可以只声明 type ，不声明 name。
        * */
        ObjectName userName = new ObjectName("com.xyb.a3jmx:type=user,name=userBean");

        // 创建资源
        User user = new User("张三", 18);

        // 进行注册
        server.registerMBean(user, userName);

        System.out.println("资源已注册");

        Thread.sleep(60 * 60 * 1000);
    }

}

















