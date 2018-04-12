package com.seproject.web;

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
	@RequestMapping(value = "/")
	public ModelAndView loginPage(){

		return new ModelAndView("Login");
	}
	
	@RequestMapping(value = "/loginCheck.html")
		public ModelAndView loginCheck(HttpServletRequest request, LoginCommand loginCommand) {
		/*=boolean isValidUser =  userService.hasMatchUser(loginCommand.getUserName(),
					                    loginCommand.getPassword());
		if (!isValidUser) {
		    System.out.println(loginCommand.getUserName() + " " +
                    loginCommand.getPassword());
			System.out.print("Not right log in message!");
			return new ModelAndView("Login", "error", "用户名或密码错误。");
		} else {
			User user = userService.findUserByUserName(loginCommand
					.getUserName());
			user.setLastIp(request.getLocalAddr());
			user.setLastVisit(new Date());
			userService.loginSuccess(user);
			//user.setUserName("ab");
			request.getSession().setAttribute("user", user);//给前端返回对象
			return new ModelAndView("Main");
		}*/

		ModelAndView view = new ModelAndView("Main");
		view.addObject("username",loginCommand.getUserName());
		view.addObject("phoneNumber",loginCommand.getPhoneNumber());

		String result = fileIOService.readUserFile("user");
		String[] check = result.split("\n");
		if (check[0].equals(loginCommand.getUserName()) && check[1].equals(loginCommand.getPassword())) {
			return view;
		} else {
			return new ModelAndView("Login", "error", "用户名或密码错误。");
		}
	}

	@RequestMapping(value = "/signUpCheck.html")
	public ModelAndView signUpCheck(HttpServletRequest request, LoginCommand loginCommand){
		/*=boolean isValidUser =  userService.hasMatchUser(loginCommand.getUserName(),
					                    loginCommand.getPassword());
		if (!isValidUser) {
		    System.out.println(loginCommand.getUserName() + " " +
                    loginCommand.getPassword());
			System.out.print("Not right log in message!");
			return new ModelAndView("Login", "error", "用户名或密码错误。");
		} else {
			User user = userService.findUserByUserName(loginCommand
					.getUserName());
			user.setLastIp(request.getLocalAddr());
			user.setLastVisit(new Date());
			userService.loginSuccess(user);
			//user.setUserName("ab");
			request.getSession().setAttribute("user", user);//给前端返回对象
			return new ModelAndView("Main");
		}*/
		/*
		String result=fileIOService.readUserFile("user");
		String [] check=result.split("\n");
		if(check[0].equals(loginCommand.getUserName())&&check[1].equals(loginCommand.getPassword())){
			return new ModelAndView("Main");
		}else{
			return new ModelAndView("Login", "error", "用户名或密码错误。");
		}
*/

		ModelAndView view = new ModelAndView("Main");
		view.addObject("username",loginCommand.getUserName());
		view.addObject("phoneNumber",loginCommand.getPhoneNumber());

		String tmp = loginCommand.getUserName() + loginCommand.getPassword();
		boolean result = fileIOService.writeUserFile("user",tmp);
		if(result){
			return view;
		}else{
			return new ModelAndView("Login", "error", "用户名或密码错误。");
		}
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Autowired
	public void setFileIOService(FileIOService fileIOService){this.fileIOService=fileIOService;}

}
