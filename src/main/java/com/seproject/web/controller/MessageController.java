package com.seproject.web.controller;

import com.seproject.web.parameter.MessageParameter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @RequestMapping(value = "/SendMessage")
    @ResponseBody
    public String send(@RequestBody String messageParamter){
        return null;
    }
    @RequestMapping(value = "/DeleteMessage")
    @ResponseBody
    public String delete(@RequestBody String messageParamter){
        return null;
    }
    @RequestMapping(value = "/GetMessage")
    @ResponseBody
    public String get(@RequestBody String phoneNumber){
        return null;
    }
}

