package com.atguigu.spring5.a1xml.a1create;

/**
 * set方法注入
 */
public class UserBasicInfo2 {

    private int age;

    private String idNo;

    private String phone;

    private String nullField;

    private String address;

    public UserBasicInfo2() {
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setNullField(String nullField) {
        this.nullField = nullField;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserBasicInfo2{" +
                "age=" + age +
                ", idNo='" + idNo + '\'' + "\n" +
                ", phone='" + phone + '\'' + "\n" +
                ", nullField='" + nullField + '\'' + "\n" +
                ", address='" + address + '\'' + "\n" +
                '}';
    }
}
