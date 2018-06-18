package com.seproject.domain.chart;

import com.seproject.domain.Mission;

import java.util.ArrayList;

public class Chart3 {
    //每个任务的总收入与总支出统计
    ArrayList<Mission> missions=new ArrayList<Mission>();
    ArrayList<Double> pay=new ArrayList<Double>();
    ArrayList<Double> earn=new ArrayList<Double>();

    public ArrayList<Mission> getMissions() {
        return missions;
    }

    public void setMissions(ArrayList<Mission> missions) {
        this.missions = missions;
    }

    public ArrayList<Double> getPay() {
        return pay;
    }

    public void setPay(ArrayList<Double> pay) {
        this.pay = pay;
    }

    public ArrayList<Double> getEarn() {
        return earn;
    }

    public void setEarn(ArrayList<Double> earn) {
        this.earn = earn;
    }



}
