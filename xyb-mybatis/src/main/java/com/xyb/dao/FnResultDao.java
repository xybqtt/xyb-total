package com.xyb.dao;

import com.xyb.entity.FnResult;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 测试sql返回结果如何接收
 */
public interface FnResultDao {

    /**
     *
     * 获取简单类型的返回值(基本类型、String)
     * @param id
     * @return
     */
    public String getSimpleTypeById(@Param("id") int id);

    /**
     * 获取引用类型返回值
     * @param id
     * @return
     */
    public FnResult getDataById(@Param("id") int id);

    /**
     * 返回map，如何接收
     * @param id
     * @return
     */
    public Map<String, Object> getMapById(@Param("id") int id);

    /**
     * 使用resultMap
     * @return
     */
    public FnResult useResultMap(@Param("value") String value);

}
