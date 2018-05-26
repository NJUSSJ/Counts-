package com.seproject.domain;

import com.seproject.common.Key;
import com.seproject.common.Searchable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

@Entity
@Table(name="Collection")
public class Collection {
    @Key
    @Id
    @Column(name="keyId")
    private String keyId;
    @Column(name="uid")
    @Searchable(varName = "uid")
    private String uid;
    @Column(name="mid")
    @Searchable(varName = "mid")
    private String mid;
    @Column(name="infoList")
    private ArrayList<String> infoList = new ArrayList<String>();
    @Column(name="state")
    private int state;//0 保存 1 提交 2 未保存
    @Column(name="credit")
    private double credit;
    @Column(name="quality")
    private int quality;

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }



    public int getState(){return state;}

    public void setState(int state){this.state=state;}

    public ArrayList<String> getInfoList() {
        return infoList;
    }

    public void setInfoList(ArrayList<String> infoList) {
        this.infoList = infoList;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }
}
