package com.seproject.domain.statisticsData;

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

    public int getWorkerMissionSum() {
        return workerMissionSum;
    }

    public void setWorkerMissionSum(int workerMissionSum) {
        this.workerMissionSum = workerMissionSum;
    }

    public int getWorkerFinishedMissionNum() {
        return workerFinishedMissionNum;
    }

    public void setWorkerFinishedMissionNum(int workerFinishedMissionNum) {
        this.workerFinishedMissionNum = workerFinishedMissionNum;
    }

    public int getWorkerUnfinishedMissionNum() {
        return workerUnfinishedMissionNum;
    }

    public void setWorkerUnfinishedMissionNum(int workerUnfinishedMissionNum) {
        this.workerUnfinishedMissionNum = workerUnfinishedMissionNum;
    }

    public ArrayList<Double> getWorkerCredit() {
        return workerCredit;
    }

    public void setWorkerCredit(ArrayList<Double> workerCredit) {
        this.workerCredit = workerCredit;
    }

    public ArrayList<String> getWorkerMissionName() {
        return workerMissionName;
    }

    public void setWorkerMissionName(ArrayList<String> workerMissionName) {
        this.workerMissionName = workerMissionName;
    }

    public ArrayList<Double> getWorkerAverageCredit() {
        return workerAverageCredit;
    }

    public void setWorkerAverageCredit(ArrayList<Double> workerAverageCredit) {
        this.workerAverageCredit = workerAverageCredit;
    }

    public ArrayList<Double> getWorkerMaxCredit() {
        return workerMaxCredit;
    }

    public void setWorkerMaxCredit(ArrayList<Double> workerMaxCredit) {
        this.workerMaxCredit = workerMaxCredit;
    }

    public ArrayList<ArrayList<Integer>> getWorkerMissionCreditQuality() {
        return workerMissionCreditQuality;
    }

    public void setWorkerMissionCreditQuality(ArrayList<ArrayList<Integer>> workerMissionCreditQuality) {
        this.workerMissionCreditQuality = workerMissionCreditQuality;
    }

    public int getWorkerCreditRank() {
        return workerCreditRank;
    }

    public void setWorkerCreditRank(int workerCreditRank) {
        this.workerCreditRank = workerCreditRank;
    }

    public int getWorkerSum() {
        return workerSum;
    }

    public void setWorkerSum(int workerSum) {
        this.workerSum = workerSum;
    }

    public double getWorkerCreditSum() {
        return workerCreditSum;
    }

    public void setWorkerCreditSum(double workerCreditSum) {
        this.workerCreditSum = workerCreditSum;
    }
}