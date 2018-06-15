package com.seproject.web.parameter;

/**
 * 计算最高奖励值用参数
 */
public class CalRewardParameter {
    private int difficulty;
    private int maxWorker;
    private String uid;

    public String getUid() {
        return uid;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getMaxWorker() {
        return maxWorker;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setMaxWorker(int maxWorker) {
        this.maxWorker = maxWorker;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
