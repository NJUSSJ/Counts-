package com.seproject.web.response;

import java.util.ArrayList;

/**
 * 评估任务用的返回对象
 */
public class ReviewResponse {
    private int type;//1是标签，2是非标签
    private ArrayList<String> label;//对于非标签，这一项没有用
    private ArrayList<Integer> picIndex;
    private ArrayList<String> uid;//对于标签式，这一项没有用
    private ArrayList<String> info;//对于标签式，这一项没有用

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<String> getLabel() {
        return label;
    }

    public void setLabel(ArrayList<String> label) {
        this.label = label;
    }

    public ArrayList<Integer> getPicIndex() {
        return picIndex;
    }

    public void setPicIndex(ArrayList<Integer> picIndex) {
        this.picIndex = picIndex;
    }

    public ArrayList<String> getUid() {
        return uid;
    }

    public void setUid(ArrayList<String> uid) {
        this.uid = uid;
    }

    public ArrayList<String> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<String> info) {
        this.info = info;
    }
}
