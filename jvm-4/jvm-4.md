@[TOC](第4章 jvm)

# 0 格式
## 0.0 格式要求
　　换行前要求2个全角空格(html代码不要加全角空格)；
　　html代码后面一定加一行空行；
　　问答句，答句前面加2个全角空格；
　　注意点、特点，如果换行，前面加2个全角空格；
　　标题和后面的内容不用空行；
    
　　视频地址：https://www.bilibili.com/video/BV1PJ411n7xZ?p=6&spm_id_from=pageDriver

## 0.1 推荐书
　　《深入理解java虚拟机》

## 0.2 用到的工具
　　JClassLib：idea插件、查看编译后的class文件；view -> show bytecode with jclasslib；对应javap -v A.class命令；

# 1 JVM与Java体系结构
## 1.1 java及JVM简介
　　Java虚拟机根本不关心运行在其内部的程序到底是使用何种编程语言编写的，它只关心“字节码”文件。也就是说Java虚拟机拥有语言无关性，并不会单纯地与Java语言“终身绑定”，只要其他编程语言的编译结果满足并包含Java虚拟机的内部指令集、符号表以及其他的辅助信息，它就是一个有效的字节码文件，就能够被虚拟机所识别并装载运行。
　　字节码：
　　1.我们平时说的java字节码，指的是用java语言编译成的字节码。准确的说任何能在jvm平台上执行的字节码格式都是一样的。所以应该统称为：jvm字节码。
　　2.不同的编译器，可以编译出相同的字节码文件，字节码文件也可以在不同的JVM上运行。
　　3.Java虚拟机与Java语言并没有必然的联系，它只与特定的二进制文件格式—Class文件格式所关联，Class文件中包含了Java虚拟机指令集（或者称为字节码、Bytecodes）和符号表，还有一些其他辅助信息。

　　多语言混合编程
　　1.Java平台上的多语言混合编程正成为主流，通过特定领域的语言去解决特定领域的问题是当前软件开发应对日趋复杂的项目需求的一个方向。
　　2.试想一下，在一个项目之中，并行处理用Clojure语言编写，展示层使用JRuby/Rails，中间层则是Java，每个应用层都将使用不同的编程语言来完成，而且，接口对每一层的开发者都是透明的，各种语言之间的交互不存在任何困难，就像使用自己语言的原生API一样方便，因为它们最终都运行在一个虚拟机之上。
　　3.对这些运行于Java虚拟机之上、Java之外的语言，来自系统级的、底层的支持正在迅速增强，以JSR-292为核心的一系列项目和功能改进（如Da Vinci Machine项目、Nashorn引擎、InvokeDynamic指令、java.lang.invoke包等），推动Java虚拟机从“Java语言的虚拟机”向 “多语言虚拟机”的方向发展。

## 1.2 虚拟机与Java虚拟机
　　虚拟机：就是一台虚拟的计算机。它是一款软件，用来执行一系列虚拟计算机指令。大体上，虚拟机可以分为系统虚拟机和程序虚拟机。
　　大名鼎鼎的Visual Box，Mware就属于系统虚拟机，它们完全是对物理计算机的仿真，提供了一个可运行完整操作系统的软件平台。
　　程序虚拟机的典型代表就是Java虚拟机，它专门为执行单个计算机程序而设计，在Java虚拟机中执行的指令我们称为Java字节码指令。
　　无论是系统虚拟机还是程序虚拟机，在上面运行的软件都被限制于虚拟机提供的资源中。

　　Java虚拟机：
　　1.Java虚拟机是一台执行Java字节码的虚拟计算机，它拥有独立的运行机制，其运行的Java字节码也未必由Java语言编译而成。
　　2.JVM平台的各种语言可以共享Java虚拟机带来的跨平台性、优秀的垃圾回器，以及可靠的即时编译器。
　　3.Java技术的核心就是Java虚拟机（JVM，Java Virtual Machine），因为所有的Java程序都运行在Java虚拟机内部。

　　作用：
　　Java虚拟机就是二进制字节码的运行环境，负责装载字节码到其内部，解释/编译为对应平台上的机器指令执行。每一条Java指令，Java虚拟机规范中都有详细定义，如怎么取操作数，怎么处理操作数，处理结果放在哪里。

