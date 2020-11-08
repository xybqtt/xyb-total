package com.xyb.entity;

import java.util.Date;

public class FnResult {

    private int id;
    private String value;
    private Date nowDate;
    private Date nowDateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getNowDate() {
        return nowDate;
    }

    public void setNowDate(Date nowDate) {
        this.nowDate = nowDate;
    }

    public Date getNowDateTime() {
        return nowDateTime;
    }

    public void setNowDateTime(Date nowDateTime) {
        this.nowDateTime = nowDateTime;
    }

    @Override
    public String toString() {
        return "FnResult{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", nowDate=" + nowDate +
                ", nowDateTime=" + nowDateTime +
                '}';
    }
}
