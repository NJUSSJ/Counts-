package com.seproject.web.controller;

import com.seproject.common.SearchCategory;
import com.seproject.domain.Collection;
import com.seproject.domain.CollectionResult;
import com.seproject.domain.Mission;
import com.seproject.domain.User;
import com.seproject.service.Factory;
import com.seproject.service.MainService;
import com.seproject.service.MissionService;
import com.seproject.service.blService.BasicBLService;
import com.seproject.web.parameter.MissionParameter;
import com.seproject.web.parameter.MissionSearchParameter;
import com.seproject.web.parameter.RecommendParameter;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MissionController {
    BasicBLService<Mission> missionBasicBLService= Factory.getBasicBLService(new Mission());
    BasicBLService<User> userService=Factory.getBasicBLService(new User());
    BasicBLService<Collection> collectionService=Factory.getBasicBLService(new Collection());
    BasicBLService<CollectionResult> collectionResultBasicBLService=Factory.getBasicBLService(new CollectionResult());
    MissionService missionService=new MissionService();
    MainService mainService=new MainService();
    @RequestMapping(value = "/MissionManage/Search")
    @ResponseBody
    /**
     * 用户在自己的任务列表里搜索（此方法存疑，因为1.需要搜索策略2.需要指定是不是搜索任务名）
     */
    public String search(@RequestBody String missionParameter){
        MissionParameter para=toMissionPara(missionParameter);
        String uid=para.getUid();
        User user=userService.findByKey(uid);
        int identity=user.getCategory();
        String result="";
        if(identity==1){
            ArrayList<Mission> missionList=missionBasicBLService.search("uid",SearchCategory.EQUAL,uid);
            result=toJsonString(missionList);
        }else if(identity==2) {
            ArrayList<Collection> collectionList = collectionService.search("uid", SearchCategory.EQUAL, uid);
            result=toJsonString(collectionList);
        }
        return result;
    }

    @RequestMapping(value = "/MissionTake/SearchInHall")
    @ResponseBody
    /**
     * 任务大厅的搜索
     */
    public ArrayList<Mission> searchInHall(@RequestBody String missionSearchParameter){
        JSONObject object=JSONObject.fromObject(missionSearchParameter);
        MissionSearchParameter para=(MissionSearchParameter) JSONObject.toBean(object,MissionSearchParameter.class);
        String range=para.getRange();
        String keyword=para.getKeyword();
        ArrayList<Mission> result=new ArrayList<Mission>();
        if(range.equals("missionName")){
            result=missionBasicBLService.search("name",SearchCategory.CONTAINS,keyword);
        }else if(range.equals("requestor")){
            result=missionBasicBLService.search("requestorNumber",SearchCategory.EQUAL,keyword);
        }else{
            ArrayList<Mission> missions=missionBasicBLService.getAllObjects();
            if(range.equals("ended")){
                for(Mission mission:missions){
                    if(mission.getState()>0&&mission.getState()<3){
                        result.add(mission);
                    }
                }
            }else if(range.equals("notEnded")){
                for(Mission mission:missions){
                    if(mission.getState()==0){
                        result.add(mission);
                    }
                }
            }else{
                result.addAll(missions);
            }
        }

        if(result.size()>3){//换一批
            int random1=(int)(Math.random()*result.size());
            int random2=(int)(Math.random()*result.size());
            while(random2==random1){random2=(int)(Math.random()*result.size());}
            int random3=(int)(Math.random()*result.size());
            while(random3==random1||random3==random2){random3=(int)(Math.random()*result.size());}
            ArrayList<Mission> randomResult=new ArrayList<Mission>();
            randomResult.add(result.get(random1));
            randomResult.add(result.get(random2));
            randomResult.add(result.get(random3));
            result=randomResult;
        }
        return result;
    }

    @RequestMapping(value = "/MissionManage/Delete")
    @ResponseBody
    /**
     * 在用户的任务列表中删除任务
     */
    public String delete(@RequestBody String missionParameter){
        MissionParameter para=toMissionPara(missionParameter);
        String uid=para.getUid();
        User user=userService.findByKey(uid);
        int identity=user.getCategory();
        String keyword=para.getKeyword();
        if(identity==1){
            ArrayList<Mission> missionList = missionBasicBLService.search("uid", SearchCategory.EQUAL, uid);
            for (Mission each : missionList) {
                if (each.getRequestorNumber() == keyword) {
                    each.setState(3);
                    break;
                }
            }
        }else if(identity==2) {//工人
            ArrayList<CollectionResult> collectionList = collectionResultBasicBLService.search("uid", SearchCategory.EQUAL, uid);
            for (CollectionResult each : collectionList) {
                if (each.getMid() == keyword) {
                    each.setState(3);
                    break;
                }
            }
        }
        return "delete success";
    }
    @RequestMapping(value = "/MissionTake/Recommend")
    @ResponseBody
    public String recommend(@RequestBody String recommendPara){
        JSONObject object=JSONObject.fromObject(recommendPara);
        RecommendParameter para=(RecommendParameter) JSONObject.toBean(object,RecommendParameter.class);
        ArrayList<Mission> missions=missionService.recommendMission(para);
        return toJsonString(missions);
    }
    @RequestMapping(value = "/MissionTake/RecommendByUser")
    @ResponseBody
    /**
     * 根据相似用户推荐任务
     */
    public String recommendByUser(@RequestBody String uid){
        ArrayList<Mission> missions=missionService.recommendByAlikeUser(uid);
        return toJsonString(missions);
    }
    @RequestMapping(value = "/MissionCheck")
    @ResponseBody
    /**
     * 任务结果查看（工人）
     */
    public String check(@RequestBody String missionPara){
        MissionParameter para=toMissionPara(missionPara);
        String uid=para.getUid();
        String resultName=para.getKeyword();
        CollectionResult result=collectionResultBasicBLService.findByKey(resultName);
        return toJsonString(result);
    }


    /**
     *自动评估（标签式）任务
     */
    @RequestMapping(value = "/MissionEvaluate/AutoEvaluate/{missionName}")
    @ResponseBody
    public void autoEvaluate(String missionName){
        missionService.autoEvaluate(missionName);
    }

    @RequestMapping(value = "/MissionEvaluate/getPictureIndex")
    @ResponseBody
    /**
     * 获取子任务的全部图片
     */
    public ArrayList<Integer> getPictureIndex(@RequestBody String missionParameter){
        System.out.println(missionParameter);
        MissionParameter para=toMissionPara(missionParameter);
        System.out.println(mainService.getPictureIndexOfSubmission(para.getUid(),para.getKeyword()));
        return mainService.getPictureIndexOfSubmission(para.getUid(),para.getKeyword());
    }


    /*****************************************************/
    /**
     * Json字符串转MissionParamter对象
     */
    public MissionParameter toMissionPara(String jsonString){
        JSONObject object=JSONObject.fromObject(jsonString);
        MissionParameter para=(MissionParameter) JSONObject.toBean(object,MissionParameter.class);
        return para;
    }

    /**
     *对象转json字符串
     */
    public String toJsonString(Object o){
        JSONObject jsonObject = JSONObject.fromObject(o);
        String ret = jsonObject.toString();
        return ret;
    }

}
