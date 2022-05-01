package com.xyb.a3jmx;

import javax.management.MBeanServer;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * 将MBean注册到MBeanServer上，阻塞，等待外部程序调用。
 * JMX的Server端
 * 参数设置：分别为主 端口、是否需要验证、ssl、文件：包含每个账户的读写权限、每个账户的密码所在文件
 * -Dcom.sun.management.jmxremote.port=9999
 * -Dcom.sun.management.jmxremote.authenticate=true  进行验证
 * -Dcom.sun.management.jmxremote.ssl=false
 *
 * 哪些用户可以登陆，及其读写权限
 * -Dcom.sun.management.jmxremote.access.file=D:\devTools\ideaWS\xybWorkspace\xyb-total-project\1_java-basic-knowledge\src\com\xyb\a3jmx\jmx.access
 *
 * 用户对应的密码
 * -Dcom.sun.management.jmxremote.password.file=D:\devTools\ideaWS\xybWorkspace\xyb-total-project\1_java-basic-knowledge\src\com\xyb\a3jmx\jmx.password
 * -Dfile.encoding=GBK
 */
public class A1JmxServer {

    public static void main(String[] args) throws Exception {
        // 1、获取MBeanServer
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();

        /*
        * 2、创建可以被MBeanServer接受的ObjectName，ObjectName 是 MBean 的唯一标示，一个 MBeanServer 不能有重复。
        * 完整的格式「自定义命名空间:type=自定义类型,name=自定义名称」。当然你可以只声明 type ，不声明 name。
        * */
        ObjectName userName = new ObjectName("com.xyb.a3jmx:type=xyb,name=userBean");

        // 3、创建资源
        User user = new User("张三", 18);

        // 4、进行注册
        ObjectInstance objectInstance = server.registerMBean(user, userName);


        // 5、注册一个动态MBean
        ObjectName myObjName = new ObjectName("com.xyb.a3jmx:type=xyb,name=myDynamicBean");
        MyDynamicMBean mmBean = new MyDynamicMBean(new DynamicEntity());
        server.registerMBean(mmBean, myObjName);


        System.out.println("资源已注册");

        Thread.sleep(60 * 60 * 1000);
    }

}

















