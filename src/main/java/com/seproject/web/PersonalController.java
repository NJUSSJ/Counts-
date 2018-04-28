package com.seproject.web;

import com.seproject.domain.Collection;
import com.seproject.domain.Mission;
import com.seproject.domain.User;
import com.seproject.service.BasicBLService;
import com.seproject.service.FileIOService;
import com.seproject.service.SearchCategory;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
public class PersonalController {

    private BasicBLService<User> basicBLService=new BasicBLService<User>(new User());
    private BasicBLService<Collection> collectionBasicBLService=new BasicBLService<Collection>(new Collection());
    private BasicBLService<Mission> missionBasicBLService=new BasicBLService<Mission>(new Mission());

    @RequestMapping(value = "/personal.html")
    public ModelAndView getPersonalInfo(HttpServletRequest request){
        ModelAndView model = new ModelAndView("Personal");
        String userName = request.getParameter("userName");
        model.addObject("userName",userName);
        String phoneNumber = request.getParameter("phoneNumber");
        model.addObject("phoneNumber", phoneNumber);
        User user = basicBLService.findByKey(phoneNumber);
        String password = user.getPassword();
        model.addObject("password", password);
        return model;
    }

    /*
    @RequestMapping(value = "/backMain.html")
    public ModelAndView getBackMain(HttpServletRequest request){
        ModelAndView model = new ModelAndView("Main");
        return model;
    }*/

    @RequestMapping(value = "/readPersonal")
    @ResponseBody
    public String readPersonal(@RequestBody String phoneNumberObj) {
        String phoneNumber = phoneNumberObj.substring(phoneNumberObj.indexOf(":")+1,phoneNumberObj.length()-1);
        User user = basicBLService.findByKey(phoneNumber);
        JSONObject json = JSONObject.fromObject(user);//将java对象转换为json对象
        String res = json.toString();//将json对象转换为字符串
        return res;
    }

    @RequestMapping(value = "/writePersonal")
    @ResponseBody
    public String writePersonal(@RequestBody String personalInfo){
        JSONObject obj = new JSONObject().fromObject(personalInfo);
        User user = (User)JSONObject.toBean(obj,User.class);
        basicBLService.update(user);

        return "write personal info success";
    }


    //筛选用户的标注过的mission
    @RequestMapping(value = "/getPersonalCollectionInfo")
    @ResponseBody
    public ArrayList<String> getCollectionInfo(@RequestBody String userInfo) {
        String phoneNumber = userInfo.substring(16,27);//获取手机号
        String category = userInfo.substring(40,41);
        if(Integer.parseInt(category) == 2) {
            collectionBasicBLService.setT(new Collection());
            ArrayList<Collection> tmpMission = collectionBasicBLService.search("uid", SearchCategory.EQUAL, phoneNumber);
            ArrayList<String > collectionNames =new ArrayList<String>();
            for(int i=0;i<tmpMission.size();i++){
                collectionNames.add(tmpMission.get(i).getMid());
            }
            return collectionNames;
        }else if(Integer.parseInt(category) == 1){
            missionBasicBLService.setT(new Mission());
            ArrayList<Mission> tmpMission = missionBasicBLService.search("requestorNumber", SearchCategory.EQUAL, phoneNumber);
            ArrayList<String > collectionNames =new ArrayList<String>();
            for(int i=0;i<tmpMission.size();i++){
                collectionNames.add(tmpMission.get(i).getName());
            }
            return collectionNames;
        }else{
            ArrayList<Mission> tmpMission = missionBasicBLService.getAllObjects();
            ArrayList<String > collectionNames =new ArrayList<String>();
            for(int i=0;i<tmpMission.size();i++){
                collectionNames.add(tmpMission.get(i).getName());
            }
            return collectionNames;
        }
        /*  int index=0;
        String[] collectionNames=new String[1000];
        for(Collection collection: tmpMission){
            collectionNames[index]= collection.getMid();
            System.out.println("返回前端的collectionNames=" + collectionNames[index]);
            index++;
        }*/
    }


}