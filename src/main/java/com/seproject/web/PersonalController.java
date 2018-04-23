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
    private FileIOService fileIOService;
    private BasicBLService<User> basicBLService=new BasicBLService<User>(new User());
    private BasicBLService<Collection> collectionBasicBLService=new BasicBLService<Collection>(new Collection());

    @RequestMapping(value = "/personal.html")
    public ModelAndView getPersonalInfo(HttpServletRequest request){
        ModelAndView model = new ModelAndView("Personal");
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
        System.out.println("phoneNumber: " + phoneNumber);
        User user = basicBLService.findByKey(phoneNumber);
        JSONObject json = JSONObject.fromObject(user);//将java对象转换为json对象
        String res = json.toString();//将json对象转换为字符串
        System.out.println("readPersonal：" + res);
        return res;
    }

    @RequestMapping(value = "/writePersonal")
    @ResponseBody
    public String writePersonal(@RequestBody String personalInfo){
        System.out.println("获取到的用户信息: " + personalInfo);
        JSONObject obj = new JSONObject().fromObject(personalInfo);
        User user = (User)JSONObject.toBean(obj,User.class);
        basicBLService.update(user);

        return "ret";
    }


    //筛选用户的标注过的mission
    @RequestMapping(value = "/getPersonalCollectionInfo")
    @ResponseBody
    public String[] getCollectionInfo(@RequestBody String userInfo) {
        System.out.println("接收到的userInfo: " + userInfo);
        String phoneNumber = userInfo.substring(16,27);//获取手机号
        System.out.println(phoneNumber);
        collectionBasicBLService.setT(new Collection());
        ArrayList<Collection> tmpMission = collectionBasicBLService.search("uid",SearchCategory.EQUAL,phoneNumber);
        int index=0;
        String[] collectionNames=new String[1000];
        for(Collection collection: tmpMission){
            collectionNames[index]= String.valueOf(collection);
            index++;
        }
        System.out.println(collectionNames);
        return collectionNames;
    }

    @Autowired
    public void setFileIOService(FileIOService fileIOService){this.fileIOService=fileIOService ;}


}