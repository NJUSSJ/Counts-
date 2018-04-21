package com.seproject.web;

import com.seproject.domain.Mission;
import com.seproject.service.BasicBLService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
    BasicBLService<Mission> missionBasicBLService;


    @RequestMapping(value = "/upload.html")
    public ModelAndView test(){
        return new ModelAndView("upload");
    }

    @RequestMapping(value = "/uploadFile")
    @ResponseBody
    public String addMission(@RequestBody String MissionJASON)throws IOException{
        System.out.println(MissionJASON);
        JSONObject jsonObject=new JSONObject().fromObject(MissionJASON);
        Mission mission=(Mission)JSONObject.toBean(jsonObject,Mission.class);
        missionBasicBLService.setT(new Mission());//lala
        Mission tmpMission=missionBasicBLService.findByKey(mission.getName());
        tmpMission.setName(mission.getName());

        missionBasicBLService.update(tmpMission);
        return "success";
    }

    @RequestMapping(value = "/uploadPics", method = RequestMethod.POST)
    @ResponseBody
    public synchronized String addPics(MultipartHttpServletRequest request)throws IOException{
        Map<String, MultipartFile> fileMap = request.getFileMap();
        String missionName=request.getParameter("name");
        System.out.println("MIssionName:"+missionName);
        missionBasicBLService.setT(new Mission());//lala
        Mission tmpMission=missionBasicBLService.findByKey(missionName);
        if(tmpMission==null){
            String startTime=request.getParameter("startTime");
            String endTime=request.getParameter("endTime");
            String description=request.getParameter("description");
            String workLevel=request.getParameter("workLevel");
            tmpMission=new Mission();
            tmpMission.setName(missionName);
            tmpMission.setWorkerLevel(workLevel);
            tmpMission.setStartTime(startTime);
            tmpMission.setEndTime(endTime);
            tmpMission.setDescription(description);
            tmpMission.setFileNum(0);
            missionBasicBLService.setT(new Mission());
            missionBasicBLService.add(tmpMission);

        }
        for(MultipartFile multipartFile : fileMap.values()) {
            String picAddress=getOutputFilename(multipartFile,Integer.parseInt(request.getParameter("indexPic")),missionName);
            ArrayList<String> tmpFiles=tmpMission.getFiles();
            tmpFiles.add(picAddress);
            int index=tmpMission.getFileNum();
            index++;
            tmpMission.setFileNum(index);
            missionBasicBLService.setT(new Mission());
            missionBasicBLService.update(tmpMission);
            saveFileToLocalDisk(multipartFile, request.getParameter("name"),Integer.parseInt(request.getParameter("indexPic")));
        }
        return "";
    }


    private void saveFileToLocalDisk(MultipartFile multipartFile, String missionName,int i) throws IOException,
            FileNotFoundException {

        String outputFileName = getOutputFilename(multipartFile,i, missionName);

        FileCopyUtils.copy(multipartFile.getBytes(), new FileOutputStream(outputFileName));
    }

    private String getOutputFilename(MultipartFile multipartFile,int i, String missionName) {


        String suffix=multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));

        return "src\\main\\webapp\\images\\"+missionName+"_"+i+suffix;
    }
    @Autowired
    public void setMissionBasicBLService(BasicBLService<Mission> missionBasicBLService){
        this.missionBasicBLService=missionBasicBLService;
        missionBasicBLService.setT(new Mission());
    }

}
