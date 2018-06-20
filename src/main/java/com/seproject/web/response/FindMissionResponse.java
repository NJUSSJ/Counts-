package com.seproject.web.response;

public class FindMissionResponse {
    String mid;//任务名称
    int tagType=0;//1:标签式 2:非标签式
    int memberNum=0;//参与者人数
    String starterName="";//发起者姓名
    int state=0;//0:未结束 1：结束但未评估 2:已评估 3：已被发起者删除

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public int getTagType() {
        return tagType;
    }

    public void setTagType(int tagType) {
        this.tagType = tagType;
    }

    public int getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }

    public String getStarterName() {
        return starterName;
    }

    public void setStarterName(String starterName) {
        this.starterName = starterName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
