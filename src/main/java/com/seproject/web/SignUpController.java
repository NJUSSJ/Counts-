package com.seproject.web;

import com.aliyuncs.exceptions.ClientException;
import com.seproject.service.MessageService;
import com.seproject.domain.User;
import com.seproject.service.Factory;
import com.seproject.service.blService.BasicBLService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Controller
public class SignUpController {

    private BasicBLService<User> userService= Factory.getBasicBLService(new User());
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

        System.out.println("GET USER");

        User newUser=new User();
        System.out.println(request.getParameter("phoneNumber"));
        newUser.setPhoneNumber(request.getParameter("phoneNumber"));
        newUser.setUserName(request.getParameter("userName"));
        newUser.setPassword(request.getParameter("passWord"));
        newUser.setCategory(Integer.parseInt(request.getParameter("category")));
        newUser.setCredit(1000);
        newUser.setDescription("1");
        userService.add(newUser);
        return new ModelAndView("Login");
    }
}
