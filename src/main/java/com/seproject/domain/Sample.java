package com.seproject.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 抽样的得到的样本
 * 可能是不需要持久化的对象
 */
public class Sample {
    private String missionName;

    private ArrayList<Integer> picIndex;

    private ArrayList<String> userId;

    private ArrayList<String> imageInfo;

    private  ArrayList<Integer> quality;

    public void setMissionName(String missionName){
        this.missionName=missionName;
    }

    public String getMissionName(){
        return this.missionName;
    }

    public void setPicIndex(ArrayList<Integer> picIndex){
        this.picIndex=picIndex;
    }

    public ArrayList<Integer> getPicIndex() {
        return picIndex;
    }

    public void setUserId(ArrayList<String> userId){
        this.userId=userId;
    }

    public ArrayList<String> getImageInfo() {
        return imageInfo;
    }

    public void setImageInfo(ArrayList<String> imageInfo){
        this.imageInfo=imageInfo;
    }

    public ArrayList<String> getUserId() {
        return userId;
    }

    public ArrayList<Integer> getQuality() {
        return quality;
    }

    public void setQuality(ArrayList<Integer> quality) {
        this.quality = quality;
    }
}
