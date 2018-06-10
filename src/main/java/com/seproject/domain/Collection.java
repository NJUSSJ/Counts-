package com.seproject.domain;

import com.seproject.common.Key;
import com.seproject.common.Searchable;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="collection")
/**
 * 工人和任务之间的匹配对象，每个工人接收任务之后就会产生
 * 同时维护一个collectionResult对象,有相同的keyid,但是只有collectionResult里的属性对工人可见
 */
public class Collection {
    @Key
    @Id
    @Column(name="keyid")
    private String keyId;
    @Column(name="uid")
    @Searchable(varName = "uid")
    private String uid;
    @Column(name="mid")
    @Searchable(varName = "mid")
    private String mid;
    @Column(name="infolist",columnDefinition = "blob")
    private ArrayList<String> infoList = new ArrayList<String>();//如果是标签式，那么里面是按图集顺序的标签
    @OneToOne(cascade = CascadeType.ALL,targetEntity =CollectionResult.class)
    @JoinColumn(name="result",referencedColumnName = "resultid")
    private CollectionResult result = new CollectionResult(this);

    public String getKeyId() { return keyId; }

    public void setKeyId(String keyId) { this.keyId = keyId; }

    public String getUid() { return uid; }

    public void setUid(String uid) { this.uid = uid; }

    public String getMid() { return mid; }

    public void setMid(String mid) { this.mid = mid; }

    public ArrayList<String> getInfoList() { return infoList; }

    public void setInfoList(ArrayList<String> infoList) { this.infoList = infoList; }

    public CollectionResult getResult() {return result; }

    public void setResult(CollectionResult result){ this.result=result; }
    /*下面这段只是为了符合之前的结构，是对collectioniResult操作*/
    public int getState(){return result.getState();}

    public void setState(int state){this.result.setState(state);}

    public double getCredit() { return result.getCredit(); }

    public void setCredit(double credit) { this.result.setCredit(credit); }

    public int getQuality() { return result.getQuality(); }

    public void setQuality(int quality) { this.result.setQuality(quality); }

    public int getRank(){return this.result.getRank();}

    public void setRank(int rank){this.result.setRank(rank);}
}
