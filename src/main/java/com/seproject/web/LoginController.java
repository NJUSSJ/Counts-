package com.seproject.web;

import com.seproject.service.BasicBLService;
import com.seproject.service.FileIOService;
import com.seproject.service.UserService;
import com.seproject.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class LoginController {
	private UserService userService;
    private FileIOService fileIOService;
    private BasicBLService<User> basicBLService;
	@RequestMapping(value = "/")
	public ModelAndView loginPage(){

		return new ModelAndView("Login");
	}
	
	@RequestMapping(value = "/loginCheck.html")
		public ModelAndView loginCheck(HttpServletRequest request,User user) {
		ModelAndView view = new ModelAndView("Main");
		boolean existed=basicBLService.checkKeyExists(user.getUserName());
		if(!existed){
			return new ModelAndView("Login", "error", "用户不存在");
		}else {
			User realUser=basicBLService.findByKey(user.getUserName());
			if(realUser.getPassword().equals(user.getPassword())){
				view.addObject("userName",realUser.getUserName());
				view.addObject("phoneNumber",realUser.getPhoneNumber());
				return view;
			}else{
				return new ModelAndView("Login", "error", "密码错误。");
			}
		}
	}

	@RequestMapping(value = "/signUpCheck.html")
	public ModelAndView signUpCheck(HttpServletRequest request,User user){
		ModelAndView view = new ModelAndView("Main");
		view.addObject("username",user.getUserName());
		view.addObject("password",user.getPassword());

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
			view.addObject("username",user.getUserName());
			//view.addObject("phoneNumber",user.getPhoneNumber());
		}
		return view;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Autowired
	public void setFileIOService(FileIOService fileIOService){this.fileIOService=fileIOService;}
	@Autowired
	public void setBasicBLService(BasicBLService<User> basicBLService){this.basicBLService=basicBLService;
		this.basicBLService.setT(new User());
	}
}
