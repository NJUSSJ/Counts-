package com.seproject.domain.StatisticsData;

import java.util.ArrayList;

public class WorkerData {
    public int missionSum;
    public int finishedMissionNum;
    public int unfinishedMissionNum;
    public ArrayList<Double> credit;// 单个任务获得的积分
    public int creditRank;
    public int workersum;//总工人数
    public double creditSum; //积分总数
/*
    public WorkerData(int missionSum, int finishedMissionNum, int unfinishedMissionNum, ArrayList<Double> credit, int creditRank, int workersum, double creditSum){
        this.missionSum = missionSum;
        this.finishedMissionNum = finishedMissionNum;
        this.unfinishedMissionNum = unfinishedMissionNum;
        this.credit = credit;
        this.creditRank = creditRank;
        this.workersum = workersum;
        this.creditSum = creditSum;
    }
    */
}