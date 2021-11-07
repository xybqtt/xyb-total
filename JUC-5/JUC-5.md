@[TOC](第4章 jvm)

# 0 格式
## 0.0 格式要求
　　换行前要求2个全角空格(html代码不要加全角空格)；
　　html代码后面一定加一行空行；
　　注意点、特点，如果换行，前面加2个全角空格；
　　标题后面需要添加空行；
　　列表全部使用ul标签控制；
　　https://www.cnblogs.com/cndarren/p/14415213.html
　　视频地址：https://www.bilibili.com/video/BV1Kw411Z7dF?p=2&spm_id_from=pageDriver
　　每个大标题之间空2行，每个小标题之间空1行
　　<span style="color: red;"></span>
　　<hr style="height: 10px; background: green;"/>
　　<div style="border: 5px black solid;"><div>

# 1 什么是JUC
## 1.1 JUC简介

　　在java中，线程部分是一个重点。JUC是java.util.concurrent工具包的简称，是一个处理线程的工具包，jdk5开始出现。

## 1.2 进程与线程

　　**进程(Process)**
　　是计算机中的程序关于某数据集合上的一次运行活动，是系统进行资源分配和调度的基本单位，是操作系统结构的基础。在当代面向线程设计的计算机结构中，进程是线程的容器。程序是指令、数据及其组织形式的描述，进程是程序的实体。

　　**线程(Thread)**
　　是操作系统能够进行运算调度的最小单位。它被包含在进程之中，是进程中的实际运作单位。一条线程指的是进程中一个单一顺序的控制流，一个进程中可以并发多个线程，每条线程并发执行不同的任务。

　　**总结来说**
　　进程：指在系统中正在运行的一个应用程序；程序一旦运行就是进程；进程——资源分配的最小单位。
　　线程：系统分配处理器时间资源的基本单元，或者说进程之内独立执行的一个单元执行流。线程——程序执行的最小单位。

## 1.3 线程的状态
### 1.3.1 线程状态枚举类

~~~
Thread.State{
    NEW, (新建)
    RUNNABLE, (准备就绪)
    BLOCKED, (阻塞)
    WAITING, (等待，就像约会，5点她没来，你大概还会等)
    TIME_WAITING, (超时等待，即只等待固定时间，就像班车9点就没有了)
    TERMINATED (终结)
};
~~~

### 1.3.2 wait和sleep的区别

~~~
sleep是Thread的静态方法，wait是Object的方法，任何对象实例都能调用。
sleep不会释放锁，它也不需要占用锁。wait会释放锁，但调用它的前提是当前线程占有锁(即代码要在synchronized中)。
它们都可以被interrupted方法中断。
~~~

## 1.4 并发和并行

　　**串行模式**
　　表示所有的任务都按顺序进行。串行是一次只能取得一个任务，并执行这个任务。

　　**并行模式**
　　并行意味着可以同时取得多个任务，并同时去执行所取得的这些任务。并行模式相当于将一条长长的队列，分成了多条短队列，所以并行缩短了任务队列长度。并行效率从代码层次上依赖于多进程/多线程代码，从硬件角度上则依赖于多核CPU。

　　**并发**
　　并发(concurrent)指的是多个程序可以同时运行的现象，更细化的是多进程可以同时运行或者多指令可以同时运行。但这不是重点，在描述并发的时候也不会去扣这种字眼是否精确，并发的重点在于它是一种现象，并发描述的是多进程同时运行的现象。但实际上，对于单核CPU来说，同一时刻只能运行一个线程。所以，这里的"同时运行"表示的不是真的同一时刻有多个线程运行的现象，这是并行的概念，而是提供一种功能让用户看来多个程序同时运行起来了，但实际上这些程序中的进程不是一直霸占CPU的，而是执行一会停一会。



　　**要解决大并发问题，通常是将大任务分解成多个小任务**，由于操作系统对进程的调度是随机的，所以切分成多个小任务后，可能会从任一小任务处执行。这可能会出现一引起现象：
~~~
可能出现一个小任务执行了多次，还没开始下个任务的情况。这里一般会采用队列或者类似的数据结构来存放各个小任务的成果。
可能出现还没准备好第一步就执行第二步的可能。这里，一般采用多路复用或异步的方式，比如只有准备好产生了事件通知才执行某个任务。
可以多进程/多线程的方式并行执行这些小任务。也可以单进程/单线程执行这些小任务，这时很可能要配合多路复用才能达到较高的效率。
~~~



