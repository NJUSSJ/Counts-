package com.seproject.domain.StatisticsData;

import java.util.ArrayList;

public class WorkerData {
    public int missionSum;
    public int finishedMissionNum;
    public int unfinishedMissionNum;
    public ArrayList<Double> credit;// 单个任务获得的积分
    public ArrayList<String> missionName; //每个任务的名称
    public int creditRank;
    public int workerSum;//总工人数
    public double  creditSum; //积分总数
}
