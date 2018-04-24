package com.seproject.service;

import com.seproject.domain.StatisticsData.SingleMissionData;
import com.seproject.domain.StatisticsData.WorkerData;
import org.springframework.stereotype.Service;
import com.seproject.domain.*;

import java.util.ArrayList;

@Service
public class FactoryService {
    /**
     * 工厂类，用于直接创建各种各样的对象
     */

    public WorkerData workerDataFactory(
            int missionSum,
            int finishedMissionNum,
            int unfinishedMissionNum,
            ArrayList<Double> credit,
            ArrayList<String> missionName,
            int creditRank,
            int workerSum,
            double  creditSum
    ){
        WorkerData workerData=new WorkerData();
        workerData.credit=credit;
        workerData.creditRank=creditRank;
        workerData.creditSum=creditSum;
        workerData.missionSum=missionSum;
        workerData.finishedMissionNum=finishedMissionNum;
        workerData.unfinishedMissionNum=unfinishedMissionNum;
        workerData.workerSum=workerSum;
        workerData.missionName=missionName;
        return workerData;
    }

    public SingleMissionData singleMissionDataFactory(
            int number0,
            int number1,
            int number2,
            int number3,
            int numberSum,
            ArrayList<Double> credit,
            ArrayList<Integer> level
    ){
        SingleMissionData singleMissionData=new SingleMissionData();

        singleMissionData.credit=credit;
        singleMissionData.level=level;
        singleMissionData.numberSum=numberSum;
        singleMissionData.number0=number0;
        singleMissionData.number1=number1;
        singleMissionData.number2=number2;
        singleMissionData.number3=number3;
        return singleMissionData;
    }

}
