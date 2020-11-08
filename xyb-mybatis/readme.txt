mybatis中文文档地址https://mybatis.org/mybatis-3/zh/index.html
git地址https://github.com/mybatis/mybatis-3
视频地址https://www.bilibili.com/video/av455721018?p=49


在mybatis.xml中的
<settings>
    <setting name="logImpl" value="STDOUT_LOGGING"/>
</settings>，如果不加这个，就可以省略mybatis的日志。

mybatis框架是一个sql映射框架：
    1)sql mapper映射：sql映射(orm思想)
    可以把数据库表中的一行数据，映射为一个java对象。一行数据可以看做
一个java对象。操作这个对象，就相当于操作表中的数据；
    2)Data Access Objects(DAOs)：数据访问，对数据库执行crud操作。
    
mybatis提供了哪些功能：
    1)创建Connection、Statement、ResultSet的能力；
    2)执行sql语句的能力，开发人员只用提供sql；
    3)循环sql，把sql的结果转为List集合的能力；
    4)关闭资源的能力，不用手动关闭Connection、Statement、ResultSet。

mybatis实现步骤：
    1)新建student表；
    2)加入mybatis和mysql驱动的坐标；
    3)创建实体类Student，一个实例用于存放从数据库查询出来的一条数据；
    4)创建持久层的dao接口StudentDao，定义操作数据库的方法；
    5)创建一个mybatis使用的配置文件，sql映射文件，用于写sql的，写在
resources目录下，StudentDao.xml；
    6)创建mybatis的主配置文件：
一个项目就一个主配置文件，提供了数据库的连接信息和sql映射文件的位置
信息，例mybatis.xml；
    7)创建使用mybatis类，通过mybatis访问数据库。

主要类的介绍：
    1)org.apache.ibatis.io.Resources：负责读取主配置文件
InputStream in = Resources.getResourceAsStream("mybatis.xml");
    2)org.apache.ibatis.session.SqlSessionFactoryBuilder：用于创建SqlSessionFactory对象
SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    3)SqlSessionFactory：重量级对象，程序创建一个对象耗时较长，占用资源较多，在整个
项目中，有一个就够用了。
SqlSessionFactory：接口，DefaultSqlSessionFactory为其实现类，作用是获取
SqlSession对象，SqlSession sqlSession = factory.openSession()。
openSession()和openSession(false)不会自动提交事务，openSession(true)会自动
提交事务。
    4)SqlSession：
接口，定义了操作数据的方法，如selectOne()、selectList()、insert()等，DefaultSqlSession
是其实现类。
这个不是线程安全的，需要在方法内部使用，在执行sql语句前，使用openSession()获取
SqlSession对象，在执行完sql语句后，需要关闭它，执行SqlSession.close()，这样可以
保证它的使用是线程安全的。

mybatis操作原理：
    mybatis本质是通过StudentDao.xml里面的namespace和id来选择使用哪一个sql，此时没用到StudentDao.java
接口。
    但是这样使用起来不方便，使用接口更符合我们平常的操作，我们可以写一个实现类StudentDaoImpl.java，
在实现类里面使用mybatis进行操作，同样需要namespace和id，但每个方法的实现中使用mybatis操作数据库的
过程大同小异，只是namespace和id有所有区别。
    所以，mybatis帮我们做了这部分工作，它通过接口生成代理类，这样我们就不用写实现类了，其实生成的代理类
的运行过程和我们自己写的实现类很相似，如com.xyb.dao.impl.StudentDaoImpl。

pageHelper插件使用：
    1)导入
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.9</version>
    </dependency>
    2)在mybatis的主配置文件添加插件，在envirments标签前面加
        <plugins><plugin interceptor="com.github.pagehelper.PageInterceptor"/></plugins>
    3)在查询之前，添加如下代码：
        PageHelper.start(int 第几页, int 一页多少数据);

   