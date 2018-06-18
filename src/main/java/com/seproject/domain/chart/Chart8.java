package com.seproject.domain.chart;

import java.util.ArrayList;

public class Chart8 {
    //一次任务中每个用户得到的积分和得分
    String mid = "";
    ArrayList<String> uid = new ArrayList<String>();
    ArrayList<Double> money = new ArrayList<Double>();
    ArrayList<Integer> quality = new ArrayList<Integer>();

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public ArrayList<String> getUid() {
        return uid;
    }

    public void setUid(ArrayList<String> uid) {
        this.uid = uid;
    }

    public ArrayList<Double> getMoney() {
        return money;
    }

    public void setMoney(ArrayList<Double> money) {
        this.money = money;
    }

    public ArrayList<Integer> getQuality() {
        return quality;
    }

    public void setQuality(ArrayList<Integer> quality) {
        this.quality = quality;
    }
}

