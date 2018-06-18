package com.seproject.domain;

import com.seproject.common.Key;
import com.seproject.common.Searchable;

import javax.persistence.*;
/**
 *评估任务结束后的工人查看结果，每个CollectionResult与对应的Collection有相同的id
 */
@Entity
@Table(name="collectionresult")
public class CollectionResult {
    public CollectionResult(){}
    public CollectionResult(Collection c){
        this.resultId=c.getKeyId();
    }
    @Key
    @Id
    @Column(name="resultid")
    private String resultId;
    @Column(name="uid")
    @Searchable(varName = "uid")
    private String uid;
    @Column(name="mid")
    @Searchable(varName = "mid")
    private String mid;
    @Column(name="credit")
    private double credit;
    @Column(name="picid")
    private String picId;//被抽样的图
    @Column(name="picgrade")
    private String picGrade;//被抽样的图的得分
    @Column(name="rank")
    private int rank;
    @Column(name="quality")
    private int quality;
    @Column(name="state")
    private int state;//0 保存 1 提交 2 未保存 3 已被工人删除 4 评估已经结束

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getPicGrade() {
        return picGrade;
    }

    public void setPicGrade(String picGrade) {
        this.picGrade = picGrade;
    }

    public int getState() { return state; }

    public void setState(int state) { this.state = state; }

    public String getResultId() { return resultId; }

    public void setResultId(String resultId) { this.resultId = resultId; }

    public int[] getPicIdValue(){
        String[] str=picId.split("#@#");
        int[] result=new int[str.length];
        for(int i=0;i<str.length;i++){
            result[i]=Integer.parseInt(str[i]);
        }
        return result;
    }

    public void setPicIdValue(int[] value){
        picId="";
        for(int i=0;i<value.length;i++){
            picId+=value[i]+"#@#";
        }
    }

    public int[] getPicGradeValue(){
        String[] str=picGrade.split("#@#");
        int[] result=new int[str.length];
        for(int i=0;i<str.length;i++){
            result[i]=Integer.parseInt(str[i]);
        }
        return result;
    }

    public void setPicGradeValue(int[] value){
        picGrade="";
        for(int i=0;i<value.length;i++){
            picGrade+=value[i]+"#@#";
        }
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }
}
