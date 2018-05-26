package com.seproject.service;

import com.seproject.domain.LoginLog;
import com.seproject.service.blService.BasicBLService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class BasicBLServiceTest {
    LoginLog log1=new LoginLog();
    LoginLog log2=new LoginLog();
    LoginLog log3=new LoginLog();
    LoginLog log4=new LoginLog();
    LoginLog log5=new LoginLog();
    BasicBLService<LoginLog> service=Factory.getBasicBLService(new LoginLog());
    @Before
    public void setUp() throws Exception {
        log1.setId(1);
        log2.setId(2);
        log3.setId(3);
        log4.setId(4);
        log5.setId(5);
        ArrayList<String> list1=new ArrayList<String>(Arrays.asList(new String[]{"1","2"}));
        ArrayList<String> list2=new ArrayList<String>(Arrays.asList(new String[]{"2","3"}));
        ArrayList<String> list3=new ArrayList<String>(Arrays.asList(new String[]{"3","4"}));
        ArrayList<String> list4=new ArrayList<String>();
        ArrayList<String> list5=new ArrayList<String>(Arrays.asList(new String[]{"5","6"}));
        log1.setList(list1);
        log2.setList(list2);
        log3.setList(list3);
        log4.setList(list4);
        log5.setList(list5);
    }

    @After
    public void tearDown() throws Exception {
        service.delete("1");
        service.delete("2");
        service.delete("3");
        service.delete("4");
        service.delete("5");
    }

    @Test
    public void add() {
        assertEquals(RM.SUCCESS,service.add(log1));
        assertEquals(RM.SUCCESS,service.add(log2));
        assertEquals(RM.SUCCESS,service.add(log3));
        assertEquals(RM.SUCCESS,service.add(log4));
        assertEquals(RM.SUCCESS,service.add(log5));
        assertEquals(RM.FAILURE,service.add(log1));
    }

    @Test
    public void delete() {
        assertEquals(RM.SUCCESS,service.delete("5"));
    }

    @Test
    public void update() {
        ArrayList<String> list=new ArrayList<String>(Arrays.asList(new String[]{"1","2","3"}));
        log1.setList(list);
        assertEquals(RM.SUCCESS,service.update(log1));
    }

    @Test
    public void findByKey() {
        service.add(log3);
        assertEquals(true,(service.findByKey("3"))!=null);
    }


    @Test
    public void checkKeyExists() {
        service.add(log3);
        assertEquals(true,(service.checkKeyExists("3")));
    }

    @Test
    public void getAllObjects() {
        service.add(log1);
        service.add(log2);
        service.add(log3);
        service.add(log4);
        assertEquals(4,(service.getAllObjects().size()));
    }
}