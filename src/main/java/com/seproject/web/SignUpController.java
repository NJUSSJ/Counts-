package com.seproject.web;

import com.seproject.service.FileIOService;
import com.seproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SignUpController {
    private UserService userService;
    private FileIOService fileIOService;

    @RequestMapping(value = "/")
    public ModelAndView signUpPage() {

        return new ModelAndView("SignUp");
    }

    @RequestMapping(value = "/signupCheck.html")
    public ModelAndView signUpCheck(HttpServletRequest request, SignUpCommand signUpCommand) {
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
        String result = fileIOService.readUserFile("user");
        String[] check = result.split("\n");
        if (check[0].equals(signUpCommand.getUserName()) && check[1].equals(signUpCommand.getPassword())) {
            return new ModelAndView("Main");
        } else {
            return new ModelAndView("SignUp", "error", "用户名或密码错误。");
        }
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setFileIOService(FileIOService fileIOService){this.fileIOService=fileIOService;}

}
