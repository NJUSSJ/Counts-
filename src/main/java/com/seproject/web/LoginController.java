package com.seproject.web;

import com.seproject.domain.Mission;
import com.seproject.service.BasicBLService;
import com.seproject.service.FileIOService;
import com.seproject.service.UserService;
import com.seproject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;

@RestController
public class LoginController {

    private BasicBLService<User> basicBLService;
    private BasicBLService<Mission> missionBasicBLService;

	@RequestMapping(value = "/")
	public ModelAndView loginPage(){

		return new ModelAndView("Login");
	}
	
	@RequestMapping(value = "/loginCheck.html")
		public ModelAndView loginCheck(HttpServletRequest request,User user) {
		ModelAndView view = new ModelAndView("Main");
		basicBLService.setT(new User());
		boolean existed=false;
		User tmpUser=basicBLService.findByKey(request.getParameter("userName"));
		if(tmpUser!=null){
		    existed=true;
        }
		if(!existed){
			System.out.println("not existed");
			return new ModelAndView("Login", "error", "用户不存在");
		}else {
			User realUser=basicBLService.findByKey(user.getUserName());
			if(realUser.getPassword().equals(user.getPassword())){
			    missionBasicBLService.setT(new Mission());
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
				return new ModelAndView("Login", "error", "密码错误。");
			}
		}
	}

	@RequestMapping(value = "/signUpCheck.html")
	public ModelAndView signUpCheck(HttpServletRequest request,User user){
		ModelAndView view = new ModelAndView("Main");

		System.out.println(user.getUserName()+"/"+user.getPassword()+"!!!!");
		basicBLService.add(user);
		boolean result=true;
		if(!result){
			return new ModelAndView("Login", "error", "用户不存在。");
		}else{
			/*User realUser=basicBLService.findByKey(phoneNumber);
			if(user.getPassword().equals(realUser.getPassword())){
				return view;
			}else{
				return new ModelAndView("Login", "error", "密码错误。");
			}*/
			view.addObject("userName",user.getUserName());
			view.addObject("phoneNumber",user.getPhoneNumber());
		}
		return view;
	}

	@Autowired
	public void setBasicBLService(BasicBLService<User> basicBLService){
	    this.basicBLService=basicBLService;
		this.basicBLService.setT(new User());
	}
	@Autowired
    public void setMissionBasicBLService(BasicBLService<Mission> missionBasicBLService){
	    this.missionBasicBLService=missionBasicBLService;
	    this.missionBasicBLService.setT(new Mission());
    }
}
