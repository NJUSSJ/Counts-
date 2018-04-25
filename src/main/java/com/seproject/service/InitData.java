package com.seproject.service;

import com.seproject.domain.*;

import java.util.ArrayList;

public class InitData {
    FactoryService factoryService = new FactoryService();
    BasicBLService<User> service1=new BasicBLService<User>(new User());
    public void initUserData() {
        ArrayList<User> userArrayList = new ArrayList<User>();
        for (int i = 0; i < 9; i++) {
            User user1 = factoryService.userFactory("1370000000" + i, "father", "123", 1000, 1, 2, "张翔宇你好！");
            User user2 = factoryService.userFactory("1380000000" + i, "father", "123", 1000, 2, 2, "张翔宇你好！");
            service1.add(user1);
            service1.add(user2);
        }
    }
}
