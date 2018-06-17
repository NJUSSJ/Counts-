package com.seproject.web.parameter;

import java.util.ArrayList;

public class FinishReviewParameter {
    private String mid;
    private ArrayList<Integer > indexs=new ArrayList<Integer>();//金标图片是第几张
    private ArrayList<Integer > answers=new ArrayList<Integer>();

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
}
