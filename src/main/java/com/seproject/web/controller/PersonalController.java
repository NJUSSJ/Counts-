package com.seproject.web.controller;

import com.seproject.domain.Collection;
import com.seproject.domain.Mission;
import com.seproject.domain.User;
import com.seproject.service.Factory;
import com.seproject.service.blService.BasicBLService;
import com.seproject.common.SearchCategory;
import com.seproject.web.parameter.MissionParameter;
import com.seproject.web.parameter.PersonalParameter;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
public class PersonalController {

    private BasicBLService<User> userBasicBLService= Factory.getBasicBLService(new User());
    private BasicBLService<Collection> collectionBasicBLService=Factory.getBasicBLService(new Collection());
    private BasicBLService<Mission> missionBasicBLService=Factory.getBasicBLService(new Mission());

    @RequestMapping(value = "/personal.html")
    public ModelAndView getPersonalInfo(HttpServletRequest request){
        ModelAndView model = new ModelAndView("Personal");
        String userName = request.getParameter("userName");
        model.addObject("userName",userName);
        String phoneNumber = request.getParameter("phoneNumber");
        model.addObject("phoneNumber", phoneNumber);
        User user = userBasicBLService.findByKey(phoneNumber);
        String password = user.getPassword();
        model.addObject("password", password);
        return model;
    }

    @RequestMapping(value = "/readPersonal")
    @ResponseBody
    public String readPersonal(@RequestBody String phoneNumberObj) {
        String phoneNumber = phoneNumberObj.substring(phoneNumberObj.indexOf(":")+1,phoneNumberObj.length()-1);
        User user = userBasicBLService.findByKey(phoneNumber);
        JSONObject json = JSONObject.fromObject(user);//将java对象转换为json对象
        String res = json.toString();//将json对象转换为字符串
        return res;
    }

    @RequestMapping(value = "/writePersonal")
    @ResponseBody
    public String writePersonal(@RequestBody String personalInfo){
        JSONObject obj = new JSONObject().fromObject(personalInfo);
        User user = (User)JSONObject.toBean(obj,User.class);
        userBasicBLService.update(user);

        return "write personal info success";
    }


    //筛选用户的标注过的mission
    @RequestMapping(value = "/getPersonalCollectionInfo")
    @ResponseBody
    public ArrayList<String> getCollectionInfo(@RequestBody String userInfo) {
        String phoneNumber = userInfo.substring(16,27);//获取手机号
        String category = userInfo.substring(40,41);
        if(Integer.parseInt(category) == 2) {
            ArrayList<Collection> tmpMission = collectionBasicBLService.search("uid", SearchCategory.EQUAL, phoneNumber);
            ArrayList<String > collectionNames =new ArrayList<String>();
            for(int i=0;i<tmpMission.size();i++){
                collectionNames.add(tmpMission.get(i).getMid());
            }
            return collectionNames;
        }else if(Integer.parseInt(category) == 1){
            ArrayList<Mission> tmpMission = missionBasicBLService.search("requestorNumber", SearchCategory.EQUAL, phoneNumber);
            ArrayList<String > collectionNames =new ArrayList<String>();
            for(int i=0;i<tmpMission.size();i++){
                collectionNames.add(tmpMission.get(i).getName());
            }
            return collectionNames;
        }else{
            ArrayList<Mission> tmpMission = missionBasicBLService.getAllObjects();
            ArrayList<String > collectionNames =new ArrayList<String>();
            for(int i=0;i<tmpMission.size();i++){
                collectionNames.add(tmpMission.get(i).getName());
            }
            return collectionNames;
        }
    }

    @RequestMapping(value = "/Icon")
    @ResponseBody
    public String chanegIcon(@RequestBody String personalPara){
        PersonalParameter para=toPersonalPara(personalPara);
        int icon=para.getIcon();
        String uid=para.getUid();
        User user=userBasicBLService.findByKey(uid);
        user.setIcon(icon);
        userBasicBLService.update(user);
        return "update success";
    }

    @RequestMapping(value = "/Topup")
    @ResponseBody
    public String topUp(@RequestBody String personalPara){
        PersonalParameter para=toPersonalPara(personalPara);
        double credit=para.getCredit();
        String uid=para.getUid();
        User user=userBasicBLService.findByKey(uid);
        double temp=user.getCredit();
        user.setCredit(temp+credit);
        userBasicBLService.update(user);
        return "top up success";
    }

    /*****************************************************/
    /**
     * Json字符串转Personal对象
     */
    public PersonalParameter toPersonalPara(String jsonString){
        JSONObject object=JSONObject.fromObject(jsonString);
        PersonalParameter para=(PersonalParameter) JSONObject.toBean(object,PersonalParameter.class);
        return para;
    }

    /**
     *对象转json字符串
     */
    public String toJsonString(Object o){
        JSONObject jsonObject = JSONObject.fromObject(o);
        String ret = jsonObject.toString();
        return ret;
    }

}