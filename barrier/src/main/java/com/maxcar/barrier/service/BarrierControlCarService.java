package com.maxcar.barrier.service;

import com.maxcar.barrier.pojo.BarrierControlCar;
import com.maxcar.barrier.pojo.BarrierControlCarExample;
import com.maxcar.base.service.BaseService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author sunyazhou
 * @Date 2018/10/24 13:54
 * @desc
 */
public interface BarrierControlCarService extends BaseService<BarrierControlCar,String> {

    int countByExample(BarrierControlCarExample example);

    int deleteByExample(BarrierControlCarExample example);

    int updateByExampleSelective(@Param("record") BarrierControlCar record, @Param("example") BarrierControlCarExample example);

    int updateByExample(@Param("record") BarrierControlCar record, @Param("example") BarrierControlCarExample example);
    List<BarrierControlCar> selectByExample(BarrierControlCarExample example);
}
