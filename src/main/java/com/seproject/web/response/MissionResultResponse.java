package com.seproject.web.response;

public class MissionResultResponse {
    int quality;
    int rank;
    double credit;
    int total;

    public void setQuality(int quality){
        this.quality = quality;
    }
    public int getQuality(){
        return this.quality;
    }
    public void setRank(int rank){
        this.rank = rank;
    }
    public int getRank(){
        return this.rank;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

