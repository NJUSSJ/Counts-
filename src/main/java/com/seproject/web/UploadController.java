package com.seproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Fortune
 */

@Controller
public class UploadController {


    @RequestMapping(value = "/upload.html")
    public ModelAndView test(){
        return new ModelAndView("upload");
    }

    @RequestMapping(value = "/uploadFile")
    @ResponseBody
    public String addMission(@RequestBody String MissonJASON){
        System.out.print(MissonJASON);
        return "666";
    }
}
