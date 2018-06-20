package com.seproject.web.controller;

import com.seproject.common.RM;
import com.seproject.common.SearchCategory;
import com.seproject.domain.*;
import com.seproject.service.Factory;
import com.seproject.service.MainService;
import com.seproject.service.blService.BasicBLService;
import com.seproject.web.parameter.*;
import com.seproject.web.response.DownloadFileResponse;
import com.seproject.web.response.MissionResultResponse;
import com.seproject.web.response.ReviewResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class MainController {
    //用于写新加的主要系统流程
    /**
     * 增加标签式任务、接受子任务、高级工人接受评估式任务
     * 自动评估标签式任务并分配奖励、手动评估标签式任务并分配奖励
     */
    private MainService mainService=new MainService();
    private BasicBLService<Mission> missionBasicBLService=Factory.getMissionBasicBLService();
    private BasicBLService<SubLabelMission> subTagMissionBasicBLService=Factory.getSubLabelMissionBasicBLService();
    private BasicBLService<GoldMission> goldMissionBasicBLService=Factory.getGoldBasicBLService();
    private BasicBLService<SubFreeMission> subFreeMissionBasicBLService=Factory.getSubFreeMissionBasicBLService();
    private BasicBLService<Collection> collectionBasicBLService=Factory.getCollectionBasicBLService();
    private BasicBLService<CollectionResult> collectionResultBasicBLService=Factory.getCollectionResultBasicBLService();


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
        for(SubLabelMission subLabelMission : subLabelMissions){
            if(subLabelMission.getUid().contains(uid)){//不能接两次任务
                return RM.FAILURE.toString();
            }
            userNum.add(subLabelMission.getUid().size());
        }
        int index=getMinIndex(userNum);
        subLabelMissions.get(index).getUid().add(uid);

        ArrayList<Integer> tempAnswer=new ArrayList<Integer>();
        for(int i=0;i<12;i++){
            tempAnswer.add(-1);
        }
        subLabelMissions.get(index).getAnswers().add(tempAnswer);
        System.out.println("&^^%%$$#kdsjfljdsfkl"+(mainService==null));
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
        for(SubFreeMission subFreeMission : subFreeMissions){
            if(subFreeMission.getUid().contains(uid)){//不能接两次任务
                return RM.FAILURE.toString();
            }
            userNum.add(subFreeMission.getUid().size());
        }
        int index=getMinIndex(userNum);
        subFreeMissions.get(index).getUid().add(uid);
        mainService.createCollection(uid,mid);
        subFreeMissionBasicBLService.update(subFreeMissions.get(index));
        return RM.SUCCESS.toString();
    }


    @RequestMapping(value = "/updateLabelMission")
    @ResponseBody
    /**
     * 更新用户对标签式任务（或金标）的标注
     */
    public String updateLabelMission(@RequestBody String parameter){
        JSONObject object=JSONObject.fromObject(parameter);
        System.out.println("更新标签标传来的参数:"+parameter);
        UpdateLabelMissionParameter para= (UpdateLabelMissionParameter) JSONObject.toBean(object,UpdateLabelMissionParameter.class);
        String uid=para.getUid();
        String mid=para.getMid();

        Mission mission=missionBasicBLService.findByKey(mid);

        int numIndex=para.getNum();
        String lab=mission.getMissionLabel().get(0);
        lab=lab.replace("[","").replace("]","");
        String[] labList=lab.split(",");
        int ans=-1;
        String ansTemp=para.getAnswer();
        if(ansTemp!=null&&ansTemp.length()>0) {
            ansTemp="\""+ansTemp+"\"";
            for (int d = 0; d < labList.length; d++) {
                if (ansTemp.equals(labList[d])) {
                    ans = d;
                    break;
                }
            }
        }
        ArrayList<GoldMission> goldMissions=goldMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        for(GoldMission goldMission:goldMissions){
            String tempUid=goldMission.getUid();
            if(tempUid.equals(uid)){
                goldMission.getResult().set(numIndex,ans);
                goldMissionBasicBLService.update(goldMission);
                return RM.SUCCESS.toString();
            }
        }
        //如果不是金标，到子任务里取
        ArrayList<SubLabelMission> subLabelMission =subTagMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        for (SubLabelMission sub : subLabelMission) {
            ArrayList<String> user = sub.getUid();
            System.out.println("子任务的userList："+user);
            if (user.contains(uid)) {
                System.out.println("找到了！！！！！！！！！！！");
                System.out.println("在："+user.indexOf(uid));
                ArrayList<ArrayList<Integer>> tempAllAnswer=sub.getAnswers();
                ArrayList<Integer> tempAnswer = tempAllAnswer.get(user.indexOf(uid));
                tempAnswer.set(numIndex, ans);
                sub.setAnswers(tempAllAnswer);//更新
                subTagMissionBasicBLService.update(sub);
                tempAllAnswer=sub.getAnswers();
                System.out.println("更新以后的答案集");
                for(ArrayList<Integer> list:tempAllAnswer){
                    System.out.println(list);
                }
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
        System.out.println("1234455667898765432");
        JSONObject object=JSONObject.fromObject(parameter);
        GetMissionParameter para= (GetMissionParameter) JSONObject.toBean(object,GetMissionParameter.class);
        String uid=para.getUid();
        String mid=para.getMid();
        boolean res=mainService.getGoldMission(mid,uid);
       if(res) {
           System.out.println(res+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
           return "1";

       }else{
           System.out.println(res+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
           return "0";
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
            ArrayList<Integer> picIn=mainService.getRestPictures(mid);
            reviewResponse.setPicIndex(picIn);//不管是工人评还是自己评，都需要金标的答案
            reviewResponse.setLabel(mission.getMissionLabel());
            if(picIn==null||picIn.size()==0){
                reviewResponse.setGoldMissionAllDone(1);
            }else{
                reviewResponse.setGoldMissionAllDone(0);
            }
        }else {//自由式
            if(evaluate==2) {//手动评，需要获取抽样的结果
                mainService.createFreeMissionSample(reviewResponse, mid);
                reviewResponse.setGoldMissionAllDone(0);
            }else{//自动评，直接开始
                mainService.autoReviewFreeMission(mid);
                reviewResponse.setGoldMissionAllDone(1);
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
        System.out.println(index);
        System.out.println(answer);
        System.out.println(finishReviewParameter.getUid());
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


    @RequestMapping(value = "/getMissionResultResponse")
    @ResponseBody
    public ModelAndView getMissionResultResponse(HttpServletRequest request){
        MissionResultResponse missionResultResponse=new MissionResultResponse();
        CollectionResult collectionResult=collectionResultBasicBLService.findByKey(request.getParameter("mid")+request.getParameter("uid"));
        missionResultResponse.setCredit(collectionResult.getCredit());
        missionResultResponse.setQuality(collectionResult.getQuality());
        missionResultResponse.setRank(collectionResult.getRank());
        ArrayList<Collection> collections= collectionBasicBLService.search("mid",SearchCategory.EQUAL,request.getParameter("mid"));
        missionResultResponse.setTotal(collections.size());

        ModelAndView view = new ModelAndView("missionResult");
        view.addObject("quality", missionResultResponse.getQuality());
        view.addObject("credit", missionResultResponse.getCredit());
        view.addObject("rank",missionResultResponse.getRank());
        view.addObject("total", missionResultResponse.getTotal());
        return view;

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

}
