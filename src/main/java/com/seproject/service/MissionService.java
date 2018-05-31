package com.seproject.service;

import com.seproject.common.SearchCategory;
import com.seproject.domain.Collection;
import com.seproject.domain.Mission;
import com.seproject.domain.User;
import com.seproject.service.blService.BasicBLService;

import java.util.ArrayList;

public class MissionService {
    BasicBLService<User> userBasicBLService=Factory.getBasicBLService(new User());
    BasicBLService<Mission> missionBasicBLService=Factory.getBasicBLService(new Mission());
    BasicBLService<Collection> collectionBasicBLService=Factory.getBasicBLService(new Collection());
    /**
     *推荐排序
     * 算法1.计算用户之间的喜好相似度，统计当前相似用户正在进行的任务，推荐给该用户
     * 算法2.计算任务标签与用户喜好的重合度，排序后推荐给该用户
     * 算法3.计算任务之间的相似度 ，如果一个用户接了任务，推荐给其与该任务类似的任务
     * 推荐列表是三者的结合，优先度1>2>3（算法1，算法2，算法3，算法1，算法2......），如果有重合则合并
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
