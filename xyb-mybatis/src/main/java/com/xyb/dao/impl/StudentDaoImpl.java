package com.xyb.dao.impl;

import com.xyb.utils.MybatisUtils;
import com.xyb.dao.StudentDao;
import com.xyb.entity.Student;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public List<Student> getAllStu() {
        System.out.println("com.xyb.dao.impl.StudentDaoImpl，这是自己写的实现类");
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        List<Student> list = sqlSession.selectList("com.xyb.dao.StudentDao.getAllStu");
        sqlSession.commit();
        sqlSession.close();
        return list;
    }

    @Override
    public int getNextMaxId() {
        return 0;
    }

    @Override
    public int insertStu(Student stu) {
        return 0;
    }

    @Override
    public Student testIfWhereParamIsObject(Student stu) {
        return null;
    }

    @Override
    public Student testWhereParamIsObject(Student stu) {
        return null;
    }

    @Override
    public List<Student> testForeach1(List<Integer> list) {
        return null;
    }

    @Override
    public List<Student> testForeach2(List<Student> list) {
        return null;
    }
}
