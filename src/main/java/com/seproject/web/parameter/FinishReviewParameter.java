package com.seproject.web.parameter;

import java.util.ArrayList;

public class FinishReviewParameter {
    private String mid;
    private ArrayList<Integer> indexs=new ArrayList<Integer>();//标签式里面是金标图片位置，自由式里面是样本图片位置
    private ArrayList<Integer> answers=new ArrayList<Integer>();//标签式里面是金标答案，自由式里面是quality
    private ArrayList<String> uid=new ArrayList<String>();//只有标签式才有用的用户id

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public ArrayList<Integer> getIndexs() {
        return indexs;
    }

    public void setIndexs(ArrayList<Integer> indexs) {
        this.indexs = indexs;
    }

    public ArrayList<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Integer> answers) {
        this.answers = answers;
    }

    public ArrayList<String> getUid() {
        return uid;
    }

    public void setUid(ArrayList<String> uid) {
        this.uid = uid;
    }
}