　　**小结**
　　并发：同一时刻多个线程在访问同一个资源，多个线程对一个点，如春运抢票、电商秒杀；
　　并行：同一时期，多项工作一起执行，之后再汇总，如泡面(烧水、将调料倒桶)；

　　**管程**
　　Monitor操作系统中叫监视器，就是java中的锁，是一种同步机制，保证同一时间，只有一个线程去访问被保护的数据或代码。JVM同步基于的进入和即出，是通过管程对象实现的，每个对象都会有一个Monitor管程对象，管程对象会随java对象一起创建或销毁。管程对象会对临界区进行加锁、解锁。

　　**用户线程和守护线程**
　　用户线程即自定义线程，主线程结束了，用户线程还在运行，jvm存活；
　　守护线程运行在后台如垃圾线程，没有用户线程了，都是守护线程，jvm结束。


# 2 Lock接口
## 2.1 Synchronized
### 2.1.1 Synchronized关键字

~~~
是一种同步锁
修饰一个代码块，被修饰的代码块称为同步语句块，其作用范围是大括号{}括起来的偌，作用的对象是调用这个代码块的对象。
修饰一个方法，被修饰的方法称为同步方法，其作用范围是整个方法，作用的对象是调用这个方法的对象。
    虽然可以使用synchronized来定义方法，但子类重写父类方法的方法并不是同步方法，如果想要同步，依然要在子类方法上加此关键字。
    但如果子类调用的是父类的此方法，则调用的父类方法依然是同步的。
修改一个静态的方法，其作用的范围是整个静态方法，作用的对象是这个类的所有对象；
修改一个类，其作用范围是此关键字括号括起来的部分，作用主要的对象是这个类的所有对象。
~~~



　　**多线程编程步骤**
~~~
创建资源类，在资源类创建属性和操作方法；
在资源类中的操作方法中进行以下操作：判断、业务、通知；
创建多个线程，调用资源类的操作方法；
防止虚假唤醒，即判断wait()时，需要把条件放到while中，而不能放到if()中，因为wait()在哪儿wait，则被唤醒时，就接着从哪儿执行，如果用的是if，则被唤醒时就根本没进行if判断，即虚假唤醒(将A2SyncUse中的while换成if就可以看到虚假唤醒)。
~~~

## 2.2 Lock

　　Lock锁实现提供了比使用同步方法和语句可以获得的更广泛的锁操作。它们允许更灵活的结构，可能具有非常不同的属性，并且可能支持多个关联的条件对象。Lock提供了比synchronized更多的功能。



　　**Lock与synchronized的区别**
~~~
Lock不是java语言内置的，synchronized是java语言的关键字，因此是内置特性。Lock是一个类，通过这个类可以实现同步访问。
Lock和synchronized有一点非常大的不同，采用synchronized不需要用户去手动释放锁，当synchronized方法或者代码块执行完之后，系统会自动让线程释放对锁的占用；而Lock则必须要用户去手动释放锁，如果没有释放，可能出现死锁。
~~~






## 2.5 小结(重点)

　　**Lock和synchronized有以下几点不同**
~~~
Lock是一个接口，而synchronized是java中的关键字，是内置的语言实现；
synchronized在发生异常时，会自动释放线程占有的锁，不会导致死锁；而Lock在异常时，如果没有主动unLock()去释放锁，则很可以造成死锁，因此使用Lock时需要在finally块中释放锁；
Lock可以让等待锁的线程响应中断，而synchronized则不行，它会使等待的线程一直等待下去，不能响应中断；
通过Lock可以知道有没有成功获取锁，synchronized则无法办到；
Lock可以提高多个线程进行读操作的效率。

在性能上来说，如果竞争资源不激烈，两者性能差不多，当激烈(即有大量线程同时竞争)时，Lock性能好于较synchronized。
~~~


# 3 线程间的通信
## 3.1 使用synchronized

　　即通过wait()、notifyAll()方法进行线程间通信。

## 3.2 使用Lock

　　即通过lock.newCondition()的await()、signalAll()方法去进行通信。







