package com.seproject.service;
import com.seproject.domain.Collection;
import com.seproject.domain.ImageInfo;
import com.seproject.domain.Mission;
import com.seproject.domain.StatisticsData.*;
import com.seproject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StatisticsService {
    //统计方法Service
    /*
    *包括下列四个基本方法：
    * （1）获取众包工人的统计数据包。
    *  （2）获取众包发起者的统计数据包。
    *  （3）获取系统管理员可见的统计数据包。
    *  （4）获取单个任务的所有统计数据包。
     */
    BasicBLService<Collection> service1=new BasicBLService<Collection>(new Collection());
    BasicBLService<User> service2= new BasicBLService<User>(new User());
    BasicBLService<Mission> service3=new BasicBLService<Mission>(new Mission());
    FactoryService factoryService;


    public WorkerData getWorkerData(String userID){


        ArrayList<Collection> collections=service1.search("uid",SearchCategory.EQUAL,userID);
        User u=service2.findByKey(userID);

        ArrayList<Double> credit=new ArrayList<Double>();
        ArrayList<String> missionName= new ArrayList<String>();
        int missionSum=collections.size();
        int unfinishedMissionNum=0;
        for(int i=0;i<collections.size();i++){
            if(collections.get(i).getState()==0){
                unfinishedMissionNum++;
            }
            credit.add(collections.get(i).getCredit());
            missionName.add(collections.get(i).getMid());
        }
        int finishedMissionNum=missionSum-unfinishedMissionNum;
        double creditSum=u.getCredit();

        ArrayList<User> users=service2.search("category",SearchCategory.EQUAL,"2");
        int creditRank=1;
        for(User user:users){
            if(user.getCredit()>u.getCredit()){
                creditRank++;
            }
        }
        int workerSum=users.size();


        WorkerData w=factoryService.workerDataFactory(missionSum,finishedMissionNum,unfinishedMissionNum,credit,missionName,creditRank,workerSum,creditSum);

        return w;
    }

    /**
     * adminMissionSum:发布的任务总数
     * adminFinishedMissionNum：已结束的任务总数
     * adminOngoingMissionNum:正在进行的任务总数
     * starterParticipantSum:每个任务的参与者总数
     * starterCreditSum：每个任务的总奖励
     * starterCreditAvg：每个任务的平均奖励
     * starterMissionName：每个任务的名称
     */
    public StarterData getStarterData(String userID){
        int missionSum=0,finishedMissionNum=0,ongoingMissionNum=0;
        ArrayList<Integer> participantSum=new ArrayList<Integer>();
        ArrayList<Double> creditSum=new ArrayList<Double>();
        ArrayList<Double> creditAvg=new ArrayList<Double>();
        ArrayList<String> missionName=new ArrayList<String>();
        ArrayList<Mission> missions=service3.getAllObjects();
        ArrayList<Collection> collections=service1.getAllObjects();
        for(Mission m:missions){
            if((m.getRequestorNumber()).equals(userID)){
                missionSum++;
                missionName.add(m.getName());
                if(m.getState()==0){
                    ongoingMissionNum++;
                }else{
                    finishedMissionNum++;
                }
                int temp=0;
                for(Collection c:collections){
                    if(c.getUid().equals(userID)){
                        temp++;
                    }
                }
                participantSum.add(temp);
                creditSum.add(m.getReward());
                creditAvg.add(m.getReward()/temp);

            }
        }
        return factoryService.starterDataFactory(missionSum,finishedMissionNum,ongoingMissionNum,participantSum,creditSum,creditAvg,missionName);
    }

    /**
     * adminUserSum：用户总数
     * adminWorkerNum：工人总数
     * adminNum：管理员总数
     * adminStarterNum：发起者总数
     * adminMissionSum：任务总数
     * adminFinishedMissionNum：已完成任务数
     * adminOngoingMissionNum：未完成任务数
     */
    public AdminData  getAdminData(){
        ArrayList<User> users=service2.getAllObjects();
        int userSum=users.size();
        int workerNum=0,adminNum=0,starterNum=0;
        for(User user:users){
            switch (user.getCategory()){
                case 0:adminNum++;break;
                case 1:starterNum++;break;
                case 2:workerNum++;break;
            }
        }

        ArrayList<Mission> missions=service3.getAllObjects();
        int missionSum=missions.size(),finishedMissionNum=0,ongoingMissionNum=0;
        for(Mission m:missions){
            if(m.getState()==0){
                ongoingMissionNum++;
            }else{
                finishedMissionNum++;
            }
        }
        return factoryService.adminDataFactory(userSum,workerNum,adminNum,starterNum,missionSum,finishedMissionNum,ongoingMissionNum);
    }
    public SingleMissionData getSingleMissionData(String missionID){
        int number0=0;
        int number1=0;
        int number2=0;
        int number3=0;
        ArrayList<Double> credit=new ArrayList<Double>();
        ArrayList<Integer> level=new ArrayList<Integer>() ;
        ArrayList<Collection> collections=service1.search("mid",SearchCategory.EQUAL,missionID);
        for(int i=0;i<collections.size();i++){
           int q= collections.get(i).getQuality();
           switch (q){
               case 0:number0++;
               break;
               case 1:number1++;
               break;
               case 2: number2++;
               break;
               case  3: number3++;
               break;
               default: break;

           }
           credit.add(collections.get(i).getCredit());
           User user=service2.findByKey(collections.get(i).getUid());
           level.add(user.getLevel());
        }
        int numberSum=collections.size();// 总完成人数，包括了无效的人数
        SingleMissionData singleMissionData=factoryService.singleMissionDataFactory(number0,number1,number2,number3,numberSum,credit,level);
        return singleMissionData;

    }


    @Autowired
    public void setFactoryService(FactoryService factoryService){this.factoryService=factoryService;}
}
