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
    ArrayList<ImageInfo> infoList = new ArrayList<ImageInfo>();
    private int state;//0 保存 1 提交 2 未保存

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

    public void setInfoList(ArrayList<ImageInfo> list) {
        infoList = list;
    }
    public ArrayList<ImageInfo> getInfoList(){return infoList;}

    public int getState(){return state;}

    public void setState(int state){this.state=state;}
}
