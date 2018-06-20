package com.seproject.service;

import com.seproject.domain.*;
import com.seproject.service.blService.BasicBLService;
import com.seproject.service.blServiceImpl.BasicBLImpl_File;
import com.seproject.service.blServiceImpl.BasicBLImpl_Hibernate;

public class Factory {
    private static BasicBLService<Collection> collectionBasicBLService;
    private static BasicBLService<CollectionResult> collectionResultBasicBLService;
    private static BasicBLService<GoldMission> goldBasicBLService;
    private static BasicBLService<SubFreeMission> subFreeMissionBasicBLService;
    private static BasicBLService<SubLabelMission> subLabelMissionBasicBLService;
    private static BasicBLService<User> userBasicBLService;
    private static BasicBLService<UserDate> userDateBasicBLService;
    private static BasicBLService<Mission> missionBasicBLService;
    public static <T> BasicBLService<T> getBasicBLService(T t){
        return new BasicBLImpl_Hibernate<T>(t);
    }
    public static BasicBLService<Collection> getCollectionBasicBLService(){
        if(collectionBasicBLService==null){
            collectionBasicBLService=getBasicBLService(new Collection());
        }
        return collectionBasicBLService;
    }
    public static BasicBLService<CollectionResult> getCollectionResultBasicBLService(){
        if(collectionResultBasicBLService==null){
            collectionResultBasicBLService=getBasicBLService(new CollectionResult());
        }
        return collectionResultBasicBLService;
    }
    public static BasicBLService<GoldMission> getGoldBasicBLService(){
        if(goldBasicBLService==null){
            goldBasicBLService=getBasicBLService(new GoldMission());
        }
        return goldBasicBLService;
    }
    public static BasicBLService<SubFreeMission> getSubFreeMissionBasicBLService(){
        if(subFreeMissionBasicBLService==null){
            subFreeMissionBasicBLService=getBasicBLService(new SubFreeMission());
        }
        return subFreeMissionBasicBLService;
    }
    public static BasicBLService<SubLabelMission> getSubLabelMissionBasicBLService(){
        if(subLabelMissionBasicBLService==null){
            subLabelMissionBasicBLService=getBasicBLService(new SubLabelMission());
        }
        return subLabelMissionBasicBLService;
    }
    public static BasicBLService<User> getUserBasicBLService(){
        if(userBasicBLService==null){
            userBasicBLService=getBasicBLService(new User());
        }
        return userBasicBLService;
    }
    public static BasicBLService<UserDate> getUserDateBasicBLService(){
        if(userDateBasicBLService==null){
            userDateBasicBLService=getBasicBLService(new UserDate());
        }
        return userDateBasicBLService;
    }
    public static BasicBLService<Mission> getMissionBasicBLService(){
        if(missionBasicBLService==null){
            missionBasicBLService=getBasicBLService(new Mission());
        }
        return missionBasicBLService;
    }
}
