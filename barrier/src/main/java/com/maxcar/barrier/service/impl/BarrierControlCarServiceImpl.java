package com.maxcar.barrier.service.impl;

import com.maxcar.barrier.dao.BarrierControlCarMapper;
import com.maxcar.barrier.pojo.BarrierControlCar;
import com.maxcar.barrier.pojo.BarrierControlCarExample;
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
    public int countByExample(BarrierControlCarExample example) {
        return barrierControlCarMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(BarrierControlCarExample example) {
        return barrierControlCarMapper.deleteByExample(example);
    }

    @Override
    public int updateByExampleSelective(BarrierControlCar record, BarrierControlCarExample example) {
        return barrierControlCarMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(BarrierControlCar record, BarrierControlCarExample example) {
        return barrierControlCarMapper.updateByExample(record,example);
    }
    @Override
    public List<BarrierControlCar> selectByExample(BarrierControlCarExample example){
        return  barrierControlCarMapper.selectByExample(example);
    }
}
