package com.seproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
