package com.seproject.service;

import com.seproject.dao.FileDao;
import com.seproject.domain.User;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BasicBLServiceTest {
    User user;
    BasicBLService service=new BasicBLService();
    public  BasicBLServiceTest (){
        this.user=new User();
        user.setLevel(7);
        user.setCredit(2.21);
        user.setPassword("password0002");
        user.setPhoneNumber("13770690055");
        user.setUserName("Jerry");
        service.setFileDao(new FileDao ());
        BasicUtilService s0=new BasicUtilService();
        s0.setFileDao(new FileDao());
        service.setBasicUtilService(s0);
        service.setT(new User());
    }


        @Test
    public void add() {
       service.add(user);
    }

    @Test
    public void delete() {
       // service.delete("13770690058");
    }

    @Test
    public void update() {
      //  service.update(user);
    }

    @Test
    public void findByKey() {

      // System.out.println(((User)service.findByKey("13770690058")).getUserName());

    }

    @Test
    public void search() {
        ArrayList<User> ars=service.search("userName","Jerry");
        System.out.println(ars.get(0).getPhoneNumber()+"///***///"+ars.get(1).getPhoneNumber())
;
    }
}