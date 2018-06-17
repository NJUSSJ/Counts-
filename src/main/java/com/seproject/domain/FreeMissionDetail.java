package com.seproject.domain;

import com.seproject.common.Key;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

/**
 * 记录自由式任务中，每个用户每张图的标注信息
 */
@Entity
@Table(name="freemissiondetail")
public class FreeMissionDetail {
    @Key
    @Id
    @Column(name="keyid")
    private String keyid;//是mid+uid+picIndex
    @Column(name="uid")
    private String uid;
    @Column(name="picindex")
    private int picIndex;//图片索引
    @Column(name="x")
    private ArrayList<Integer> x;
    @Column(name="y")
    private ArrayList<Integer> y;
    @Column(name="height")
    private ArrayList<Integer> height;
    @Column(name="weight")
    private ArrayList<Integer> weight;
    @Column(name="summary")
    private String summary;//整体标

    public String getKeyid() {
        return keyid;
    }

    public void setKeyid(String keyid) {
        this.keyid = keyid;
    }

    public int getPicIndex() {
        return picIndex;
    }

    public void setPicIndex(int picIndex) {
        this.picIndex = picIndex;
    }

    public ArrayList<Integer> getX() {
        return x;
    }

    public void setX(ArrayList<Integer> x) {
        this.x = x;
    }

    public ArrayList<Integer> getY() {
        return y;
    }

    public void setY(ArrayList<Integer> y) {
        this.y = y;
    }

    public ArrayList<Integer> getHeight() {
        return height;
    }

    public void setHeight(ArrayList<Integer> height) {
        this.height = height;
    }

    public ArrayList<Integer> getWeight() {
        return weight;
    }

    public void setWeight(ArrayList<Integer> weight) {
        this.weight = weight;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
