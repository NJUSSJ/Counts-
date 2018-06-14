package com.seproject.web.controller;

import com.seproject.common.RM;
import com.seproject.common.SearchCategory;
import com.seproject.domain.*;
import com.seproject.service.Factory;
import com.seproject.service.MainService;
import com.seproject.service.blService.BasicBLService;
import com.seproject.web.parameter.FinishReviewParameter;
import com.seproject.web.parameter.GetLabelMissionParameter;
import com.seproject.web.parameter.UpdateLabelMissionParameter;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
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
    BasicBLService<GoldMission> goldMissionBasicBLService=Factory.getBasicBLService(new GoldMission());

    @RequestMapping(value = "/addLabelMission")
    @ResponseBody
    public String addLabelMission(HttpServletRequest request){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        JSONObject object=JSONObject.fromObject(request.getParameter(""));
        System.out.println("mission 转 object:"+object);
        Mission m =(Mission) JSONObject.toBean(object,Mission.class);
        missionBasicBLService.add(m);
        mainService.createSubMission(m);
        return RM.SUCCESS.toString();
    }

    @RequestMapping(value = "/getLabelMission")
    @ResponseBody
    public String getLabelMission(@RequestBody String parameter){
        JSONObject object=JSONObject.fromObject(parameter);
        GetLabelMissionParameter para= (GetLabelMissionParameter) JSONObject.toBean(object,GetLabelMissionParameter.class);
        String uid=para.getUid();
        String mid=para.getMid();
        ArrayList<SubMission> subMissions=subMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        ArrayList<Integer> userNum=new ArrayList<Integer>();
        for(SubMission subMission:subMissions){userNum.add(subMission.getUid().size());}
        int index=getMinIndex(userNum);
        subMissions.get(index).getUid().add(uid);
        subMissions.get(index).getAnswers().add(new ArrayList<Integer>());
        mainService.createCollection(uid,mid);
        subMissionBasicBLService.update(subMissions.get(index));
        return RM.SUCCESS.toString();
    }

    @RequestMapping(value = "/updateLabelMission")
    @ResponseBody
    /**
     * 更新用户对任务的标注
     */
    public String updateLabelMission(String parameter){
        JSONObject object=JSONObject.fromObject(parameter);
        UpdateLabelMissionParameter para= (UpdateLabelMissionParameter) JSONObject.toBean(object,UpdateLabelMissionParameter.class);
        String uid=para.getUid();
        String mid=para.getMid();
        ArrayList<Integer> list=para.getNums();
        ArrayList<SubMission> subMission=subMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        for(int i=0;i<subMission.size();i++){
            SubMission sub=subMission.get(i);
            ArrayList<String> user=sub.getUid();
            if(user.contains(uid)){
                sub.getAnswers().set(user.indexOf(uid),list);
                subMissionBasicBLService.update(sub);
                return RM.SUCCESS.toString();
            }
        }
        return RM.FAILURE.toString();
    }

    @RequestMapping(value = "/updateGoldMission")
    @ResponseBody
    /**
     * 更新高级用户的标注
     */
    public String updateGoldMission(String parameter){
        JSONObject object=JSONObject.fromObject(parameter);
        UpdateLabelMissionParameter para= (UpdateLabelMissionParameter) JSONObject.toBean(object,UpdateLabelMissionParameter.class);
        String uid=para.getUid();
        String mid=para.getMid();
        ArrayList<Integer> list=para.getNums();
        ArrayList<GoldMission> goldMission=goldMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        for(GoldMission each:goldMission){
            if(each.getUid().equals(uid)){
                each.setResult(list);
                goldMissionBasicBLService.update(each);
                return RM.SUCCESS.toString();
            }
        }
        return RM.FAILURE.toString();
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

    @RequestMapping(value = "/startReview")
    @ResponseBody
    public String startReview(String mid){
        JSONObject jsonObject = JSONObject.fromObject(mainService.getRestPictures(mid));//这里INT 数组是索引
        String ret = jsonObject.toString();
        return ret;
    }

    @RequestMapping(value = "/finishReview")
    @ResponseBody
    public void finishReview(String para){
        JSONObject object=JSONObject.fromObject(para);
        FinishReviewParameter finishReviewParameter= (FinishReviewParameter) JSONObject.toBean(object,FinishReviewParameter.class);

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
