package com.seproject.web;

import com.seproject.common.Constant;
import com.seproject.domain.Mission;
import com.seproject.domain.User;
import com.seproject.service.Factory;
import com.seproject.service.MathService;
import com.seproject.service.blService.BasicBLService;
import com.seproject.web.parameter.CalRewardParameter;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * @author Fortune
 */

@Controller
public class UploadController {
    BasicBLService<Mission> missionBasicBLService=Factory.getBasicBLService(new Mission());
    BasicBLService<User> userBasicBLService= Factory.getBasicBLService(new User());


    @RequestMapping(value = "/upload.html")
    public ModelAndView test(HttpServletRequest request){
        return new ModelAndView("upload","requestorNum",request.getParameter("userPhone"));
    }

    @RequestMapping(value = "/uploadFinish.html")
    @ResponseBody
    public ModelAndView finish(HttpServletRequest request){
        String phoneNumber=request.getParameter("phoneNumber");
        User tmpUser=userBasicBLService.findByKey(phoneNumber);
        ModelAndView view=new ModelAndView("Main");
        view.addObject("userCategory",tmpUser.getCategory());
        view.addObject("userName",tmpUser.getUserName());
        view.addObject("phoneNumber", tmpUser.getPhoneNumber());
        return view;

    }


    @RequestMapping(value = "/uploadPics", method = RequestMethod.POST)
    @ResponseBody
    public synchronized String addPics(MultipartHttpServletRequest request)throws IOException{
        Map<String, MultipartFile> fileMap = request.getFileMap();
        String missionName=request.getParameter("name");
        Mission tmpMission=missionBasicBLService.findByKey(missionName);
        if(tmpMission==null){
            String startTime=request.getParameter("startTime");
            String endTime=request.getParameter("endTime");
            String description=request.getParameter("description");
            String workLevel=request.getParameter("workLevel");
            String requestorPhone=request.getParameter("requestorPhone");
            double reward=Double.parseDouble(request.getParameter("reward"));
            int difficulty = Integer.parseInt(request.getParameter("difficulty"));
            String picType = request.getParameter("picType");
            int tagType = Integer.parseInt(request.getParameter("tagType"));
            String missionLabelString = request.getParameter("missionLabel");
            //System.out.println("missionLabelString: " + missionLabelString);
            //如果以数组传过来 传过来的missionLabel为空 根本接收不到 所以我传String 处理一下再存
            //String[] tmp = missionLabelString.substring(1,missionLabelString.length()-1).split(",");
            //ArrayList<String> missionLabel = new ArrayList<String>();
            //for(int i = 0;i<tmp.length;i++){
              //  missionLabel.set(i, tmp[i]);
            //}
            ArrayList<String> missionLabel = new ArrayList<String>();
            String[] tmp = missionLabelString.split(" ");
            int index = 0;
            for(int i = 0;i<tmp.length;i++){
                if(!tmp[i].equals("") && tmp[i] != null) {
                    missionLabel.add(tmp[i]);
                }
            }
            //System.out.println("missionLabel: "+ missionLabel.toString());
            int maxWorkerNum = Integer.parseInt(request.getParameter("maxWorkerNum"));

            tmpMission=new Mission();
            tmpMission.setName(missionName);
            tmpMission.setWorkerLevel(workLevel);
            tmpMission.setStartTime(startTime);
            tmpMission.setEndTime(endTime);
            tmpMission.setDescription(description);
            tmpMission.setReward(reward);
            tmpMission.setMaxWorkerNum(maxWorkerNum);
            tmpMission.setRequestorNumber(requestorPhone);
            tmpMission.setFileNum(0);
            tmpMission.setState(0);

            tmpMission.setDifficulty(difficulty);
            tmpMission.setPicType(picType);
            tmpMission.setTagType(tagType);
            tmpMission.setMissionLabel(missionLabel);
            tmpMission.setMaxWorkerNum(maxWorkerNum);

            missionBasicBLService.add(tmpMission);//此处需要修改
            User tmpUser=userBasicBLService.findByKey(request.getParameter("requestorPhone"));
            tmpUser.setCredit(tmpUser.getCredit()-reward);
            userBasicBLService.update(tmpUser);

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

    @RequestMapping(value = "/findEnough")
    @ResponseBody
    /**
     * 检测发起者余额是否充足
     */
    public Boolean findEnough(@RequestBody String missionReward){
        String userPhone=missionReward.split("#")[1];
        double settedReward=Double.parseDouble(missionReward.split("#")[0]) ;
        double existedReward=userBasicBLService.findByKey(userPhone).getCredit();
        if(settedReward>existedReward){
            return false;
        }
        return true;
    }
    private void saveFileToLocalDisk(MultipartFile multipartFile, String missionName,int i) throws IOException,
            FileNotFoundException {

        String outputFileName = getOutputFilename(multipartFile,i, missionName);

        FileCopyUtils.copy(multipartFile.getBytes(), new FileOutputStream(outputFileName));
    }

    private String getOutputFilename(MultipartFile multipartFile,int i, String missionName) {

        String suffix=multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        String path="";
        try {
            File nav=  ResourceUtils.getFile("classpath:resources/missionImages/navigation.txt");
            path=nav.getAbsolutePath();
            path=path.replace("\\","/");
            int index=path.lastIndexOf("/");
            path=path.substring(0,index);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return path+"/"+missionName+"_"+i+suffix;
    }

    @RequestMapping(value = "/calReward", method = RequestMethod.POST)
    @ResponseBody
    public double calReward(@RequestBody String callRewardParameter){
        String str = callRewardParameter.replace('&', ',');
        JSONObject object= JSONObject.fromObject("{" + str + "}");
        CalRewardParameter para=(CalRewardParameter) JSONObject.toBean(object,CalRewardParameter.class);
        int difficulty=para.getDifficulty();
        double base=0;
        switch (difficulty){
            case 1:base=1;break;
            case 2:base=1.1;break;
            default:base=1.2;
        }
        User user=userBasicBLService.findByKey(para.getUid());
        int level=user.getLevel();
        double p=1+level*0.05;
        double discount=0.9-level*Constant.DISCOUNT_ON_LEVEL;//这句话存疑
        return base* Math.pow(p,Constant.TASK_NUMBER)*para.getMaxWorker()*discount;
    }


}
