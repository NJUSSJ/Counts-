package com.seproject.web;

import com.aliyuncs.exceptions.ClientException;
import com.seproject.domain.MessageService;
import com.seproject.domain.User;
import com.seproject.service.BasicBLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ValueConstants;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Controller
public class SignUpController {

    private BasicBLService<User> userService=new BasicBLService<User>(new User());
    @RequestMapping(value = "/sendVaricationCode")
    @ResponseBody
    public String sendVarication(@RequestBody String Number){
        User user=userService.findByKey(Number);
        if(user!=null){
            return 0+"";
        }
        /*
        生成验证码
         */
        Random random=new Random();
        int variCode=random.nextInt(90000)+10000;

        MessageService.setPhonrNum(Number);
        MessageService.setVariCode(variCode);
        try {
            MessageService.sendSms();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return variCode+"";
    }

    @RequestMapping (value = "/signUpDetails")
    public ModelAndView signDetails(HttpServletRequest request){

        ModelAndView modelAndView=new ModelAndView("signUpdetails");
        String Number=request.getParameter("phoneNumber");
        modelAndView.addObject("number", Number);
        return modelAndView;
    }

    @RequestMapping(value = "/setUser")
    public ModelAndView setUser(HttpServletRequest request){
        User newUser=new User();
        System.out.println(request.getParameter("phoneNumber"));
        newUser.setPhoneNumber(request.getParameter("phoneNumber"));
        newUser.setUserName(request.getParameter("userName"));
        newUser.setPassword(request.getParameter("passWord"));
<<<<<<< HEAD
        newUser.setCredit(1000);
        userService.setT(new User());//lala
=======
>>>>>>> bb99aaa1456330cc53f7efaa1343751e719b78ce
        userService.add(newUser);
        return new ModelAndView("Login");
    }
}
