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
@Table(name="mission")
public class Mission {
    @Key
    @Id
    @Searchable(varName = "name")
    @Column(name="name")
    String name;
    @Column(name="starttime")
    String startTime;
    @Column(name="endtime")
    String endTime;
    @Column(name="description")
    String description;
    @Column(name="workerlevel")
    String workerLevel;//接受工人的最低等级
    @Column(name="files",columnDefinition = "blob")
    ArrayList<String> files=new ArrayList<String>();
    @Column(name="filenum")
    int fileNum;
    @Column(name="reward")
    double reward;
    @Column(name = "recommendlabel",columnDefinition = "blob")
    private ArrayList<String> recommendLabel;//推荐类别列表
    @Searchable(varName = "requestorNumber")
    @Column(name="requestornumber")
    String requestorNumber;//发起者ID
    @Column(name="state")
    int state;//0:未结束 1：结束但未评估 2:已评估 3：已被发起者删除
    @Column(name="difficulty")
    @Searchable(varName = "difficulty")
    private int difficulty;//1,2,3
    @Column(name="missionlabel",columnDefinition = "blob")
    private ArrayList<String> missionLabel;//标签式任务的标签列表
    @Column(name="tagtype")
    @Searchable(varName = "tagType")
    private int tagType;//1:标签式 2:非标签式
    @Column(name="pictype")
    private String picType;
    @Column(name="maxworkernum")
    private int maxWorkerNum;
    @Column(name = "bonusStrategy")
    private int bonusStrategy; //1，平均分配 2，double-nothing 3,双色球
    @Column(name = "evaluateStrategy")
    private int evaluateStrategy;//1,自动 2，手动 3，雇用工人
    public Mission(){

    }
    public Mission(String name, String endTime, String description, ArrayList<String> files, int fileNum, String workerLevel, int reward, String requestorNumber, int difficulty, int tagType, String picType, ArrayList<String> missionLabel, int maxWorkerNum, int evaluateStrategy, int bonusStrategy){
        this.name=name;
        this.endTime=endTime;
        this.description=description;
        this.files=files;
        this.fileNum=fileNum;
        this.workerLevel=workerLevel;
        this.reward=reward;
        this.requestorNumber=requestorNumber;
        this.difficulty = difficulty;
        this.tagType = tagType;
        this.picType = picType;
        this.missionLabel = missionLabel;
        this.maxWorkerNum = maxWorkerNum;
        this.evaluateStrategy = evaluateStrategy;
        this.bonusStrategy = bonusStrategy;
    }

    public void setEvaluateStrategy(int evaluateStrategy) {
        this.evaluateStrategy = evaluateStrategy;
    }

    public int getEvaluateStrategy() {
        return evaluateStrategy;
    }

    public void setBonusStrategy(int bonusStrategy) {
        this.bonusStrategy = bonusStrategy;
    }

    public int getBonusStrategy() {
        return bonusStrategy;
    }

    public int getMaxWorkerNum() {
        return maxWorkerNum;
    }

    public void setMaxWorkerNum(int maxWorkerNum) {
        this.maxWorkerNum = maxWorkerNum;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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

    public int getTagType() {
        return tagType;
    }

    public void setTagType(int tagType) {
        this.tagType = tagType;
    }

    public String getPicType() {
        return picType;
    }

    public void setPicType(String picType) {
        this.picType = picType;
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
