package com.seproject.web;

import com.seproject.domain.Mission;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.*;

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
    public String addMission(@RequestBody String MissionJASON)throws IOException{

        JSONObject jsonObject=new JSONObject().fromObject(MissionJASON);
        Mission mission=(Mission)JSONObject.toBean(jsonObject,Mission.class);

        return "success";
    }

    @RequestMapping(value = "/uploadPics", method = RequestMethod.POST)
    @ResponseBody
    public String addPics(MultipartHttpServletRequest request)throws IOException{

        Map<String, MultipartFile> fileMap = request.getFileMap();

        for(MultipartFile multipartFile : fileMap.values()) {
            saveFileToLocalDisk(multipartFile);
        }
        return "";
    }



    private void saveFileToLocalDisk(MultipartFile multipartFile) throws IOException,
            FileNotFoundException {

        String outputFileName = getOutputFilename(multipartFile,1);

        FileCopyUtils.copy(multipartFile.getBytes(), new FileOutputStream(outputFileName));
    }

    private String getOutputFilename(MultipartFile multipartFile,int i) {
        return "collections\\"+i+"_"+multipartFile.getOriginalFilename();
    }


}
