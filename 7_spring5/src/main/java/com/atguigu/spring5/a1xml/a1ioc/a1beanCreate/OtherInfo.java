package com.atguigu.spring5.a1xml.a1ioc.a1beanCreate;


import java.util.*;

/**
 * 注入集合类型
 */
public class OtherInfo {

    // 数组
    private String[] courseArr;

    // map
    private Map<String, String> accountMap;

    // set
    private Set<String> programLanSet;

    // List<基本类型>
    private List<String> shcoolList;

    // List<基本类型>，从外部引用
    private List<String> friendList;

    // Properties
    private Properties properties;


    public String[] getCourseArr() {
        return courseArr;
    }

    public void setCourseArr(String[] courseArr) {
        this.courseArr = courseArr;
    }

    public Map<String, String> getAccountMap() {
        return accountMap;
    }

    public void setAccountMap(Map<String, String> accountMap) {
        this.accountMap = accountMap;
    }

    public Set<String> getProgramLanSet() {
        return programLanSet;
    }

    public void setProgramLanSet(Set<String> programLanSet) {
        this.programLanSet = programLanSet;
    }

    public List<String> getShcoolList() {
        return shcoolList;
    }

    public void setShcoolList(List<String> shcoolList) {
        this.shcoolList = shcoolList;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public List<String> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<String> friendList) {
        this.friendList = friendList;
    }


    @Override
    public String toString() {
        return "OtherInfo{" +
                "courseArr=" + Arrays.toString(courseArr) +
                ", accountMap=" + accountMap +
                ", programLanSet=" + programLanSet +
                ", shcoolList=" + shcoolList +
                ", friendList=" + friendList +
                ", properties=" + properties +
                '}';
    }
}
