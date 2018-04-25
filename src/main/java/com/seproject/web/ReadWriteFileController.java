package com.seproject.web;

import com.seproject.domain.Collection;
import com.seproject.service.BasicBLService;
import com.seproject.service.FileIOService;
import com.seproject.service.StatisticsService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by julia98 on 2018/3/22.
 */
@Controller
public class ReadWriteFileController {
    FileIOService fileIOService;
//    StatisticsService statisticsService;
     BasicBLService<Collection> collectionService=new BasicBLService<Collection>(new Collection());
    @RequestMapping(value = "/write")
    @ResponseBody
    public String writeFile(@RequestBody String imgid){
       // String imgid = params.getString("imgid");
        System.out.print("获取到的文件名" + imgid);
        fileIOService.updateDataFile(imgid);
        return "666";
    }

    @RequestMapping(value = "/tag")
    @ResponseBody
    public String getTag(@RequestBody String collectionNameAndPicName){
        JSONObject jsonObject = JSONObject.fromObject(collectionNameAndPicName);
        String collectionName = jsonObject.getString("collectionName");
        String picName = jsonObject.getString("picName");

        return "666";
    }

    @RequestMapping(value = "/read")
    @ResponseBody
    public String readFile(@RequestBody String imageInfo) {
/*        System.out.print("imageInfo:"+imageInfo);
        String [] temp1=imageInfo.split(":");
        String [] temp2=temp1[1].split(",");
        String name=temp2[0];
        int id=Integer.parseInt(temp1[2].replace("}",""));
        String res = fileIOService.getJsonString(name, id);
        if(!res.startsWith("{")){
            res="{"+res;
            res=res+"}";
        }
        System.out.println("result:"+res);
        return res;*/
        return "{}";
    }

    /**
     * 用户提交任务的方法
     * 改变图集状态从0变到1
     * @param request
     * @return
     */
    @RequestMapping(value = "/submit")
    @ResponseBody
    public String submitTagInfo(@RequestBody HttpServletRequest request){
        String missionAndPhoneNumber = request.getParameter("missionAndPhoneNumber");
        Collection collection=null;

        if((collection=collectionService.findByKey(missionAndPhoneNumber))!=null){
            collection.setState(1);
            collectionService.update(collection);
        }else{
            System.out.println("要提交的图集不存在"+"：主键为"+missionAndPhoneNumber);
        }

        return "666";
    }


    @Autowired
    public void setFileIOService(FileIOService fileIOService){this.fileIOService=fileIOService ;}
 /*   @Autowired
    public  void setStatisticsService(StatisticsService statisticsService){
        this.statisticsService=statisticsService;
    }
    */
}
