package com.seproject.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @RequestMapping(value = "/SendMessage")
    @ResponseBody
    public String send(String phoneNumber,String missionName){
        return null;
    }
    @RequestMapping(value = "/DeleteMessage")
    @ResponseBody
    public String delete(String phoneNumber,int index){
        return null;
    }
    @RequestMapping(value = "/GetMessage")
    @ResponseBody
    public String get(String phoneNumber){
        return null;
    }
}

