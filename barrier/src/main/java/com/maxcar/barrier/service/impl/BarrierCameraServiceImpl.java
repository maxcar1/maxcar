package com.maxcar.barrier.service.impl;

import com.maxcar.barrier.dao.BarrierCameraMapper;
import com.maxcar.barrier.pojo.BarrierCamera;
import com.maxcar.barrier.pojo.BarrierCameraExample;
import com.maxcar.barrier.service.BarrierCameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
/**
 * @author songxuefeng
 * @create 2018-11-21 14:50
 * @description: ${description}
 **/
@Service("barrierCameraService")
public class BarrierCameraServiceImpl implements BarrierCameraService {

    @Autowired
    BarrierCameraMapper barrierCameraMapper;

    @Override
    public List<BarrierCamera> selectCameraByBarrierId(BarrierCamera barrierCamera) {
        BarrierCameraExample barrierCameraExample = new BarrierCameraExample();
        barrierCameraExample.createCriteria().andBarrierIdEqualTo(barrierCamera.getBarrierId())
                .andMarketIdEqualTo(barrierCamera.getMarketId()).andIsValidEqualTo(1);
        return barrierCameraMapper.selectByExample(barrierCameraExample);
    }

    @Override
    public BarrierCamera getCameraInfoByIp(String ip) {
        BarrierCameraExample example = new BarrierCameraExample();
        example.createCriteria().andDeviceIpEqualTo(ip);
        return barrierCameraMapper.selectByExample(example).get(0);
    }
}
