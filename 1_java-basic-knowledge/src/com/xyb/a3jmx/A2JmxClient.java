package com.xyb.a3jmx;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * 使用java程序开发的jmx客户端。
 * 流程如下：
 * 1、需要知道JMXServer的ip和port，"service:jmx:rmi:///jndi/rmi://" + host + ":" + port + "/jmxrmi"
 * 2、获取JMXConnector连接；
 * 3、从JMXConnector获取MBeanServerConnection连接；
 * 4、可以从MBeanServerConnection获取根据ObjectName(就是在Server端发布时，注册的ObjectName)所有MBeanInfo信息；
 * 5、可以通MBeanInfo操作MBServer端的属性、方法、订阅通知等。
 * MBeanAttributeInfo : mBeanInfo.getAttributes()获取属性；
 * MBeanOperationInfo : mBeanInfo.getOperations()操作方法；
 * MBeanNotificationInfo :  mBeanInfo.getNotifications()，查看可订阅通知。
 * 6、也可以通过JMX代理，用与服务器端MBean接口有相同方法的接口去生成代理，直接调用代理的方法，就可以操作属性和方法了。
 */
public class A2JmxClient {

    public static void main(String[] args) throws Exception {

        String host = "127.0.0.1";
        String port = "9999";
        String account = "admin";
        String password = "password";

        Map<String, Object> environment = new HashMap<>();
        environment.put(JMXConnector.CREDENTIALS, new String[]{account, password});

        // 1、获取JMX服务端的连接器
        JMXConnector connector = JMXConnectorFactory.connect(
                new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + host + ":" + port + "/jmxrmi")
                , environment // 有服务器端如果设置了账号密码，则需要添加这个参数
        );

        if (connector == null)
            return;

        // 2、获取MBeanServer的连接
        MBeanServerConnection mbServerConn = connector.getMBeanServerConnection();

        // 3、获取所有在MBeanServer上注册的MBean.ObjectName，第一个能数为null，代表获取所有ObjectName，此处仅演示一个
        Set<ObjectName> objectNames = mbServerConn.queryNames(new ObjectName("com.xyb.a3jmx:type=user,name=userBean"), null);

