package com.seproject.service;
import com.seproject.domain.Collection;
import com.seproject.domain.StatisticsData.*;
import com.seproject.domain.User;
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
    public WorkerData getWorkerData(String userID){
        WorkerData w=new WorkerData();

        ArrayList<Collection> collections=service1.search("uid",SearchCategory.EQUAL,userID);
        User u=service2.findByKey(userID);
        return w;
    }
    public StarterData getStarterData(String userID){

        return null;
    }
    public AdminData  getAdminData(){
        return null;

    }
    public SingleMissionData getSingleMissionData(String missionID){
        return null;
    }
}
