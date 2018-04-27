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
    public ArrayList<ArrayList<String>> adminUserMissionQuality;//每一项为[userName,mission,quality]的数组 按照以上两项的排序
}
