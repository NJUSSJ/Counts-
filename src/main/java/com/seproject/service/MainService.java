package com.seproject.service;

import com.seproject.common.SearchCategory;
import com.seproject.domain.*;
import com.seproject.service.blService.BasicBLService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

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
        int base=groupNum*10;
                //-------------
        for(int i=0;i<groupNum;i++){
            SubMission subMission=new SubMission();
            subMission.setMid(m.getName());
            subMission.setSeed(i);
            subMission.setKeyID(m.getName()+i);
            int random1=base+(int)(Math.random()*goldNum);
            subMission.setId1(random1);
            subMission.setId2(random1+goldNum/2);
            ArrayList<ArrayList<Integer>> temparr =new ArrayList<ArrayList<Integer>>();
            ArrayList<String> userarr=new ArrayList<String>();
            subMission.setUid(userarr);
            subMission.setAnswers(temparr);
            subMissionBasicBLService.add(subMission);
        }
        //-----------------
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
            reviewLableMission(mid);
        }
        return result;
    }

    public void reviewLableMission(String mid){
        Mission mission=missionBasicBLService.findByKey(mid);
        ArrayList<SubMission> subMissions=subMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        ArrayList<GoldMission> goldMissions=goldMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        for(int i=0;i<subMissions.size();i++){
            ArrayList<Double> weight=new ArrayList<Double>();
            int index1=subMissions.get(i).getId1();
            int index2=subMissions.get(i).getId2();
            int answer1=-1;
            int answer2=-1;
            for(GoldMission g:goldMissions){
                if(g.getPictrueIndex().contains(index1)){
                    int x1=g.getPictrueIndex().indexOf(index1);
                    answer1=g.getResult().get(x1);
                }
                if(g.getPictrueIndex().contains(index2)){
                    int x1=g.getPictrueIndex().indexOf(index2);
                    answer2=g.getResult().get(x1);
                }
                if(answer1>=0&&answer2>=0){
                    break;
                }

            }
            for(int j=0;j<subMissions.get(i).getAnswers().size();j++){
                ArrayList<Integer> userAnswer=subMissions.get(i).getAnswers().get(j);
                if(userAnswer.size()>0){
                    boolean r2=(userAnswer.get(userAnswer.size()-1)==answer2);
                    boolean r1=(userAnswer.get(userAnswer.size()-2)==answer1);
                    if(r1&&r2){
                        weight.add(1.5);
                    }else if(r1!=r2){
                        weight.add(1.0);
                    }else{
                        weight.add(0.5);
                    }
                }else{
                    //用户接了任务但没做
                    weight.add(0.0);
                }
            }
            int labelNumber=mission.getMissionLabel().size();
            double vote[][]=new double[10][labelNumber];//
            for(int j=0;j<10;j++){
                for(int k=0;k<labelNumber;k++){
                    vote[j][k]=0;
                }

            }
            for(int j=0;j<subMissions.get(i).getAnswers().size();j++){
                ArrayList<Integer> userAnswer=subMissions.get(i).getAnswers().get(j);
                for(int k=0;k<userAnswer.size();k++){
                    vote[k][userAnswer.get(k)]+=weight.get(j);
                }
            }

            /**
             * 找到了所有的标准答案，判断每个用户每题对不对，根据答题情况直接发奖励
             */
            int standardAnswers[]=getStandard(vote);
            boolean isCorrect[][]=new boolean[subMissions.get(i).getUid().size()][12];
            for(int j=0;j<isCorrect.length;j++){
                ArrayList<Integer> userAnswer=subMissions.get(i).getAnswers().get(j);
                for(int k=0;k<10;k++){
                    if(userAnswer.get(k)==standardAnswers[k]){
                        isCorrect[j][k]=true;
                    }else{
                        isCorrect[j][k]=false;
                    }
                }
                if(userAnswer.get(10)==answer1){
                    isCorrect[j][10]=true;
                }else{
                    isCorrect[j][10]=false;
                }

                if(userAnswer.get(11)==answer2){
                    isCorrect[j][11]=true;
                }else{
                    isCorrect[j][11]=false;
                }
            }
            double money[]=giveMoney_DoubleNothing(isCorrect,subMissions.get(i).getUid());
            setCollection(money,subMissions.get(i).getUid(),mid);
        }

    }


    public void finishReview(ArrayList<Integer> index,ArrayList<Integer> answer,String mid){
        ArrayList<GoldMission> goldMissions=goldMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        for(int i=0;i<index.size();i++){
            int eachIndex=index.get(i);
            for(GoldMission goldMission:goldMissions){
                ArrayList<Integer> picIndex=goldMission.getPictrueIndex();
                if(picIndex.contains(index)){
                    goldMission.getResult().set(picIndex.indexOf(eachIndex),answer.get(i));
                    break;
                }
            }
        }
        reviewLableMission(mid);
    }

    /**
     * 根据投票选出获取标准答案
     * @param vote
     * @return
     */
    public int[] getStandard(double[][] vote){
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
     * 根据子任务的完成情况为每个工人设置collection
     */
    public void setCollection(double[] money,ArrayList<String> uid,String mid) {

        int rank[] = new int[money.length];

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
                if (collectionResult.getUid() == each) {
                    collectionResult.setQuality(8);//默认值
                    collectionResult.setCredit(money[i]);
                    collectionResult.setRank(rank[i]);
                    break;
                }
            }
        }
    }

    public double[] giveMoney_DoubleNothing(boolean x[][],ArrayList<String> uids){
        double base=1.6;
        double money[]=new double[x.length];
        // x 是 横坐标用户，纵坐标12个题是否正确的二维数组
        for(int i=0;i<x.length;i++){
            double m=0.15;
            for(int j=0;j<x[0].length;j++){
                if(x[i][j]==true){
                    m*=base;
                }else{
                    m=0;
                }
            }
            money[i]=m;
        }
        return money;
    }

    public ArrayList<Integer> getPictureIndexOfSubmission(String uid,String mid){

        return new ArrayList<Integer>();
    }

}
