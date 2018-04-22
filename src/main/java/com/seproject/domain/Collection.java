package com.seproject.domain;

import com.seproject.service.Key;

import java.util.ArrayList;

public class Collection {
    @Key
    private String cid;
    private String userPhoneNumber;
    private String missionName;
    private ArrayList<ImageInfo> infoList;
    private int state;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public ArrayList<ImageInfo> getInfoList() {
        return infoList;
    }

    public void setInfoList(ArrayList<ImageInfo> infoList) {
        this.infoList = infoList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
