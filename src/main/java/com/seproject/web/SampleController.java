/**
 * @author Fortune
 */
package com.seproject.web;

import com.seproject.domain.Mission;
import com.seproject.service.BasicBLService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {

    BasicBLService<Mission> missionBasicBLService=new BasicBLService<Mission>(new Mission());

    @RequestMapping(value = "/getSample")
    public void getSample(@RequestBody String missionName){
        Mission tmpMission=missionBasicBLService.findByKey(missionName);
        int state=tmpMission.getState();
        if(state==0){

        }else{

        }
    }
}
