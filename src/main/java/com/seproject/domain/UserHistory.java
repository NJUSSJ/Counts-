package com.seproject.domain;

import com.seproject.common.Key;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="userhistory")
public class UserHistory {
    @Key
    @Id
    @Column(name="uid")
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
