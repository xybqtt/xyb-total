package com.atguigu.spring5.a2anno.a1ioc.a1beanCreate;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 注入集合类型
 */
@Component(value = "otherInfo")
public class OtherInfo {

    @Value(value = "男")
    private String sexField;

    public String getSexField() {
        return sexField;
    }

    public void setSexField(String sexField) {
        this.sexField = sexField;
    }


    @Override
    public String toString() {
        return "OtherInfo{" +
                "sexField='" + sexField + '\'' +
                '}';
    }
}
