package com.xyb.entity;

import java.util.Date;

public class FnParam {

    private int id;
    private String type;
    private String value;
    private Date nowDate;
    private Date nowDateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        return "FnParam{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                ", nowDate=" + nowDate +
                ", nowDateTime=" + nowDateTime +
                '}';
    }
}
