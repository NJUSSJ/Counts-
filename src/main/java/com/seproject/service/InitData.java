package com.seproject.service;

import com.seproject.domain.*;
import com.seproject.service.blService.BasicBLService;

import java.util.ArrayList;

public class InitData {
    FactoryService factoryService = new FactoryService();
    BasicBLService<User> service1=Factory.getBasicBLService(new User());
    BasicBLService<CollectionResult> collectionResultBasicBLService=Factory.getCollectionResultBasicBLService();
    public void initUserData() {
        ArrayList<User> userArrayList = new ArrayList<User>();
        if(service1.checkKeyExists("13700000000")){
            //若主键已存在，不进行任何操作
        }
        else {
            for (int i = 0; i < 9; i++) {
                User user1 = factoryService.userFactory("1370000000" + i, "father", "123", 1000, 1, 2, "张翔宇你好！");
                User user2 = factoryService.userFactory("1380000000" + i, "father", "123", 1000, 2, 2, "张翔宇你好！");
                service1.add(user1);
                service1.add(user2);
            }
        }
    }

    public void initChartData(){
        for(int i=0;i<20;i++){
            String mid=Integer.toString(i);
            mid="m"+mid;
            for(int j=0;j<50;j++){
                String uid=Integer.toString(j);
                uid="u"+uid;
                CollectionResult cr=new CollectionResult();
                cr.setMid(mid);
                cr.setUid(uid);
                cr.setQuality((int)(Math.random()*10));
                cr.setState(4);
                cr.setCredit(Math.random()*16);
                cr.setRank(1);
            }
        }
    }
}
