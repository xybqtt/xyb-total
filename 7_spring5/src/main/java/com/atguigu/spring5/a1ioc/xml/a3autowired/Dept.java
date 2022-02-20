package com.atguigu.spring5.a1ioc.xml.a3autowired;

public class Dept  {

    private String name;

    public void setName(String name) {
        name = name;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "name='" + name + '\'' +
                '}';
    }
}
