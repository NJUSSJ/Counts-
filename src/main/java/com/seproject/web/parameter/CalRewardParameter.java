package com.seproject.web.parameter;

/**
 * 计算最高奖励值用参数
 */
public class CalRewardParameter {
    private int difficulty;
    private int maxWorker;
    private String uid;
    private int bonusStrategy;
    private int evaluateStrategy;
    private int picNum;

    public int getPicNum() {
        return picNum;
    }

    public void setPicNum(int picNum) {
        this.picNum = picNum;
    }

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

    public int getBonusStrategy() {
        return bonusStrategy;
    }

    public void setBonusStrategy(int bonusStrategy) {
        this.bonusStrategy = bonusStrategy;
    }

    public int getEvaluateStrategy() {
        return evaluateStrategy;
    }

    public void setEvaluateStrategy(int evaluateStrategy) {
        this.evaluateStrategy = evaluateStrategy;
    }
}
