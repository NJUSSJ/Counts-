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
import com.seproject.web.controller.MainController;
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
    //private BasicBLService<User> basicBLService= Factory.<User>getBasicBLService(new User());
    private BasicBLService<User> basicBLService= Factory.getUserBasicBLService();
    //private BasicBLService<Mission> missionBasicBLService= Factory.<Mission>getBasicBLService(new Mission());
    private BasicBLService<Mission> missionBasicBLService= Factory.getMissionBasicBLService();
    //private BasicBLService<Collection> collectionBasicBLService= Factory.<Collection>getBasicBLService(new Collection());
    private BasicBLService<Collection> collectionBasicBLService= Factory.getCollectionBasicBLService();
    //private BasicBLService<CollectionResult> collectionResultBasicBLService=Factory.getBasicBLService(new CollectionResult());
    private BasicBLService<CollectionResult> collectionResultBasicBLService=Factory.getCollectionResultBasicBLService();
    @RequestMapping(value = "/details.html")
    public ModelAndView getDetailofCollection(HttpServletRequest request){
        String missionName=request.getParameter("imageURL");
        Mission tmpMission=missionBasicBLService.findByKey(missionName);
        int picNum=tmpMission.getFileNum();
        double credit=tmpMission.getReward();
        String startTime=tmpMission.getStartTime();
        String endTime=tmpMission.getEndTime();
        String Level=tmpMission.getWorkerLevel();
        String description=tmpMission.getDescription();
        String picType = tmpMission.getPicType();
        int tagType = tmpMission.getTagType();
        int difficulty = tmpMission.getDifficulty();
        int evaluateStrategy = tmpMission.getEvaluateStrategy();
        int bonusStrategy = tmpMission.getBonusStrategy();
        ArrayList<String> missionLabel = tmpMission.getMissionLabel();
        System.out.println("missionLabel: " + missionLabel.toString());
        int maxWorkerNum = tmpMission.getMaxWorkerNum();

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
        model.addObject("startTime", startTime);
        model.addObject("endTime", endTime);
        model.addObject("Level",Level);
        model.addObject("description",description);

        model.addObject("picType",picType);
        model.addObject("tagType",tagType);
        model.addObject("difficulty",difficulty);
        model.addObject("missionLabel",missionLabel);
        model.addObject("maxWorkerNum",maxWorkerNum);
        model.addObject("bonusStrategy",bonusStrategy);
        model.addObject("evaluateStrategy",evaluateStrategy);

        return model;
    }

    @RequestMapping(value = "/addMissionToUser")
    @ResponseBody
    public String addMissionToUser(@RequestBody String collectionInfo){
        JSONObject object=new JSONObject().fromObject(collectionInfo);
        Collection collection=(Collection)JSONObject.toBean(object,Collection.class);
        String uid=collection.getUid();
        String mid=collection.getMid();
        Mission mission = missionBasicBLService.findByKey(mid);
        int type = mission.getTagType();
        if(type==2){
            return new MainController().getFreeMission(collectionInfo);
        }else{
            return new MainController().getLabelMission(collectionInfo);
        }
    }

    @RequestMapping(value = "/addJudgeMissionToUser")
    @ResponseBody
    public String addJudgeMissionToUser(@RequestBody String collectionInfo){
        JSONObject object=new JSONObject().fromObject(collectionInfo);
        Collection collection=(Collection)JSONObject.toBean(object,Collection.class);
        String uid=collection.getUid();
        String mid=collection.getMid();
        Mission mission = missionBasicBLService.findByKey(mid);
        int type = mission.getTagType();
        if(type==2){
            return new MainController().getGoldMission(collectionInfo);
        }else{
            return new MainController().getGoldMission(collectionInfo);
        }
    }


}
