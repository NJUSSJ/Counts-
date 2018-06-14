package com.seproject.domain;

import com.seproject.common.Key;
import com.seproject.common.Searchable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
@Entity
@Table(name="GoldMission")
public class GoldMission {
    @Key
    @Id
    @Searchable(varName = "keyID")
    @Column(name="keyIid")
    String keyID="";//mid+i
    @Searchable(varName = "mid")
    @Column(name="mid")
    String mid=""; //任务ID
    @Searchable(varName = "uid")
    @Column(name="uid")
    String uid="";

    @Column(name="pictureindex")
    ArrayList<Integer> pictrueIndex=new ArrayList<Integer>();
    @Column(name="result")
    ArrayList<Integer> result=new ArrayList<Integer>();

    public GoldMission(){}
    public String getKeyID() {
        return keyID;
    }

    public void setKeyID(String keyID) {
        this.keyID = keyID;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public ArrayList<Integer> getPictrueIndex() {
        return pictrueIndex;
    }

    public void setPictrueIndex(ArrayList<Integer> pictrueIndex) {
        this.pictrueIndex = pictrueIndex;
    }

    public ArrayList<Integer> getResult() {
        return result;
    }

    public void setResult(ArrayList<Integer> result) {
        this.result = result;
    }
}
