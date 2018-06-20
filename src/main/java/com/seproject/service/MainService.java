package com.seproject.service;

import com.seproject.common.SearchCategory;
import com.seproject.domain.Collection;
import com.seproject.domain.*;
import com.seproject.service.blService.BasicBLService;
import com.seproject.web.response.ReviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MainService {
    private LanguageService languageService;
    private NewsService newsService;
    private BasicBLService<SubLabelMission> subLabelMissionBasicBLService=Factory.getSubLabelMissionBasicBLService();
    private BasicBLService<GoldMission> goldMissionBasicBLService=Factory.getGoldBasicBLService();
    private BasicBLService<Collection> collectionBasicBLService=Factory.getCollectionBasicBLService();
    private BasicBLService<CollectionResult> collectionResultBasicBLService=Factory.getCollectionResultBasicBLService();
    private BasicBLService<Mission> missionBasicBLService=Factory.getMissionBasicBLService();
    private BasicBLService<User> userBasicBLService=Factory.getUserBasicBLService();
    private BasicBLService<SubFreeMission> subFreeMissionBasicBLService=Factory.getSubFreeMissionBasicBLService();
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
            int random2=base+(random1+goldNum/2)%goldNum;
            subLabelMission.setId1(random1 );
            subLabelMission.setId2(random2);
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
            subFreeMission.setMid(m.getName());
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
        collection.setKeyId(mid+uid);//collection的id是mid+uid
        collection.setUid(uid);
        collection.setMid(mid);
        Mission m=missionBasicBLService.findByKey(mid);
        int picnum=m.getFileNum();
        ArrayList<String> ars=new ArrayList<String>();
        for(int i=0;i<picnum;i++){
            ars.add("");
        }
        collection.setInfoList(ars);
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
        String startName=mission.getRequestorNumber();
        System.out.println("发起者账号:"+startName);
        ArrayList<GoldMission> goldMissions = goldMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        System.out.println("goldMission.size():"+goldMissions.size());
        for (GoldMission goldMission : goldMissions) {
            System.out.println("goldMissionOldName:"+goldMission.getUid());
            if (goldMission.getUid().equals(uid)) {//不能接两次金标
                return false;
            }
            if (goldMission.getUid().equals(startName)) {
                goldMission.setUid(uid);
                goldMissionBasicBLService.update(goldMission);
                User user = userBasicBLService.findByKey(uid);
                user.setCredit(user.getCredit() + 15);
                userBasicBLService.update(user);
                createCollection(uid,mid);
                return true;
            }
        }
        return false;
    }

    /**
     * 没评完的金标让发起者自己评
     */
    public ArrayList<Integer> getRestPictures(String mid){
        ArrayList<Integer> result=new ArrayList<Integer>();
        Mission mission=missionBasicBLService.findByKey(mid);
        String starter=mission.getRequestorNumber();
        ArrayList<GoldMission> goldMissions=goldMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        for(GoldMission goldMission:goldMissions){
            if(goldMission.getUid().equals(starter)){
                result.addAll(goldMission.getPictrueIndex());
            }
        }
        if(result.size()==0){
            System.out.println("金标已经评完，开启标签式自动评估");
            reviewLabelMission(mid);
        }
        System.out.println("金标没评完，自己平去吧");
        return result;
    }

    /**
     * 标签式任务评审入口
     */
    private void reviewLabelMission(String mid){
        Mission mission=missionBasicBLService.findByKey(mid);
        int bonus=mission.getBonusStrategy();
        ArrayList<SubLabelMission> subLabelMissions =subLabelMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        ArrayList<GoldMission> goldMissions=goldMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        System.out.println("金标排列");
        for(GoldMission goldMission:goldMissions){
            System.out.println(goldMission.getPictrueIndex());
            System.out.println(goldMission.getResult());
        }
        for (SubLabelMission subLabelMission : subLabelMissions) {
            ArrayList<Double> weight = new ArrayList<Double>();
            int index1 = subLabelMission.getId1();
            System.out.println("index1:"+index1);
            int index2 = subLabelMission.getId2();
            System.out.println("index2:"+index2);
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
                    weight.add(0.0); //用户接了任务但没做
                }
            }
            String[] mLabel=mission.getMissionLabel().get(0).split(",");
            int labelNumber=mLabel.length;
            double vote[][] = new double[10][labelNumber];//
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < labelNumber; k++) {
                    vote[j][k] = 0;
                }

            }
            for (int j = 0; j < subLabelMission.getAnswers().size(); j++) {
                String user=subLabelMission.getUid().get(j);
                ArrayList<Integer> userAnswer = subLabelMission.getAnswers().get(j);
                System.out.println("用户"+user+"的答案是："+userAnswer);
                for (int k = 0; k < 10; k++) {
                    if(userAnswer.get(k)>=0) {
                        vote[k][userAnswer.get(k)] += weight.get(j);
                    }
                }
            }

            //找到了所有的标准答案，判断每个用户每题对不对，根据答题情况直接发奖励
            int[] standardAnswers = getStandard(vote);
            System.out.println("标准答案");
            for(int a=0;a<standardAnswers.length;a++){
                System.out.println(standardAnswers[a]);
            }
            int isCorrect[][] = new int[subLabelMission.getUid().size()][12];
            for (int j = 0; j < isCorrect.length; j++) {
                ArrayList<Integer> userAnswer = subLabelMission.getAnswers().get(j);
                for (int k = 0; k < 10; k++) {
                    if(userAnswer.get(k)==-1){//如果跳过
                        isCorrect[j][k]=-1;
                    }else if (userAnswer.get(k) == standardAnswers[k]) {
                        isCorrect[j][k] =1;
                    }else {
                        isCorrect[j][k]=0;
                    }
                    //如果一个任务没有得到标准答案，则自动算工人标的是对的
                    if(standardAnswers[k]==-1){
                        isCorrect[j][k]=1;
                    }
                }
                if(userAnswer.get(10)==-1){
                    isCorrect[j][10]=-1;
                }else if(userAnswer.get(10)==answer1){
                    isCorrect[j][10]=1;
                }else isCorrect[j][10]=0;
                if(userAnswer.get(11)==-1){
                    isCorrect[j][11]=-1;
                }else if(userAnswer.get(11)==answer2){
                    isCorrect[j][11]=1;
                }else isCorrect[j][11]=0;
            }

            //根据事先设置的策略分配
            double[] money=null;
            if(bonus==2) {//2号策略：double nothing
                money = giveMoney_DoubleNothing(isCorrect);
            }else if(bonus==3){//3号策略：双色球
                money = giveMoney_DoubleColorBall(isCorrect);
            }
            setCollection(money, subLabelMission.getUid(), mid);
        }
        mission.setState(2);
        missionBasicBLService.update(mission);
    }

    /**
     *把发起者自己评好的金标设置好答案，启动标签评估
     */
    public void finishReview(ArrayList<Integer> index,ArrayList<Integer> answer,String mid){
        ArrayList<GoldMission> goldMissions=goldMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        System.out.println("index:"+index);
        System.out.println("answer:"+answer);
        System.out.println("goldMissionSize:"+goldMissions.size());
        for(int i=0;i<index.size();i++){
            int eachIndex = index.get(i);
            for(GoldMission goldMission:goldMissions){
                ArrayList<Integer> picIndex=goldMission.getPictrueIndex();
                if(picIndex.contains(eachIndex)){
                    goldMission.getResult().set(picIndex.indexOf(eachIndex),answer.get(i));
                    goldMissionBasicBLService.update(goldMission);
                    break;
                }
            }
        }
        reviewLabelMission(mid);
    }

    /**
     * 根据投票选出获取标准答案(标签式)
     */
    private int[] getStandard(double[][] vote){
        int result[]=new int[vote.length];
        for(int i=0;i<vote.length;i++){
            boolean isValid=false;
            double temp[]= vote[i];
            double[] origin=new double[temp.length];
            for(int b=0;b<temp.length;b++){
                origin[b]=temp[b];
            }
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
                temp=origin;
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
            for (int i = 0; i < money.length ; i++) {
                if(i!=j) {
                    if (money[j] < money[i]) {
                        rank[j]++;
                    }
                }
            }
        }
        ArrayList<CollectionResult> collectionResults = collectionResultBasicBLService.search("mid", SearchCategory.EQUAL, mid);
        for (int i = 0; i < uid.size(); i++) {
            String each = uid.get(i);
            User user=userBasicBLService.findByKey(each);
            user.setCredit(user.getCredit()+money[i]);
            if(rank[i]<=(uid.size()/2)){
                user.setLevel(user.getLevel()+1);
            }
            userBasicBLService.update(user);

            Message m1=new Message(uid.get(i)+" * "+getCurrentTime(),"System",uid.get(i),0,
                    "尊敬的用户 "+user.getUserName()+" : 您已成功完成任务 "+mid+" ,您在本次任务中表现出色，获得了"+money[i]+"积分,"
            +"希望您再接再厉！");
            newsService.sendMessage(m1);

            for (CollectionResult collectionResult : collectionResults) {
                if (collectionResult.getUid().equals(each)) {
                    collectionResult.setQuality(8);//默认值
                    collectionResult.setCredit(money[i]);
                    collectionResult.setRank(rank[i]);
                    collectionResult.setState(4);
                    collectionResultBasicBLService.update(collectionResult);
                    break;
                }
            }
        }
    }

    private double[] giveMoney_DoubleNothing(int x[][]){
        //使用double_nothing策略分配奖励
        System.out.println("vote:");
        for(int a=0;a<x.length;a++){
            for(int b=0;b<x[0].length;b++){
                System.out.println(x[a][b]);
            }
        }
        double base=1.6;
        double[] money = new double[x.length];
        // x 是 横坐标用户，纵坐标12个题是否正确的二维数组
        for(int i=0;i<x.length;i++){
            double m=0.15;
            for(int j=0;j<x[0].length;j++){
                if(x[i][j]==1){
                    m*=base;
                }else if(x[i][j]==0){
                    m=0;
                }
            }
            money[i]=m;
        }
        System.out.println("money:");
        for(int i=0;i<money.length;i++){
            System.out.println(money[i]);
        }
        return money;
    }

    private double[] giveMoney_DoubleColorBall(int x[][]){
        //使用双色球策略分配奖励
        double [] money=new double[x.length];
        for(int i=0;i<x.length;i++){
            int blue=0;
            int red=0;
            if(x[i][10]!=1 && x[i][11]!=1){
                blue=0;
            }else if((x[i][10]==1&&x[i][11]!=1)||(x[i][10]!=1&&x[i][11]==1)){
                blue=1;
            }else if(x[i][10]==1 && x[i][11]==1){
                blue=2;
            }

            for(int j=0;j<10;j++){
                if(x[i][j]==1){
                    red++;
                }
            }
            /*
             * 蓝球命中2个，则获得0.9RMB , 命中1个，则获得0.45RMB
             * 10+2     0.15*12*10,则获得18 RMB
             * 9+2      0.15*12*7.5，则获得13.5RMB
             * 8+2 /10+1     0.15*12*5 则获得9RMB
             * 7+2 /9+1     0.15*12*2.5 则获得4.5RMB
             * 6+2/8+1      0.15*12  则获得1.8RMB
             */

            if(blue==2){
                if(red==10){
                    money[i]=18;
                }else if(red==9){
                    money[i]=9;
                }else if(red==8){
                    money[i]=4.5;
                }else if(red==7){
                    money[i]=2.25;
                }else if(red==6){
                    money[i]=1.5;
                }else{
                    money[i]=0.9;
                }
            }else if(blue==1){
                if(red==10){
                    money[i]=9;
                }else if(red==9){
                    money[i]=4.5;
                }else if(red==8){
                    money[i]=1.8;
                }else{
                    money[i]=0.45;
                }
            }else{
                money[i]=0;
            }

        }
        return money;
    }

    private double[] giveMoney_Average(int x[] ,double base){
        //给评估工人分钱,传入的是每个工人标的图片张数，和一张多少钱
        double money[]=new double[x.length];
        for(int i=0;i<x.length;i++){
            money[i]=x[i]*base;
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
            //如果子任务没有，就到金标任务去找
            ArrayList<GoldMission> goldMissions=goldMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
            for(GoldMission goldMission:goldMissions){
                String tempUid=goldMission.getUid();
                if(tempUid.equals(uid)){
                    result.addAll(goldMission.getPictrueIndex());
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

    /**
     * 评审自由式任务
     */
    public void autoReviewFreeMission(String mid){
        Mission mission=missionBasicBLService.findByKey(mid);
        mission.setState(2);
        missionBasicBLService.update(mission);

        ArrayList<SubFreeMission> subFreeMissions=subFreeMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        for(SubFreeMission subFreeMission:subFreeMissions){
            ArrayList<String> users=subFreeMission.getUid();
            System.out.println("users:"+users);
            if(users!=null&&users.size()>0) {
                ArrayList<Integer> index = getPictureIndexOfSubmission(users.get(0), mid);
                ArrayList<Double> grade=getGrade(index,users,mid);
                System.out.println("grade"+grade);
                double avg=0.0;
                for(Double each:grade){
                    avg+=each;
                }
                avg/=users.size();
                int[] rank=new int[grade.size()];//排名列表
                for(int i=0;i<rank.length;i++){rank[i]=1;}
                for(int i=0;i<rank.length;i++){
                    for(int j=0;j<rank.length;j++){
                        if(j!=i){
                            if(grade.get(j)>grade.get(i)){
                                rank[i]++;
                            }
                        }
                    }
                }
                //用得分存储排名和积分到相应的结果中

                System.out.println("users:"+users);
                for(int i=0;i<users.size();i++){ ;
                    User user=userBasicBLService.findByKey(users.get(i));
                    double reward=1.5*grade.get(i)/avg;
                    user.setCredit(user.getCredit()+reward);//1.5*得分/平均得分
                    if(rank[i]<=users.size()/2){
                        user.setLevel(user.getLevel()+1);
                    }
                    userBasicBLService.update(user);

                    Message m1=new Message(user.getPhoneNumber()+" * "+getCurrentTime(),"System",user.getPhoneNumber(),0,
                            "尊敬的用户 "+user.getUserName()+" : 您已成功完成任务 "+mid+" ,您在本次任务中表现出色，获得了"+reward+"积分,"
                                    +"希望您再接再厉！");
                    newsService.sendMessage(m1);

                    CollectionResult collectionResult=collectionResultBasicBLService.findByKey(mid+users.get(i));
                    collectionResult.setRank(rank[i]);
                    collectionResult.setCredit(reward);
                    double eachGrade=grade.get(i);
                    collectionResult.setQuality((int)eachGrade);
                    collectionResult.setState(4);
                    collectionResultBasicBLService.update(collectionResult);
                }
            }
        }
    }

    /**
     * 计算自由式任务中子任务的得分
     */
    public ArrayList<Double> getGrade(ArrayList<Integer> index,ArrayList<String> uid,String mid){
        ArrayList<Double> grade=new ArrayList<Double>();//对应每个用户的所有图片的得分之和
        for(int i=0;i<uid.size();i++){
            grade.add(0.0);
        }
        for(int eachIndex:index) {
            ArrayList<FreeMissionDetail> details = new ArrayList<FreeMissionDetail>();
            if(uid.size()==1){//如果只有一个用户标，直接拿分即可
                grade.set(0,1.0);
                return grade;
            }
            for (int j = 0; j < uid.size(); j++) {
                Collection collection=collectionBasicBLService.findByKey(mid+uid.get(j));
                details.add(new FreeMissionDetail(collection.getInfoList().get(eachIndex),eachIndex));
            }
            double avgFrameNum = 0;
            double avgFrameSquare = 0;
            double maxFrameNum=0;
            double minFrameNum=0;
            double maxFrameSquare=0;
            double minFrameSquare=0;
            for (FreeMissionDetail eachDetail : details) {
                double frameNum=eachDetail.getX().size();
                double frameSq=0;
                avgFrameNum += eachDetail.getX().size();
                for (int k = 0; k < eachDetail.getHeight().size(); k++) {
                    avgFrameSquare += eachDetail.getHeight().get(k) * eachDetail.getWeight().get(k);
                    frameSq+=eachDetail.getHeight().get(k) * eachDetail.getWeight().get(k);
                }
                if(frameNum>maxFrameNum) maxFrameNum=frameNum;
                if(frameNum<minFrameNum) minFrameNum=frameNum;
                if(frameSq>maxFrameSquare) maxFrameSquare=frameSq;
                if(frameSq<minFrameSquare) minFrameNum=frameSq;

            }
            avgFrameNum /= uid.size();//平均框数量
            avgFrameSquare /= uid.size();//平均框面积

            if(avgFrameNum==0||avgFrameSquare==0) continue;//说明这张图无效

            for(int j=0;j<uid.size();j++){
                FreeMissionDetail detail=details.get(j);

            }

            for (int j = 0; j < uid.size(); j++) {
                double eachGrade = 0;
                String summary = details.get(j).getSummary();
                for (int p = 0; p < uid.size(); p++) {
                    System.out.println("P:"+p);
                    if (p != j) {
                        double similar = languageService.simliarityOfText(summary, details.get(p).getSummary());
                        if (similar < 0.5) {
                            eachGrade+=0.1;//加一个较低的分
                        }else {
                            eachGrade+=similar;
                        }
                    }
                }
                if (eachGrade > 0) {
                    double frame = details.get(j).getX().size();
                    double frameSquare = 0.0;
                    for (int m = 0; m < details.get(j).getHeight().size(); m++) {
                        frameSquare += details.get(j).getHeight().get(m) * details.get(j).getWeight().get(m);
                    }
                    eachGrade*=100;
                    double temp=Math.abs(frame - avgFrameNum)/ (maxFrameNum-minFrameNum);
                    eachGrade *= (1.0 - temp);
                    eachGrade *= (1.0 - (Math.abs(frameSquare - avgFrameSquare)) / maxFrameSquare-minFrameSquare);
                }
                grade.set(j,grade.get(j)+eachGrade);
            }
            }
        return grade;
    }

    /**
     * 给自由式任务抽样
     */
    public void createFreeMissionSample(ReviewResponse response,String mid){
        ArrayList<SubFreeMission> subFreeMissions=subFreeMissionBasicBLService.search("mid",SearchCategory.EQUAL,mid);
        for(SubFreeMission subFreeMission:subFreeMissions){
            ArrayList<String> uid=subFreeMission.getUid();
            if(uid==null||uid.size()<=0||uid.get(0).length()<=0) continue;//如果这个子任务没人做，直接跳过
            ArrayList<Integer> levels=new ArrayList<Integer>();
            int avgLevel=0;
            for(String eachUid:uid){
                User user=userBasicBLService.findByKey(eachUid);
                levels.add(user.getLevel());
                avgLevel+=user.getLevel();
            }
            avgLevel/=uid.size();
            for(int k=0;k<uid.size();k++){
                Collection collection=collectionBasicBLService.findByKey(mid+uid.get(k));
                if(levels.get(k)>=avgLevel){//高级工人抽一张
                    int random1=(int)(Math.random()*10);
                    response.getPicIndex().add(random1+subFreeMission.getSeed()*10);
                    response.getUid().add(uid.get(k));
                    response.getInfo().add(collection.getInfoList().get(random1));
                }else{//低级工人抽两张
                    int random1=(int)(Math.random()*10);
                    int random2=(random1+5)%10;
                    response.getPicIndex().add(random1+subFreeMission.getSeed()*10);
                    response.getUid().add(uid.get(k));
                    response.getInfo().add(collection.getInfoList().get(random1));
                    response.getPicIndex().add(random1+subFreeMission.getSeed()*10);
                    response.getUid().add(uid.get(k));
                    response.getInfo().add(collection.getInfoList().get(random2));

                }
            }
        }
    }

    /**
     *抽样评估自由式任务
     */
    public void manualReviewFreeMission(ArrayList<Integer> pic,ArrayList<Integer> quality,ArrayList<String> uid,String mid){
        Mission mission=missionBasicBLService.findByKey(mid);
        mission.setState(2);
        missionBasicBLService.update(mission);

        Map<String,Integer> result=new HashMap<String,Integer>();
        for(int i=0;i<quality.size();i++){
            if(result.containsKey(uid.get(i))) {
                if (result.get(uid.get(i)) > quality.get(i)) {
                    result.put(uid.get(i), quality.get(i));//取最差的做质量
                }
            }else {
                result.put(uid.get(i), quality.get(i));
            }
        }
        ArrayList<Integer> qua=new ArrayList<Integer>();
        ArrayList<String> u=new ArrayList<String>();
        for(String key:result.keySet()){
            u.add(key);
            qua.add(result.get(key));
        }
        int[] rank=new int[u.size()];
        System.out.println("qua:"+qua);
        System.out.println("u:"+u);
        for(int i=0;i<qua.size();i++){
            rank[i]=1;
            for(int j=0;j<qua.size();j++){
                if(i!=j){
                    if(qua.get(j)>qua.get(i)){
                        rank[i]++;
                    }
                }
            }
        }

        for(int i=0;i<u.size();i++){
            String key=u.get(i);
            User user=userBasicBLService.findByKey(key);
            int q=result.get(key);
            CollectionResult collectionResult=collectionResultBasicBLService.findByKey(mid+key);

            user.setCredit(user.getCredit()+1.5*q/10);
            if(rank[i]<=(u.size()/2)){
                user.setLevel(user.getLevel()+1);
            }
            userBasicBLService.update(user);
            System.out.println("collectionResult:"+(collectionResult==null));
            collectionResult.setQuality(q);
            collectionResult.setCredit(1.5*q/10);
            collectionResult.setRank(rank[i]);
            ArrayList<Integer> picIndex=new ArrayList<Integer>();
            ArrayList<Integer> picGrade=new ArrayList<Integer>();
            int count=0;
            for(int j=0;j<uid.size();j++){
                if(uid.get(j).equals(key)){
                    picIndex.add(pic.get(j));
                    picGrade.add(quality.get(j));
                    count++;
                    if(count==2) break;
                }
            }
            int[] picInd=new int[picIndex.size()];
            int[] pivGra=new int[picGrade.size()];
            for(int k=0;k<picIndex.size();k++){
                picInd[k]=picIndex.get(k);
                pivGra[k]=picGrade.get(k);
            }
            collectionResult.setPicIdValue(picInd);
            collectionResult.setPicGradeValue(pivGra);
            collectionResult.setState(4);
            collectionResultBasicBLService.update(collectionResult);
        }

    }

    /**
     * 获取当前时间
     */
    public  String getCurrentTime(){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  df.format(new Date());
    }
    @Autowired
    public void setLanguageService(LanguageService languageService){
        this.languageService=languageService;
    }

    @Autowired
    public void setNewsService(NewsService newsService){this.newsService=newsService;}
}
