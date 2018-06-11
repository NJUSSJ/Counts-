package com.seproject.domain;

import java.util.ArrayList;

public class GoldMission {
    String keyID="";//mid+i
    String mid=""; //任务ID
    String uid="";
    ArrayList<Integer> pictrueIndex=new ArrayList<Integer>();
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