　　特点：
　　1.一次编译，到处运行；
　　2.自动内存管理；
　　3.自动垃圾回收功能。

　　JVM的位置
　　![avatar](./pictures/1jvm/1-1.png)

　　JVM是运行在操作系统之上的，它与硬件没有直接的交互
　　![avatar](./pictures/1jvm/1-2.png)

## 1.3 JVM的整体结构
　　![avatar](./pictures/1jvm/1-3.png)
　　HotSpot VM是目前市面上高性能虚拟机的代表作之一。它采用解释器与即时编译器并存的架构。
　　
## 1.4 Java代码执行流程
　　![avatar](./pictures/1jvm/1-4.png)

## 1.5 JVM的架构模型
　　Java编译器输入的指令流基本上是一种基于栈的指令集架构，另外一种指令集架构则是基于寄存器的指令集架构。
　　这两种架构之间的区别：
　　基于栈式架构的特点：
　　1.设计和实现更简单，适用于资源受限的系统；
　　2.避开了寄存器的分配难题：使用零地址指令方式分配；
　　3.指令流中的指令大部分是零地址指令，其执行过程依赖于操作栈。指令集更小，编译器容易实现；
　　4.不需要硬件支持，可移植性更好，更好实现跨平台。
　　
　　基于寄存器架构的特点：
　　1.典型的应用是x86的二进制指令集：比如传统的PC以及Android的Davlik虚拟机；
　　2.指令集架构则完全依赖硬件，可移植性差；
　　3.性能优秀和执行更高效；
　　4.花费更少的指令去完成一项操作；
　　5.在大部分情况下，基于寄存器架构的指令集往往都以一地址指令、二地址指令和三地址指令为主，而基于栈式架构的指令集却是以零地址指令为主。
　　
　　运行1条指令一般需要3个内容：数据源地址、进行的操作、数据目标地址，比如将ax寄存器的值放到bx中，对于寄存器架构，我们只用1条指令"mov bx, ax"就可以执行，但是我们需要知道3个内容；对于栈式架构，我们每次操作的源数据地址或目标数据地址必定有一个是栈顶元素，所以我们只用知道"操作+源地址"或"操作+目标地址"，所以指令集小，但是进行1个操作，需要多条指令才能实现，先把ax内容压栈，再弹出栈顶元素到bx。
　　
## 1.6 JVM的生命周期
　　虚拟机的启动：
　　Java虚拟机的启动是通过引导类加载器（bootstrap class loader）创建一个初始类（initial class）来完成的，这个类是由虚拟机的具体实现指定的。
　　
　　虚拟机的执行：
　　1.一个运行中的Java虚拟机有着一个清晰的任务：执行Java程序；
　　2.程序开始执行时他才运行，程序结束时他就停止；
　　3.执行一个所谓的Java程序的时候，真真正正在执行的是一个叫做Java虚拟机的进程。
　　
　　虚拟机的退出，有如下的几种情况：
　　1.程序正常执行结束；
　　2.程序在执行过程中遇到了异常或错误而异常终止；
　　3.由于操作系统用现错误而导致Java虚拟机进程终止；
　　4.某线程调用Runtime类或system类的exit方法，或Runtime类的halt方法，并且Java安全管理器也允许这次exit或halt操作；
　　5.除此之外，JNI（Java Native Interface）规范描述了用JNI Invocation API来加载或卸载 Java虚拟机时，Java虚拟机的退出情况。

## 1.7 JVM的发展历程
### 1.7.1 Sun Classic VM
　　1.早在1996年Java1.0版本的时候，Sun公司发布了一款名为sun classic VM的Java虚拟机，它同时也是世界上第一款商用Java虚拟机，JDK1.4时完全被淘汰。
　　2.这款虚拟机内部只提供解释器。现在还有及时编译器，因此效率比较低，而及时编译器会把热点代码缓存起来，那么以后使用热点代码的时候，效率就比较高。
　　3.如果使用JIT编译器，就需要进行外挂。但是一旦使用了JIT编译器，JIT就会接管虚拟机的执行系统。解释器就不再工作。解释器和编译器不能配合工作。
　　4.现在hotspot内置了此虚拟机。

