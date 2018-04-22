package com.seproject.service;

import com.seproject.dao.FileDao;
import com.seproject.domain.Mission;
import com.seproject.domain.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasicBLServiceTest1 {
     BasicBLService<Mission> service=new BasicBLService<Mission>(new Mission());
     @Test
    public void add() {
        Mission m1=new Mission();
        m1.setName("111");
        service.add(m1);
        Mission m2=new Mission();
        m2.setName("222");
        service.add(m2);
    }
}