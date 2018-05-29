package com.seproject.domain;

import com.seproject.common.Key;
import com.seproject.common.Searchable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
@Entity
@Table(name="Mission")
public class Mission {
    @Key
    @Id
    @Column(name="name")
    String name;
    @Column(name="endTime")
    String endTime;
    @Column(name="description")
    String description;
    @Column(name="workerLevel")
    String workerLevel;
    @Column(name="files")
    ArrayList<String> files=new ArrayList<String>();
    @Column(name="filenum")
    int fileNum;
    @Column(name="reward")
    double reward;
    @Column(name="maxnum")
    int maxNum;
    @Column(name = "recommendlabel")
    private ArrayList<String> recommendLabel;//推荐类别列表
    @Searchable(varName = "requestorNumber")
    @Column(name="requestornumber")
    String requestorNumber;//发起者ID
    @Column(name="state")
    int state;//0:未完成 1：完成 2:已经评估 3：已被发起者删除
    @Column(name="difficulty")
    private int difficulty;
    @Column(name="missionlabel")
    private ArrayList<String> missionLabel;//标签式任务的标签列表
    public Mission(){

    }
    public Mission(String name, String endTime, String description, ArrayList<String> files, int fileNum, String workerLevel, int reward, int maxNum, String requestorNumber){
        this.name=name;
        this.endTime=endTime;
        this.description=description;
        this.files=files;
        this.fileNum=fileNum;
        this.workerLevel=workerLevel;
        this.maxNum=maxNum;
        this.reward=reward;
        this.requestorNumber=requestorNumber;
    }

    public void setWorkerLevel(String workerLevel){
        this.workerLevel=workerLevel;
    }

    public String getWorkerLevel(){
        return this.workerLevel;
    }

    public void setFileNum(int num){
        this.fileNum=num;
    }

    public int getFileNum(){
        return this.fileNum;
    }

    public void setFiles(ArrayList<String> files){
        this.files=files;
    }

    public ArrayList<String> getFiles(){
        return  this.files;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public String getDescription(){
        return this.description;
    }

    public void setEndTime(String endTime){
        this.endTime=endTime;
    }

    public String getEndTime(){
        return this.endTime;
    }

    public void setReward(double reward){
        this.reward=reward;
    }

    public double getReward(){
        return this.reward;
    }

    public void setMaxNum(int maxNum){
        this.maxNum=maxNum;
    }

    public int getMaxNum(){
        return this.maxNum;
    }

    public void setRequestorNumber(String requestorNumber){
        this.requestorNumber=requestorNumber;
    }

    public String getRequestorNumber(){
        return this.requestorNumber;
    }

    public int getState(){return state;}

    public void setState(int state){this.state=state;}

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public ArrayList<String> getMissionLabel() {
        return missionLabel;
    }

    public void setMissionLabel(ArrayList<String> missionLabel) {
        this.missionLabel = missionLabel;
    }

    public ArrayList<String> getRecommendLabel() {
        return recommendLabel;
    }

    public void setRecommendLabel(ArrayList<String> recommendLabel) {
        this.recommendLabel = recommendLabel;
    }
}
