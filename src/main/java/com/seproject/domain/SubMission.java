package com.seproject.domain;

import com.seproject.common.Key;
import com.seproject.common.Searchable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
@Entity
@Table(name="SubMission")
public class SubMission {
    //子任务
    @Key
    @Id
    @Searchable(varName = "keyID")
    @Column(name="keyid")
      String keyID="";//mid+seed
    @Searchable(varName = "mid")
    @Column(name="mid")
      String mid=""; //任务ID
    @Column(name="uid")
      ArrayList<String> uid=new ArrayList<String>();
    @Searchable(varName = "seed")
    @Column(name="seed")
      int seed=0;//种子
    @Column(name="answers")
      ArrayList<ArrayList<Integer>> answers=new ArrayList<ArrayList<Integer>>();
    @Column(name="id1")
      int id1;//第一个金标的索引
    @Column(name="id2")
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
