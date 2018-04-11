package com.seproject.domain;

import com.seproject.service.Key;
import com.seproject.service.ValueType;

import java.util.ArrayList;

public class Test_Gift {
    //用于测试。
    @Key(type = ValueType.STRING)
    private String giftID;
    private ArrayList<User> list;
    private User user;

    public String getGiftID(){return giftID;}

    public void setGiftID(String giftID){this.giftID=giftID;}

    public User getUser(){return user;}

    public void setUser(User user){this.user=user;}

    public ArrayList<User> getList() {
        return list;
    }

    public void setList(ArrayList<User> list) {
        this.list = list;
    }
}
