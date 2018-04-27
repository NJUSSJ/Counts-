package com.seproject.service;

import com.seproject.domain.*;
import com.seproject.domain.StatisticsData.*;
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
            int adminUserSum,
            int adminWorkerNum,
            int adminNum,
            int adminStarterNum,
            int adminMissionSum,
            int adminFinishedMissionNum,
            int adminOngoingMissionNum,
            ArrayList<String> adminLevelName,
            ArrayList<Integer> adminLevelWorkerNum,
            ArrayList<String> adminUserName,
            ArrayList<String> adminMissionName,
            ArrayList<ArrayList<String>> adminUserMissionQuality
    ){
        AdminData adminData=new AdminData();
        adminData.adminUserSum=adminUserSum;
        adminData.adminWorkerNum=adminWorkerNum;
        adminData.adminNum=adminNum;
        adminData.adminStarterNum=adminStarterNum;
        adminData.adminMissionSum=adminMissionSum;
        adminData.adminFinishedMissionNum=adminFinishedMissionNum;
        adminData.adminOngoingMissionNum=adminOngoingMissionNum;
        adminData.adminLevelName=adminLevelName;
        adminData.adminLevelWorkerNum=adminLevelWorkerNum;
        adminData.adminUserName=adminUserName;
        adminData.adminMissionName=adminMissionName;
        adminData.adminUserMissionQuality=adminUserMissionQuality;
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
            double  creditSum,
            ArrayList<Double> maxCredit,
            ArrayList<Double> avg
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
        workerData.workerAverageCredit=avg;
        workerData.workerMaxCredit=maxCredit;
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

    public User userFactory(
            String phoneNumber,
            String userName,
            String password,
            double credit,
            int category,
            int level,
            String description

    ){
        User user=new User();
        user.setCategory(category);
        user.setLevel(level);
        user.setPassword(password);
        user.setCredit(credit);
        user.setUserName(userName);
        user.setPhoneNumber(phoneNumber);
        user.setDescription(description);
        return user;
    }
}
