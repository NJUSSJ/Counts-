package com.seproject.service;

import com.seproject.common.SearchCategory;
import com.seproject.domain.*;
import com.seproject.service.blService.BasicBLService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MainService {
    BasicBLService<SubMission> subMissionBasicBLService=Factory.getBasicBLService(new SubMission());
    BasicBLService<GoldMission> goldMissionBasicBLService=Factory.getBasicBLService(new GoldMission());
    BasicBLService<Collection> collectionBasicBLService=Factory.getBasicBLService(new Collection());
    BasicBLService<CollectionResult> collectionResultBasicBLService=Factory.getBasicBLService(new CollectionResult());
    BasicBLService<Mission> missionBasicBLService=Factory.getBasicBLService(new Mission());
    public void  createSubMission(Mission m){
        int n0=m.getFileNum()%10;
        int n1=m.getFileNum();
        n1=n1-n0;
        int groupNum=n1/10;//n 是普通组数
        int n2=(int)(n1*0.1);
        int goldNum=n2*10+n0;//n3 是金标个数，n2+1是金标组数
        int goldGroupNum=n2+1;
                //-------------
        for(int i=0;i<groupNum;i++){
            SubMission subMission=new SubMission();
            subMission.setMid(m.getName());
            subMission.setSeed(i);
            subMission.setKeyID(m.getName()+i);
            int random1=(int)(Math.random()*goldNum);
            subMission.setId1(random1);
            subMission.setId2(random1+goldNum/2);
            ArrayList<ArrayList<Integer>> temparr =new ArrayList<ArrayList<Integer>>();
            ArrayList<String> userarr=new ArrayList<String>();
            subMission.setUid(userarr);
            subMission.setAnswers(temparr);
            subMissionBasicBLService.add(subMission);
        }
        //-----------------
        int base=groupNum*10;
        for(int i=0;i<goldGroupNum;i++){
            GoldMission goldMission=new GoldMission();
            goldMission.setMid(m.getName());
            goldMission.setUid(m.getRequestorNumber());
            goldMission.setKeyID(m.getName()+i);

            ArrayList<Integer> index=new ArrayList<Integer>();
            for(int j=0;j<10;j++){
                int num=base+10*i+j;
                if(num<m.getFileNum()){
                    index.add(num);
                }else{
                    break;
                }
            }
            goldMissionBasicBLService.add(goldMission);
        }

    }


    public void createCollection(String uid,String mid){
        Collection collection=new Collection();
        collection.setKeyId(uid+mid);
        collection.setUid(uid);
        collection.setMid(mid);
        CollectionResult collectionResult=new CollectionResult();
        collectionResult.setMid(mid);
        collectionResult.setUid(uid);
        collectionBasicBLService.add(collection);
        collectionResultBasicBLService.add(collectionResult);
    }

    public boolean getGoldMission(String mid,String uid){
        Mission mission=missionBasicBLService.findByKey(mid);
        String name=mission.getRequestorNumber();
        ArrayList<GoldMission> goldMissions=new ArrayList<GoldMission>();
        goldMissions=goldMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        for(int i=0;i<goldMissions.size();i++){
            if(goldMissions.get(i).getUid().equals(name)){
                goldMissions.get(i).setUid(uid);
                goldMissionBasicBLService.update(goldMissions.get(i));
                return true;
            }
        }

        return false;
    }
}
