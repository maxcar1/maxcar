package com.maxcar.statistics.dao;

import com.maxcar.statistics.model.entity.CarStockMonth;
import com.maxcar.statistics.model.request.StockRequest;

import java.util.List;
import java.util.Map;

public interface CarStockMonthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CarStockMonth record);

    int insertSelective(CarStockMonth record);

    CarStockMonth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CarStockMonth record);

    int updateByPrimaryKey(CarStockMonth record);

   Integer getStockPrice(StockRequest stockRequest);
}