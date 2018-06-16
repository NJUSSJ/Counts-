package com.seproject.service;

import com.seproject.common.RM;
import com.seproject.common.SearchCategory;
import com.seproject.domain.Message;
import com.seproject.service.blService.BasicBLService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 消息系统的逻辑
 */
@Service
public class NewsService {
    private BasicBLService<Message> messageBasicBLService=Factory.getBasicBLService(new Message());
    public RM sendMessage(Message message){
        RM rm=messageBasicBLService.add(message);
        if(rm.equals(RM.SUCCESS)){
            return rm;
        }else{
            return RM.FAILURE;
        }
    }

    public RM deleteMessage(String keyID){
        RM rm=messageBasicBLService.delete(keyID);
        if(rm.equals(RM.SUCCESS)){
            return rm;
        }else{
            return RM.FAILURE;
        }
    }

    public ArrayList<Message> getAllMessages(String uid){
        return messageBasicBLService.search("receiverID", SearchCategory.EQUAL,uid);
    }
}
