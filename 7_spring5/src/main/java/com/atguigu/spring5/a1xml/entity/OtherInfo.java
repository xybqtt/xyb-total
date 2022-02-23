package com.atguigu.spring5.a1xml.entity;


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

    // Properties
    private Properties properties;

    // List<引用类型>，其它的arr、map等注入引用类型也是一样的
    private List<User> maleFriendList;

    // List<引用类型>，其它的arr、map等注入引用类型也是一样的
    private List<User> femaleFriendList;

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

    public List<User> getMaleFriendList() {
        return maleFriendList;
    }

    public void setMaleFriendList(List<User> maleFriendList) {
        this.maleFriendList = maleFriendList;
    }

    public List<User> getFemaleFriendList() {
        return femaleFriendList;
    }

    public void setFemaleFriendList(List<User> femaleFriendList) {
        this.femaleFriendList = femaleFriendList;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "OtherInfo{" +
                "courseArr=" + Arrays.toString(courseArr) +
                ", accountMap=" + accountMap +
                ", programLanSet=" + programLanSet +
                ", shcoolList=" + shcoolList +
                ", properties=" + properties +
                ", maleFriendList=" + maleFriendList +
                ", femaleFriendList=" + femaleFriendList +
                '}';
    }
}
