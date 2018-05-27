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

}
