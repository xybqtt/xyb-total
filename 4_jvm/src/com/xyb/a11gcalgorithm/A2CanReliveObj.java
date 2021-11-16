package com.xyb.a11gcalgorithm;

/**
 * 验证对象可复活的情况：即对象的finalization机制。
 * finalize()方法中实现复活一次，此方法只会被调用1次。
 */
public class A2CanReliveObj {

    public static Object obj;

    public static void main(String[] args) throws InterruptedException {
        obj = new A2CanReliveObj();
        obj = null;

        // 对象应该被清除，但通过finalize()，第一次成功拯救自己
        System.gc();
        System.out.println("进行了第1次GC");

        // 因为Finalizer线程优先级低，暂停2秒，等待它
        Thread.sleep(2000);
        if(obj == null)
            System.out.println("obj is dead");
        else
            System.out.println("obj is still alive");


        obj = null;
        // 此次GC不会调用finalize()方法，因为之前被调用过一次了
        System.gc();
        System.out.println("第2次GC");
        Thread.sleep(2000);
        if(obj == null)
            System.out.println("obj is dead");
        else
            System.out.println("obj is still alive");


    }

    /**
     * 如果此对象没有被引用，只要在此方法中将其重新被引用，即可实现1次复活，被
     * 类变量引用，即可复活。
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("调用当前类重写的finalize()方法");
        obj = this; // 将此对象与GC ROOT相连接，保证可达
    }
}
