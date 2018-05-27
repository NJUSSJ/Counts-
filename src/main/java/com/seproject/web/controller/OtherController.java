package com.seproject.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OtherController {
    @RequestMapping(value = "/Sign")
    @ResponseBody
    /**
     * 签到
     */
    public String sign(String phoneNumber){
        return null;
    }
    @RequestMapping(value = "/Qualify")
    @ResponseBody
    /**
     * 大v认证
     */
    public String qualify(String phoneNumber){
        return null;
    }
    @RequestMapping(value = "/Help")
    @ResponseBody
    /**
     * 新手帮助
     */
    public String help(String phoneNumber){
        return null;
    }
}
