package com.seproject.service;

import com.seproject.domain.testDomain.LoginLog;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class MathServiceTest {
  /*  LoginLog log1=new LoginLog();
    LoginLog log2=new LoginLog();
    LoginLog log3=new LoginLog();
    LoginLog log4=new LoginLog();
    LoginLog log5=new LoginLog();
    MathService<LoginLog> ms=new MathService<LoginLog>();
    ArrayList<LoginLog> list1=new ArrayList<LoginLog>();
    ArrayList<LoginLog> list2=new ArrayList<LoginLog>();
    LoginLog[] list={log1,log2,log3,log4,log5};
    @Before
    public void setUp() throws Exception {
        log1.setId(1);
        log2.setId(2);
        log3.setId(3);
        log4.setId(2);
        log5.setId(1);
        list1.add(log1);
        list1.add(log2);
        list1.add(log3);
        list2.add(log4);
        list2.add(log5);
    }

    @Test
    public void union() {
        ArrayList<LoginLog> result=ms.union(null,null);
        for(LoginLog each:result){
            System.out.println(each.getId()+"result");
        }
        for(LoginLog each:list1){
            System.out.println(each.getId()+"list1");
        }
        for(LoginLog each:list2){
            System.out.println(each.getId()+"list2");
        }

        System.out.println("-----Union-----");
    }

    @Test
    public void intersection() {
        ArrayList<LoginLog> result=ms.intersection(list1,list2);
        for(LoginLog each:result){
            System.out.println(each.getId()+"result");
        }
        for(LoginLog each:list1){
            System.out.println(each.getId()+"list1");
        }
        for(LoginLog each:list2){
            System.out.println(each.getId()+"list2");
        }

        System.out.println("-----InterSection-----");
    }

    @Test
    public void difference() {
        ArrayList<LoginLog> result=ms.difference(null,list2);
        for(LoginLog each:result){
            System.out.println(each.getId()+"result");
        }
        for(LoginLog each:list1){
            System.out.println(each.getId()+"list1");
        }
        for(LoginLog each:list2){
            System.out.println(each.getId()+"list2");
        }
        System.out.println("-----Difference-----");
    }

    @Test
    public void upSort() {
        ms.upSort(list,"id");
        for(LoginLog each:list){
            System.out.println(each.getId());
        }
    }

    @Test
    public void downSort() {
        ms.downSort(list,"id");
        for(LoginLog each:list){
            System.out.println(each.getId());
        }
    }*/
}