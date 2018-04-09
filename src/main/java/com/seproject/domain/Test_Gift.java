package com.seproject.domain;

import com.seproject.service.Key;
import com.seproject.service.ValueType;

public class Test_Gift {
    //用于测试。
    @Key(type = ValueType.STRING)
    public String giftID;

    public int currentNum;

}
