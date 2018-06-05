package com.seproject.domain;

import com.seproject.common.Key;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import java.sql.Date;
import java.util.ArrayList;


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
    private int flag;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Date[] getDate() {
/*        Date[] result=new Date[7];
        for(int i=0;i<7;i++){
            result[i]=Date.valueOf(date.get(i));
        }*/
        return date;
    }

    public void setDate(Date[] date) {
/*        for(int i=0;i<7;i++){
            this.date.set(i,date[i].toString());
        }*/
        this.date=date;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int index) {
        this.flag = index;
    }
}
