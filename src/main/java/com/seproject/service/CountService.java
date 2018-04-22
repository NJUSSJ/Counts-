package com.seproject.service;

import com.seproject.domain.Mission;
import com.seproject.domain.Sample;
import org.springframework.stereotype.Service;

/**
 * 统计的业务逻辑
 */
@Service
public class CountService {
    /**
     * 抽样方法
     */
    public Sample getSample(Mission m){
        return new Sample();
    }


    /**
     * 筛选方法
     */
}
