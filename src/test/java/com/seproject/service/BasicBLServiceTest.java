package com.seproject.service;

import com.seproject.dao.FileDao;
import com.seproject.domain.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BasicBLServiceTest {

    BasicBLService<User> service=new BasicBLService<User>();
    @Before
    public void setUp() throws Exception {
        service.setFileDao(new FileDao());
        BasicUtilService utilService=new BasicUtilService();
        utilService.setFileDao(new FileDao());
        service.setBasicUtilService(utilService);
        service.setT(new User());
    }

    @Test
    public void add() {
        User user1=new User();
        user1.setCredit(12);
        user1.setPhoneNumber("18181");
        user1.setLevel(98);
        user1.setUserName("ssj");
        user1.setPassword("pawd");
        User user2=new User();
        user2.setCredit(12);
        user2.setPhoneNumber("1834181");
        user2.setLevel(98);
        user2.setUserName("ssj");
        user2.setPassword("pawd");
        User user3=new User();
        user3.setCredit(12);
        user3.setPhoneNumber("1812381");
        user3.setLevel(98);
        user3.setUserName("ssj");
        user3.setPassword("pawd");
        service.add(user1);
        service.add(user2);
        service.add(user3);
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }

    @Test
    public void findByKey() {
        System.out.println(service.findByKey("18181"));
    }

    @Test
    public void search() {
      //ArrayList<User> list=service.search("level",SearchCategory.SMALLER_THAN,"1000");
       //*System.out.println(list.get(0).getPhoneNumber());
        //System.out.println(list.get(1).getPhoneNumber());
        //System.out.println(list.get(2).getPhoneNumber());*//*
    }

    @Test
    public void checkKeyExists() {
        System.out.println(service.checkKeyExists("18181"));
        System.out.println(service.checkKeyExists("78"));
    }

    @Test
    public void getAllObjects() {
        ArrayList<User> list=service.getAllObjects();
        System.out.println("size:"+list.size());
        for(User user:list){
            System.out.println(user.getPhoneNumber());
        }
    }

    @Test
    public void setFileDao() {
    }

    @Test
    public void setBasicUtilService() {
    }
}