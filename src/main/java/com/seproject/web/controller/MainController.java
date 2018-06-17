package com.seproject.web.controller;

import com.seproject.common.RM;
import com.seproject.common.SearchCategory;
import com.seproject.domain.*;
import com.seproject.service.Factory;
import com.seproject.service.MainService;
import com.seproject.service.blService.BasicBLService;
import com.seproject.web.parameter.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class MainController {
    MainService mainService =new MainService();
    //用于写新加的主要系统流程
    /**
     * 增加标签式任务、接受子任务、高级工人接受评估式任务
     * 自动评估标签式任务并分配奖励、手动评估标签式任务并分配奖励
     */
    BasicBLService<Mission> missionBasicBLService=Factory.getBasicBLService(new Mission());
    BasicBLService<SubLabelMission> subTagMissionBasicBLService=Factory.getBasicBLService(new SubLabelMission());
    BasicBLService<GoldMission> goldMissionBasicBLService=Factory.getBasicBLService(new GoldMission());
    BasicBLService<SubFreeMission> subFreeMissionBasicBLService=Factory.getBasicBLService(new SubFreeMission());
    BasicBLService<Collection> collectionBasicBLService=Factory.getBasicBLService(new Collection());
    BasicBLService<CollectionResult> collectionResultBasicBLService=Factory.getBasicBLService(new CollectionResult());

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
    /**
     * 工人接标签式任务
     */
    public String getLabelMission(@RequestBody String parameter){
        JSONObject object=JSONObject.fromObject(parameter);
        GetMissionParameter para= (GetMissionParameter) JSONObject.toBean(object,GetMissionParameter.class);
        String uid=para.getUid();
        String mid=para.getMid();
        ArrayList<SubLabelMission> subLabelMissions =subTagMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        ArrayList<Integer> userNum=new ArrayList<Integer>();
        for(SubLabelMission subLabelMission : subLabelMissions){userNum.add(subLabelMission.getUid().size());}
        int index=getMinIndex(userNum);
        subLabelMissions.get(index).getUid().add(uid);
        subLabelMissions.get(index).getAnswers().add(new ArrayList<Integer>());
        mainService.createCollection(uid,mid);
        subTagMissionBasicBLService.update(subLabelMissions.get(index));
        return RM.SUCCESS.toString();
    }

    @RequestMapping(value = "/getFreeMission")
    @ResponseBody
    /**
     * 工人接自由式任务
     */
    public String getFreeMission(@RequestBody String parameter){
        JSONObject object=JSONObject.fromObject(parameter);
        GetMissionParameter para= (GetMissionParameter) JSONObject.toBean(object,GetMissionParameter.class);
        String uid=para.getUid();
        String mid=para.getMid();
        ArrayList<SubFreeMission> subFreeMissions =subFreeMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        ArrayList<Integer> userNum=new ArrayList<Integer>();
        for(SubFreeMission subFreeMission : subFreeMissions){userNum.add(subFreeMission.getUid().size());}
        int index=getMinIndex(userNum);
        subFreeMissions.get(index).getUid().add(uid);
        mainService.createCollection(uid,mid);
        subFreeMissionBasicBLService.update(subFreeMissions.get(index));
        return RM.SUCCESS.toString();
    }


    @RequestMapping(value = "/updateLabelMission")
    @ResponseBody
    /**
     * 更新用户对标签式任务的标注
     */
    public String updateLabelMission(@RequestBody String parameter){
        JSONObject object=JSONObject.fromObject(parameter);
        UpdateLabelMissionParameter para= (UpdateLabelMissionParameter) JSONObject.toBean(object,UpdateLabelMissionParameter.class);
        String uid=para.getUid();
        String mid=para.getMid();
        ArrayList<Integer> list=para.getNums();
        ArrayList<SubLabelMission> subLabelMission =subTagMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        for(int i = 0; i< subLabelMission.size(); i++){
            SubLabelMission sub= subLabelMission.get(i);
            ArrayList<String> user=sub.getUid();
            if(user.contains(uid)){
                sub.getAnswers().set(user.indexOf(uid),list);
                subTagMissionBasicBLService.update(sub);
                //修改状态为已经提交
                CollectionResult cr=collectionResultBasicBLService.findByKey(mid+uid);
                cr.setState(1);
                collectionResultBasicBLService.update(cr);
                return RM.SUCCESS.toString();
            }
        }
        return RM.FAILURE.toString();
    }

    @RequestMapping(value = "/updateFreeMission")
    @ResponseBody
    /**
     * 更新用户对自由式任务的标注
     */
    public String updateFreeMission(@RequestBody String parameter){
        JSONObject object=JSONObject.fromObject(parameter);
        UpdateFreeMissionParameter para= (UpdateFreeMissionParameter) JSONObject.toBean(object,UpdateFreeMissionParameter.class);
        String uid=para.getUid();
        String mid=para.getMid();
        Collection collection=collectionBasicBLService.findByKey(uid+mid);
        collection.setInfoList(para.getInfo());
        collectionBasicBLService.update(collection);
        return RM.SUCCESS.toString();
    }

    @RequestMapping(value = "/updateGoldMission")
    @ResponseBody
    /**
     * 更新高级用户的标注
     */
    public String updateGoldMission(@RequestBody String parameter){
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

                CollectionResult cr=collectionResultBasicBLService.findByKey(mid+uid);
                cr.setState(1);
                collectionResultBasicBLService.update(cr);
                return RM.SUCCESS.toString();
            }
        }
        return RM.FAILURE.toString();
    }

    /**
     * 高级工人接金标任务
     */
    @RequestMapping(value = "/getGoldMission")
    @ResponseBody
    public String getGoldMission (@RequestBody String parameter){
        JSONObject object=JSONObject.fromObject(parameter);
        GetMissionParameter para= (GetMissionParameter) JSONObject.toBean(object,GetMissionParameter.class);
        String uid=para.getUid();
        String mid=para.getMid();
        boolean res=mainService.getGoldMission(mid,uid);
       if(res) {
           return RM.SUCCESS.toString();
       }else{
           return RM.FAILURE.toString();
       }
    }

    @RequestMapping(value = "/startReview")
    @ResponseBody
    public String startReview(@RequestBody String mid){
        JSONObject jsonObject = JSONObject.fromObject(mainService.getRestPictures(mid));//这里INT 数组是索引
        String ret = jsonObject.toString();
        return ret;
    }

    @RequestMapping(value = "/finishReview")
    @ResponseBody
    public void finishReview(@RequestBody String para){
        JSONObject object=JSONObject.fromObject(para);
        FinishReviewParameter finishReviewParameter= (FinishReviewParameter) JSONObject.toBean(object,FinishReviewParameter.class);

    }

    @RequestMapping(value = "/checkCommit")
    @ResponseBody
    public int checkCommit(@RequestBody String missionPara){
        JSONObject object=JSONObject.fromObject(missionPara);
        MissionParameter missionParameter=(MissionParameter)JSONObject.toBean(object,MissionParameter.class);
        String mid=missionParameter.getKeyword();
        String uid=missionParameter.getUid();
        int check=collectionResultBasicBLService.findByKey(mid+uid).getState();
        if(check==1){
            //已经提交
            return 1;
        }
        else{
            //其他状态
            return 2;
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
