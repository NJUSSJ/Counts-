package com.seproject.domain.chart;

import java.util.ArrayList;

public class Chart2 {
    //单个用户在不同任务中获得的钱数
    String uid="";
    ArrayList<String> mid=new ArrayList<String>();
    ArrayList<Double> money=new ArrayList<Double>();

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public ArrayList<String> getMid() {
        return mid;
    }

    public void setMid(ArrayList<String> mid) {
        this.mid = mid;
    }

    public ArrayList<Double> getMoney() {
        return money;
    }

    public void setMoney(ArrayList<Double> money) {
        this.money = money;
    }
}
