package com.seproject.service;
import com.seproject.common.SearchCategory;
import com.seproject.domain.Collection;
import com.seproject.domain.CollectionResult;
import com.seproject.domain.Mission;
import com.seproject.domain.statisticsData.*;
import com.seproject.domain.User;
import com.seproject.service.blService.BasicBLService;
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
    BasicBLService<CollectionResult> service1=Factory.getBasicBLService(new CollectionResult());
    BasicBLService<User> service2= Factory.getBasicBLService(new User());
    BasicBLService<Mission> service3=Factory.getBasicBLService(new Mission());
    FactoryService factoryService;


    public WorkerData getWorkerData(String userID){
        System.out.println("service1IsNull:"+(service1==null));
        System.out.println("userIDisNull:"+(userID==null));
        CollectionResult re=service1.findByKey("haha");
        System.out.println(re==null);
        ArrayList<CollectionResult> collections=service1.search("uid", SearchCategory.EQUAL,userID);
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

        //获取任务积分的最大值和平均值
        ArrayList<Double> creditMax=new ArrayList<Double>();
        ArrayList<Double>creditAvg =new ArrayList<Double>();
        for(int i=0;i<missionName.size();i++){
            Mission mission1=service3.findByKey(missionName.get(i));
            ArrayList<CollectionResult> collection1=service1.search("mid",SearchCategory.EQUAL,missionName.get(i));
            if(collection1.size()>0) {
                creditAvg.add(mission1.getReward() / collection1.size());
            }else {
                creditAvg.add(-1.0);// 防止除数为0
            }
            double maxcredit=0;
            for(int j=0;j<collection1.size();j++){
                if(collection1.get(j).getCredit()>maxcredit){
                    maxcredit=collection1.get(j).getCredit();
                }
            }
            creditMax.add(maxcredit);
        }

        WorkerData w=factoryService.workerDataFactory(missionSum,finishedMissionNum,unfinishedMissionNum,credit,missionName,creditRank,workerSum,creditSum,creditMax,creditAvg);

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
        ArrayList<CollectionResult> collections=service1.getAllObjects();
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
                for(CollectionResult c:collections){
                    if(c.getUid().equals(userID)){
                        temp++;
                    }
                }
                participantSum.add(temp);
                creditSum.add(m.getReward());
                if(temp!=0) {
                    creditAvg.add(m.getReward() / temp);
                }else{
                    creditAvg.add(0.0);
                }

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
     * adminLevelName：等级名称
     * adminLevelWorkerNum:不同级别工人数
     * adminUserName：所有用户的用户名 需要排序
     * adminMissionName：所有任务名 需要排序
     * adminUserMissionQuality：每一项为[userName,mission,quality]的数组
     */
    public AdminData  getAdminData(){
        ArrayList<User> users=service2.getAllObjects();
        int userSum=users.size();
        int workerNum=0,adminNum=0,starterNum=0;
        int maxLevel=0;
        for(User user:users){
            switch (user.getCategory()){
                case 0:adminNum++;break;
                case 1:starterNum++;break;
                case 2:workerNum++;
                    if(user.getLevel()>maxLevel&&user.getCategory()==2){
                        maxLevel=user.getLevel();
                    }
                     break;
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

        //求用户等级最大值。
        ArrayList<Integer> levels=new ArrayList<Integer>();
        ArrayList<String> levelNames=new ArrayList<String>();
        for(int i=0;i<maxLevel;i++){
            levels.add(0);
            levelNames.add("Level"+(Integer.toString(i+1)));
        }
        for(User user:users){
            if(user.getCategory()==2){
                int k0=user.getLevel();
                if(k0>0)
                levels.set(k0-1,levels.get(k0-1)+1);
            }
        }

        ArrayList<String> userNames=new ArrayList<String>();
        for(User user:users){
            userNames.add(user.getUserName());
        }
        for(int i=0;i<userNames.size()-1;i++){
            for(int j=0;j<userNames.size();j++){
                if(userNames.get(i).compareTo(userNames.get(j))>0){
                    String temp=userNames.get(i);
                    userNames.set(i,userNames.get(j));
                    userNames.set(j,temp);
                }
            }
        }

        ArrayList<String> missionNames=new ArrayList<String>();
        for(Mission m:missions){
            missionNames.add(m.getName());
        }
        for(int i=0;i<missionNames.size()-1;i++){
            for(int j=0;j<missionNames.size();j++){
                if(missionNames.get(i).compareTo(missionNames.get(j))>0){
                    String temp=missionNames.get(i);
                    missionNames.set(i,missionNames.get(j));
                    missionNames.set(j,temp);
                }
            }
        }

        ArrayList<ArrayList<Integer>> details=new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<userNames.size();i++){
            for(int j=0;j<missionNames.size();j++){
                ArrayList<Integer> inner=new ArrayList<Integer>();
                String uName=userNames.get(i),mName=missionNames.get(j);
                CollectionResult c=service1.findByKey(mName+uName);
                if(c!=null) {
                    if(c.getQuality()>=0) {
                        inner.add(i);
                        inner.add(j);
                        System.out.println(mName + uName + "key of colletion");
                        inner.add(c.getQuality());
                        details.add(inner);
                    }
                }
            }
        }
        AdminData data=factoryService.adminDataFactory(userSum,workerNum,adminNum,starterNum,missionSum,finishedMissionNum,ongoingMissionNum,levelNames,levels,userNames,missionNames,details);
        return data;

    }
    public SingleMissionData getSingleMissionData(String missionID){
        int number0=0;
        int number1=0;
        int number2=0;
        int number3=0;
        ArrayList<Double> credit=new ArrayList<Double>();
        ArrayList<Integer> level=new ArrayList<Integer>() ;
        ArrayList<CollectionResult> collections=service1.search("mid",SearchCategory.EQUAL,missionID);
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
