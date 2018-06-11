package com.seproject.web.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MainController {

    //用于写新加的主要系统流程
    /**
     * 增加标签式任务、接受子任务、高级工人接受评估式任务
     * 自动评估标签式任务并分配奖励、手动评估标签式任务并分配奖励
     */

    private int getMinIndex(ArrayList<Integer> arr){
        int min=100;
        int minIndex=0;
        for(int i=0;i<arr.size();i++){
            if(arr.get(i)<min){
                min=arr.get(i);
                minIndex=i ;
            }
        }
        return minIndex;
    }

}
