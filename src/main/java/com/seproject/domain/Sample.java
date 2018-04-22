package com.seproject.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 抽样的得到的样本
 * 可能是不需要持久化的对象
 */
public class Sample {
    private String missionName;
    private Map<String,ImageInfo> info=new HashMap<String, ImageInfo>();

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public Map<String, ImageInfo> getInfo() {
        return info;
    }

    public void setInfo(Map<String, ImageInfo> info) {
        this.info = info;
    }
}
