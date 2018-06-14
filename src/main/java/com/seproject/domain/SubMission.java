package com.seproject.domain;

import java.util.ArrayList;

public class SubMission {
    //子任务
      String keyID="";//mid+seed
      String mid=""; //任务ID
      ArrayList<String> uid=new ArrayList<String>();
      int seed=0;//种子
      ArrayList<ArrayList<Integer>> answers=new ArrayList<ArrayList<Integer>>();
      int id1;//第一个金标的索引
      int id2;

      public SubMission() {
      }


      public String getKeyID() {
        return keyID;
    }
      public void setKeyID(String keyID) {
        this.keyID = keyID;
    }

      public String getMid() {
        return mid;
    }

      public void setMid(String mid) {
        this.mid = mid;
    }

      public ArrayList<String> getUid() {
        return uid;
    }

      public void setUid(ArrayList<String> uid) {
        this.uid = uid;
    }

      public int getSeed() {
        return seed;
    }

      public void setSeed(int seed) {
        this.seed = seed;
    }

      public ArrayList<ArrayList<Integer>> getAnswers() {
        return answers;
    }

      public void setAnswers(ArrayList<ArrayList<Integer>> answers) {
        this.answers = answers;
    }

      public int getId1() {
          return id1;
      }

      public void setId1(int id1) {
          this.id1 = id1;
      }
      public int getId2() {
          return id2;
      }

      public void setId2(int id2) {
          this.id2 = id2;
      }
}
