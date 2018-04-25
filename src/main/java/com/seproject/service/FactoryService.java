package com.seproject.service;

import com.seproject.domain.StatisticsData.AdminData;
import com.seproject.domain.StatisticsData.SingleMissionData;
import com.seproject.domain.StatisticsData.StarterData;
import com.seproject.domain.StatisticsData.WorkerData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FactoryService {
    /**
     * 工厂类，用于直接创建各种各样的对象
     */
    public StarterData starterDataFactory(
            int missionSum,
            int finishedMissionNum,
            int ongoingMissionNum,
            ArrayList<Integer> participantSum,
            ArrayList<Double> creditSum,
            ArrayList<Double> creditAvg,
            ArrayList<String> missionName
    ){
        StarterData starterData=new StarterData();
        starterData.starterMissionSum =missionSum;
        starterData.starterFinishedMissionNum =finishedMissionNum;
        starterData.starterOngoingMissionNum =ongoingMissionNum;
        starterData.starterParticipantSum =participantSum;
        starterData.starterCreditSum =creditSum;
        starterData.starterCreditAvg =creditAvg;
        starterData.starterMissionName =missionName;
        return starterData;
    }

    public AdminData adminDataFactory(
            int userSum,
            int workerNum,
            int adminNum,
            int starterNum,
            int missionSum,
            int finishedMissionNum,
            int ongoingMissionNum
    ){
        AdminData adminData=new AdminData();
        adminData.adminUserSum =userSum;
        adminData.adminWorkerNum =workerNum;
        adminData.adminNum=adminNum;
        adminData.adminStarterNum =starterNum;
        adminData.adminMissionSum =missionSum;
        adminData.adminFinishedMissionNum =finishedMissionNum;
        adminData.adminOngoingMissionNum =ongoingMissionNum;
        return adminData;
    }

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
        workerData.workerCredit =credit;
        workerData.workerCreditRank =creditRank;
        workerData.workerCreditSum =creditSum;
        workerData.workerMissionSum =missionSum;
        workerData.workerFinishedMissionNum =finishedMissionNum;
        workerData.workerUnfinishedMissionNum =unfinishedMissionNum;
        workerData.workerSum=workerSum;
        workerData.workerMissionName =missionName;
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
