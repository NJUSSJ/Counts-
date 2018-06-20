package com.seproject.web.parameter;

public class ChangeCreditParameter {
    String uid;
    double delta;//可正可负，表示要加上或者要减掉的积分

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }
}
