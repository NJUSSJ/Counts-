package com.seproject.web;

import com.seproject.service.FileIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PersonalController {
    private FileIOService fileIOService;

    @RequestMapping(value = "/personal.html")
    public ModelAndView getPersonalInfo(HttpServletRequest request){
        ModelAndView model = new ModelAndView("Personal");
        return model;
    }

    @RequestMapping(value = "/backMain.html")
    public ModelAndView getBackMain(HttpServletRequest request){
        ModelAndView model = new ModelAndView("Main");
        return model;
    }

    @RequestMapping(value = "/readPersonal")
    @ResponseBody
    public String readPersonal(@RequestBody String personalInfo) {
        System.out.print(personalInfo);
        String [] temp1=personalInfo.split(":");
        String [] temp2=temp1[1].split(",");
        String phoneNumber=temp2[0];
        String res = fileIOService.getPersonalJsonString(phoneNumber);
        if(!res.startsWith("{")){
            res="{"+res;
            res=res+"}";
        }
        return res;
    }

    @RequestMapping(value = "/writePersonal")
    @ResponseBody
    public String writePersonal(@RequestBody String phoneNumber){
        System.out.print("获取到的用户电话号码" + phoneNumber);
        fileIOService.updateUserFile(phoneNumber);
        return null;
    }


    @Autowired
    public void setFileIOService(FileIOService fileIOService){this.fileIOService=fileIOService ;}
}