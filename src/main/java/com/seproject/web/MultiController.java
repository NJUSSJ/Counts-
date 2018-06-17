/**
 * @authr fortune
 */
package com.seproject.web;

import com.seproject.domain.Mission;
import com.seproject.domain.User;
import com.seproject.service.Factory;
import com.seproject.service.blService.BasicBLService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
public class MultiController {
    private BasicBLService<User> basicBLService= Factory.getBasicBLService(new User());
    private BasicBLService<Mission> missionBasicBLService=Factory.getBasicBLService(new Mission());

    @RequestMapping(value = "/test1.html")
    public ModelAndView getTagPage(HttpServletRequest request){
        ModelAndView model;
        String phoneNumber = request.getParameter("phoneNumber");
        String sufixx="\'missionImages/";
        String url=sufixx+request.getParameter("collection")+"_"+request.getParameter("imageURL")+".jpg\'";
        String collection = request.getParameter("collection");
        int tagType = missionBasicBLService.findByKey(collection).getTagType();
        if(tagType == 1){
            model = new ModelAndView("LabelEdit");
        }else {
            model = new ModelAndView("SingleEdit");
        }
        System.out.println(url+"!!!!!!!!!!");
        model.addObject("url",url);
        model.addObject("collection",collection);
        int picNum = missionBasicBLService.findByKey(collection).getFileNum();
        model.addObject("picNum",picNum);
        User user = basicBLService.findByKey(phoneNumber);
        System.out.println("phoneNumber:"+phoneNumber+"!!!!!*()()**");
        System.out.println("userCategory:"+user.getCategory()+"!!!!!!!!!");
        model.addObject("userCategory",user.getCategory());
        model.addObject("userName",user.getUserName());
        model.addObject("userPhone",phoneNumber);
        model.addObject("tagType", tagType);
        ArrayList<String> missionLabel = missionBasicBLService.findByKey(collection).getMissionLabel();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!" + missionLabel);
        model.addObject("missionLabel", missionLabel);
        return model;
    }

    @RequestMapping(value = "/getCollectionInfo")
    @ResponseBody
    /**
     * 未来会成为推荐任务的调用方法
     */
    public String[] getCollectionInfo(@RequestBody String user) {

        ArrayList<Mission> tmpMission=missionBasicBLService.getAllObjects();
        int index=0;
        String[] missionNames=new String[1000];
        if(tmpMission!=null&&tmpMission.size()>0) {
            for (Mission mission : tmpMission) {
                missionNames[index] = mission.getName() + "^" + mission.getDescription();
                index++;
            }
        }
        return missionNames;
    }

    @RequestMapping(value = "/getMissionDetails")
    @ResponseBody
    public ModelAndView getMissionDetails(HttpServletRequest request){
        String missionName=request.getParameter("missionName");
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
        ArrayList<String> missionLabel = tmpMission.getMissionLabel();
        int maxWorkerNum = tmpMission.getMaxWorkerNum();

        ModelAndView view= new ModelAndView("MissionDetails");
        view.addObject("missionName",missionName);
        view.addObject("picNum",picNum);
        view.addObject("credit",credit);
        view.addObject("startTime", startTime);
        view.addObject("endTime", endTime);
        view.addObject("Level",Level);
        view.addObject("description",description);

        view.addObject("picTye",picType);
        view.addObject("tagType",tagType);
        view.addObject("difficulty",difficulty);
        view.addObject("missionLabel",missionLabel);
        view.addObject("maxWorkerNum",maxWorkerNum);
        return view;
    }

    @RequestMapping(value = "/getSubmission")
    @ResponseBody
    public ModelAndView getSubMission(HttpServletRequest request){
        String mid = "\'"+request.getParameter("imageURL")+"\'";
        String uid = request.getParameter("userPhone");
        Mission mission = missionBasicBLService.findByKey(request.getParameter("imageURL"));

        int tagType = mission.getTagType();
        ModelAndView view = new ModelAndView("personalSubmission");
        view.addObject("mid", mid);
        view.addObject("uid", uid);
        view.addObject("tagType", tagType);

        return view;
    }


}
