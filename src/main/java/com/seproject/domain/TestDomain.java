package com.seproject.domain;

import com.seproject.service.Key;
import com.seproject.service.Searchable;

public class TestDomain {
    @Key
    private int id;
    @Searchable(varName = "name")
    private String name;
    @Searchable(varName = "num")
    private int num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