### 1.7.2 Exact VM
<ol>
    <li>为了解决上一个虚拟机问题，jdk1.2时，Sun提供了此虚拟机。</li>
    <li>Exact Memory Management：准确式内存管理
        <ul>
            <li>也可以叫Non-Conservative/Accurate Memory Management</li>
            <li>虚拟机可以知道内存中某个位置的数据具体是什么类型。</li>
        </ul>
    </li>
    <li>具备现代高性能虚拟机的维形
        <ul>
            <li>热点探测；</li>
            <li>编译器与解释器混合工作模式。</li>
        </ul>
    </li>
    <li>只在solaris平台短暂使用，其他平台上还是classic vm，英雄气短，终被Hotspot虚拟机替换</li>
</ol>

### 1.7.3 HotSpot VM
<ol>
    <li>HotSpot历史
        <ul>
            <li>最初由一家名为“Longview Technologies”的小公司设计</li>
            <li>1997年，此公司被sun收购；2009年，Sun公司被甲骨文收购。</li>
            <li>JDK1.3时，HotSpot VM成为默认虚拟机</li>
        </ul>
    </li>
    <li>目前Hotspot占有绝对的市场地位，称霸武林。
        <ul>
            <li>不管是现在仍在广泛使用的JDK6，还是使用比例较多的JDK8中，默认的虚拟机都是HotSpot</li>
            <li>Sun / Oracle JDK 和 OpenJDK 的默认虚拟机</li>
            <li>因此本课程中默认介绍的虚拟机都是HotSpot，相关机制也主要是指HotSpot的Gc机制。（比如其他两个商用虚机都没有方法区的概念）</li>
        </ul>
    </li>
    <li>从服务器、桌面到移动端、嵌入式都有应用；</li>
    <li>名称中的HotSpot指的就是它的热点代码探测技术
        <ul>
            <li>通过计数器找到最具编译价值代码，触发即时编译或栈上替换</li>
            <li>通过编译器与解释器协同工作，在最优化的程序响应时间与最佳执行性能中取得平衡</li>
        </ul>
    </li>
</ol>

### 1.7.4 JRockit
<ol>
    <li>专注于服务器端应用
        <ul>
            <li>它可以不太关注程序启动速度，因此JRockit内部不包含解析器实现，全部代码都靠即时编译器编译后执行。</li>
        </ul>
    </li>
    <li>大量的行业基准测试显示，JRockit JVM是世界上最快的JVM
        <ul>
            <li>使用JRockit产品，客户已经体验到了显著的性能提高（一些超过了70%）和硬件成本的减少（达50%）</li>
        </ul>
    </li>
    <li>优势：全面的Java运行时解决方案组合
        <ul>
            <li>JRockit面向延迟敏感型应用的解决方案JRockit Real Time提供以毫秒或微秒级的JVM响应时间，适合财务、军事指挥、电信网络的需要</li>
            <li>MissionControl服务套件，它是一组以极低的开销来监控、管理和分析生产环境中的应用程序的工具。</li>
        </ul>
    </li>
    <li>2008年，JRockit被oracle收购；</li>
    <li>Oracle表达了整合两大优秀虚拟机的工作，大致在JDK8中完成。整合的方式是在HotSpot的基础上，移植JRockit的优秀特性；</li>
    <li>高斯林：目前就职于谷歌，研究人工智能和水下机器人</li>
</ol>

### 1.7.5 IBM的J9
<ol>
    <li>全称：IBM Technology for Java Virtual Machine，简称IT4J，内部代号：J9；</li>
    <li>市场定位与HotSpot接近，服务器端、桌面应用、嵌入式等多用途VM；</li>
    <li>广泛用于IBM的各种Java产品；</li>
    <li>目前，有影响力的三大商用虚拟机之一，也号称是世界上最快的Java虚拟机；</li>
    <li>2017年左右，IBM发布了开源J9VM，命名为openJ9，交给EClipse基金会管理，也称为Eclipse OpenJ9。</li>
</ol>

