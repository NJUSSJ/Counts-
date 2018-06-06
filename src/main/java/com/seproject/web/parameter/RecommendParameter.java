package com.seproject.web.parameter;

/**
 * 四维任务推荐用参数
 */
public class RecommendParameter {
    private String uid;
    private int[] difficulty;//难度数组
    private double wantedCredit;//期望的积分
    private boolean useTag;//是否使用标签

    public String getUid() {return uid; }

    public void setUid(String uid) { this.uid = uid; }

    public int[] getDifficulty() { return difficulty; }

    public void setDifficulty(int[] difficulty) { this.difficulty = difficulty; }

    public double getWantedCredit() { return wantedCredit; }

    public void setWantedCredit(double wantedCredit) { this.wantedCredit = wantedCredit; }

    public boolean getUseTag() {
        return useTag;
    }

    public void setUseTag(boolean useTag) {
        this.useTag = useTag;
    }
}
