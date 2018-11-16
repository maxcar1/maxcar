package com.maxcar.barrier.dao;

import com.maxcar.barrier.entity.BarrierCarRespose;
import com.maxcar.barrier.pojo.BarrierControlCar;
import com.maxcar.barrier.pojo.BarrierControlCarExample;
import java.util.List;

import com.maxcar.base.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

public interface BarrierControlCarMapper extends BaseDao<BarrierControlCar,String> {
    int countByExample(BarrierControlCarExample example);

    int deleteByExample(BarrierControlCarExample example);

    int deleteByPrimaryKey(String id);

    int insert(BarrierControlCar record);

    int insertSelective(BarrierControlCar record);

    List<BarrierControlCar> selectByExample(BarrierControlCarExample example);

    BarrierControlCar selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BarrierControlCar record, @Param("example") BarrierControlCarExample example);

    int updateByExample(@Param("record") BarrierControlCar record, @Param("example") BarrierControlCarExample example);

    int updateByPrimaryKeySelective(BarrierControlCar record);

    int updateByPrimaryKey(BarrierControlCar record);

    List<BarrierCarRespose> selectAllByListType(BarrierControlCar controlCar);
}