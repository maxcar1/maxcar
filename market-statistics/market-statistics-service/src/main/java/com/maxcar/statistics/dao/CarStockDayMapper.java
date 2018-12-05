package com.maxcar.statistics.dao;

import com.maxcar.statistics.model.entity.CarStockDayEntity;

public interface CarStockDayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarStockDayEntity record);

    int insertSelective(CarStockDayEntity record);

    CarStockDayEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarStockDayEntity record);

    int updateByPrimaryKey(CarStockDayEntity record);
}