### 1.7.6 KVM和CDC / CLDC Hotspot
<ol>
    <li>Oracle在Java ME产品线上的两款虚拟机为：CDC/CLDC HotSpot Implementation VM；</li>
    <li>KVM（Kilobyte）是CLDC-HI早期产品；</li>
    <li>目前移动领域地位尴尬，智能机被Android和iOS二分天下；</li>
    <li>KVM简单、轻量、高度可移植，面向更低端的设备上还维持自己的一片市场
        <ul>
            <li>智能控制器、传感器；</li>
            <li>老人手机、经济欠发达地区的功能手机。</li>
        </ul>
    </li>
    <li>所有的虚拟机的原则：一次编译，到处运行。</li>
</ol>

### 1.7.7 Azul VM
<ol>
    <li>前面三大"高性能Java虚拟机"使用在通用硬件平台上这里Azul VW和BEA Liquid VM是与特定硬件平台绑定、软硬件配合的专有虚拟机；</li>
    <li>高性能Java虚拟机中的战斗机；</li>
    <li>Azul VM是Azul Systems公司在HotSpot基础上进行大量改进，运行于Azul Systems公司的专有硬件Vega系统上的Java虚拟机；</li>
    <li>每个Azul VM实例都可以管理至少数十个CPU和数百GB内存的硬件资源，并提供在巨大内存范围内实现可控的GC时间的垃圾收集器、专有硬件优化的线程调度等优秀特性；</li>
    <li>2010年，AzulSystems公司开始从硬件转向软件，发布了自己的Zing JVM，可以在通用x86平台上提供接近于Vega系统的特性。</li>
</ol>

### 1.7.8 Liquid VM
<ol>
    <li>高性能Java虚拟机中的战斗机；</li>
    <li>BEA公司开发的，直接运行在自家Hypervisor系统上；</li>
    <li>Liquid VM即是现在的JRockit VE（Virtual Edition），Liquid VM不需要操作系统的支持，或者说它自己本身实现了一个专用操作系统的必要功能，如线程调度、文件系统、网络支持等；</li>
    <li>随着JRockit虚拟机终止开发，Liquid vM项目也停止了。</li>
</ol>

### 1.7.9 Apache Harmony
<ol>
    <li>Apache也曾经推出过与JDK1.5和JDK1.6兼容的Java运行平台Apache Harmony；</li>
    <li>它是IBM和Intel联合开发的开源JVM，受到同样开源的OpenJDK的压制，Sun坚决不让Harmony获得JCP认证，最终于2011年退役，IBM转而参与OpenJDK；</li>
    <li>虽然目前并没有Apache Harmony被大规模商用的案例，但是它的Java类库代码吸纳进了Android SDK。</li>
</ol>

### 1.7.10 Micorsoft JVM
<ol>
    <li>微软为了在IE3浏览器中支持Java Applets，开发了Microsoft JVM；</li>
    <li>只能在Windows平台下运行。但确是当时Windows下性能最好的Java VM；</li>
    <li>1997年，Sun以侵犯商标、不正当竞争罪名指控微软成功，赔了Sun很多钱。微软WindowsXP SP3中抹掉了其VM。现在Windows上安装的jdk都是HotSpot。</li>
</ol>

### 1.7.11 Taobao JVM
<ol>
    <li>由AliJVM团队发布。阿里，国内使用Java最强大的公司，覆盖云计算、金融、物流、电商等众多领域，需要解决高并发、高可用、分布式的复合问题。有大量的开源产品；</li>
    <li>基于OpenJDK 开发了自己的定制版本AlibabaJDK，简称AJDK。是整个阿里Java体系的基石；</li>
    <li>基于OpenJDK Hotspot VM发布的国内第一个优化、深度定制且开源的高性能服务器版Java虚拟机
        <ul>
            <li>创新的GCIH（GC invisible heap）技术实现了off-heap，即将生命周期较长的Java对象从heap中移到heap之外，并且GC不能管理GCIH内部的Java对象，以此达到降低GC的回收频率和提升GC的回收效率的目的；</li>
            <li>GCIH中的对象还能够在多个Java虚拟机进程中实现共享；</li>
            <li>使用crc32指令实现JVM intrinsic 降低JNI 的调用开销；</li>
            <li>PMU hardware 的Java profiling tool 和诊断协助功能；</li>
            <li>针对大数据场景的ZenGc；</li>
        </ul>
    </li>
    <li>taobao vm应用在阿里产品上性能高，硬件严重依赖intel的cpu，损失了兼容性，但提高了性能
        <ul>
            <li>目前已经在淘宝、天猫上线，把oracle官方JvM版本全部替换了。</li>
        </ul>
    </li>
