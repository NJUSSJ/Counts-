package com.seproject.service;

import com.seproject.common.SearchCategory;
import com.seproject.domain.*;
import com.seproject.service.blService.BasicBLService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class MainService {
    private BasicBLService<SubLabelMission> subLabelMissionBasicBLService=Factory.getBasicBLService(new SubLabelMission());
    private BasicBLService<GoldMission> goldMissionBasicBLService=Factory.getBasicBLService(new GoldMission());
    private BasicBLService<Collection> collectionBasicBLService=Factory.getBasicBLService(new Collection());
    private BasicBLService<CollectionResult> collectionResultBasicBLService=Factory.getBasicBLService(new CollectionResult());
    private BasicBLService<Mission> missionBasicBLService=Factory.getBasicBLService(new Mission());
    private BasicBLService<User> userBasicBLService=Factory.getBasicBLService(new User());
    private BasicBLService<SubFreeMission> subFreeMissionBasicBLService=Factory.getBasicBLService(new SubFreeMission());
    /**
     * 创建子任务
     */
    public void createSubMission(Mission m){
        if(m.getTagType()==1){
            createSubTagMission(m);
        }else{
            createSubFreeMission(m);
        }
    }

    /**
     * 创建标签式子任务
     */
    private void createSubTagMission(Mission m){
        int picNum=m.getFileNum();
        int[] array=getThreeNum(picNum);
        int groupNum=array[0],goldNum=array[1],goldGroupNum=array[2];
        int base=groupNum*10;
        for(int i=0;i<groupNum;i++){
            SubLabelMission subLabelMission =new SubLabelMission();
            subLabelMission.setMid(m.getName());
            subLabelMission.setSeed(i);
            subLabelMission.setKeyID(m.getName()+i);
            int random1=base+(int)(Math.random()*goldNum);
            subLabelMission.setId1(random1 );
            subLabelMission.setId2(random1+goldNum/2);
            ArrayList<ArrayList<Integer>> temparr =new ArrayList<ArrayList<Integer>>();
            ArrayList<String> userarr=new ArrayList<String>();
            subLabelMission.setUid(userarr);
            subLabelMission.setAnswers(temparr);
            subLabelMissionBasicBLService.add(subLabelMission);
        }
        //分配金标任务
        for(int i=0;i<goldGroupNum;i++){
            GoldMission goldMission=new GoldMission();
            goldMission.setMid(m.getName());
            goldMission.setUid(m.getRequestorNumber());
            goldMission.setKeyID(m.getName()+i);

            ArrayList<Integer> index=new ArrayList<Integer>();
            ArrayList<Integer> answer=new ArrayList<Integer>();
            for(int j=0;j<10;j++){
                int num=base+10*i+j;
                if(num<m.getFileNum()){
                    index.add(num);
                    answer.add(-1);
                }else{
                    break;
                }
            }
            goldMission.setPictrueIndex(index);
            goldMission.setResult(answer);
            goldMissionBasicBLService.add(goldMission);
        }
    }

    /**
     * 创建自由式子任务
     */
    private void createSubFreeMission(Mission m){
        int picNum=m.getFileNum();
        int groupNum=picNum/10;
        if(groupNum%10!=0)groupNum++;
        for(int i=0;i<groupNum;i++){
            SubFreeMission subFreeMission=new SubFreeMission();
            subFreeMission.setKeyID(m.getName()+i);
            subFreeMission.setMid(m.getEndTime());
            subFreeMission.setSeed(i);
            ArrayList<String> user=new ArrayList<String>();
            subFreeMission.setUid(user);
            subFreeMissionBasicBLService.add(subFreeMission);
        }
    }
    /**
     * 获得图片组数，金标数，金标组数形成的数组
     */
    private int[] getThreeNum(int picNum){
        int[] result=new int[3];
        int goldNum=0,groupNum=0,goldGroupNum=0;
        if(picNum>=100) {
        /*
            如果图片数超过100，如876,则用87+6=93,向下找末尾是6的数，即86作为金标数
            876-86=790，正好分79组
            目标数金标数稍大于普通组数，如果不满足这个条件，就给金标数加10，直到满足为止
         */
            int tail=picNum%10;
            goldNum=(picNum/10)+tail;
            while(goldNum%10!=tail){
                goldNum--;
            }
            groupNum=(picNum-goldNum)/10;
            while(groupNum>goldNum){
                goldNum+=10;
                groupNum--;
            }
            goldGroupNum=goldNum/10;
            if(goldNum%10!=0) goldGroupNum++;
        }else if(picNum>10){
            if(picNum < 20){
                goldNum=picNum%10;
                groupNum=1 ;
                goldGroupNum=1;//如果在11到20之间，直接取个位数做金标数，只要一组
            }else if(picNum%10==0){
                goldNum=10;
                goldGroupNum=1;
                groupNum=picNum/10-goldGroupNum;//如果是20,30...90,直接取10个做金标，一组
            }else if(picNum%10<=5){
                goldNum=picNum%10+10;
                groupNum=(picNum-goldNum)/10;
                goldGroupNum=2;//如果个位小于5，取（10+个位）做金标，两组
            }else{
                goldNum=picNum%10;
                groupNum=(picNum-goldNum)/10;
                goldGroupNum=1;//如果个位大于5，直接取个位，一组
            }
        }
        result[0]=groupNum;
        result[1]=goldNum;
        result[2]=goldGroupNum;
        return result;
    }

    /**
     * 创建对应的collection和collectionResult
     */
    public void createCollection(String uid,String mid){
        Collection collection=new Collection();
        collection.setKeyId(uid+mid);
        collection.setUid(uid);
        collection.setMid(mid);
        CollectionResult collectionResult=new CollectionResult();
        collectionResult.setResultId(collection.getKeyId());
        collectionResult.setMid(mid);
        collectionResult.setUid(uid);
        collectionBasicBLService.add(collection);
        collectionResultBasicBLService.add(collectionResult);
    }

    /**
     * 高级工人接金标任务的相关逻辑
     */
    public boolean getGoldMission(String mid,String uid){
        Mission mission=missionBasicBLService.findByKey(mid);
        String name=mission.getRequestorNumber();
        ArrayList<GoldMission> goldMissions = goldMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        for (GoldMission goldMission : goldMissions) {
            if (goldMission.getUid().equals(name)) {
                goldMission.setUid(uid);
                goldMissionBasicBLService.update(goldMission);
                User user = userBasicBLService.findByKey(uid);
                user.setCredit(user.getCredit() + 160);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Integer> getRestPictures(String mid){
        ArrayList<Integer> result=new ArrayList<Integer>();
        Mission mission=missionBasicBLService.findByKey(mid);
        String starter=mission.getRequestorNumber();
        ArrayList<GoldMission> goldMissions=goldMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        for(GoldMission goldMission:goldMissions){
            if(!goldMission.getUid().equals(starter)){
                result.addAll(goldMission.getPictrueIndex());
            }
        }
        if(goldMissions.size()==0){
            reviewLabelMission(mid);
        }
        return result;
    }

    private void reviewLabelMission(String mid){
        Mission mission=missionBasicBLService.findByKey(mid);
        ArrayList<SubLabelMission> subLabelMissions =subLabelMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        ArrayList<GoldMission> goldMissions=goldMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        for (SubLabelMission subLabelMission : subLabelMissions) {
            ArrayList<Double> weight = new ArrayList<Double>();
            int index1 = subLabelMission.getId1();
            int index2 = subLabelMission.getId2();
            int answer1 = -1;
            int answer2 = -1;
            for (GoldMission g : goldMissions) {
                if (g.getPictrueIndex().contains(index1)) {
                    int x1 = g.getPictrueIndex().indexOf(index1);
                    answer1 = g.getResult().get(x1);
                }
                if (g.getPictrueIndex().contains(index2)) {
                    int x1 = g.getPictrueIndex().indexOf(index2);
                    answer2 = g.getResult().get(x1);
                }
                if (answer1 >= 0 && answer2 >= 0) {
                    break;
                }

            }
            for (int j = 0; j < subLabelMission.getAnswers().size(); j++) {
                ArrayList<Integer> userAnswer = subLabelMission.getAnswers().get(j);
                if (userAnswer.size() > 0) {
                    boolean r2 = (userAnswer.get(userAnswer.size() - 1) == answer2);
                    boolean r1 = (userAnswer.get(userAnswer.size() - 2) == answer1);
                    if (r1 && r2) {
                        weight.add(1.5);
                    } else if (r1 != r2) {
                        weight.add(1.0);
                    } else {
                        weight.add(0.5);
                    }
                } else {
                    //用户接了任务但没做
                    weight.add(0.0);
                }
            }
            int labelNumber = mission.getMissionLabel().size();
            double vote[][] = new double[10][labelNumber];//
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < labelNumber; k++) {
                    vote[j][k] = 0;
                }

            }
            for (int j = 0; j < subLabelMission.getAnswers().size(); j++) {
                ArrayList<Integer> userAnswer = subLabelMission.getAnswers().get(j);
                for (int k = 0; k < userAnswer.size(); k++) {
                    vote[k][userAnswer.get(k)] += weight.get(j);
                }
            }

            //找到了所有的标准答案，判断每个用户每题对不对，根据答题情况直接发奖励
            int standardAnswers[] = getStandard(vote);
            boolean isCorrect[][] = new boolean[subLabelMission.getUid().size()][12];
            for (int j = 0; j < isCorrect.length; j++) {
                ArrayList<Integer> userAnswer = subLabelMission.getAnswers().get(j);
                for (int k = 0; k < 10; k++) {
                    isCorrect[j][k] = (userAnswer.get(k) == standardAnswers[k]);
                }
                isCorrect[j][10] = (userAnswer.get(10) == answer1);
                isCorrect[j][11] = (userAnswer.get(11) == answer2);
            }
            double money[] = giveMoney_DoubleNothing(isCorrect);
            setCollection(money, subLabelMission.getUid(), mid);
        }

    }


    public void finishReview(ArrayList<Integer> index,ArrayList<Integer> answer,String mid){
        ArrayList<GoldMission> goldMissions=goldMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        for(int i=0;i<index.size();i++){
            int eachIndex = index.get(i);
            for(GoldMission goldMission:goldMissions){
                ArrayList<Integer> picIndex=goldMission.getPictrueIndex();
                if(picIndex.contains(eachIndex)){
                    goldMission.getResult().set(picIndex.indexOf(eachIndex),answer.get(i));
                    break;
                }
            }
        }
        reviewLabelMission(mid);
    }

    /**
     * 根据投票选出获取标准答案
     */
    private int[] getStandard(double[][] vote){
        //vote.length=10
        int result[]=new int[vote.length];
        for(int i=0;i<vote.length;i++){
            boolean isValid=false;
            double temp[]= vote[i];
            Arrays.sort(temp);//升序

            if(temp[temp.length-2]!=0){
                if(temp[temp.length-1]/temp[temp.length-2]>=3){
                    isValid=true;
                }
            }else{
                isValid=true;
            }
            if(!isValid){
                result[i]=-1;
            }else{
                double max=temp[temp.length-1];
                temp=vote[i];
                for(int k=0;k<temp.length;k++){
                    if(temp[k]==max){
                        result[i]=k;
                        break;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 根据标签式子任务的完成情况为每个工人设置collection
     */
    private void setCollection(double[] money, ArrayList<String> uid, String mid) {
        int[] rank = new int[money.length];

        for (int j = 0; j < money.length; j++) {
            rank[j] = 1;
            for (int i = 0; i < money.length && i != j; i++) {
                if (money[j] < money[i]) {
                    rank[j]++;
                }
            }
        }
        ArrayList<CollectionResult> collectionResults = collectionResultBasicBLService.search("mid", SearchCategory.EQUAL, mid);
        for (int i = 0; i < uid.size(); i++) {
            String each = uid.get(i);
            for (CollectionResult collectionResult : collectionResults) {
                if (collectionResult.getUid().equals(each)) {
                    collectionResult.setQuality(8);//默认值
                    collectionResult.setCredit(money[i]);
                    collectionResult.setRank(rank[i]);
                    break;
                }
            }
        }
    }

    private double[] giveMoney_DoubleNothing(boolean x[][]){
        double base=1.6;
        double[] money = new double[x.length];
        // x 是 横坐标用户，纵坐标12个题是否正确的二维数组
        for(int i=0;i<x.length;i++){
            double m=0.15;
            for(int j=0;j<x[0].length;j++){
                if(x[i][j]){
                    m*=base;
                }else{
                    m=0;
                }
            }
            money[i]=m;
        }
        return money;
    }

    /**
     * 获取用户的子任务里图片的索引
     */
    public ArrayList<Integer> getPictureIndexOfSubmission(String uid,String mid){
        ArrayList<Integer> result=new ArrayList<Integer>();
        Mission mission=missionBasicBLService.findByKey(mid);
        if(mission.getTagType()==1) {
            ArrayList<SubLabelMission> subLabelMissions = subLabelMissionBasicBLService.search("mid", SearchCategory.EQUAL, mid);
            for (SubLabelMission subLabelMission : subLabelMissions) {
                ArrayList<String> user = subLabelMission.getUid();
                if (user.contains(uid)) {
                    int seed = subLabelMission.getSeed();
                    for (int j = 0; j < 10; j++) {
                        result.add(seed + j);
                    }
                    result.add(subLabelMission.getId1());
                    result.add(subLabelMission.getId2());//把金标也加上
                    return result;
                }
            }
        }else{
            int groupNum=mission.getFileNum()/10;
            int tail=mission.getFileNum()%10;
            ArrayList<SubFreeMission> subFreeMissions=subFreeMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
            for(SubFreeMission subFreeMission:subFreeMissions){
                ArrayList<String> user=subFreeMission.getUid();
                if(user.contains(uid)){
                    int seed=subFreeMission.getSeed();
                    if(seed==groupNum){//如果图片数不是10的倍数，又是最后一组
                        for(int i=0;i<tail;i++){
                            result.add(mission.getFileNum()-i-1);
                        }
                        for(int i=0;i<10-tail;i++){
                            result.add(i);
                        }
                    }
                    else{
                        for(int i=0;i<10;i++){
                            result.add(seed+i);
                        }
                    }
                }
            }
        }
        return result;
    }

}
