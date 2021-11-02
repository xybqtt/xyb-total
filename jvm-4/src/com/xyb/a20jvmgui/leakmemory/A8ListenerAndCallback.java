package com.xyb.a20jvmgui.leakmemory;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 内存泄漏8：监听器和回调
 *      如果客户端在你实现的API中注册回调，却没有显式得取消，那么就会积聚。
 *
 *      需要确保回调立即被当作垃圾回收的最佳方法是只保存它的弱引用，例如将他们保存成为WeakHashMap中的键。
 *
 */
public class A8ListenerAndCallback {

}
