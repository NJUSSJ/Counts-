package com.seproject.web.controller;

import com.seproject.domain.User;
import com.seproject.service.Factory;
import com.seproject.service.blService.BasicBLService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    //BasicBLService<User> userBasicBLService= Factory.getBasicBLService(new User());
    BasicBLService<User> userBasicBLService= Factory.getUserBasicBLService();
    @RequestMapping(value = "/UserManage/ban/{phoneNumber}")
    @ResponseBody
    public String ban(String phoneNumber){
        User user=userBasicBLService.findByKey(phoneNumber);
        user.setState(-1);
        userBasicBLService.update(user);
        return "ban success";
    }
    @RequestMapping(value = "/Qualify")
    @ResponseBody
    /**
     * 大v认证
     */
    public String qualify(String phoneNumber){
        User user=userBasicBLService.findByKey(phoneNumber);
        user.setState(1);
        userBasicBLService.update(user);
        return "qualify success";
    }
}
