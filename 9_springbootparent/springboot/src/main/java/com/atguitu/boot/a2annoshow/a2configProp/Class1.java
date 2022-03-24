package com.atguitu.boot.a2annoshow.a2configProp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component(value = "class1")
@ConfigurationProperties(prefix = "class1") // 从properties文件中读取前缀为"class1"的，并且后面与字段名一样的值，赋值给对应的属性。
public class Class1 {

    private String clsField1;

    private Integer clsField2;

    public Class1() {
    }

    public Class1(String clsField1, Integer clsField2) {
        this.clsField1 = clsField1;
        this.clsField2 = clsField2;
    }

    public String getClsField1() {
        return clsField1;
    }

    public void setClsField1(String clsField1) {
        this.clsField1 = clsField1;
    }

    public Integer getClsField2() {
        return clsField2;
    }

    public void setClsField2(Integer clsField2) {
        this.clsField2 = clsField2;
    }


    @Override
    public String toString() {
        return "Class1{" +
                "clsField1='" + clsField1 + '\'' +
                ", clsField2=" + clsField2 +
                '}';
    }
}
