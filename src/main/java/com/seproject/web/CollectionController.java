/**
 * @author fortune
 */
package com.seproject.web;

import com.seproject.service.FileIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CollectionController {
    private FileIOService fileIOService;

    @RequestMapping(value = "/details.html")
    public ModelAndView getDetailofCollection(HttpServletRequest request){
        String fixx="\'http://120.79.221.158:8080/Pictures/";
        String url=fixx+request.getParameter("imageURL")+"/\'";
        String collection="\'"+request.getParameter("imageURL")+"\'";
        ModelAndView model=new ModelAndView("MultiPic");
        model.addObject("url",url);
        model.addObject("collection", collection);
        return model;
    }

    @Autowired
    public void setFileIOService(FileIOService fileIOService){this.fileIOService=fileIOService ;}
}
