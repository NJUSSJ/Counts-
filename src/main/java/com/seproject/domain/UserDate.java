package com.seproject.domain;


import java.sql.Date;

/**
 * 用户签到的日期数组
 */
public class UserDate {
    public UserDate(){}
    public UserDate(String uid){
        this.uid=uid;
        date=new Date[7];
        index=-1;
    }

    private String uid;
    private Date[] date;
    private int index;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Date[] getDate() {
        return date;
    }

    public void setDate(Date[] date) {
        this.date = date;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
