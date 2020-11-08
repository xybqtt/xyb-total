package com.xyb.dao;

import com.xyb.entity.FnParam;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 用于测试给方法传递参数
 */
public interface FnParamDao {

    /**
     * 测试传递简单类型的参数(基本类型、String)
     * @param id
     * @return
     */
    public FnParam getDataById(int id);


    /**
     * 测试多个参数传递，用@Param的值和mapper文件中的#{}匹配
     * @param id
     * @param type
     * @return
     */
    public FnParam getDataByIdAndType(@Param("id") int id, @Param("type") String type);

    /**
     * 使用对象作为方法参数
     * @param fnParam
     * @return
     */
    public FnParam getDataByObj(FnParam fnParam);

    /**
     * 根据位置传递参数
     * @param id
     * @param value
     * @return
     */
    public FnParam getDataByLoc(int id, String value);

    /**
     * 传递参数为map
     * @param myMap
     * @return
     */
    public FnParam getDataByMap(Map<String, Object> myMap);


    /**
     * 测试${}
     * @param colName
     * @param id
     * @param type
     * @return
     */
    public FnParam getDataTest$(@Param("colName") String colName, @Param("id") int id, @Param("type") String type);



}
