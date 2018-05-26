package com.seproject.service;

import com.seproject.service.blService.BasicBLService;
import com.seproject.service.blServiceImpl.BasicBLImpl_File;

public class Factory {
    public static <T> BasicBLService<T> getBasicBLService(T t){
        return new BasicBLImpl_File<T>(t);
    }

}
