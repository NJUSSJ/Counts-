package com.seproject.web.controller;

import com.seproject.domain.Mission;
import com.seproject.service.Factory;
import com.seproject.service.blService.BasicBLService;
import com.seproject.domain.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
public class LoginController {

    private BasicBLService<User> basicBLService= Factory.getBasicBLService(new User());
    private BasicBLService<Mission> missionBasicBLService=Factory.getBasicBLService(new Mission());

	@RequestMapping(value = "/")
	public ModelAndView loginPage(){
		return new ModelAndView("Login");
	}
	
	@RequestMapping(value = "/loginCheck.html")
		public ModelAndView loginCheck(HttpServletRequest request,User user) {
		ModelAndView view = new ModelAndView("Main");
		boolean existed=false;
		User tmpUser=basicBLService.findByKey(request.getParameter("userName"));
		if(tmpUser!=null){
		    existed=true;
        }
		if(!existed){
			//System.out.println("not existed");
			return new ModelAndView("Login", "error", "\'用户不存在\'");
		}else {
			User realUser=basicBLService.findByKey(user.getUserName());
			if(realUser.getPassword().equals(user.getPassword())){
                ArrayList<Mission> missions=missionBasicBLService.getAllObjects();
                ArrayList<String> missionNames=new ArrayList<String>();
                int index=0;
                for(Mission mission: missions){
                    missionNames.add("\""+mission.getName()+"\"");
                    index++;
                }
				view.addObject("userName",realUser.getUserName());
				view.addObject("phoneNumber",realUser.getPhoneNumber());
				view.addObject("userCategory", realUser.getCategory());
				return view;
			}else{
			    System.out.println("Wrong!");
				return new ModelAndView("Login", "error", "\'密码错误\'");
			}
		}
	}

	@RequestMapping(value = "/signUpCheck.html")
	public ModelAndView signUpCheck(HttpServletRequest request,User user){
		ModelAndView view = new ModelAndView("Main");

		basicBLService.add(user);
		boolean result=true;
		if(!result){
			return new ModelAndView("Login", "error", "用户不存在。");
		}else{
			view.addObject("userName",user.getUserName());
			view.addObject("phoneNumber",user.getPhoneNumber());
		}
		return view;
	}
	@RequestMapping(value = "/SignUp")
	@ResponseBody
	public String signUp(String phoneNumber,String password){
		return null;
	}

	@RequestMapping(value = "/Login/changePassword")
	@ResponseBody
	public String changePassword(String phoneNumber,String password){
		return null;
	}

}
