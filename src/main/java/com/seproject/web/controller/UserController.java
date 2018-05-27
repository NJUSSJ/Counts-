package com.seproject.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @RequestMapping(value = "/UserManage/ban/{phoneNumber}")
    @ResponseBody
    public String ban(String phoneNumber){
        return null;
    }
    @RequestMapping(value = "/UserManage/filt")
    @ResponseBody
    /**
     * 筛选异常行为用户
     */
    public String filt(){
        return null;
    }
}
