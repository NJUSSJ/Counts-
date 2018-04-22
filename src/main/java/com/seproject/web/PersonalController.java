package com.seproject.web;

import com.seproject.domain.User;
import com.seproject.service.BasicBLService;
import com.seproject.service.FileIOService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PersonalController {
    private FileIOService fileIOService;
    private BasicBLService<User> basicBLService=new BasicBLService<User>(new User());

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


    @Autowired
    public void setFileIOService(FileIOService fileIOService){this.fileIOService=fileIOService ;}


}