</ol>

### 1.7.12 Dalvik VM
<ol>
    <li>谷歌开发的，应用于Android系统，并在Android2.2中提供了JIT，发展迅猛；</li>
    <li>Dalvik VM只能称作虚拟机，而不能称作“Java虚拟机”，它没有遵循 Java虚拟机规范，不能直接执行Java的Class文件；</li>
    <li>基于寄存器架构，不是jvm的栈架构；</li>
    <li>执行的是编译以后的dex（Dalvik Executable）文件。执行效率比较高
        <ul>
            <li>它执行的dex（Dalvik Executable）文件可以通过class文件转化而来，使用Java语法编写应用程序，可以直接使用大部分的Java API等；</li>
        </ul>
    </li>
    <li>Android 5.0使用支持提前编译（Ahead of Time Compilation，AoT）的ART VM替换Dalvik VM。</li>
</ol>

### 1.7.13 Graal VM
<ol>
    <li>2018年4月，oracle Labs公开了Graal VM，号称 "Run Programs Faster Anywhere"，野心勃勃。与1995年java的"write once，run anywhere"遥相呼应；</li>
    <li>Graal VM在HotSpot VM基础上增强而成的跨语言全栈虚拟机，可以作为"任何语言"的运行平台使用。语言包括：Java、Scala、Groovy、Kotlin；C、C++、Javascript、Ruby、Python、R等；</li>
    <li>支持不同语言中混用对方的接口和对象，支持这些语言使用已经编写好的本地库文件；</li>
    <li>工作原理是将这些语言的源代码或源代码编译后的中间格式，通过解释器转换为能被Graal VM接受的中间表示。Graal VM提供Truffle工具集快速构建面向一种新语言的解释器。在运行时还能进行即时编译优化，获得比原生编译器更优秀的执行效率；</li>
    <li>如果说HotSpot有一天真的被取代，Graal VM希望最大。但是Java的软件生态没有丝毫变化。</li>
</ol>

# 2 类加载子系统
## 2.1 内存结构概述
<ol>
    <li>Class文件</li>
    <li>类加载子系统</li>
    <li>运行时数据区
        <ul>
            <li>方法区</li>
            <li>堆</li>
            <li>程序计数器</li>
            <li>虚拟机栈</li>
            <li>本地方法栈</li>
        </ul>
    </li>
    <li>执行引擎</li>
    <li>本地方法接口</li>
    <li>本地方法库</li>
</ol>

　　简图
　　![avatar](./pictures/2classloader/2-1.png)
　　
　　完整图
　　![avatar](./pictures/2classloader/2-2.png)

## 2.2 类加载器与类的加载过程
　　类加载器子系统作用
　　1.类加载器子系统负责从文件系统或者网络中加载Class文件，class文件在文件开头有特定的文件标识。
　　2.ClassLoader只负责class文件的加载，至于它是否可以运行，则由Execution Engine决定。
　　3.加载的类信息存放于一块称为方法区的内存空间。除了类的信息外，方法区中还会存放运行时常量池信息，可能还包括字符串字面量和数字常量（这部分常量信息是Class文件中常量池部分的内存映射）
　　![avatar](./pictures/2classloader/2-3.png)

