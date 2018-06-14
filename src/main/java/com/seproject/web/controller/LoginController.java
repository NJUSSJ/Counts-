package com.seproject.web.controller;

import com.seproject.domain.Mission;
import com.seproject.service.Factory;
import com.seproject.service.blService.BasicBLService;
import com.seproject.domain.User;
import com.seproject.web.parameter.LoginParameter;
import com.seproject.web.parameter.PersonalParameter;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
public class LoginController {

    private BasicBLService<User> userBasicBLService= Factory.getBasicBLService(new User());
    private BasicBLService<Mission> missionBasicBLService=Factory.getBasicBLService(new Mission());

	@RequestMapping(value = "/")
	public ModelAndView loginPage(){
		return new ModelAndView("Login");
	}
	
	@RequestMapping(value = "/loginCheck.html")
		public ModelAndView loginCheck(HttpServletRequest request,User user) {

	    //View for common users
		ModelAndView view = new ModelAndView("Main");
        //View for admin
        ModelAndView viewAdmin = new ModelAndView("administer");

		boolean existed=false;
		User tmpUser=userBasicBLService.findByKey(request.getParameter("userName"));
		if(tmpUser!=null){
		    existed=true;
        }
		if(!existed){
			//System.out.println("not existed");
			return new ModelAndView("Login", "error", "\'用户不存在\'");
		}else {
			User realUser=userBasicBLService.findByKey(user.getUserName());
			if(realUser.getPassword().equals(user.getPassword())){
                ArrayList<Mission> missions=missionBasicBLService.getAllObjects();
                ArrayList<String> missionNames=new ArrayList<String>();
                int index=0;
                if(missions!=null&&missions.size()>0) {
					for (Mission mission : missions) {
						missionNames.add("\"" + mission.getName() + "\"");
						index++;
					}
				}
                if(realUser.getCategory()!=3){
                    view.addObject("userName",realUser.getUserName());
                    view.addObject("phoneNumber",realUser.getPhoneNumber());
                    view.addObject("userCategory", realUser.getCategory());
                    return view;
                }else{
                    viewAdmin.addObject("userName", realUser.getUserName());
                    viewAdmin.addObject("phoneNumber", realUser.getPhoneNumber());
                    viewAdmin.addObject("userCategory", realUser.getCategory());
                    return viewAdmin;
                }

			}else{
			    System.out.println("Wrong!");
				return new ModelAndView("Login", "error", "\'密码错误\'");
			}
		}
	}

	@RequestMapping(value = "/signUpCheck.html")
	public ModelAndView signUpCheck(HttpServletRequest request,User user){
		ModelAndView view = new ModelAndView("Main");

		userBasicBLService.add(user);
		boolean result=true;
		if(!result){
			return new ModelAndView("Login", "error", "用户不存在。");
		}else{
			view.addObject("userName",user.getUserName());
			view.addObject("phoneNumber",user.getPhoneNumber());
		}
		return view;
	}
	@RequestMapping(value = "/Login/changePassword")
	@ResponseBody
	public String changePassword(@RequestBody String parameter){
		JSONObject object=JSONObject.fromObject(parameter);
		LoginParameter para=(LoginParameter) JSONObject.toBean(object,LoginParameter.class);
		String uid=para.getUid();
		User user=userBasicBLService.findByKey(uid);
		user.setPassword(para.getPassword());
		return "change password success";
	}

}
