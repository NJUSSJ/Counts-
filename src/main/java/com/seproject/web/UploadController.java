package com.seproject.web;

import com.seproject.domain.Mission;
import com.seproject.domain.User;
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
    BasicBLService<Mission> missionBasicBLService=new BasicBLService<Mission>(new Mission());
    BasicBLService<User> userBasicBLService=new BasicBLService<User>(new User());


    @RequestMapping(value = "/upload.html")
    public ModelAndView test(HttpServletRequest request){
        return new ModelAndView("upload","requestorNum",request.getParameter("userPhone"));
    }

    @RequestMapping(value = "/uploadFinish.html")
    @ResponseBody
    public ModelAndView finish(HttpServletRequest request){
        System.out.println(request.getParameter("phoneNumber"));
        String phoneNumber=request.getParameter("phoneNumber");
        User tmpUser=userBasicBLService.findByKey(phoneNumber);
        ModelAndView view=new ModelAndView("Main");
        view.addObject("userCategory",tmpUser.getCategory());
        view.addObject("userName",tmpUser.getUserName());
        view.addObject("phoneNumber", tmpUser.getPhoneNumber());
        return view;

    }

    @RequestMapping(value = "/uploadFile")
    @ResponseBody
    public String addMission(@RequestBody String MissionJASON)throws IOException{
        System.out.println(MissionJASON);
        JSONObject jsonObject=new JSONObject().fromObject(MissionJASON);
        Mission mission=(Mission)JSONObject.toBean(jsonObject,Mission.class);
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
            String requestorPhone=request.getParameter("requestorPhone");
            int reward=Integer.parseInt(request.getParameter("reward"));
            int expectedNum=Integer.parseInt(request.getParameter("expectedNum"));
            tmpMission=new Mission();
            tmpMission.setName(missionName);
            tmpMission.setWorkerLevel(workLevel);
            tmpMission.setStartTime(startTime);
            tmpMission.setEndTime(endTime);
            tmpMission.setDescription(description);
            tmpMission.setReward(reward);
            tmpMission.setExpectedNum(expectedNum);
            tmpMission.setRequestorNumber(requestorPhone);
            tmpMission.setFileNum(0);
            missionBasicBLService.add(tmpMission);

        }
        for(MultipartFile multipartFile : fileMap.values()) {
            String picAddress=getOutputFilename(multipartFile,Integer.parseInt(request.getParameter("indexPic")),missionName);
            ArrayList<String> tmpFiles=tmpMission.getFiles();
            tmpFiles.add(picAddress);
            int index=tmpMission.getFileNum();
            index++;
            tmpMission.setFileNum(index);
            missionBasicBLService.update(tmpMission);
            saveFileToLocalDisk(multipartFile, request.getParameter("name"),Integer.parseInt(request.getParameter("indexPic")));
        }
        return "";
    }

    @RequestMapping(value = "/findMission")
    @ResponseBody
    public Boolean findMission(@RequestBody String missionName){
        return missionBasicBLService.checkKeyExists(missionName);
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
}
