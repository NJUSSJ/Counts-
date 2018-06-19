package com.seproject.service;

import com.seproject.service.blService.BasicBLService;
import com.seproject.service.blServiceImpl.BasicBLImpl_File;
import com.seproject.service.blServiceImpl.BasicBLImpl_Hibernate;

public class Factory {
    public static <T> BasicBLService<T> getBasicBLService(T t){
        return new BasicBLImpl_Hibernate<T>(t);
    }

}
