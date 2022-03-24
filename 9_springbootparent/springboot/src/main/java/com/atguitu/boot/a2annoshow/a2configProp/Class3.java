package com.atguitu.boot.a2annoshow.a2configProp;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 此类没有被@Component注解，也没有被EnableConfigurationProperties添加，即不能被ioc管理，所以不能通过容器获取，当然也就不能
 * 通过properties设置属性了。
 */
@ConfigurationProperties(prefix = "class3")
public class Class3 {

    private String clsField1;

    private Integer clsField2;

    public Class3() {
    }

    public Class3(String clsField1, Integer clsField2) {
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
