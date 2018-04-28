package com.seproject.domain.StatisticsData;

import java.util.ArrayList;

public class AdminData {
    public int adminUserSum;
    public int adminWorkerNum;
    public int adminNum;
    public int adminStarterNum;
    public int adminMissionSum;
    public int adminFinishedMissionNum;
    public int adminOngoingMissionNum;
    public ArrayList<String> adminLevelName;//等级名称 例：["LEVEL1", "LEVEL2", "LEVEL3", "LEVEL4", "LEVEL5", "LEVEL6"]
    public ArrayList<Integer> adminLevelWorkerNum;//不同级别工人数
    public ArrayList<String> adminUserName;//所有用户的用户名 需要排序
    public ArrayList<String> adminMissionName;//所有任务名 需要排序
    public ArrayList<ArrayList<Integer>> adminUserMissionQuality;//每一项为[userName,mission,quality]的数组 按照以上两项的排序
    public int getAdminUserSum() {
        return adminUserSum;
    }

    public void setAdminUserSum(int adminUserSum) {
        this.adminUserSum = adminUserSum;
    }

    public int getAdminWorkerNum() {
        return adminWorkerNum;
    }

    public void setAdminWorkerNum(int adminWorkerNum) {
        this.adminWorkerNum = adminWorkerNum;
    }

    public int getAdminNum() {
        return adminNum;
    }

    public void setAdminNum(int adminNum) {
        this.adminNum = adminNum;
    }

    public int getAdminStarterNum() {
        return adminStarterNum;
    }

    public void setAdminStarterNum(int adminStarterNum) {
        this.adminStarterNum = adminStarterNum;
    }

    public int getAdminMissionSum() {
        return adminMissionSum;
    }

    public void setAdminMissionSum(int adminMissionSum) {
        this.adminMissionSum = adminMissionSum;
    }

    public int getAdminFinishedMissionNum() {
        return adminFinishedMissionNum;
    }

    public void setAdminFinishedMissionNum(int adminFinishedMissionNum) {
        this.adminFinishedMissionNum = adminFinishedMissionNum;
    }

    public int getAdminOngoingMissionNum() {
        return adminOngoingMissionNum;
    }

    public void setAdminOngoingMissionNum(int adminOngoingMissionNum) {
        this.adminOngoingMissionNum = adminOngoingMissionNum;
    }

    public ArrayList<String> getAdminLevelName() {
        return adminLevelName;
    }

    public void setAdminLevelName(ArrayList<String> adminLevelName) {
        this.adminLevelName = adminLevelName;
    }

    public ArrayList<Integer> getAdminLevelWorkerNum() {
        return adminLevelWorkerNum;
    }

    public void setAdminLevelWorkerNum(ArrayList<Integer> adminLevelWorkerNum) {
        this.adminLevelWorkerNum = adminLevelWorkerNum;
    }

    public ArrayList<String> getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(ArrayList<String> adminUserName) {
        this.adminUserName = adminUserName;
    }

    public ArrayList<String> getAdminMissionName() {
        return adminMissionName;
    }

    public void setAdminMissionName(ArrayList<String> adminMissionName) {
        this.adminMissionName = adminMissionName;
    }

    public ArrayList<ArrayList<Integer>> getAdminUserMissionQuality() {
        return adminUserMissionQuality;
    }

    public void setAdminUserMissionQuality(ArrayList<ArrayList<Integer>> adminUserMissionQuality) {
        this.adminUserMissionQuality = adminUserMissionQuality;
    }
}
