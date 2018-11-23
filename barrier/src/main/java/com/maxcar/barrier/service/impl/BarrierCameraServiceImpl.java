package com.maxcar.barrier.service.impl;

import com.maxcar.barrier.dao.BarrierCameraMapper;
import com.maxcar.barrier.pojo.BarrierCamera;
import com.maxcar.barrier.pojo.BarrierCameraExample;
import com.maxcar.barrier.service.BarrierCameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author songxuefeng
 * @create 2018-11-21 14:50
 * @description: ${description}
 **/
@Service("barrierCameraService")
public class BarrierCameraServiceImpl implements BarrierCameraService {
    @Autowired
    private BarrierCameraMapper barrierCameraMapper;
    @Override
    public BarrierCamera getCameraInfoByIp(String ip) {
        BarrierCameraExample example=new BarrierCameraExample();
        example.createCriteria().andDeviceIpEqualTo(ip);
        return barrierCameraMapper.selectByExample(example).get(0);
    }
}
