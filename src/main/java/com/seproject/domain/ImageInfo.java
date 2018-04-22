package com.seproject.domain;

import java.util.ArrayList;

public class ImageInfo {
    private String imgid;
    private int sentids;
    //private ArrayList<EachSentence> sentences;
    private String filename;
    private int fixedx;
    private int fixedy;
    private int fixedwidth;
    private int fixedheight;
    private ArrayList<ArrayList<Integer>> list;

    public int getSentids() {
        return sentids;
    }

    public void setSentids(int sentids) {
        this.sentids = sentids;
    }

  /*  public ArrayList<EachSentence> getSentences() {
        return sentences;
    }

    public void setSentences(ArrayList<EachSentence> sentences) {
        this.sentences = sentences;
    }
*/
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getFixedheight() {
        return fixedheight;
    }

    public void setFixedheight(int fixedheight) {
        this.fixedheight = fixedheight;
    }

    public int getFixedwidth() {
        return fixedwidth;
    }

    public void setFixedwidth(int fixedwidth) {
        this.fixedwidth = fixedwidth;
    }

    public int getFixedx() {
        return fixedx;
    }

    public void setFixedx(int fixedx) {
        this.fixedx = fixedx;
    }

    public int getFixedy() {
        return fixedy;
    }

    public void setFixedy(int fixedy) {
        this.fixedy = fixedy;
    }

    public ArrayList<ArrayList<Integer>> getList() {
        return list;
    }

    public void setList(ArrayList<ArrayList<Integer>> list) {
        this.list = list;
    }

    public String getImgid() { return imgid; }

    public void setImgid(String imgid) { this.imgid = imgid; }
}
