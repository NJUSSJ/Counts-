package com.seproject.domain;

import com.seproject.service.Key;
import com.seproject.service.Searchable;

import java.util.ArrayList;

public class Collection {
    @Key
    private String keyId;
    @Searchable(varName = "uid")
    private String uid;
    @Searchable(varName = "mid")
    private String mid;
    private ArrayList<String> infoList = new ArrayList<String>();
    private int state;//0 保存 1 提交 2 未保存
    private double credit;
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
