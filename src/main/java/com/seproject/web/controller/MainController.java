package com.seproject.web.controller;

import com.seproject.common.RM;
import com.seproject.common.SearchCategory;
import com.seproject.domain.*;
import com.seproject.service.Factory;
import com.seproject.service.MainService;
import com.seproject.service.blService.BasicBLService;
import com.seproject.web.parameter.GetLabelMissionParameter;
import com.seproject.web.parameter.MissionParameter;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MainController {
    MainService mainService;
    //用于写新加的主要系统流程
    /**
     * 增加标签式任务、接受子任务、高级工人接受评估式任务
     * 自动评估标签式任务并分配奖励、手动评估标签式任务并分配奖励
     */
    BasicBLService<Mission> missionBasicBLService=Factory.getBasicBLService(new Mission());
    BasicBLService<SubMission> subMissionBasicBLService=Factory.getBasicBLService(new SubMission());
    BasicBLService<Collection> collectionBasicBLService=Factory.getBasicBLService(new Collection());
    BasicBLService<CollectionResult> collectionResultBasicBLService=Factory.getBasicBLService(new CollectionResult());
    @RequestMapping(value = "/addLabelMission")
    @ResponseBody
    public String addLabelMission(String mission){
        JSONObject object=JSONObject.fromObject(mission);
        System.out.println("mission 转 object:"+object);
        Mission m =(Mission) JSONObject.toBean(object,Mission.class);
        missionBasicBLService.add(m);
        mainService.createSubMission(m);
        return RM.SUCCESS.toString();
    }

    @RequestMapping(value = "/getLabelMission")
    @ResponseBody
    public String getLabelMission(String parameter){
        JSONObject object=JSONObject.fromObject(parameter);
        GetLabelMissionParameter para= (GetLabelMissionParameter) JSONObject.toBean(object,GetLabelMissionParameter.class);
        String uid=para.getUid();
        String mid=para.getMid();
        ArrayList<SubMission> subMissions=subMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        ArrayList<Integer> userNum=new ArrayList<Integer>();
        for(SubMission subMission:subMissions){userNum.add(subMission.getUid().size());}
        int index=getMinIndex(userNum);
        subMissions.get(index).getUid().add(uid);
        mainService.createCollection(uid,mid);
        subMissionBasicBLService.update(subMissions.get(index));
        return RM.SUCCESS.toString();
    }

    @RequestMapping(value = "/getGoldMission")
    @ResponseBody
    public String getGoldMission (String parameter){
        JSONObject object=JSONObject.fromObject(parameter);
        GetLabelMissionParameter para= (GetLabelMissionParameter) JSONObject.toBean(object,GetLabelMissionParameter.class);
        String uid=para.getUid();
        String mid=para.getMid();
        boolean res=mainService.getGoldMission(mid,uid);
       if(res==true) {
           return RM.SUCCESS.toString();
       }else{
           return RM.FAILURE.toString();
       }
    }

    private int getMinIndex(ArrayList<Integer> arr){
        int min=100;
        int minIndex=0;
        for(int i=0;i<arr.size();i++){
            if(arr.get(i)<min){
                min=arr.get(i);
                minIndex=i ;
            }
        }
        return minIndex;
    }
@Autowired
    public void setMainService(MainService mainService){this.mainService=mainService;}
}
