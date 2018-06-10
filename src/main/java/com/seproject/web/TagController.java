package com.seproject.web;

import com.seproject.domain.Collection;
import com.seproject.domain.CollectionResult;
import com.seproject.domain.User;
import com.seproject.service.Factory;
import com.seproject.service.blService.BasicBLService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * Created by julia98 on 2018/3/22.
 */
@Controller
public class TagController {

    BasicBLService<Collection> collectionService= Factory.getBasicBLService(new Collection());
    BasicBLService<CollectionResult> collectionResultBasicBLService= Factory.getBasicBLService(new CollectionResult());
    @RequestMapping(value = "/write")
    @ResponseBody
    public String writeFile(@RequestBody String imgid){
        JSONObject jsonObject = JSONObject.fromObject(imgid);
        String temp0[]=jsonObject.getString("imgid").split("-");
        String starterMissionName=temp0[0];
        String picName0= temp0[1]; //这个属性必须是数字,且从1开始
        String userName= temp0[2];

        Collection collection=collectionService.findByKey(starterMissionName+userName);
        ArrayList<String> infoList=collection.getInfoList();
        infoList.set(Integer.parseInt(picName0)-1,imgid);
        collection.setInfoList(infoList);
        collectionService.update(collection);
        return "666";
    }

    @RequestMapping(value = "/read")
    @ResponseBody
    public String readFile(@RequestBody String imageInfo) {
        //加载单张图片的标注信息
        JSONObject jsonObject = JSONObject.fromObject(imageInfo);
        String collectionName=jsonObject.getString("collectionName");
        String picName=jsonObject.getString("picName");
        String phoneNumber=jsonObject.getString("phoneNumber");
        Collection collection=collectionService.findByKey(collectionName+phoneNumber);String jsonString=collection.getInfoList().get(Integer.parseInt(picName)-1);
        return jsonString;
    }

    /**
     * 用户提交任务的方法
     * 改变图集状态从0变到1
     * @param str
     * @return
     */
    @RequestMapping(value = "/submitTag")
    @ResponseBody
    public int submitTagInfo(@RequestBody String str){
        JSONObject jsonObject = JSONObject.fromObject(str);
        String missionAndPhoneNumber = jsonObject.getString("missionAndPhoneNumber");
        CollectionResult collection=null;

        if((collection=collectionResultBasicBLService.findByKey(missionAndPhoneNumber))!=null){
            collection.setState(1);
            collectionResultBasicBLService.update(collection);
        }else{
            System.out.println("要提交的图集不存在"+"：主键为"+missionAndPhoneNumber);
        }

        return 1;
    }


    @RequestMapping(value = "/getSubmitTag")
    @ResponseBody
    public int getSubmitTagInfo(@RequestBody String str){
        JSONObject jsonObject = JSONObject.fromObject(str);
        String missionAndPhoneNumber = jsonObject.getString("missionAndPhoneNumber");
        Collection collection=null;

        return collectionResultBasicBLService.findByKey(missionAndPhoneNumber).getState();
    }



}
