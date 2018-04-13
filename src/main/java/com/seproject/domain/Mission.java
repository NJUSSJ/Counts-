package com.seproject.domain;

import com.seproject.service.Key;

public class Mission {
    @Key
    String name;

    /*
    String requestorID;
    double reward;
    String ddl;
    String startTime;
    String missionName;
    int max_finisher;
     */

    String startTime;
    String endTime;
    String description;
    String workerLevel;
    String[] files;
    int fileNum;
    String[] tags;

    public Mission(){

    }
    public Mission(String name, String startTime, String endTime, String description, String[] files, int fileNum, String workerLevel){
        this.name=name;
        this.startTime=startTime;
        this.endTime=endTime;
        this.description=description;
        this.files=files;
        this.fileNum=fileNum;
        this.workerLevel=workerLevel;
    }

    public void setWorkerLevel(String workerLevel){
        this.workerLevel=workerLevel;
    }

    public String getWorkerLevel(){
        return this.workerLevel;
    }

    public void setTags(String[] tags){
        this.tags=tags;
    }

    public String[] getTags(){
        return this.tags;
    }

    public void setFileNum(int num){
        this.fileNum=num;
    }

    public int getFileNum(){
        return this.fileNum;
    }

    public void setFiles(String files[]){
        this.files=files;
    }

    public String[] getFiles(){
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






}
