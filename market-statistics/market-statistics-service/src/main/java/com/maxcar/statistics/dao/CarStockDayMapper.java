package com.maxcar.statistics.dao;

import com.maxcar.statistics.model.entity.CarStockDayEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarStockDayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarStockDayEntity record);

    int insertSelective(CarStockDayEntity record);

    CarStockDayEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarStockDayEntity record);

    int updateByPrimaryKey(CarStockDayEntity record);

    List<CarStockDayEntity> selectCarpriceDay(@Param("priceStart") int priceStart , @Param("priceEnd") int priceEnd);
}