package com.seproject.domain;

import com.seproject.common.Key;
import com.seproject.common.Searchable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

/**
 * 自由式任务的子任务
 */
@Entity
@Table(name="subfreemission")
public class SubFreeMission {
    @Key
    @Id
    @Searchable(varName = "keyID")
    @Column(name="keyid")
    private String keyID="";//mid+seed
    @Searchable(varName = "mid")
    @Column(name="mid")
    private String mid=""; //任务ID
    @Column(name="uid")
    private ArrayList<String> uid=new ArrayList<String>();
    @Searchable(varName = "seed")
    @Column(name="seed")
    private int seed=0;//种子

    public String getKeyID() { return keyID; }

    public void setKeyID(String keyID) { this.keyID = keyID; }

    public String getMid() { return mid; }

    public void setMid(String mid) { this.mid = mid; }

    public ArrayList<String> getUid() { return uid; }

    public void setUid(ArrayList<String> uid) { this.uid = uid; }

    public int getSeed() { return seed; }

    public void setSeed(int seed) { this.seed = seed; }
}