　　类加载器ClassLoader角色
　　1.class file存在于本地硬盘上，可以理解为设计师画在纸上的模板，而最终这个模板在执行的时候是要加载到JVM当中来根据这个文件实例化出n个一模一样的实例。
　　2.class file加载到JVM中，被称为DNA元数据模板，放在方法区。
　　3.在.class文件->JVM->最终成为元数据模板，此过程就要一个运输工具（类装载器Class Loader），扮演一个快递员的角色。
　　
### 2.2.1 加载阶段(Loading)
　　1.通过一个类的全限定名获取定义此类的二进制字节流；
　　2.将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构；
　　3.在内存中生成一个代表这个类的java.lang.Class对象，作为方法区这个类的各种数据的访问入口。
　　
　　加载class文件的方式：
　　1.从本地系统中直接加载；
　　2.通过网络获取，典型场景：Web Applet；
　　3.从zip压缩包中读取，成为日后jar、war格式的基础；
　　4.运行时计算生成，使用最多的是：动态代理技术；
　　5.由其他文件生成，典型场景：JSP应用；
　　6.从专有数据库中提取.class文件，比较少见；
　　7.从加密文件中获取，典型的防Class文件被反编译的保护措施。

### 2.2.2 链接阶段(Linking)
　　验证(Verify)：
　　1.目的在子确保Class文件的字节流中包含信息符合当前虚拟机要求，保证被加载类的正确性，不会危害虚拟机自身安全。
　　2.主要包括四种验证，文件格式验证，元数据验证，字节码验证，符号引用验证。

　　准备(Prepare)： 
　　1.为类变量分配内存并且设置该类变量的默认初始值，即零值；
　　2.这里不包含用final修饰的static，因为final在编译的时候就会分配了，准备阶段会显式初始化；
　　3.这里不会为实例变量分配初始化，类变量会分配在方法区中，而实例变量是会随着对象一起分配到Java堆中。
　　
　　解析(Resolve)： 
　　1.将常量池内的符号引用转换为直接引用的过程；
　　2.事实上，解析操作往往会伴随着JVM在执行完初始化之后再执行；
　　3.符号引用就是一组符号来描述所引用的目标。符号引用的字面量形式明确定义在《java虚拟机规范》的Class文件格式中。直接引用就是直接指向目标的指针、相对偏移量或一个间接定位到目标的句柄；
　　4.解析动作主要针对类或接口、字段、类方法、接口方法、方法类型等。对应常量池中的CONSTANT_Class_info，CONSTANT_Fieldref_info、CONSTANT_Methodref_info等。
　　TODO：什么是将符号引用转换为直接引用？如main方法中的sout，本质是调用PrintWriter.class的println()方法，在常量池中"#27 = NameAndType        #36:#37        // out:Ljava/io/PrintStream;"，难道是将符号引用直接改为方法区中对应的地址？



### 2.2.3 初始化阶段
　　1.初始化阶段就是执行类构造器方法<clinit>()的过程；
　　2.此方法不需定义，是javac编译器自动收集类中的所有类变量的赋值动作和静态代码块中的语句合并而来；
　　3.构造器方法中指令按语句在源文件中出现的顺序执行；
　　4.<clinit>()不同于类的构造器。关联：构造器是虚拟机视角下的<init>()；
　　5.若该类具有父类，JVM会保证子类的<clinit>()执行前，父类的<clinit>()已经执行完毕；
　　6.虚拟机必须保证一个类的<clinit>()方法在多线程下被同步加锁。
　　
　　注意：类变量的声明可以写在static代码块的下面，因为static代码块是在init阶段进行的，在Linking的prepare阶段，会给所有的类变量设置默认初始值，此时类变量已经存在，再在后面进行static代码时是不会报错的。
　　
## 2.3 类加载器分类
　　JVM支持两种类型的类加载器 。分别为引导类加载器（Bootstrap ClassLoader）和自定义类加载器（User-Defined ClassLoader）。
　　从概念上来讲，自定义类加载器一般指的是程序中由开发人员自定义的一类类加载器，但是Java虚拟机规范却没有这么定义，而是将所有派生于抽象类ClassLoader的类加载器都划分为自定义类加载器。
　　无论类加载器的类型如何划分，在程序中我们最常见的类加载器始终只有3个，这里的四者之间的关系是包含关系。不是上层下层，也不是子父类的继承关系。如下所示：
　　![avatar](./pictures/2classloader/2-4.png)

