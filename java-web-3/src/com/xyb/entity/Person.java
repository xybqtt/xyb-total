package com.xyb.entity;

import java.util.List;
import java.util.Map;

public class Person {

    private String name;

    private int age;

    private String[] lovers;

    private List<String> schoolList;

    private Map<String, Object> map;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String[] getLovers() {
        return lovers;
    }

    public void setLovers(String[] lovers) {
        this.lovers = lovers;
    }

    public List<String> getSchoolList() {
        return schoolList;
    }

    public void setSchoolList(List<String> schoolList) {
        this.schoolList = schoolList;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
