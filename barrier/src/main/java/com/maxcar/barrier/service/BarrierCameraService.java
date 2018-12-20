package com.maxcar.barrier.service;


import com.maxcar.barrier.pojo.BarrierCamera;

import java.util.List;
import com.maxcar.barrier.pojo.BarrierCamera;
/**
 * @author songxuefeng
 * @create 2018-11-21 14:50
 * @description: ${description}
 **/
public interface BarrierCameraService {
    List<BarrierCamera> selectCameraByBarrierId(BarrierCamera barrierCamera);
    BarrierCamera getCameraInfoByIp(String ip);
}
