package com.seproject.domain.StatisticsData;

import java.util.ArrayList;

public class WorkerData {
    public int workerMissionSum;
    public int workerFinishedMissionNum;
    public int workerUnfinishedMissionNum;
    public ArrayList<Double> workerCredit;// 单个任务获得的积分
    public ArrayList<String> workerMissionName; //每个任务的名称 需要排序(定坐标）
    public ArrayList<Double> workerAverageCredit;//每个任务平均分
    public ArrayList<Double> workerMaxCredit;//每个任务最大分
    public ArrayList<ArrayList<Integer>> workerMissionCreditQuality;//每一项为 [mission,credit,quality]的数组 需排序
    public int workerCreditRank;
    public int workerSum;//总工人数
    public double workerCreditSum; //积分总数
/*
    public WorkerData(int adminMissionSum, int adminFinishedMissionNum, int workerUnfinishedMissionNum, ArrayList<Double> workerCredit, int workerCreditRank, int workersum, double starterCreditSum){
        this.adminMissionSum = adminMissionSum;
        this.adminFinishedMissionNum = adminFinishedMissionNum;
        this.workerUnfinishedMissionNum = workerUnfinishedMissionNum;
        this.workerCredit = workerCredit;
        this.workerCreditRank = workerCreditRank;
        this.workersum = workersum;
        this.starterCreditSum = starterCreditSum;
    }
    */
}