package com.seproject.web;

import com.seproject.domain.statisticsData.AdminData;
import com.seproject.domain.statisticsData.SingleMissionData;
import com.seproject.domain.statisticsData.StarterData;
import com.seproject.domain.statisticsData.WorkerData;
import com.seproject.service.StatisticsService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChartController {

    StatisticsService statisticsService;
    //向前端传输发起者的chartData
    @RequestMapping(value = "/starterChartData")
    @ResponseBody
    public String getStarterChartData(@RequestBody String userInfo) {

        String phoneNumber = userInfo.substring(16,27);//获取手机号


        //需要通过uid获取相应data
        StarterData starterData = statisticsService.getStarterData(phoneNumber);
        JSONObject jsonObject = JSONObject.fromObject(starterData);
        String ret = jsonObject.toString();

        return ret;
    }

    //向前端传输工人的chartData
    @RequestMapping(value = "/workerChartData")
    @ResponseBody
    public String getWorkerChartData(@RequestBody String userInfo) {
        String phoneNumber = userInfo.substring(16,27);//获取手机号
        System.out.println(phoneNumber+"!!!!!!!!!!!!!!!!");
        //需要通过uid获取相应data
        System.out.println("staticsService is null"+(statisticsService==null));
        WorkerData workerData = statisticsService.getWorkerData(phoneNumber);
        JSONObject jsonObject = JSONObject.fromObject(workerData);
        String ret = jsonObject.toString();

        return ret;
    }

    //向前端传输管理员的chartData
    @RequestMapping(value = "/adminChartData")
    @ResponseBody
    public String getAdminChartData(@RequestBody String userInfo) {
        String phoneNumber = userInfo.substring(16,27);//获取手机号

        //需要通过uid获取相应data
        AdminData adminData =statisticsService.getAdminData();
        JSONObject jsonObject = JSONObject.fromObject(adminData);
        String ret = jsonObject.toString();

        return ret;
    }
    //向前端传输单个任务的chartDara
    @RequestMapping(value = "/singleMissionData")
    @ResponseBody
    public String getMissionChartData(@RequestBody String missionInfo){
        SingleMissionData data=statisticsService.getSingleMissionData(missionInfo);
        JSONObject jsonObject = JSONObject.fromObject(data);
        String ret = jsonObject.toString();
        return ret;
    }
    @Autowired
    public void setStatisticsService(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }
}
