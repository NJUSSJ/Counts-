package com.seproject.service;

import com.seproject.domain.Mission;
import com.seproject.domain.Sample;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 评审的业务逻辑
 */
@Service
public class ReviewService {
    /**
     * 抽样方法
     */
    private double completeAward=0.2;

    public Sample getSample(Mission m){
        /**
         * 若 level <= AverageLevel
         *        抽两张， 取最差的一张作为q ;
         * 若 level > AverageLevel
         *        抽一张， 直接作为q
         */
        return new Sample();
    }


    /**
     * 筛选方法
     */

    /**
     * 奖励分配算法
     */
    public void award(String missionID, ArrayList<Double> quality){
        /**
         * award[i] = 0.2 *m/n+ 0.8*(m/n)*q[i]/Average(q)
         *          = (m/n)* (t+(1-t)*q[i]/Average(q))
         *          其中 t=completeAward=0.2 , m 为任务总奖励
         *
         * 调用这个方法后，涉及到的每个用户的积分将被重新计算
         */
    }

    /**
     *  private ArrayList<Double> calculateQ (String sampleID, ArrayList<Double> quality)
     */
}
