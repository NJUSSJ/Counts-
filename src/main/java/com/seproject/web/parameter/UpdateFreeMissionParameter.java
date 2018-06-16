package com.seproject.web.parameter;

import java.util.ArrayList;

public class UpdateFreeMissionParameter {
    private String uid;
    private String mid;
    private ArrayList<String> info;//对自由式的标注信息，用来替换对应的collection里的信息

    public String getUid() { return uid; }

    public String getMid() { return mid; }

    public ArrayList<String> getInfo() { return info; }
}
