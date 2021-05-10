1、视频地址：https://www.bilibili.com/video/BV1Kb411W75N?p=29&spm_id_from=pageDriver。134节是eclipse的使用。

2、JDK、JRE、JVM的关系
    JDK = JRE + 开发工具集(例javac编译工具等)；
    JRE = JVM + Java SE标准类库；

3、为什么要配置java环境变量？为什么必须用JAVA_HOME这种格式？
    1)安装好jdk后如果不配置环境变量，则只能在安装的那个bin目录使用相关命令(如javac)；
    2)可以直接在系统环境变量中插入"目录\bin"来添加环境变量，但是tomcat在运行时会去环境变量中找
    JAVA_HOME这个内容，所以必须使用JAVA_HOME形式；
    3)最后把java的环境变量移到最上面，以免其它的java命令被调用。

4、java程序的运行流程
    1)编写：编写"Helloworld.java"程序；
    2)编译：使用"javac Helloworld.java"编译出相关字节码文件"Helloworld.class"，注意"Helloworld.class"
    中的Helloworld不是java文件名，而是文件中的类名；一个java源文件中有几个类，则会编译出几个cls文件。
    另外，windows不区分大小写；
    3)运行：使用"java Helloworld"运行cls文件，命令不要加.class。

5、java注释：
    1、/*注释*/和/**文档注释*/是不一样的，后面一个可以被jdk提供的工具javadoc解析，生成一套以网页形式体现
    的该程序的说明文档。
    2、文档注释调用命令：javadoc -d dir1 -author -version Helloworld.java，可以生成
    一个dir1的目录，里面的index.html可以显示文档内容，需要类是public。

6、java的api文档下载地址：http://www.oracle.com/technetword/java/javase/downloads/index.html。

7、public注意点：
    1)一个java文件中只能有一个public类；
    2)被public修饰的类的类名必须和java源文件名一致。

8、java中的关键字和保留字：
    关键字：即开发人员不要使用这些字去声明变量
    1)数据类型相关：class、interface、enum、byte、short、int、long、float、double、char、
    boolean、void；
    2)流程控制相关：if、else、switch、case、default、while、do、for、break、continue、return；
    3)访问权限相关：private、protected、public；
    4)定义类、函数、变量修饰符相关：abstract、final、static、synchronized；
    5)类间关系：extends、implements；
    6)类声明、引用、判断实例相关：new、this、super、instanceof；
    7)异常：try、catch、finally、throw、throws；
    8)包：package、import；
    9)其他修饰符：native、strictfp、transient、volatile、assert；
    10)定义数据类型的字面值：true、false、null。

    保留字：现有java版本尚未使用，但以后可以会当做关键字：goto、const。

9、java命名要求和规范：
    要求：
    1)可包含的字符：所有英文字母、0-9、_、$；
    2)数字不能开头；
    3)可以包含关键字和保留字；
    4)区分大小写，无长度限制；
    5)不能含空格。

    规范：
    1)包名全小写；
    2)类、接口：大驼峰(首字母大写)；
    3)变量、方法名：小驼峰(首字母小写)；
    4)常量名：全大写，多单词时，用下划线连接。



