package com.xyb.instanceobj8;

/**
 * new一个类的过程：
 *      　　1.加载类元信息；
 *      　　2.为对象分配内存；
 *      　　3.处理并发问题；
 *      　　4.属性的默认初始化(零值初始化)；
 *      　　5.设置对象头信息；
 *      　　6.属性的显式初始化、代码块中初始化、构造器中初始化。
 */
public class ProcessOfNew1 {

    int id = 1; // 属性显式初始化
    String name;
    Account acct;

    {
        name = "张三"; // 代码块中初始化
    }

    public ProcessOfNew1() {

        // 构造器中初始化
        acct = new Account();
        acct.accountNo = "5";

    }

    class Account {

        String accountNo;

    }
}

