package com.seproject.service;

import com.seproject.common.SearchCategory;
import com.seproject.domain.*;
import com.seproject.service.blService.BasicBLService;
import com.seproject.web.parameter.RecommendParameter;
import org.springframework.stereotype.Service;
import sun.util.resources.cldr.ar.CalendarData_ar_YE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
@Service
public class MissionService {
    private BasicBLService<User> userBasicBLService=Factory.getBasicBLService(new User());
    private BasicBLService<Mission> missionBasicBLService=Factory.getBasicBLService(new Mission());
    private BasicBLService<Collection> collectionBasicBLService=Factory.getBasicBLService(new Collection());
    private BasicBLService<CollectionResult> collectionResultBasicBLService=Factory.getBasicBLService(new CollectionResult());
    /**
     * 四维推荐排序
     * 用户设置难度，期望积分
     * 根据难度，期望积分，用户标签，用户等级四个维度进行任务推荐，故称为四维推荐
     * 1.先选出正在进行的任务
     * 2.从中选出等级符合的任务
     * 3.从中根据难度选（可选）
     * 4.从中根据用户标签选（可选）
     * 5.从中根据期望积分选（可选）
     */
    public ArrayList<Mission> recommendMission(RecommendParameter para){
        ArrayList<Mission> result=new ArrayList<Mission>();
        String uid=para.getUid();
        User user=userBasicBLService.findByKey(uid);
        int level=user.getLevel();

        boolean useDifficulty=false,useTag,useWanted=false;
        useTag=para.getUseTag();
        int[] diffculty=para.getDifficulty();
        if(diffculty.length>0){
            useDifficulty=true;
        }
        double credit=para.getWantedCredit();
        if(credit>0){
            useWanted=true;
        }
        ArrayList<Mission> missions=missionBasicBLService.getAllObjects();
        for(Mission mission:missions){
            if(mission.getState()!=0){
                continue;
            }
            if(Integer.parseInt(mission.getWorkerLevel())>level){
                continue;
            }
            //难度
            if(useDifficulty){
                int temp=mission.getDifficulty();
                boolean in=false;
                for (int aDiffculty : diffculty) {
                    if (aDiffculty == temp) {
                        in = true;
                        break;
                    }
                }
                if(!in){
                    continue;
                }
            }
            //标签
            if(useTag){
                ArrayList<String> tags=user.getTags();
                ArrayList<String> labels=mission.getRecommendLabel();
                boolean in=false;
                for(String each:labels){
                    if(tags.contains(each)){
                        in=true;
                        break;
                    }
                }
                if(!in){
                    continue;
                }
            }
            //期望积分
            if(useWanted){
                double avg=mission.getReward()/mission.getMaxWorkerNum();
                if(avg<credit){
                    continue;
                }
            }
            result.add(mission);
        }

        return result;
    }

    /**
     *根据相似用户进行任务推荐
     */
    public ArrayList<Mission> recommendByAlikeUser(String uid){
        ArrayList<User> alikeUsers=findAlikeUser(uid);
        ArrayList<Mission> result=new ArrayList<Mission>();
        ArrayList<Mission> ongoingMission=missionBasicBLService.search("state",SearchCategory.EQUAL,"0");
        ArrayList<String> ongoingId=new ArrayList<String>();
        for(Mission mission:ongoingMission){
            ongoingId.add(mission.getName());
        }
        for(User user:alikeUsers){
            ArrayList<Collection> tempCollection=collectionBasicBLService.search("uid",SearchCategory.EQUAL,user.getPhoneNumber());
            for(Collection collection:tempCollection){
                if(ongoingId.contains(collection.getMid())){
                    Mission toAdd=ongoingMission.get(ongoingId.indexOf(collection.getMid()));
                    if(!result.contains(toAdd)){
                        result.add(toAdd);
                    }
                }
            }
        }
        return result;
    }



    /**
     *根据历史行为寻找到相似的用户
     *算法：比较两个用户曾经完成过的任务的排名情况，如果差的绝对值不大于1视为相似，并且相似的任务数占总任务数的比例超过一定值(0.6)，则证明相似
     */
    private ArrayList<User> findAlikeUser(String uid){
        ArrayList<User> allUser=userBasicBLService.getAllObjects();
        ArrayList<User> alikeUser=new ArrayList<User>();
        ArrayList<CollectionResult> originalCollection=collectionResultBasicBLService.search("uid",SearchCategory.EQUAL,uid);
        Map<String,Integer> origin=new HashMap<String,Integer>();
        for(CollectionResult collection:originalCollection){
            String key=collection.getMid();
            int rank=collection.getRank();
            origin.put(key,rank);
        }
        for(User user:allUser){
            if(!user.getPhoneNumber().equals(uid)) {//自己和自己必然是最相似的，所以不考虑
                double alikeNum = 0;
                ArrayList<CollectionResult> tempCollection = collectionResultBasicBLService.search("uid", SearchCategory.EQUAL, user.getPhoneNumber());
                for (CollectionResult collection : tempCollection) {
                    String mid = collection.getMid();
                    if (origin.containsKey(mid)) {
                        int tempRank = collection.getRank();
                        int rank = origin.get(mid);
                        int distance = rank - tempRank;
                        if (distance <= 1 &&distance >= -1) {
                            alikeNum++;
                        }
                    }
                }
                double likeRate = alikeNum / tempCollection.size();
                if (likeRate > 0.6) {
                    alikeUser.add(user);
                }
            }
        }
        return alikeUser;
    }


    /**
     * 自动评估标签式任务
     ************************************************************************************************************
     *                                                  定义                                                    *
     ************************************************************************************************************
     * “认可度”：对于一张图，将所有的工人对其标注的标签作为全集，其中出现次数最多的标签的出现次数与总标签出现次数的比*
     * 认可度高，代表一张图的结果有较多的人达成统一意见
     * “认可度梯度”：认可度每次下降的值
     * “认可答案”：认可度超过一定值时，出现次数最多的答案
     * **********************************************************************************************************
     *                                                  算法                                                    *
     * **********************************************************************************************************
     * 1.为认可度设置起始值，梯度，下限（>0.5），设置抽样图片下限
     * 2.计算出任务中每张图片的认可度
     * 3.每张图片的认可度与当前认可度标准比较，达到标准的图片加入抽样图片
     * 4.如果抽样图片数未达到下限，认可度下降，重复3，直到抽样图片数超过下限（进入5）或者认可度达到下限（进入6）
     * 5.计算每个工人在抽样图片集中的答案和认可答案的重合率，乘100得到成绩，结束
     * 6.将所有图片的认可度排序，选出认可度在0.5到下限之间的图片，将这些认可度求和得到sum，令each=0
     * 7.比较每个工人的答案和认可答案，如果一致，each加认可度，否则为0
     * 8.each*100/sum为成绩，结束
     **/
/*    public void autoEvaluate(String mid){
    }*/
}
