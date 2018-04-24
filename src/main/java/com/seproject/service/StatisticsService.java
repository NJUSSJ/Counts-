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


    public StarterData getStarterData(String userID){

        return null;
    }
    public AdminData  getAdminData(){
        return null;

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

    /**
     * 根据 Image id 自动将其封装在对应的Collection 里
     */
    public RM fillCollection (ImageInfo imageInfo)
    {
      /*  String temp0[]=imageInfo.getImgid().split("-");
        String missionName=temp0[0];
        String picName0= temp0[1]; //这个属性必须是数字,且从1开始
        String userName= temp0[2];

        Collection collection=service1.findByKey(missionName+userName);
        ArrayList<ImageInfo> infoList=collection.getInfoList();
        infoList.set(Integer.parseInt(picName0)-1,imageInfo);
        collection.setInfoList(infoList);
        service1.update(collection);*/
        return RM.SUCCESS;
    }

    @Autowired
    public void setFactoryService(FactoryService factoryService){this.factoryService=factoryService;}
}
