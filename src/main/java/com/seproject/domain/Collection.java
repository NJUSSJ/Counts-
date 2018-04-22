package com.seproject.domain;

import com.seproject.service.Key;
import com.seproject.service.Searchable;

import java.util.ArrayList;

public class Collection {
    @Key
    private String keyId;
    @Searchable(varName = "uid")
    private String uid;
    @Searchable(varName = "mid")
    private String mid;
    ArrayList<TagInfo> infoList = new ArrayList<TagInfo>();
    private int state;

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
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

    public void setInfoList(ArrayList<TagInfo> list) {
        infoList = list;
    }
    public ArrayList<TagInfo> getInfoList(){return infoList;}

    public int getState(){return state;}

    public void setState(int state){this.state=state;}
}
