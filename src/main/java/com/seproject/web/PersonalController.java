package com.seproject.web;

import com.seproject.service.FileIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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


    @Autowired
    public void setFileIOService(FileIOService fileIOService){this.fileIOService=fileIOService ;}
}