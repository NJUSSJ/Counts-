package com.seproject.domain.chart;

import java.util.ArrayList;

public class Chart1 {
    //单个任务每个工人获得的钱数
    String mid="";
    ArrayList<String> uid=new ArrayList<String>();

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

    ArrayList<Double> money=new ArrayList<Double>();
}
