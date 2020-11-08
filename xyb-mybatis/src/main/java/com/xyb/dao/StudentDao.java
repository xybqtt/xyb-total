package com.xyb.dao;

import com.xyb.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// 操作student表
public interface StudentDao {

    public List<Student> getAllStu();

    /**
     * 得到当前最大id+1后的值
     * @return
     */
    public int getNextMaxId();

    /**
     * 插入一条数据
     * @param stu
     * @return 插入的条数
     */
    public int insertStu(Student stu);


    /**
     * 测试if标签，其中参数是引用类型
     * @param stu
     * @return
     */
    public Student testIfWhereParamIsObject(Student stu);


    /**
     * 测试where标签，其中参数是引用类型
     * @param stu
     * @return
     */
    public Student testWhereParamIsObject(Student stu);

    /**
     * 测试foreach标签，主要用于循环数组、集合，
     * 测试list中的标签是简单类型
     *
     * @param list
     * @return
     */
    public List<Student> testForeach1(List<Integer> list);

    /**
     * 测试foreach标签，主要用于循环数组、集合，
     * 测试list中的标签是不是简单类型
     *
     * @param list
     * @return
     */
    public List<Student> testForeach2(List<Student> list);
}
