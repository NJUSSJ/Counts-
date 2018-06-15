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
    private String keyId;//
    @Column(name="uid")
    @Searchable(varName = "uid")
    private String uid;
    @Column(name="mid")
    @Searchable(varName = "mid")
    private String mid;
    @Column(name="infolist",columnDefinition = "blob")
    private ArrayList<String> infoList = new ArrayList<String>();//如果是标签式，那么里面是空的

    public String getKeyId() { return keyId; }

    public void setKeyId(String keyId) { this.keyId = keyId; }

    public String getUid() { return uid; }

    public void setUid(String uid) { this.uid = uid; }

    public String getMid() { return mid; }

    public void setMid(String mid) { this.mid = mid; }

    public ArrayList<String> getInfoList() { return infoList; }

    public void setInfoList(ArrayList<String> infoList) { this.infoList = infoList; }

}
