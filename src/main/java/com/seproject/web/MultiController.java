/**
 * @authr fortune
 */
package com.seproject.web;

import com.seproject.domain.Collection;
import com.seproject.domain.Mission;
import com.seproject.domain.User;
import com.seproject.service.BasicBLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
public class MultiController {
    private BasicBLService<User> basicBLService=new BasicBLService<User>(new User());
    private BasicBLService<Mission> missionBasicBLService=new BasicBLService<Mission>(new Mission());

    @RequestMapping(value = "/test1.html")
    public ModelAndView getTagPage(HttpServletRequest request){
        String phoneNumber = request.getParameter("phoneNumber");
        String sufixx="\'../../images/";
        String url=sufixx+request.getParameter("collection")+"_"+request.getParameter("imageURL")+".jpg\'";
        ModelAndView model=new ModelAndView("SingleEdit");
        model.addObject("url",url);
        String collection = request.getParameter("collection");
        model.addObject("collection",collection);
        int picNum = missionBasicBLService.findByKey(collection).getFileNum();
        model.addObject("picNum",picNum);
        User user = basicBLService.findByKey(phoneNumber);
        model.addObject("userCategory",user.getCategory());
        model.addObject("userName",user.getUserName());
        model.addObject("userPhone",phoneNumber);
        return model;
    }

    @RequestMapping(value = "/getCollectionInfo")
    @ResponseBody
    public String[] getCollectionInfo(@RequestBody String User) {
        missionBasicBLService.setT(new Mission());
        ArrayList<Mission> tmpMission=missionBasicBLService.getAllObjects();
        int index=0;
        String[] missionNames=new String[1000];
        for(Mission mission: tmpMission){
            missionNames[index]=mission.getName()+"^"+mission.getDescription();
            index++;
        }
        System.out.println(User);
        return missionNames;
    }

    @RequestMapping(value = "/getMissionDetails")
    @ResponseBody
    public ModelAndView getMissionDetais(HttpServletRequest request){
        System.out.println("Get!!!!!!!!!!!!");
        String missionName=request.getParameter("missionName");
        Mission tmpMission=missionBasicBLService.findByKey(missionName);
        int picNum=tmpMission.getFileNum();
        double credit=tmpMission.getReward();
        int expectedNum=tmpMission.getExpectedNum();
        String startTime=tmpMission.getStartTime();
        String endTime=tmpMission.getEndTime();
        String Level=tmpMission.getWorkerLevel();
        String description=tmpMission.getDescription();

        ModelAndView view= new ModelAndView("MissionDetails");
        view.addObject("missionName",missionName);
        view.addObject("picNum",picNum);
        view.addObject("credit",credit);
        view.addObject("expectedNum",expectedNum);
        view.addObject("startTime", startTime);
        view.addObject("endTime", endTime);
        view.addObject("Level",Level);
        view.addObject("description",description);
        return view;
    }




}
