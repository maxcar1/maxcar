package com.maxcar.statistics.dao;

import com.maxcar.statistics.model.entity.CarStockDay;

public interface CarStockDayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarStockDay record);

    int insertSelective(CarStockDay record);

    CarStockDay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarStockDay record);

    int updateByPrimaryKey(CarStockDay record);
}