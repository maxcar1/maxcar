package com.maxcar.barrier.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxcar.barrier.dao.BarrierControlCarMapper;
import com.maxcar.barrier.entity.BarrierCarRespose;
import com.maxcar.barrier.pojo.BarrierControlCar;
import com.maxcar.barrier.service.BarrierControlCarService;
import com.maxcar.base.dao.BaseDao;
import com.maxcar.base.service.impl.BaseServiceImpl;
import com.maxcar.base.util.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author sunyazhou
 * @Date 2018/10/24 13:54
 * @desc
 */
@Service("barrierControlCarService")
public class BarrierControlCarServiceImpl extends BaseServiceImpl<BarrierControlCar,String> implements BarrierControlCarService {

    @Autowired
    private BarrierControlCarMapper barrierControlCarMapper;

    @Override
    public BaseDao<BarrierControlCar, String> getBaseMapper() {
        return barrierControlCarMapper;
    }

    @Override
    public int insertSelective(BarrierControlCar record) {
        record.setUpdateTime(new Date());
        return barrierControlCarMapper.insertSelective(record);
    }

    @Override
    public PageInfo selectAllByListType(BarrierControlCar controlCar) {
        PageHelper.startPage(controlCar.getCurrentPage(),controlCar.getPageSize());
        List<BarrierCarRespose> barrierCarResposes = barrierControlCarMapper.selectAllByListType(controlCar);
        PageInfo pageInfo = new PageInfo(barrierCarResposes);
        return pageInfo;
    }
}
