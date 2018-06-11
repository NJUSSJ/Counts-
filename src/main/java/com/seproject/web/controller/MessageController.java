package com.seproject.web.controller;

import com.seproject.web.parameter.MessageParameter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MessageController {
    @RequestMapping(value = "/message.html")
    @ResponseBody
    public ModelAndView messagePage(HttpServletRequest request){
        ModelAndView messageView = new ModelAndView("Message");
        String phoneNumber = request.getParameter("phoneNumber");
        messageView.addObject("phoneNumber",phoneNumber);
        return messageView;
    }
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

