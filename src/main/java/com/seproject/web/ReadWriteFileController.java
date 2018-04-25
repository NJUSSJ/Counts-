package com.seproject.web;

import com.seproject.domain.Collection;
import com.seproject.domain.User;
import com.seproject.service.BasicBLService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by julia98 on 2018/3/22.
 */
@Controller
public class ReadWriteFileController {

    private BasicBLService<User> basicBLService=new BasicBLService<User>(new User());
    BasicBLService<Collection> collectionService=new BasicBLService<Collection>(new Collection());

    @RequestMapping(value = "/write")
    @ResponseBody
    public String writeFile(@RequestBody String imgid){
       // String imgid = params.getString("imgid");
        System.out.println("获取到的json字符串：" + imgid);

        JSONObject jsonObject = JSONObject.fromObject(imgid);
        String temp0[]=jsonObject.getString("imgid").split("-");
        String starterMissionName=temp0[0];
        String picName0= temp0[1]; //这个属性必须是数字,且从1开始
        String userName= temp0[2];

        Collection collection=collectionService.findByKey(starterMissionName+userName);
        System.out.println(collection);
        ArrayList<String> infoList=collection.getInfoList();
        System.out.println(infoList.size()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        infoList.set(Integer.parseInt(picName0)-1,imgid);
        collection.setInfoList(infoList);
        collectionService.update(collection);


        return "666";
    }
/*
    @RequestMapping(value = "/tag")
    @ResponseBody
    public String getTag(@RequestBody String collectionNameAndPicName){
        JSONObject jsonObject = JSONObject.fromObject(collectionNameAndPicName);
        String collectionName = jsonObject.getString("collectionName");
        String picName = jsonObject.getString("picName");

        return "666";
    }*/

    @RequestMapping(value = "/read")
    @ResponseBody
    public String readFile(@RequestBody String imageInfo) {
        //加载单张图片的标注信息
        JSONObject jsonObject = JSONObject.fromObject(imageInfo);
        String collectionName=jsonObject.getString("collectionName");
        String picName=jsonObject.getString("picName");
        System.out.println("picName: " + picName);
        String phoneNumber=jsonObject.getString("phoneNumber");
        Collection collection=collectionService.findByKey(collectionName+phoneNumber);
        String jsonString=collection.getInfoList().get(Integer.parseInt(picName)-1);
        System.out.println("从后端往前端发送的json字符串：" + jsonString);
        return jsonString;
    }

    /**
     * 用户提交任务的方法
     * 改变图集状态从0变到1
     * @param request
     * @return
     */
    @RequestMapping(value = "/submit")
    @ResponseBody
    public String submitTagInfo(HttpServletRequest request){
        String missionAndPhoneNumber = request.getParameter("missionAndPhoneNumber");
        System.out.println(missionAndPhoneNumber);
        Collection collection=null;

        if((collection=collectionService.findByKey(missionAndPhoneNumber))!=null){
            collection.setState(1);
            collectionService.update(collection);
        }else{
            System.out.println("要提交的图集不存在"+"：主键为"+missionAndPhoneNumber);
        }

        return "提交成功！";
    }




}