### 2.3.1 虚拟机自带的加载器
　　启动类加载器(引导类加载器，Bootstrap ClassLoader)
　　1.这个类加载使用C/C++语言实现的，嵌套在JVM内部；
　　2.它用来加载Java的核心库（JAVA_HOME/jre/lib/rt.jar、resources.jar或sun.boot.class.path路径下的内容），用于提供JVM自身需要的类；
　　3.并不继承自java.lang.ClassLoader，没有父加载器；
　　4.加载扩展类和应用程序类加载器，并指定为他们的父类加载器；
　　5.出于安全考虑，Bootstrap启动类加载器只加载包名为java、javax、sun等开头的类。

　　扩展类加载器(Extension ClassLoader)
　　1.Java语言编写，由sun.misc.Launcher$ExtClassLoader实现；
　　2.派生于ClassLoader类；
　　3.父类加载器为启动类加载器；
　　4.从java.ext.dirs系统属性所指定的目录中加载类库，或从JDK的安装目录的jre/1ib/ext子目录（扩展目录）下加载类库。如果用户创建的JAR放在此目录下，也会自动由扩展类加载器加载。
　　
　　应用程序类加载器(系统类加载器，AppClassLoader)
　　1.java语言编写，由sun.misc.LaunchersAppClassLoader实现；
　　2.派生于ClassLoader类；
　　3.父类加载器为扩展类加载器；
　　4.它负责加载环境变量classpath或系统属性java.class.path指定路径下的类库；
　　5.该类加载是程序中默认的类加载器，一般来说，Java应用的类都是由它来完成加载；
　　6.通过ClassLoader#getSystemclassLoader() 方法可以获取到该类加载器。

### 2.3.2 用户自定义类加载器
　　在Java的日常应用程序开发中，类的加载几乎是由上述3种类加载器相互配合执行的，在必要时，我们还可以自定义类加载器，来定制类的加载方式。 为什么要自定义类加载器？
　　1.隔离加载类；
　　2.修改类加载的方式；
　　3.扩展加载源；
　　4.防止源码泄漏。

　　用户自定义类加载器实现步骤：
　　1.开发人员可以通过继承抽象类ava.lang.ClassLoader类的方式，实现自己的类加载器，以满足一些特殊的需求；
　　2.在JDK1.2之前，在自定义类加载器时，总会去继承ClassLoader类并重写loadClass() 方法，从而实现自定义的类加载类，但是在JDK1.2之后已不再建议用户去覆盖loadclass() 方法，而是建议把自定义的类加载逻辑写在findClass()方法中；
　　3.在编写自定义类加载器时，如果没有太过于复杂的需求，可以直接继承URLClassLoader类，这样就可以避免自己去编写findClass()  方法及其获取字节码流的方式，使自定义类加载器编写更加简洁。
　　
## 2.4 ClassLoader的使用说明
　　ClassLoader类是一个抽象类，其后所有的类加载器都继承自ClassLoader(不包括启动类加载器)。
|方法名称|描述|
|:---|:---|
|getParent()|返回该类加载器的超类加载器|
|loadClass(String name)|加载名称为name的类，返回结果为java.lang.Class类的实例|
|findClass(String name)|查找名称为name的类，返回结果为java.lang.Class类的实例|
|findLoadedClass(String name)|查找名称为name的已经被加载过的类，返回结果为java.lang.Class类的实例|
|defineClass(String name, byte[] b, int off, int len)|把字节数组b中的内容转换为1个java类，返回结果为java.lang.Class类的实例|
|resolveClass(Class<?> c)|录入指定的一个Java类|

　　sun.misc.Launcher 它是一个java虚拟机的入口应用
　　![avatar](./pictures/2classloader/2-5.png)

　　获取ClassLoader的途径
|方法|描述|
|:---|:---|
|class.getClassLoader()|获取当前ClassLoader|
|Thread.currentThread().getContextClassLoader()|获取当前线程上下文的ClassLoader|
|ClassLoader.getSystemClassLoader()|获取系统的ClassLoader|
|DriverManager.getCallerClassLoader()|获取调用者的ClassLoader|

