package com.seproject.web.parameter;

/**
 * 计算最高奖励值用参数
 */
public class CallRewardParameter {
    private int difficulty;
    private int maxNum;
    private String uid;

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
