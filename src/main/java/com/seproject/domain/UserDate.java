package com.seproject.domain;

import com.seproject.common.Key;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;


/**
 * 用户签到的日期数组
 */
@Entity
@Table(name="userdate")
public class UserDate {
    public UserDate(){}
    public UserDate(String uid){
        this.uid=uid;
        date=new Date[7];
        flag=-1;
    }
    @Key
    @Id
    @Column(name="uid")
    private String uid;
    @Column(name="date",columnDefinition = "blob")
    private Date[] date;
    @Column(name="flag")
    private int flag;//注意，index是mysql的保留字，不能起这种名字

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
        this.date=date;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int index) {
        this.flag = index;
    }
}
