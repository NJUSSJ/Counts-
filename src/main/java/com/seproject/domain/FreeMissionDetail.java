package com.seproject.domain;

import com.seproject.common.Key;
import com.seproject.web.parameter.FreeMissionParameter;
import net.sf.json.JSONObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

/**
 * 记录自由式任务中，每个用户每张图的标注信息
 */
public class FreeMissionDetail {
    private int picIndex;//图片索引
    private ArrayList<Integer> x;
    private ArrayList<Integer> y;
    private ArrayList<Integer> height;
    private ArrayList<Integer> weight;
    private String summary;//整体标

    public FreeMissionDetail(String jsonString,int picIndex){
        this.picIndex=picIndex;
        this.summary="";
        if(jsonString==null||jsonString.length()<=0){
            x=new ArrayList<Integer>();
            y=new ArrayList<Integer>();
            height=new ArrayList<Integer>();
            weight=new ArrayList<Integer>();
        }else {
            JSONObject jsonObject = JSONObject.fromObject(jsonString);
            FreeMissionParameter parameter = (FreeMissionParameter) JSONObject.toBean(jsonObject, FreeMissionParameter.class);
            ArrayList<Integer> tempX = parameter.getFixedx();
            ArrayList<Integer> tempY = parameter.getFixedy();
            ArrayList<Integer> tempHeight = parameter.getFixedheight();
            ArrayList<Integer> tempWeight = parameter.getFixedwidth();
            ArrayList<Integer> x = new ArrayList<Integer>();
            ArrayList<Integer> y = new ArrayList<Integer>();
            ArrayList<Integer> height = new ArrayList<Integer>();
            ArrayList<Integer> width = new ArrayList<Integer>();
            for (int i = 0; i < tempHeight.size(); i++) {
                if (tempHeight.get(i) != 0) {
                    x.add(tempX.get(i));
                    y.add(tempY.get(i));
                    height.add(tempHeight.get(i));
                    width.add(tempWeight.get(i));
                }
            }
            this.x = x;
            this.y = y;
            this.height = height;
            this.weight = width;
            String info = parameter.getSentences().toString();
            if (info.contains("status=2")) {
                int index = info.indexOf("status=2");
                info = info.substring(0, index);
                index = info.lastIndexOf("[");
                int endIndex = info.lastIndexOf("]");
                this.summary = info.substring(index + 1, endIndex);
            }
        }
    }

    public int getPicIndex() {
        return picIndex;
    }

    public void setPicIndex(int picIndex) {
        this.picIndex = picIndex;
    }

    public ArrayList<Integer> getX() {
        return x;
    }

    public void setX(ArrayList<Integer> x) {
        this.x = x;
    }

    public ArrayList<Integer> getY() {
        return y;
    }

    public void setY(ArrayList<Integer> y) {
        this.y = y;
    }

    public ArrayList<Integer> getHeight() {
        return height;
    }

    public void setHeight(ArrayList<Integer> height) {
        this.height = height;
    }

    public ArrayList<Integer> getWeight() {
        return weight;
    }

    public void setWeight(ArrayList<Integer> weight) {
        this.weight = weight;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
