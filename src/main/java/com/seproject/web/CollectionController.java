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
        missionBasicBLService.setT(new Mission());
        picNum=missionBasicBLService.findByKey(missionName).getFileNum();
        String fixx="\'http://120.79.221.158:8080/Pictures/";
        String url=fixx+request.getParameter("imageURL")+"/\'";
        String collection="\'"+request.getParameter("imageURL")+"\'";
        ModelAndView model=new ModelAndView("MultiPic");
        model.addObject("url",url);
        model.addObject("collection", collection);
        model.addObject("picNum",picNum);
        return model;
    }


}
