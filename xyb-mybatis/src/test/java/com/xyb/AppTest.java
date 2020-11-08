package com.xyb;


import com.github.pagehelper.PageHelper;
import com.xyb.dao.FnParamDao;
import com.xyb.dao.FnResultDao;
import com.xyb.dao.StudentDao;
import com.xyb.dao.impl.StudentDaoImpl;
import com.xyb.entity.FnParam;
import com.xyb.entity.FnResult;
import com.xyb.entity.Student;
import com.xyb.utils.MybatisUtils;
import org.apache.ibatis.session.*;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppTest {

    //
    @Test
    public void test01() throws IOException {
        //  注意，这种方法并没有用到接口StudentDao，有它和没它一样能操作数据库
        justMybatis();
        // 通过实现类和mybatis操作数据库结合
        useImpl();
        // mybatis生成代理类，操作数据库
        useMybatisProxy();


        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        // 测试参数为对象的if标签的使用
        Student student = new Student();
        student.setId(1001);
        student = dao.testIfWhereParamIsObject(student);
        System.out.println(student.toString());
        System.out.println();

        // 测试参数为对象的where标签的使用
        student = new Student();
        student.setId(1002);
        student = dao.testWhereParamIsObject(student);
        System.out.println(student.toString());


        // 测试foreach标签，list中的元素是简单类型
        List<Integer> idList = new ArrayList<Integer>();
        idList.add(1001);
        idList.add(1002);
        List<Student> stuList1 = dao.testForeach1(idList);
        stuList1.forEach(stu -> System.out.println(stu.toString()));
        System.out.println();


        // 测试foreach标签，list中的元素是对象
        List<Student> stuList2 = dao.testForeach2(stuList1);
        stuList2.forEach(stu -> System.out.println(stu.toString()));
        System.out.println();


        sqlSession.close();
    }

    /**
     * 测试如何在接收查询到的返回结果
     */
    @Test
    public void test03(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        FnResultDao dao = sqlSession.getMapper(FnResultDao.class);
        // 测试接收简单类型返回值
        String str = dao.getSimpleTypeById(1);
        System.out.println(str);
        System.out.println();

        // 测试引用类型返回值
        FnResult fnResult = dao.getDataById(1);
        System.out.println(fnResult.toString());
        System.out.println();

        // 测试返回map
        Map<String, Object> map = dao.getMapById(1);
        Set<String> keySet = map.keySet();
        keySet.forEach(key -> System.out.println(key + ":" + map.get(key)));


        // 测试使用resultMap
        fnResult = dao.useResultMap("2");
        System.out.println(fnResult.toString());
        System.out.println();



    }


    /**
     * 测试如何在mapper.xml中给方法传参
     */
    @Test
    public void test02(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        FnParamDao dao = sqlSession.getMapper(FnParamDao.class);
        // 测试传递简单参数
        FnParam fnParam = dao.getDataById(1);
        System.out.println(fnParam.toString());
        System.out.println();

        // 测试传递多个参数
        fnParam = dao.getDataByIdAndType(1, "string");
        System.out.println(fnParam.toString());
        System.out.println();

        // 测试传递java对象
        fnParam.setId(2);
        fnParam = dao.getDataByObj(fnParam);
        System.out.println(fnParam.toString());
        System.out.println();

        // 测试根据参数位置传递参数
        fnParam = dao.getDataByLoc(2, "String");
        System.out.println(fnParam.toString());
        System.out.println();

        // 测试根据参数位置传递参数
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", 2);
        map.put("type", "string");
        fnParam = dao.getDataByMap(map);
        System.out.println(fnParam.toString());
        System.out.println();

        // 测试${}
        fnParam = dao.getDataTest$("id", 2, "string");
        System.out.println(fnParam.toString());
        System.out.println();

    }

    /**
     * 这个用的是mybatis动态代理，生成StudentDao的代理实现类，通过代理实现类操作数据库，
     * 本质和自己写一个实现类一样，但是是mybatis帮我们生成的，简单。
     */
    private void useMybatisProxy() {

        System.out.println("3、这是通过mybatis生成代理操作的数据库");
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        saveProxyFile(dao.getClass(), "mybatis生成的代理类");

        // 分页功能
        PageHelper.startPage(0, 3);
        List<Student> list = dao.getAllStu();

        list.forEach(stu -> System.out.println(stu.toString()));

        sqlSession.commit();
        sqlSession.close();

    }

    /**
     * 使用StudentDao的实现类StudentDaoImpl中的方法，在实现类的方法中使用了mybatis
     */
    private void useImpl() {
        System.out.println("2、这是通过实现类和mybatis操作数据库");
        StudentDao dao = new StudentDaoImpl();
        List<Student> list = dao.getAllStu();

        list.forEach(stu -> System.out.println(stu.toString()));
        System.out.println();
        System.out.println();

    }

    /**
     * 仅用mybatis操作数据库，不需要用StudentDao接口
     */
    private void justMybatis() {

        System.out.println("1、只使用mybatis进行操作数据库");
        // 4、【重要】获取SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        // 5、【重要】指定要执行sql的语句标识，sql映射文件的namespace + "." + 标签的id
        String sqlId = "com.xyb.dao.StudentDao" + "." + "getAllStu";
        // 6、执行sql
        List<Student> list = sqlSession.selectList(sqlId);
        list.forEach(stu -> System.out.println(stu.toString()));

        Student stu = new Student();
        stu.setId(sqlSession.selectOne("com.xyb.dao.StudentDao.getNextMaxId"));
        stu.setName("wangWu");
        stu.setEmail("ww@163.com");
        stu.setAge(29);
        int insertNum = sqlSession.insert("com.xyb.dao.StudentDao.insertStu", stu);
        // mybatis默认是不自动提交事务，此处手动提交
        sqlSession.commit();
        System.out.println("插入了" + insertNum + "条。");

        // 7、关闭SqlSession
        sqlSession.close();
        System.out.println();
        System.out.println();

    }

    /**
     * 保存生成的代理文件
     * @param proxyCls
     * @param filePath 文件名
     */
    private static void saveProxyFile(Class proxyCls, String... filePath) {
        if (filePath.length == 0) {
            System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        } else {
            FileOutputStream out = null;
            try {
                byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", proxyCls.getInterfaces());
                out = new FileOutputStream(filePath[0] + "$Proxy0.class");
                out.write(classFile);
                System.out.println("生成的文件已保存在此模块下，名为" + filePath + "$Proxy0.class");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.flush();
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