## 2.5 双亲委派机制
　　Java虚拟机对class文件采用的是按需加载的方式，也就是说当需要使用该类时才会将它的class文件加载到内存生成class对象。而且加载某个类的class文件时，Java虚拟机采用的是双亲委派模式，即把请求交由父类处理，它是一种任务委派模式。
　　工作原理：
　　1.如果一个类加载器收到了类加载请求，它并不会自己先去加载，而是把这个请求委托给父类的加载器去执行；
　　2.如果父类加载器还存在其父类加载器，则进一步向上委托，依次递归，请求最终将到达顶层的启动类加载器；
　　3.如果父类加载器可以完成类加载任务，就成功返回，倘若父类加载器无法完成此加载任务，子加载器才会尝试自己去加载，这就是双亲委派模式。
　　![avatar](./pictures/2classloader/2-6.png)
　　
　　优势
<ol>
    <li>避免类的重复加载；</li>
    <li>保护程序安全，防止核心API被随意篡改 ；
        <ul>
            <li>自定义类：java.lang.String；</li>
            <li>自定义类：java.lang.ShkStart（报错：阻止创建 java.lang开头的类）。</li>
        </ul>
    </li>
</ol>

　　沙箱安全机制
　　自定义String类，但是在加载自定义String类的时候会率先使用引导类加载器加载，而引导类加载器在加载的过程中会先加载jdk自带的文件（rt.jar包中java\lang\String.class），报错信息说没有main方法，就是因为加载的是rt.jar包中的string类。这样可以保证对java核心源代码的保护，这就是沙箱安全机制。
　　
## 2.6 其它
　　如何判断两个class对象是否相同？
　　在JVM中表示两个class对象是否为同一个类存在两个必要条件：
　　1.类的完整类名必须一致，包括包名；
　　2.加载这个类的ClassLoader（指ClassLoader实例对象）必须相同。
　　换句话说，在JVM中，即使这两个类对象（class对象）来源同一个Class文件，被同一个虚拟机所加载，但只要加载它们的ClassLoader实例对象不同，那么这两个类对象也是不相等的。

　　对类加载器的引用
　　JVM必须知道一个类型是由启动加载器加载的还是由用户类加载器加载的。如果一个类型是由用户类加载器加载的，那么JVM会将这个类加载器的一个引用作为类型信息的一部分保存在方法区中。当解析一个类型到另一个类型的引用的时候，JVM需要保证这两个类型的类加载器是相同的。
　　
　　类的主动使用和被动使用
　　主动使用，又分为七种情况：
　　1.创建类的实例 
　　2.访问某个类或接口的静态变量，或者对该静态变量赋值 
　　3.调用类的静态方法 
　　4.反射（比如：Class.forName（"com.atguigu.Test"）） 
　　5.初始化一个类的子类 
　　6.Java虚拟机启动时被标明为启动类的类 
　　7.JDK 7 开始提供的动态语言支持：java.lang.invoke.MethodHandle实例的解析结果；　　REF_getStatic、REF_putStatic、REF_invokeStatic句柄对应的类没有初始化，则初始化 
　　除了以上七种情况，其他使用Java类的方式都被看作是对类的被动使用，都不会导致类的初始化。
　　
　　
　　

# 6 堆
　　堆针对一个JVM进程来说是唯一的，也就是一个进程只有一个JVM，但是进程包含多个线程，他们是共享同一堆空间的。
　　![avatar](pictures/6heap/6-1.png)
　　堆的核心概述：
　　1.Java堆区在JVM启动的时候即被创建，其空间大小也就确定了(可以设置空间大小)。是JVM管理的最大一块内存空间。
　　2.一个JVM实例只存在一个堆内存，堆也是Java内存管理的核心区域。
　　3.堆可以处于物理上不连续的内存空间中，但在逻辑上它应该被视为连续的。
　　5.所有的线程共享Java堆，在这里还可以划分线程私有的缓冲区（Thread Local Allocation Buffer，TLAB）。
　　6.所有的对象实例以及数组都应当在运行时分配在堆上。（The heap is the run-time data area from which memory for all class instances and arrays is allocated），数组和对象可能永远不会存储在栈上，因为栈帧中保存引用，这个引用指向对象或者数组在堆中的位置。
　　7.在方法结束后，堆中的对象不会马上被移除，仅仅在垃圾收集的时候才会被移除。
　　8.堆，是GC（Garbage Collection，垃圾收集器）执行垃圾回收的重点区域。


　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
　　
