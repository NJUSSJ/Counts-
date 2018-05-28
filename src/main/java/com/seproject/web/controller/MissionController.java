package com.seproject.web.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MissionController {
    @RequestMapping(value = "/MissionManage/Search")
    @ResponseBody
    public String search(@RequestBody String missionParameter){
        return null;
    }
    @RequestMapping(value = "/MissionManage/Delete")
    @ResponseBody
    public String delete(@RequestBody String missionParameter){
        return null;
    }
    @RequestMapping(value = "/MissionTake/Recommend")
    @ResponseBody
    public String recommend(@RequestBody String phoneNumber){
        return null;
    }
    @RequestMapping(value = "/MissionCheck")
    @ResponseBody
    /**
     * 任务结果查看（工人）
     */
    public String check(String phoneNumber,String missionName){
        return null;
    }
    @RequestMapping(value = "/MissionEvaluate/AutoEvaluate/{missionName}}")
    @ResponseBody
    public void autoEvaluate(String missionName){
    }
}
