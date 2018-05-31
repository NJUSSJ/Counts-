package com.seproject.domain;

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
    private String keyid;
    private double credit;
    private int picId[];
    private int picGrade[];
    private int rank;
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
