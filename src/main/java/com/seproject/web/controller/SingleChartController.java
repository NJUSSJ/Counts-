package com.seproject.web.controller;

import com.seproject.common.SearchCategory;
import com.seproject.domain.CollectionResult;
import com.seproject.domain.chart.Chart1;
import com.seproject.service.Factory;
import com.seproject.service.blService.BasicBLService;
import org.hibernate.action.internal.CollectionRecreateAction;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class SingleChartController {
    BasicBLService<CollectionResult> collectionResultBasicBLService=Factory.getBasicBLService(new CollectionResult());
    //返回各种单张的图表
    @RequestMapping(value = "/singleChart/getChart1")
    @ResponseBody
    public Chart1 getChart1(@RequestBody String mid){

        Chart1 chart1=new Chart1();
        chart1.setMid(mid);
        ArrayList<CollectionResult> crs=collectionResultBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        ArrayList<String> uid=new ArrayList<String>();
        ArrayList<Double> money=new ArrayList<Double>();
        for(CollectionResult cr:crs){
            uid.add(cr.getUid());
            money.add(cr.getCredit());
        }
        chart1.setMoney(money);
        chart1.setUid(uid);
        return chart1;
    }
}
