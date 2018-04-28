package com.seproject.domain;

import com.seproject.service.Key;
import com.seproject.service.Searchable;

import java.util.ArrayList;

public class Mission {
    @Key
    String name;

    String startTime;
    String endTime;
    String description;
    String workerLevel;
    ArrayList<String> files=new ArrayList<String>();
    int fileNum;
    double reward;
    int expectedNum;

    @Searchable(varName = "requestorNumber")
    String requestorNumber;
    int state;//0:未完成 1：完成 2:已经评估
    public Mission(){

    }
    public Mission(String name, String startTime, String endTime, String description, ArrayList<String> files, int fileNum, String workerLevel, int reward, int expectedNum, String requestorNumber){
        this.name=name;
        this.startTime=startTime;
        this.endTime=endTime;
        this.description=description;
        this.files=files;
        this.fileNum=fileNum;
        this.workerLevel=workerLevel;
        this.expectedNum=expectedNum;
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

    public void setStartTime(String startTime){
        this.startTime=startTime;
    }

    public String getStartTime(){
        return startTime;
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

    public void setExpectedNum(int expectedNum){
        this.expectedNum=expectedNum;
    }

    public int getExpectedNum(){
        return this.expectedNum;
    }

    public void setRequestorNumber(String requestorNumber){
        this.requestorNumber=requestorNumber;
    }

    public String getRequestorNumber(){
        return this.requestorNumber;
    }

    public int getState(){return state;}

    public void setState(int state){this.state=state;}

}
