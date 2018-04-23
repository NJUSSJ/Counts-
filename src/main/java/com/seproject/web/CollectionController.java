/**
 * @author fortune
 */
package com.seproject.web;

import com.seproject.domain.Mission;
import com.seproject.domain.User;
import com.seproject.service.BasicBLService;
import com.seproject.service.FileIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CollectionController {


    private FileIOService fileIOService;
    private BasicBLService<User> basicBLService=new BasicBLService<User>(new User());
    private BasicBLService<Mission> missionBasicBLService=new BasicBLService<Mission>(new Mission());

    @RequestMapping(value = "/details.html")
    public ModelAndView getDetailofCollection(HttpServletRequest request){
        int picNum;
        String missionName=request.getParameter("imageURL");
        picNum=missionBasicBLService.findByKey(missionName).getFileNum();
        String fixx="\'../../images/";
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

        return model;
    }

    @RequestMapping(value = "/addMissionToUser")
    @ResponseBody
    public void addMissionToUser(){

    }
}
