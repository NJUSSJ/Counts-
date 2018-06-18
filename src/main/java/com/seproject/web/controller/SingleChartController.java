package com.seproject.web.controller;

import com.seproject.common.SearchCategory;
import com.seproject.domain.CollectionResult;
import com.seproject.domain.Mission;
import com.seproject.domain.User;
import com.seproject.domain.chart.*;
import com.seproject.service.Factory;
import com.seproject.service.blService.BasicBLService;
import org.hibernate.action.internal.CollectionRecreateAction;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class SingleChartController {
    BasicBLService<CollectionResult> collectionResultBasicBLService=Factory.getBasicBLService(new CollectionResult());
    BasicBLService<Mission> missionBasicBLService=Factory.getBasicBLService(new Mission());
    BasicBLService<User> userBasicBLService=Factory.getBasicBLService(new User()) ;
    //返回各种单张的图表
    @RequestMapping(value = "/singleChart/getChart1")
    @ResponseBody
    public Chart1 getChart1(@RequestBody String mid){

        Chart1 chart1=new Chart1();
        chart1.setMid(mid);
        ArrayList<CollectionResult> crs=collectionResultBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        ArrayList<String> uid=new ArrayList<String>();
        ArrayList<Double> money=new ArrayList<Double>();
        for(CollectionResult cr:crs){
            uid.add(cr.getUid());
            money.add(cr.getCredit());
        }
        chart1.setMoney(money);
        chart1.setUid(uid);
        return chart1;
    }

    @RequestMapping(value = "/singleChart/getChart2")
    @ResponseBody
    public Chart2 getChart2(@RequestBody String uid){
        Chart2 chart2=new Chart2();
        chart2.setUid(uid);
        ArrayList<CollectionResult> crs=collectionResultBasicBLService.search("uid",SearchCategory.EQUAL,uid);
        ArrayList<String> mid=new ArrayList<String>();
        ArrayList<Double> money=new ArrayList<Double>();
        for(CollectionResult cr:crs){
            mid.add(cr.getMid());
            money.add(cr.getCredit());
        }
        chart2.setMid(mid);
        chart2.setMoney(money);
        return chart2;


    }

    @RequestMapping(value = "/singleChart/getChart3")
    @ResponseBody
    public Chart3 getChart3(){
        Chart3 chart3=new Chart3();
        //这张图要计算整个数据库，可能加载出来很慢
        ArrayList<Mission> missions=missionBasicBLService.getAllObjects();
        ArrayList<Double> pay=new ArrayList<Double>();
        ArrayList<Double> earn=new ArrayList<Double>();
        for(Mission m:missions){
            pay.add(m.getReward());
            double x=0;
            ArrayList<CollectionResult> crs=collectionResultBasicBLService.search("mid",SearchCategory.EQUAL,m.getName());
            for(CollectionResult cr:crs){
                x=x+cr.getCredit();
            }
            earn.add(x);
        }
        chart3.setEarn(earn);
        chart3.setMissions(missions);
        chart3.setPay(pay);
       return chart3;
    }

    @RequestMapping(value = "/singleChart/getChart7")
    @ResponseBody
    public Chart7 getChart7(){
        Chart7 chart7=new Chart7();
        ArrayList<User> users=userBasicBLService.getAllObjects();

        ArrayList<String> uid=new ArrayList<String>();
        ArrayList<Double> credit=new ArrayList<Double>();
        for(User u:users){
            if(u.getCategory()>0){
                uid.add(u.getPhoneNumber());
                credit.add(u.getCredit());
            }
        }
        chart7.setCredit(credit);
        chart7.setUid(uid);
        return chart7;
    }

    @RequestMapping(value = "/singleChart/getChart8")
    @ResponseBody
    public Chart8 getChart8(@RequestBody String mid){

        Chart8 chart8=new Chart8();
        chart8.setMid(mid);
        ArrayList<CollectionResult> crs=collectionResultBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        ArrayList<String> uid=new ArrayList<String>();
        ArrayList<Double> money=new ArrayList<Double>();
        ArrayList<Integer> quality=new ArrayList<Integer>();
        for(CollectionResult cr:crs){
            uid.add(cr.getUid());
            money.add(cr.getCredit());
            quality.add(cr.getQuality());
        }
        chart8.setMoney(money);
        chart8.setUid(uid);
        chart8.setQuality(quality);
        return chart8;
    }

}
