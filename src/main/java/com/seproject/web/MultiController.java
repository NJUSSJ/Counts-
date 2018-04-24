/**
 * @authr fortune
 */
package com.seproject.web;

import com.seproject.domain.Mission;
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
    private BasicBLService<Mission> missionBasicBLService=new BasicBLService<Mission>(new Mission());

    @RequestMapping(value = "/test1.html")
    public ModelAndView getTagPage(HttpServletRequest request){
        String userName = request.getParameter("userName");
        String phoneNumber = request.getParameter("phoneNumber");
        String sufixx="\'../../images/";
        String url=sufixx+request.getParameter("collection")+"_"+request.getParameter("imageURL")+".jpg\'";
        ModelAndView model=new ModelAndView("SingleEdit");
        model.addObject("url",url);
        model.addObject("userName",userName);
        model.addObject("phoneNumber",phoneNumber);
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

    @RequestMapping(value = "/getMisionDetais")
    @ResponseBody
    public ModelAndView getMissionDetais(HttpServletRequest request){
        String missionName=request.getParameter("missionName");
        Mission tmpMission=missionBasicBLService.findByKey(missionName);
        int picNum=tmpMission.getFileNum();
        ModelAndView view= new ModelAndView("MissionDetails");
        view.addObject("missionName",missionName);
        view.addObject("picNum",picNum);
        return view;
    }




}
