package com.atguigu.spring5.a1create;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 集合信息注入
 */
public class UserCollectionDI {

    private List<Course> courseList;

    private String[] usedPhoneArr;

    private List<String> shcoolList;

    private Map<String, String> accountMap;

    private Set<String> friendSet;

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public void setUsedPhoneArr(String[] usedPhoneArr) {
        this.usedPhoneArr = usedPhoneArr;
    }

    public void setShcoolList(List<String> shcoolList) {
        this.shcoolList = shcoolList;
    }

    public void setAccountMap(Map<String, String> accountMap) {
        this.accountMap = accountMap;
    }

    public void setFriendSet(Set<String> friendSet) {
        this.friendSet = friendSet;
    }

    @Override
    public String toString() {
        return "UserCollectionDI{" +
                "courseList=" + courseList + "\n" +
                ", usedPhoneArr=" + Arrays.toString(usedPhoneArr) + "\n" +
                ", shcoolList=" + shcoolList + "\n" +
                ", accountMap=" + accountMap + "\n" +
                ", friendSet=" + friendSet + "\n" +
                '}';
    }
}
