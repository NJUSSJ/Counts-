package com.seproject.web.controller;

import com.seproject.common.RM;
import com.seproject.common.SearchCategory;
import com.seproject.domain.*;
import com.seproject.service.Factory;
import com.seproject.service.MainService;
import com.seproject.service.ReviewService;
import com.seproject.service.blService.BasicBLService;
import com.seproject.web.parameter.*;
import com.seproject.web.response.ReviewResponse;
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

        ArrayList<Integer> tempAnswer=new ArrayList<Integer>();
        for(int i=0;i<12;i++){
            tempAnswer.add(-1);
        }
        subLabelMissions.get(index).getAnswers().add(tempAnswer);

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
        System.out.println("更新标签标传来的参数:"+parameter);
        UpdateLabelMissionParameter para= (UpdateLabelMissionParameter) JSONObject.toBean(object,UpdateLabelMissionParameter.class);
        String uid=para.getUid();
        String mid=para.getMid();
         int k=para.getNum();
        ArrayList<SubLabelMission> subLabelMission =subTagMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        for(int i = 0; i< subLabelMission.size(); i++){
            SubLabelMission sub= subLabelMission.get(i);
            ArrayList<String> user=sub.getUid();
            if(user.contains(uid)){
                ArrayList<Integer> tempanswer=sub.getAnswers().get(user.indexOf(uid));
                tempanswer.set(k,para.getAnswer());
                subTagMissionBasicBLService.update(sub);
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

 /*   @RequestMapping(value = "/updateGoldMission")
    @ResponseBody

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

**/
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
    /**
     * 开启评估
     */
    public String startReview(@RequestBody String mid){
        System.out.println(mid);
        Mission mission=missionBasicBLService.findByKey(mid);
        int type=mission.getTagType();
        int bonus=mission.getBonusStrategy();
        int evaluate=mission.getEvaluateStrategy();
        ReviewResponse reviewResponse=new ReviewResponse();
        reviewResponse.setType(type);
        reviewResponse.setPicIndex(new ArrayList<Integer>());
        reviewResponse.setLabel(new ArrayList<String>());
        reviewResponse.setUid(new ArrayList<String>());
        reviewResponse.setInfo(new ArrayList<String>());
        if(type==1){//标签式
            reviewResponse.setPicIndex(mainService.getRestPictures(mid));//不管是工人评还是自己评，都需要金标的答案
            reviewResponse.setLabel(mission.getMissionLabel());
        }else {//自由式
            if(evaluate==2) {//手动评，需要获取抽样的结果
                mainService.createFreeMissionSample(reviewResponse, mid);
            }else{//自动评，直接开始
                mainService.autoReviewFreeMission(mid);
            }
        }
        JSONObject jsonObject = JSONObject.fromObject(reviewResponse);//这里INT 数组是索引
        String ret = jsonObject.toString();
        System.out.println(ret);
        return ret;
    }

    @RequestMapping(value = "/finishReview")
    @ResponseBody
    /**
     * 处理完评估以后的结果，重新发起评估
     */
    public void finishReview(@RequestBody String para){
        JSONObject object=JSONObject.fromObject(para);
        FinishReviewParameter finishReviewParameter= (FinishReviewParameter) JSONObject.toBean(object,FinishReviewParameter.class);
        ArrayList<Integer> index=finishReviewParameter.getIndexs();
        ArrayList<Integer> answer=finishReviewParameter.getAnswers();
        String mid=finishReviewParameter.getMid();
        Mission mission=missionBasicBLService.findByKey(mid);
        if(mission.getTagType()==1) {//如果是标签式，那么调用这个方法的目的是完善金标的答案
            mainService.finishReview(index, answer, mid);
        }else{//如果是自由式，那么调用这个方法获得了对样本的评价结果
            mainService.manualReviewFreeMission(index,answer,finishReviewParameter.getUid(),mid);
        }
    }

    @RequestMapping(value = "/commit")
    @ResponseBody
    public int commit(@RequestBody String missionPara){
        JSONObject object=JSONObject.fromObject(missionPara);
        MissionParameter missionParameter=(MissionParameter)JSONObject.toBean(object,MissionParameter.class);
        String mid=missionParameter.getKeyword();
        String uid=missionParameter.getUid();
        System.out.println(mid+uid+"!!!!!!!");
        CollectionResult cr=collectionResultBasicBLService.findByKey(mid+uid);
        if(cr.getState() == 1){
            return 2;//已提交过
        }else {
            cr.setState(1);
            collectionResultBasicBLService.update(cr);
            return 1;
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
