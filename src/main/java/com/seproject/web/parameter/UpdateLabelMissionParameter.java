package com.seproject.web.parameter;

import java.util.ArrayList;

public class UpdateLabelMissionParameter {
    private String mid;
    private String uid;
    private ArrayList<Integer> nums;

    public String getMid() { return mid; }

    public void setMid(String mid) { this.mid = mid; }

    public String getUid() { return uid; }

    public void setUid(String uid) { this.uid = uid; }

    public ArrayList<Integer> getNums() { return nums; }

    public void setNums(ArrayList<Integer> nums) { this.nums = nums; }
}
