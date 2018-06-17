package com.seproject.web.parameter;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;

public class FreeMissionParameter {
    private String keyid;
    private ArrayList<String> sentences;
    private ArrayList<Integer> sentids;
    private String imgid;
    private String filename;
    private ArrayList<Integer> fixedx;
    private ArrayList<Integer> fixedy;
    private ArrayList<Integer> fixedwidth;
    private ArrayList<Integer> fixedheight;
    private ArrayList<String> list;


    public String getKeyid() {
        return keyid;
    }

    public void setKeyid(String keyid) {
        this.keyid = keyid;
    }

    public ArrayList<String> getSentences() {
        return sentences;
    }

    public void setSentences(ArrayList<String> sentences) {
        this.sentences = sentences;
    }

    public ArrayList<Integer> getSentids() {
        return sentids;
    }

    public void setSentids(ArrayList<Integer> sentids) {
        this.sentids = sentids;
    }

    public String getImgid() {
        return imgid;
    }

    public void setImgid(String imgid) {
        this.imgid = imgid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public ArrayList<Integer> getFixedx() {
        return fixedx;
    }

    public void setFixedx(ArrayList<Integer> fixedx) {
        this.fixedx = fixedx;
    }

    public ArrayList<Integer> getFixedy() {
        return fixedy;
    }

    public void setFixedy(ArrayList<Integer> fixedy) {
        this.fixedy = fixedy;
    }

    public ArrayList<Integer> getFixedwidth() {
        return fixedwidth;
    }

    public void setFixedwidth(ArrayList<Integer> fixedwidth) {
        this.fixedwidth = fixedwidth;
    }

    public ArrayList<Integer> getFixedheight() {
        return fixedheight;
    }

    public void setFixedheight(ArrayList<Integer> fixedheight) {
        this.fixedheight = fixedheight;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }
}
