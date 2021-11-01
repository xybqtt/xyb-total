package com.xyb.a20jvmgui.leakmemory;

/**
 * 内存泄漏6：改变Hash值
 *      当一个对象被存储进HashSet集合中后，就不能修改这个对象中那些参与计算hash值的字段了。
 *      否则，对象修改后的hash值与最初储存到HashSet集合中时的hash值就不同了，在这种情况下即使使用contains方法使用该对象的当前引用作为的参数去HashSet集合中检索对象，也将返回找不至对象的结果，这也会导致无法从HashSet集合中单独删除当前对象，造成内存泄漏。
 *
 *      因为string不可变，我们可以把string存入HashSet，或当作HashMap的key值。
 *
 *      如果想要自定义保存到散列表的时候，需要保证对象的hashCode不可变。
 *
 */
public class A6HashValue {

}
