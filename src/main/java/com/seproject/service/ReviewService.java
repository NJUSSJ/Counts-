package com.seproject.service;

import com.seproject.domain.Collection;
import com.seproject.domain.Mission;
import com.seproject.domain.Sample;
import com.seproject.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 评审的业务逻辑
 */
@Service
public class ReviewService {
    /**
     * 抽样方法
     */

    BasicBLService<Collection> service1=new BasicBLService<Collection>(new Collection());
    BasicBLService<User> service2= new BasicBLService<User>(new User());
    BasicBLService<Mission> service3=new BasicBLService<Mission>(new Mission());

    private double completeAward=0.2;

    public Sample getSample(String mid){
        /**
         * 若 level <= AverageLevel
         *        抽两张， 取最差的一张作为q ;
         * 若 level > AverageLevel
         *        抽一张， 直接作为q
         */
        Mission m=service3.findByKey(mid);
        ArrayList <Collection> collections=service1.search("mid",SearchCategory.EQUAL,m.getName());
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
            if(userList.get(i).getLevel()>averageLevel){
                int x=(int)(Math.random()*num);
                String info=collections.get(i).getInfoList().get(x);
                userId.add(userList.get(i).getPhoneNumber());
                imageInfo.add(info);
                quality.add(0);
                picIndex.add(x+1);
            }else{
                int x=(int)(Math.random()*num);
                String info=collections.get(i).getInfoList().get(x);
                userId.add(userList.get(i).getPhoneNumber());
                imageInfo.add(info);
                quality.add(0);
                picIndex.add(x+1);

                x=(x+num/2)%num;

                info=collections.get(i).getInfoList().get(x);
                userId.add(userList.get(i).getPhoneNumber());
                imageInfo.add(info);
                quality.add(0);
                picIndex.add(x+1);

            }
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
                subCollection.add(c);
            }
        }

        double qualityAvg=0;
        double realCreditSum=0;
        for(Collection c:subCollection){ qualityAvg+=c.getQuality(); }
        qualityAvg/=subCollection.size();
        for(Collection c:subCollection){
            double result=sumCredit*(baseCredit+(1-baseCredit)*c.getQuality()/qualityAvg)/subCollection.size();
            realCreditSum+=result;
            c.setCredit(result);

        }

        User starter=service2.findByKey(m.getRequestorNumber());
        starter.setCredit(starter.getCredit()+(sumCredit-realCreditSum));
    }

    /**
     *  private ArrayList<Double> calculateQ (String sampleID, ArrayList<Double> quality)
     */
}
