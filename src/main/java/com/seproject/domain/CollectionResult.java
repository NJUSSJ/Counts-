package com.seproject.domain;

import com.seproject.common.Key;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="collectionresult")
/**
 *评估任务结束后的工人查看结果，每个CollectionResult与对应的Collection有相同的id
 */
public class CollectionResult {
    public CollectionResult(){}
    public CollectionResult(Collection c){
        this.keyid=c.getKeyId();
        this.credit=c.getCredit();
        this.quality=c.getQuality();
    }
    @Key
    @Id
    @Column(name="keyid")
    private String keyid;
    @Column(name="credit")
    private double credit;
    @Column(name="picid",columnDefinition = "blob")
    private int picId[];
    @Column(name="picgrade",columnDefinition = "blob")
    private int picGrade[];
    @Column(name="rank")
    private int rank;
    @Column(name="quality")
    private int quality;

    public String getKeyid() {
        return keyid;
    }

    public void setKeyid(String keyid) {
        this.keyid = keyid;
    }

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
}
