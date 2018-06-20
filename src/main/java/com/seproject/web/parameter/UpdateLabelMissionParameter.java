package com.seproject.web.parameter;

import java.util.ArrayList;

public class UpdateLabelMissionParameter {
    private String mid;
    private String uid;
    private int num;//图片是第几张
    private String answer="";//图片的答案是第几个选项,若没有选就点了next ,设置成-1

    public String getMid() { return mid; }

    public void setMid(String mid) { this.mid = mid; }

    public String getUid() { return uid; }

    public void setUid(String uid) { this.uid = uid; }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getNum() { return num; }

    public void setNum(int num) { this.num = num; }

}
