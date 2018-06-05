package com.seproject.service;

import com.seproject.common.SearchCategory;
import com.seproject.domain.*;
import com.seproject.service.blService.BasicBLService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 评审的业务逻辑
 */
@Service
public class ReviewService {
    /**
     * 抽样方法Plus
     * 根据评估策略，设置抽样数
     * 如果由“评估工人”来参与，则抽样数设为较大值
     * 如果发起者亲自评价，则抽样数设为1，2
     */

    BasicBLService<Collection> service1=Factory.getBasicBLService(new Collection());
    BasicBLService<User> service2= Factory.getBasicBLService(new User());
    BasicBLService<Mission> service3=Factory.getBasicBLService(new Mission());
    BasicBLService<CollectionResult> collectionResultBasicBLService=Factory.getBasicBLService(new CollectionResult());

    private double completeAward=0.2;

    public Sample getSample(String mid){
        /**
         * 若 level <= AverageLevel
         *        抽两张， 取最差的一张作为q ;
         * 若 level > AverageLevel
         *        抽一张， 直接作为q
         */
        Mission m=service3.findByKey(mid);
        ArrayList<Collection> collections=service1.search("mid", SearchCategory.EQUAL,m.getName());
        ArrayList<Collection> collections1=new ArrayList<Collection>();
        Sample sample=new Sample();
        ArrayList<User> userList=new ArrayList<User>();
        double averageLevel=0;
        for(Collection collection:collections){
            if(collection.getState()==1){
                collections1.add(collection);
                User user=service2.findByKey(collection.getUid());
                userList.add(user);
                averageLevel+=user.getLevel();
                collection.setQuality(3);
                service1.update(collection);
            }else{
                collection.setQuality(-1);
                service1.update(collection);
            }
        }

        averageLevel=averageLevel/userList.size();

        ArrayList<Integer> picIndex=new ArrayList<Integer>();

        ArrayList<String> userId=new ArrayList<String>();

        ArrayList<String> imageInfo=new ArrayList<String>();

        ArrayList<Integer> quality=new ArrayList<Integer>();

        sample.setMissionName(mid);

        int num=m.getFileNum();
        for(int i=0;i<userList.size();i++){
            Collection collection=collections.get(i);
            CollectionResult collectionResult=new CollectionResult(collection);
            if(userList.get(i).getLevel()>averageLevel){
                int x=(int)(Math.random()*num);
                String info=collection.getInfoList().get(x);
                userId.add(userList.get(i).getPhoneNumber());
                imageInfo.add(info);
                quality.add(0);
                picIndex.add(x+1);
                int[] pics={x+1};
                collectionResult.setPicId(pics);
            }else{
                int x=(int)(Math.random()*num);
                int[] pics=new int[2];
                String info=collection.getInfoList().get(x);
                userId.add(userList.get(i).getPhoneNumber());
                imageInfo.add(info);
                quality.add(0);
                picIndex.add(x+1);
                pics[0]=x+1;
                x=(x+num/2)%num;
                pics[1]=x+1;
                info=collections.get(i).getInfoList().get(x);
                userId.add(userList.get(i).getPhoneNumber());
                imageInfo.add(info);
                quality.add(0);
                picIndex.add(x+1);
                collectionResult.setPicId(pics);
            }
            collectionResultBasicBLService.add(collectionResult);
        }

        sample.setImageInfo(imageInfo);
        sample.setPicIndex(picIndex);
        sample.setQuality(quality);
        sample.setUserId(userId);
        return sample;
    }


    /**
     * 筛选方法
     */

    /**
     * 奖励分配算法
     */
    public void award(String missionID){
        /**
         * award[i] = 0.2 *m/n+ 0.8*(m/n)*q[i]/Average(q)
         *          = (m/n)* (t+(1-t)*q[i]/Average(q))
         *          其中 t=completeAward=0.2 , m 为任务总奖励
         *
         * 调用这个方法后，涉及到的每个用户的积分将被重新计算
         */
        double baseCredit=0.2;
        Mission m=service3.findByKey(missionID);
        double sumCredit=m.getReward();
        ArrayList<Collection> collections=service1.getAllObjects();
        ArrayList<Collection> subCollection=new ArrayList<Collection>();
        for(Collection c:collections){
            if(c.getMid().equals(missionID)){
                if(c.getQuality()!=-1) {
                    subCollection.add(c);
                }
            }
        }

        double qualityAvg=0;
        double realCreditSum=0;
        for(Collection c:subCollection){ qualityAvg+=c.getQuality(); }
        boolean allBad=false;
        //如果总和是0，那么每张图的q都是0，进入特殊判断
        if(qualityAvg==0){
            allBad=true;
        }
        qualityAvg /= subCollection.size();
        for (Collection c : subCollection) {
            double result;
            if(allBad){
                result=sumCredit/subCollection.size();
            }else {
                result = sumCredit * (baseCredit + (1 - baseCredit) * c.getQuality() / qualityAvg) / subCollection.size();
            }
            realCreditSum += result;
            c.setCredit(result);
            User user=service2.findByKey(c.getUid());
            user.setCredit(user.getCredit()+result);
            user.setLevel(user.getLevel()+1);//奖励分配以后用户等级升级
            service1.update(c);
            service2.update(user);
        }

        User starter=service2.findByKey(m.getRequestorNumber());
        starter.setCredit(starter.getCredit()+(sumCredit-realCreditSum));
    }

    public void setSampleResult(Sample sample){
        Mission m=service3.findByKey(sample.getMissionName());
        m.setState(2);
        service3.update(m);
        ArrayList<String> uid=sample.getUserId();
        ArrayList<Integer> q=sample.getQuality();

        ArrayList<Collection> collections=service1.search("mid",SearchCategory.EQUAL,sample.getMissionName());
        for(int i=0;i<q.size();i++){
            for(Collection c:collections){
                if(c.getUid().equals(uid.get(i))){
                    if(q.get(i)<c.getQuality()){
                        c.setQuality(q.get(i));
                        service1.update(c);
                        break;
                    }
                }
            }
        }

        award(sample.getMissionName());
    }

    /**
     *  private ArrayList<Double> calculateQ (String sampleID, ArrayList<Double> quality)
     */
}
