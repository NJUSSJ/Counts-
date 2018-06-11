package com.seproject.domain;

import java.util.ArrayList;

public class SubMission {
    //子任务
      String keyID="";//mid+uid
      String mid=""; //任务ID
      String uid="";
      int seed=0;//种子
      ArrayList<Integer> answers=new ArrayList<Integer>();
      ArrayList<Integer> goldIndex=new ArrayList<Integer>();//金标索引

    public String getKeyID() {
        return keyID;
    }

    public SubMission() {
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

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public ArrayList<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Integer> answers) {
        this.answers = answers;
    }

    public ArrayList<Integer> getGoldIndex() {
        return goldIndex;
    }

    public void setGoldIndex(ArrayList<Integer> goldIndex) {
        this.goldIndex = goldIndex;
    }
}
