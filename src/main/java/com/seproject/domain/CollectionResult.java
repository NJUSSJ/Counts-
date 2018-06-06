package com.seproject.domain;

import com.seproject.common.Key;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    @Column(name="credit")
    private double credit;
    @Column(name="picid",columnDefinition = "blob")
    private int picId[];//被抽样的图
    @Column(name="picgrade",columnDefinition = "blob")
    private int picGrade[];//被抽样的图的得分
    @Column(name="rank")
    private int rank;
    @Column(name="quality")
    private int quality;
    @Column(name="state")
    private int state;//0 保存 1 提交 2 未保存 3 已被工人删除

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public int[] getPicId() {
        return picId;
    }

    public void setPicId(int[] picId) {
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

    public int[] getPicGrade() {
        return picGrade;
    }

    public void setPicGrade(int[] picGrade) {
        this.picGrade = picGrade;
    }

    public int getState() { return state; }

    public void setState(int state) { this.state = state; }

    public String getResultId() { return resultId; }

    public void setResultId(String resultId) { this.resultId = resultId; }
}
