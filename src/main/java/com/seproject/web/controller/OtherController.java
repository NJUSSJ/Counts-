package com.seproject.web.controller;

import com.seproject.common.SearchCategory;
import com.seproject.domain.Collection;
import com.seproject.domain.Mission;
import com.seproject.domain.User;
import com.seproject.domain.UserDate;
import com.seproject.service.Factory;
import com.seproject.service.FileIOService;
import com.seproject.service.blService.BasicBLService;
import com.seproject.web.parameter.ChangeCreditParameter;
import com.seproject.web.response.FindMissionResponse;
import com.seproject.web.response.FindUserResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;

@RestController
public class OtherController {
    //BasicBLService<UserDate> userDateBasicBLService= Factory.getBasicBLService(new UserDate());
    BasicBLService<UserDate> userDateBasicBLService= Factory.getUserDateBasicBLService();
    //BasicBLService<User> userBasicBLService=Factory.getBasicBLService(new User());
    BasicBLService<User> userBasicBLService=Factory.getUserBasicBLService();
    BasicBLService<Mission> missionBasicBLService=Factory.getMissionBasicBLService();
    BasicBLService<Collection> collectionBasicBLService=Factory.getCollectionBasicBLService();
    FileIOService fileIOService;
    @RequestMapping(value = "/Sign")
    @ResponseBody
    /**
     * 签到
     */
    public int sign(@RequestBody String phoneNumber){
        phoneNumber = phoneNumber.split("=")[1];
        System.out.println("phoneNumber:"+phoneNumber);
        String message="sign up success";
        int result=0;
        Date now=new Date(System.currentTimeMillis());
        User user=userBasicBLService.findByKey(phoneNumber);
        double reward=0;
        boolean existed=userDateBasicBLService.checkKeyExists(phoneNumber);
        UserDate userDate;
        if(!existed){ userDate=new UserDate(phoneNumber); }
        else {
            userDate = userDateBasicBLService.findByKey(phoneNumber);
        }
        Date[] dateArray = userDate.getDate();
        int index = userDate.getFlag();
        if(index==-1){//如果整个日期数组为空，添加第一项并令index=0
            reward=2;
            dateArray[0]=now;
            index=0;
            result=1;
        }else {//否则比较上次签到时间和今天的差
            Date lastTime = dateArray[index];
            int day = ((int) (now.getTime() / (1000 * 3600 * 24))) - ((int)(lastTime.getTime() / (1000 * 3600 * 24)));
            if (day < 1) {
                System.out.println("part1");
                message = "you have signed up today";
            } else if (day >= 1 && day < 2) {
                System.out.println("part2");
                result=1;
                index++;
                reward = (index+1) * 2;
                if (index == 6) {//如果签满七天，清空日期数组
                    index = -1;
                    clearArray(dateArray);
                }else {
                    dateArray[index] = now;
                }
            } else {//如果不连续，清空日期数组，添加第一项为今天
                System.out.println("part3");
                result=1;
                clearArray(dateArray);
                reward=2;
                index = 0;
                dateArray[index] = now;
            }
        }
        userDate.setDate(dateArray);
        userDate.setFlag(index);
        if(existed) { userDateBasicBLService.update(userDate); }
        else{userDateBasicBLService.add(userDate);}

        if(reward>0) {
            user.setCredit(user.getCredit() + reward);
            userBasicBLService.update(user);
        }
        System.out.println(message);
        return result;
    }

    @RequestMapping(value = "/ChangeCredit")
    @ResponseBody
    public int changeCredit(@RequestBody ChangeCreditParameter changeCreditParameter){
        System.out.println("收钱啦！！！！！！！！！！！！！");
        String uid=changeCreditParameter.getUid();
        double delta=changeCreditParameter.getDelta();
        System.out.println(delta);
        User user=userBasicBLService.findByKey(uid);
        double origin=user.getCredit();
        if(origin+delta<0){
            return 0;//积分减完为负数，不能进行此操作
        }else {
            origin=origin+delta;
            user.setCredit(origin);
            userBasicBLService.update(user);
            return 1;
        }
    }

    @RequestMapping(value = "/findUserByAdmin")
    @ResponseBody
    public String findUser(@RequestBody String uid){
        uid = uid.substring(9, uid.lastIndexOf("\""));
        FindUserResponse findUserResponse=new FindUserResponse();
        User user=userBasicBLService.findByKey(uid);
        if(user==null){
            return "0";
        }else{
            findUserResponse.setCategory(user.getCategory());
            findUserResponse.setCredit(user.getCredit());
            findUserResponse.setLevel(user.getLevel());
            findUserResponse.setUid(user.getPhoneNumber());
            findUserResponse.setUserName(user.getUserName());
            JSONObject object = JSONObject.fromObject(findUserResponse);
            return object.toString();
        }

    }

    @RequestMapping(value = "/findMissionByAdmin")
    @ResponseBody
    public String findMission(@RequestBody String mid){
        mid = mid.substring(9, mid.lastIndexOf("\""));
        FindMissionResponse findMissionResponse=new FindMissionResponse();
        Mission mission=missionBasicBLService.findByKey(mid);
        ArrayList<Collection> collections=collectionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        if(mission==null){
            return "0";
        }else{
            findMissionResponse.setMemberNum(collections.size());
            findMissionResponse.setMid(mid);
            findMissionResponse.setStarterName(mission.getRequestorNumber());
            findMissionResponse.setState(mission.getState());
            findMissionResponse.setTagType(mission.getTagType());
            JSONObject object = JSONObject.fromObject(findMissionResponse);
            return object.toString();
        }
    }

    private void clearArray(Date[] dateArray){
        for(int i=0;i<7;i++) {
            dateArray[i] = null;
        }
    }
    @Autowired
    public void setFileIOService(FileIOService fileIOService){
        this.fileIOService=fileIOService;
    }

}
