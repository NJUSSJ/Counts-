/**
 * @authr fortune
 */
package com.seproject.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MultiController {
    @RequestMapping(value = "/test1.html")
    public ModelAndView getTagPage(HttpServletRequest request){
        String sufixx="\'http://120.79.221.158:8080/Pictures/";
        String url=sufixx+request.getParameter("collection")+"/"+request.getParameter("imageURL")+".jpg\'";
        ModelAndView model=new ModelAndView("SingleEdit");
        model.addObject("url",url);
        return model;
    }
}
