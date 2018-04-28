package com.seproject.domain.StatisticsData;

import java.util.ArrayList;

public class SingleMissionData {
    public int number0;//差
    public int number1;
    public int number2;
    public int number3;//优

    public int numberSum;// 完成总人数。

    public ArrayList<Double> credit;
    public ArrayList<Integer> level;

    public int getNumber0() {
        return number0;
    }

    public void setNumber0(int number0) {
        this.number0 = number0;
    }

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public int getNumber3() {
        return number3;
    }

    public void setNumber3(int number3) {
        this.number3 = number3;
    }

    public int getNumberSum() {
        return numberSum;
    }

    public void setNumberSum(int numberSum) {
        this.numberSum = numberSum;
    }

    public ArrayList<Double> getCredit() {
        return credit;
    }

    public void setCredit(ArrayList<Double> credit) {
        this.credit = credit;
    }

    public ArrayList<Integer> getLevel() {
        return level;
    }

    public void setLevel(ArrayList<Integer> level) {
        this.level = level;
    }
}
