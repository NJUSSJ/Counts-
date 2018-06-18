package com.seproject.web.controller;

import com.seproject.domain.User;
import com.seproject.domain.UserDate;
import com.seproject.service.Factory;
import com.seproject.service.blService.BasicBLService;
import net.sf.json.JSONString;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
public class OtherController {
    BasicBLService<UserDate> userDateBasicBLService= Factory.getBasicBLService(new UserDate());
    BasicBLService<User> userBasicBLService=Factory.getBasicBLService(new User());
    @RequestMapping(value = "/Sign")
    @ResponseBody
    /**
     * 签到
     */
    public String sign(@RequestBody String phoneNumber){
        phoneNumber = phoneNumber.split("=")[1];
        System.out.println(phoneNumber);
        String message="sign up success";
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
        }else {//否则比较上次签到时间和今天的差
            Date lastTime = dateArray[index];
            int day = ((int) (now.getTime() / (1000 * 3600 * 24))) - ((int)(lastTime.getTime() / (1000 * 3600 * 24)));
            if (day < 1) {
                message = "";
            } else if (day >= 1 && day < 2) {
                index++;
                reward = (index+1) * 2;
                if (index == 6) {//如果签满七天，清空日期数组
                    index = -1;
                    clearArray(dateArray);
                }else {
                    dateArray[index] = now;
                }
            } else {//如果不连续，清空日期数组，添加第一项为今天
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
        return message;
    }

    public void clearArray(Date[] dateArray){
        for(int i=0;i<7;i++) {
            dateArray[i] = null;
        }
    }

}
