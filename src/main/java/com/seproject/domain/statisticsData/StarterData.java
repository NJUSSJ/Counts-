package com.seproject.domain.statisticsData;

import java.util.ArrayList;

public class StarterData {
    public int starterMissionSum;
    public int starterFinishedMissionNum;
    public int starterOngoingMissionNum;
    public ArrayList<Integer> starterParticipantSum;
    public ArrayList<Double> starterCreditSum;
    public ArrayList<Double> starterCreditAvg;
    public ArrayList<String> starterMissionName;

    public int getStarterMissionSum() {
        return starterMissionSum;
    }

    public void setStarterMissionSum(int starterMissionSum) {
        this.starterMissionSum = starterMissionSum;
    }

    public int getStarterFinishedMissionNum() {
        return starterFinishedMissionNum;
    }

    public void setStarterFinishedMissionNum(int starterFinishedMissionNum) {
        this.starterFinishedMissionNum = starterFinishedMissionNum;
    }

    public int getStarterOngoingMissionNum() {
        return starterOngoingMissionNum;
    }

    public void setStarterOngoingMissionNum(int starterOngoingMissionNum) {
        this.starterOngoingMissionNum = starterOngoingMissionNum;
    }

    public ArrayList<Integer> getStarterParticipantSum() {
        return starterParticipantSum;
    }

    public void setStarterParticipantSum(ArrayList<Integer> starterParticipantSum) {
        this.starterParticipantSum = starterParticipantSum;
    }

    public ArrayList<Double> getStarterCreditSum() {
        return starterCreditSum;
    }

    public void setStarterCreditSum(ArrayList<Double> starterCreditSum) {
        this.starterCreditSum = starterCreditSum;
    }

    public ArrayList<Double> getStarterCreditAvg() {
        return starterCreditAvg;
    }

    public void setStarterCreditAvg(ArrayList<Double> starterCreditAvg) {
        this.starterCreditAvg = starterCreditAvg;
    }

    public ArrayList<String> getStarterMissionName() {
        return starterMissionName;
    }

    public void setStarterMissionName(ArrayList<String> starterMissionName) {
        this.starterMissionName = starterMissionName;
    }
}
