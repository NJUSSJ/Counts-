package com.seproject.service.blService;

import com.seproject.common.RM;
import com.seproject.common.SearchCategory;

import java.util.ArrayList;

public interface BasicBLService<T> {
    RM add(T t0);
    RM delete(String keyValue);
    RM update(T t0);
    T findByKey(String keyValue);
    ArrayList<T> search(String keyName, SearchCategory category, String keyValue);
    boolean checkKeyExists(String keyValue);
    ArrayList<T> getAllObjects();
}
