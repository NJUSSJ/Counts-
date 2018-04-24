package com.seproject.web;

import com.seproject.domain.StatisticsData.AdminData;
import com.seproject.domain.StatisticsData.StarterData;
import com.seproject.domain.StatisticsData.WorkerData;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChartController {

    //向前端传输发起者的chartData
    @RequestMapping(value = "/starterChartData")
    @ResponseBody
    public String getStarterChartData(@RequestBody String userInfo) {
        System.out.println("接收到的userInfo: " + userInfo);
        String phoneNumber = userInfo.substring(16,27);//获取手机号
        System.out.println(phoneNumber);

        //需要通过uid获取相应data
        StarterData starterData = new StarterData();
        JSONObject jsonObject = JSONObject.fromObject(starterData);
        String ret = jsonObject.toString();

        return ret;
    }

    //向前端传输工人的chartData
    @RequestMapping(value = "/workerChartData")
    @ResponseBody
    public String getWorkerChartData(@RequestBody String userInfo) {
        System.out.println("接收到的userInfo: " + userInfo);
        String phoneNumber = userInfo.substring(16,27);//获取手机号
        System.out.println(phoneNumber);

        //需要通过uid获取相应data
        WorkerData workerData = new WorkerData();
        JSONObject jsonObject = JSONObject.fromObject(workerData);
        String ret = jsonObject.toString();

        return ret;
    }

    //向前端传输管理员的chartData
    @RequestMapping(value = "/adminChartData")
    @ResponseBody
    public String getAdminChartData(@RequestBody String userInfo) {
        System.out.println("接收到的userInfo: " + userInfo);
        String phoneNumber = userInfo.substring(16,27);//获取手机号
        System.out.println(phoneNumber);

        //需要通过uid获取相应data
        AdminData adminData = new AdminData();
        JSONObject jsonObject = JSONObject.fromObject(adminData);
        String ret = jsonObject.toString();

        return ret;
    }
}
