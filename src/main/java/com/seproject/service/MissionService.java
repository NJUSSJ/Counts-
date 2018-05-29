package com.seproject.service;

import com.seproject.common.SearchCategory;
import com.seproject.domain.Collection;
import com.seproject.domain.Mission;
import com.seproject.domain.Sample;
import com.seproject.domain.User;
import com.seproject.service.blService.BasicBLService;
import org.omg.CORBA.CODESET_INCOMPATIBLE;

import javax.persistence.Column;
import java.util.ArrayList;

public class MissionService {
    BasicBLService<User> userBasicBLService=Factory.getBasicBLService(new User());
    BasicBLService<Mission> missionBasicBLService=Factory.getBasicBLService(new Mission());
    BasicBLService<Collection> collectionBasicBLService=Factory.getBasicBLService(new Collection());
    /**
     *推荐排序
     * @return 根据用户的擅长类别排序出的任务列表
     */
    public ArrayList<Mission> recommentSort(String uid){
        User user=userBasicBLService.findByKey(uid);
        ArrayList<String> userTags=user.getTags();
        ArrayList<Mission> missions=missionBasicBLService.getAllObjects();
        ArrayList<Integer> nums=new ArrayList<Integer>();
        for(int i=0;i<missions.size();i++){
            ArrayList<String> missionTags=missions.get(i).getRecommendLabel();
            int count=0;
            for(String eachTag:missionTags){
                if(userTags.contains(eachTag)){
                    count++;
                }
            }
            nums.add(count);
            //排序
            for(int j=0;i<missions.size()-1;i++){
                for(int k=j+1;k<missions.size();k++){
                    int pre=nums.get(i);
                    int back=nums.get(j);
                    if(back>pre){
                        nums.set(i,back);
                        nums.set(j,pre);
                        Mission mPre=missions.get(i);
                        Mission mBack=missions.get(j);
                        missions.set(i,mBack);
                        missions.set(j,mPre);
                    }
                }
            }
        }
        return missions;
    }
    /**
     * 自动评估标签式任务
     *
     ***************************
     * 算法：图片总数为pics，根据pics设定抽样系数p
     * 根据每张图片获得的标签数排序，获得前pics*p个答案最统一的图片，统计每个用户在pics*p中答对的个数x，将100*x/pics*p作为最终得分*
     *
     **/
    public void autoEvaluate(String mid){
        Mission mission=missionBasicBLService.findByKey(mid);
        ArrayList<Collection> collections=collectionBasicBLService.search("mid", SearchCategory.EQUAL,mid);
        int pics=mission.getFileNum();
        ArrayList<String> label=mission.getMissionLabel();
        int labelNum=label.size();
        double p;
        if(pics<=10){ p=1; }
        else if(pics>10&&pics<=30){ p=0.8; }
        else if(pics>30&&pics<=50){ p=0.6; }
        else if(pics>50&&pics<=70){ p=0.4; }
        else if(pics>70&&pics<100){ p=0.2; }
        else { p=0.1; }
        int[][] countArray=new int[pics][labelNum];
        for(int i=0;i<pics;i++) {
            for (int j = 0; j < labelNum; j++) {
                countArray[i][j] = 0;
            }
        }
        for (Collection collection : collections) {
            collection.setQuality(0);//遍历的同时把得分清零
            ArrayList<String> eachLabelList = collection.getInfoList();
            for (int k = 0; k < eachLabelList.size(); k++) {
                int index = label.indexOf(eachLabelList.get(k));
                countArray[k][index]++;
            }
        }
        int[][] sortArray = new int[pics][2];
        for(int i=0;i<pics;i++){
            sortArray[i][0]=i;//0项指示是第几张图
            int max=0;
            for(int j=0;j<labelNum;j++){
                if(countArray[i][j]>max){
                    max=countArray[i][j];
                    sortArray[i][1]=j;//1项指示出现最多的标注是label里第几个
                }
            }
        }
        int sampleNum=(int)(pics*p);
        for(int i=0;i<sampleNum;i++){//冒泡排序每次把最大值排到前面
            for(int j=i+1;j<pics;j++){
                if(sortArray[j][1]>sortArray[i][1]){
                    int[] temp=sortArray[j];
                    sortArray[j]=sortArray[i];
                    sortArray[i]=temp;
                }
            }
        }
        for(Collection collection:collections){
            ArrayList<String> eachLabelList = collection.getInfoList();
            for(int i=0;i<sampleNum;i++){
                if(eachLabelList.get(sortArray[i][0]).equals(label.get(sortArray[i][1]))){
                    collection.setQuality(collection.getQuality()+1);
                }
            }
        }
        for(Collection collection:collections){
            collection.setQuality(collection.getQuality()*100/sampleNum);
        }
    }
}
