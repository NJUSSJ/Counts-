/**
 * @author fortune
 */
package com.seproject.web;

import com.seproject.domain.Collection;
import com.seproject.domain.CollectionResult;
import com.seproject.domain.Mission;
import com.seproject.domain.User;
import com.seproject.service.Factory;
import com.seproject.service.blService.BasicBLService;
import com.seproject.service.FileIOService;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
public class CollectionController {


    private FileIOService fileIOService;
    private BasicBLService<User> basicBLService= Factory.<User>getBasicBLService(new User());
    private BasicBLService<Mission> missionBasicBLService= Factory.<Mission>getBasicBLService(new Mission());
    private BasicBLService<Collection> collectionBasicBLService= Factory.<Collection>getBasicBLService(new Collection());
    private BasicBLService<CollectionResult> collectionResultBasicBLService=Factory.getBasicBLService(new CollectionResult());
    @RequestMapping(value = "/details.html")
    public ModelAndView getDetailofCollection(HttpServletRequest request){
        String missionName=request.getParameter("imageURL");
        Mission tmpMission=missionBasicBLService.findByKey(missionName);
        int picNum=tmpMission.getFileNum();
        double credit=tmpMission.getReward();
        int expectedNum=tmpMission.getMaxNum();
        //String startTime=tmpMission.getStartTime();
        String endTime=tmpMission.getEndTime();
        String Level=tmpMission.getWorkerLevel();
        String description=tmpMission.getDescription();

        picNum=missionBasicBLService.findByKey(missionName).getFileNum();
        String fixx="\'missionImages/";
        String url=fixx+request.getParameter("imageURL")+"_\'";
        String collection="\'"+request.getParameter("imageURL")+"\'";
        String userPhone=request.getParameter("userPhone");
        String userName=basicBLService.findByKey(userPhone).getUserName();
        int userCategory=Integer.parseInt(request.getParameter("userCategory"));
        int Tagable=Integer.parseInt(request.getParameter("Tagable"));


        ModelAndView model=new ModelAndView("MultiPic");
        model.addObject("url",url);
        model.addObject("collection", collection);
        model.addObject("picNum",picNum);
        model.addObject("userPhone", userPhone);
        model.addObject("userCategory", userCategory);
        model.addObject("userName",userName);
        model.addObject("Tagable", Tagable);

        model.addObject("credit",credit);
        model.addObject("expectedNum",expectedNum);
        //model.addObject("startTime", startTime);
        model.addObject("endTime", endTime);
        model.addObject("Level",Level);
        model.addObject("description",description);

        return model;
    }

    @RequestMapping(value = "/addMissionToUser")
    @ResponseBody
    public String addMissionToUser(@RequestBody String collectionInfo){
        JSONObject object=new JSONObject().fromObject(collectionInfo);
        Collection collection=(Collection)JSONObject.toBean(object,Collection.class);
        String uid=collection.getUid();
        String mid=collection.getMid();
        int uLevel=basicBLService.findByKey(uid).getLevel();
        int mLevel=Integer.parseInt(missionBasicBLService.findByKey(mid).getWorkerLevel());
        if(uLevel<mLevel){
            return "0";
        }

        String missionDate=missionBasicBLService.findByKey(mid).getEndTime();
        Date now=new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate=dateFormat.format(now);
        if(missionDate.compareTo(nowDate)<0){
            return "1";
        }

        Mission tmpMission=missionBasicBLService.findByKey(mid);
        int picNum=tmpMission.getFileNum();
        collection.setKeyId(mid+uid);
        CollectionResult collectionResult=new CollectionResult(collection);
        collectionResult.setUid(uid);
        collectionResult.setMid(mid);
        collectionResult.setState(0);
        ArrayList<String> tmpArray=new ArrayList<String>();
        for(int i=0;i<picNum;i++){
            tmpArray.add("{}");
        }
        collection.setInfoList(tmpArray);
        collectionBasicBLService.add(collection);
        collectionResultBasicBLService.add(collectionResult);
        return "2";
    }
}