        // 4、获取MBeanServer注册的MBean属性信息、方法信息 、通知信息，及其读写属性、调用方法、订阅通知的操作。
        objectNames.stream().forEach(objectName -> {
            try {
                // 获取MBeanInfo，此类只能获取属性的信息，获取属性值要通过MBServerConnection
                MBeanInfo mBeanInfo = mbServerConn.getMBeanInfo(objectName);
                StringBuilder sb = new StringBuilder("");

                // 1、遍历MBean属性信息
                sb.append("获取" + objectName + "属性信息：" + "\n");
                for (MBeanAttributeInfo attrInfo : mBeanInfo.getAttributes()) {
                    sb.append("    属性名：" + attrInfo.getName() + "\n");
                    sb.append("        类型：" + attrInfo.getType() + "\n");
                    sb.append("        描述：" + attrInfo.getDescription() + "\n");
                    sb.append("        isIs：" + attrInfo.isIs() + "\n");
                    sb.append("        可读：" + attrInfo.isReadable() + "\n");
                    sb.append("        可写：" + attrInfo.isWritable() + "\n");
                    // 获取属性值
                    sb.append("        获取属性值：" + mbServerConn.getAttribute(objectName, attrInfo.getName()) + "\n");
                    if ("Age".equals(attrInfo.getName()) && attrInfo.isWritable()) {
                        sb.append("        设置属性值：mbServerConn.setAttribute(objectName, new Attribute(attrInfo.getName(), 1))就行。" + "\n");
                        // 设置属性值
                        mbServerConn.setAttribute(objectName, new Attribute(attrInfo.getName(), 1));
                        sb.append("        修改后的属性值：" + mbServerConn.getAttribute(objectName, attrInfo.getName()) + "\n");
                    }

                }

                // 2、遍历MBean方法信息
                sb.append("\n" + "获取" + objectName + "方法(操作)信息：" + "\n");
                for (MBeanOperationInfo methodInfo : mBeanInfo.getOperations()) {
                    sb.append("    方法名：" + methodInfo.getName() + "\n");
                    sb.append("        返回类型：" + methodInfo.getReturnType() + "\n");
                    sb.append("        参数信息：" + "\n");
                    for (MBeanParameterInfo paramInfo : methodInfo.getSignature())
                        sb.append("            " + paramInfo.toString() + "\n");
                    sb.append("        方法影响：" + methodInfo.getImpact() + "\n");


                    // 调用add方法
                    if ("add".equals(methodInfo.getName())) {
                        int addResult = (int) mbServerConn.invoke(
                                objectName,
                                methodInfo.getName(),
                                new Integer[]{1, 2},
                                new String[]{Integer.class.getCanonicalName(), Integer.class.getCanonicalName()}
                        );
                        sb.append("        方法运行结果(1 + 2 = )：" + addResult + "\n");
                    }

                }

                // 3、遍历通知信息
                sb.append("\n" + "获取" + objectName + "通知信息：" + "\n");
                for (MBeanNotificationInfo notification : mBeanInfo.getNotifications()) {
                    sb.append("    通知类型：" + notification.getName() + "\n");
                    sb.append("        通知类型对应字符串：" + Arrays.toString(notification.getNotifTypes()) + "\n");
                }

                System.out.println(sb.toString());

                // 对通知进行订阅
                mbServerConn.addNotificationListener(objectName, new NotificationListener() {
                    /**
                     * 订阅后客户端发出的消息，在这块处理。
                     * @param notification
                     * @param handback
                     */
                    @Override
                    public void handleNotification(Notification notification, Object handback) {
                        System.out.println("客户端收到服务器端消息了");
                        System.out.println("\nReceived notification:");
                        System.out.println("\tClassName: " + notification.getClass().getName());
                        System.out.println("\tSource: " + notification.getSource());
                        System.out.println("\tType: " + notification.getType());
                        System.out.println("\tMessage: " + notification.getMessage());
                        if (notification instanceof AttributeChangeNotification) {
                            AttributeChangeNotification acn =
                                    (AttributeChangeNotification) notification;
                            System.out.println("\tAttributeName: " + acn.getAttributeName());
                            System.out.println("\tAttributeType: " + acn.getAttributeType());
                            System.out.println("\tNewValue: " + acn.getNewValue());
                            System.out.println("\tOldValue: " + acn.getOldValue());
                        }
                    }
                }, null, null);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 5、另一种：读写属性、调用方法的写法，只要接口方法签名一致就能通过这种方式调用，不必是同一接口(因为2个服务器，接口一全限定名一致了也没用)，此处就不重新写一个接口了。
        UserMBean userMBean = JMX.newMBeanProxy(mbServerConn, new ObjectName("com.xyb.a3jmx:type=user,name=userBean"), UserMBean.class, true);
        StringBuilder sb = new StringBuilder("");
        sb.append("使用JMX的代理功能去操作服务端UserMBean：" + "\n");
        sb.append("    调用sayHello()：" + "\n");
        userMBean.sayHello();
        sb.append("    调用userMBean.getUserName()：" + userMBean.getUserName() + "\n");
        sb.append("    调用userMBean.add(1, 3)：" + userMBean.add(1, 3) + "\n");
        sb.append("    调用userMBean.getAge()：" + userMBean.getAge() + "\n");
        sb.append("    调用userMBean.getAge(10)。" + "\n");
        userMBean.setAge(10);
        sb.append("    调用userMBean.getAge()：" + userMBean.getAge() + "\n");
        System.out.println(sb);


        Thread.sleep(60 * 60 * 1000);


    }
}

















