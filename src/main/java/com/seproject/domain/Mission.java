package com.seproject.domain;

import com.seproject.service.Key;
import com.seproject.service.ValueType;

public class Mission {
    @Key
    String missionID;

    String requestorID;
    double reward;
    String ddl;
    String startTime;
    String missionName;
    int max_finisher;


}
