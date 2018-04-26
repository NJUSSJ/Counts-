/**
 * @author Fortune
 */
package com.seproject.web;

import com.seproject.domain.Mission;
import com.seproject.domain.Sample;
import com.seproject.service.BasicBLService;
import com.seproject.service.ReviewService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    BasicBLService<Mission> missionBasicBLService=new BasicBLService<Mission>(new Mission());
    ReviewService reviewService=new ReviewService();

    @RequestMapping(value = "/getSample")
    @ResponseBody
    public String getSample(@RequestBody String missionName){
        Mission tmpMission=missionBasicBLService.findByKey(missionName);
        tmpMission.setState(1);
        missionBasicBLService.update(tmpMission);
        int state=tmpMission.getState();
        if(state==0){
            return "0";
        }else if(state==2){
            return "2";
        }
        Sample sample=reviewService.getSample(missionName);
        JSONObject object=JSONObject.fromObject(sample);
        String sampleInfo=object.toString();
        System.out.println(sampleInfo);
        System.out.println(sample.getImageInfo().get(0));
        return sampleInfo;
    }
}
